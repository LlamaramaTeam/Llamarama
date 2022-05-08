package io.github.llamarama.team.common.entity.mossyllama;

import io.github.llamarama.team.common.entity.ModEntityTypes;
import io.github.llamarama.team.common.entity.woolyllama.WoollyLlamaEntity;
import io.github.llamarama.team.common.tag.ModBlockTags;
import io.github.llamarama.team.common.util.Constants;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Fertilizable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.*;
import net.minecraft.world.gen.feature.UndergroundConfiguredFeatures;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class MossyLlamaEntity extends WoollyLlamaEntity {

    @NotNull
    private static final TrackedData<Integer> MOSSY_LLAMA_TIMER =
        DataTracker.registerData(MossyLlamaEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private static final String DEADLY_KEY = "Deadly";
    private final AtomicBoolean deadly = new AtomicBoolean(true);

    public MossyLlamaEntity(EntityType<? extends WoollyLlamaEntity> entityType, World world) {
        super(entityType, world);
        this.setWoolTimer(this.getRandom().nextInt(20 * 60 * 10));
    }

    public static boolean canSpawn(@NotNull EntityType<MossyLlamaEntity> type, @NotNull ServerWorldAccess worldAccess, SpawnReason reason, @NotNull BlockPos pos, Random ignoredRandom) {
        if (reason == SpawnReason.CHUNK_GENERATION) {
            return false;
        } else if (reason == SpawnReason.EVENT) {
            return true;
        } else {
            return worldAccess.getNonSpectatingEntities(
                MossyLlamaEntity.class,
                type.createSimpleBoundingBox(pos.getX(), pos.getY(), pos.getZ()).expand(128)
            ).isEmpty();
        }
    }

    @Override
    public boolean canSpawn(WorldAccess world, SpawnReason spawnReason) {
        return !world.isClient() && canSpawn(
            ModEntityTypes.MOSSY_LLAMA,
            ((ServerWorldAccess) world),
            spawnReason,
            this.getBlockPos(),
            this.getRandom()
        );
    }

    @Nullable
    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData, @Nullable NbtCompound entityNbt) {
        if (spawnReason == SpawnReason.EVENT) {
            this.deadly.set(false);
        }

        return super.initialize(world, difficulty, spawnReason, entityData, entityNbt);
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound tag) {
        super.writeCustomDataToNbt(tag);
        tag.putBoolean(DEADLY_KEY, this.deadly.get());
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound tag) {
        super.readCustomDataFromNbt(tag);
        this.deadly.set(tag.getBoolean(DEADLY_KEY));
    }

    @Override
    public int getWoolTimer() {
        return this.dataTracker.get(MOSSY_LLAMA_TIMER);
    }

    @Override
    protected void setWoolTimer(int woolTimer) {
        this.dataTracker.set(MOSSY_LLAMA_TIMER, woolTimer);
    }

    @Override
    public void tick() {
        super.tick();

        if (!world.isClient) {
            Random random = this.getRandom();
            this.trySpawnParticles(random);

            if (this.world.getGameRules().getBoolean(GameRules.DO_MOB_GRIEFING)) {
                this.trySpawnMoss(random);
                this.tryGrowAzalea(random);
            }
        }
    }

    @Override
    public boolean isTrader() {
        return false;
    }

    @Override
    protected void shearedTick() {
        if (this.getWoolTimer() != 0) {
            this.setWoolTimer(this.getWoolTimer() - 1);
        } else if (this.getSheared()) {
            this.setSheared(false);
            world.playSoundFromEntity(null, this,
                SoundEvents.BLOCK_GRASS_PLACE,
                SoundCategory.AMBIENT, 1.0f, 1.0f);
        }
    }

    @Override
    protected WoollyLlamaEntity getChild() {
        return ModEntityTypes.MOSSY_LLAMA.create(this.world);
    }

    @Override
    public boolean isBaby() {
        return false;
    }

    @Override
    protected @NotNull ItemStack getShearedItem() {
        return Registry.BLOCK.getOrCreateEntryList(ModBlockTags.LUSH_GROWTH)
            .getRandom(this.random)
            .map(RegistryEntry::value)
            .orElse(Blocks.FLOWERING_AZALEA)
            .asItem()
            .getDefaultStack();
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(MOSSY_LLAMA_TIMER, 20 * 60 * 10);
    }

    private void tryGrowAzalea(@NotNull Random random) {
        if (!this.getSheared() && random.nextInt(1000) == 0) {
            BlockPos.streamOutwards(this.getBlockPos(), 1, 1, 1)
                .filter(pos -> this.world.getBlockState(pos).isIn(ModBlockTags.AZALEA_BLOCKS))
                .findFirst()
                .ifPresent(pos -> {
                    BlockState state = world.getBlockState(pos);

                    if (state.getBlock() instanceof Fertilizable fertilizable) {
                        fertilizable.grow((ServerWorld) this.world, random, pos, state);
                    }
                });
        }
    }

    private void trySpawnMoss(@NotNull Random random) {
        if (!this.deadly.get()) {
            return;
        }

        boolean isInPushGrowth =
            this.world.getBlockState(this.getBlockPos().down()).isIn(ModBlockTags.LUSH_GROWTH);
        // System.out.println(this.getMovementSpeed());
        if (this.getMovementSpeed() > 0 && !isInPushGrowth && random.nextInt(100) == 0) {
            ServerWorld sw = (ServerWorld) this.world;
            // Use the vanilla method for better mod integration
            UndergroundConfiguredFeatures.MOSS_PATCH_BONEMEAL.value().generate(sw,
                sw.getChunkManager().getChunkGenerator(), random, this.getBlockPos().down());
        }
    }

    private void trySpawnParticles(@NotNull Random random) {
        int chanceOfParticles = random.nextInt(20);

        if (chanceOfParticles == 0) {
            BlockPos llamaPos = this.getBlockPos();
            BlockPos.Mutable mutable = new BlockPos.Mutable();
            for (int i = random.nextInt(20); i > 0; i--) {
                mutable.set(llamaPos.getX() + random.nextInt(10),
                    llamaPos.getY() + random.nextInt(10),
                    llamaPos.getZ() + random.nextInt(10));
                ((ServerWorld) this.world).spawnParticles(ParticleTypes.SPORE_BLOSSOM_AIR,
                    llamaPos.getX() + random.nextGaussian(),
                    llamaPos.getY() + random.nextGaussian() + 1,
                    llamaPos.getZ() + random.nextGaussian(),
                    1, 0, 0, 0, 0.3
                );
            }
        }
    }

    @Override
    protected boolean canStartRiding(Entity entity) {
        return false;
    }

    @Override
    protected void putPlayerOnBack(PlayerEntity player) {
        player.sendMessage(Constants.CANNOT_RIDE_TEXT, true);
    }

}
