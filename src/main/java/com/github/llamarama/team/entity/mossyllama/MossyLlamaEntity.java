package com.github.llamarama.team.entity.mossyllama;

import com.github.llamarama.team.block.ModBlockTags;
import com.github.llamarama.team.entity.ModEntityTypes;
import com.github.llamarama.team.entity.woolyllama.WoollyLlamaEntity;
import com.github.llamarama.team.util.Constants;
import com.github.llamarama.team.util.PosUtilities;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Fertilizable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class MossyLlamaEntity extends WoollyLlamaEntity {

    @NotNull
    private static final TrackedData<Integer> MOSSY_LLAMA_TIMER =
            DataTracker.registerData(MossyLlamaEntity.class, TrackedDataHandlerRegistry.INTEGER);

    public MossyLlamaEntity(EntityType<? extends WoollyLlamaEntity> entityType, World world) {
        super(entityType, world);
        this.setWoolTimer(this.getRandom().nextInt(20 * 60 * 10));
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
            this.trySpawnMoss(random);
            this.tryGrowAzalea(random);
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
        return ModBlockTags.LUSH_GROWTH.getRandom(this.random).asItem().getDefaultStack();
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(MOSSY_LLAMA_TIMER, 20 * 60 * 10);
    }

    private void tryGrowAzalea(@NotNull Random random) {
        if (this.getSheared() && random.nextInt(1000) == 0) {
            BlockPos.streamOutwards(this.getBlockPos(), 1, 1, 1)
                    .filter(pos -> ModBlockTags.AZALEA_BLOCKS.contains(this.world.getBlockState(pos).getBlock()))
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
        boolean isOnMoss = this.world.getBlockState(this.getBlockPos().down()).isOf(Blocks.MOSS_BLOCK);
        if (random.nextInt(10) == 0 && !PosUtilities.checkForNoVelocity(this.getVelocity()) && !isOnMoss) {
            BlockPos.streamOutwards(this.getBlockPos(), 2, 1, 2).forEach(pos -> {
                boolean canMossReplace = this.world.getBlockState(pos).isIn(BlockTags.MOSS_REPLACEABLE);

                if (canMossReplace) {
                    boolean isWithinDistance = PosUtilities.getDistanceFrom(pos, this.getBlockPos()) <= 2;

                    if (isWithinDistance || random.nextInt(3) == 0) {
                        world.setBlockState(pos, Blocks.MOSS_BLOCK.getDefaultState());

                        int azaleaChance = random.nextInt(4);

                        if (PosUtilities.arePositionsEqual(pos.up(), this.getBlockPos())) {
                            if (azaleaChance == 0) {
                                boolean flowering = random.nextInt(2) == 0;
                                BlockState stateToPlace = flowering ?
                                        Blocks.FLOWERING_AZALEA.getDefaultState() :
                                        Blocks.AZALEA.getDefaultState();

                                world.setBlockState(pos.up(), stateToPlace);
                            } else if (azaleaChance == 2) {
                                world.setBlockState(pos.up(), Blocks.MOSS_CARPET.getDefaultState());
                            }
                        }
                    }
                }
            });
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