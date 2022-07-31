package io.github.llamarama.team.common.entity.spawn;

import io.github.llamarama.team.common.entity.caravantrader.CaravanTraderEntity;
import io.github.llamarama.team.common.register.ModEntityTypes;
import io.github.llamarama.team.common.tag.ModEntityTags;
import io.github.llamarama.team.common.util.PosUtilities;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.passive.LlamaEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.GameRules;
import net.minecraft.world.SpawnHelper;
import net.minecraft.world.spawner.Spawner;

import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class CaravanTraderSpawnFactory implements Spawner {

    private int spawnDelay;

    public CaravanTraderSpawnFactory() {
        this.spawnDelay = -1;
    }

    @Override
    public int spawn(ServerWorld world, boolean spawnMonsters, boolean spawnAnimals) {
        if (!world.isClient) {
            if (this.spawnDelay < 0) {
                this.spawnDelay = this.nextSpawnDelay(world.getRandom());
                return 0;
            }

            if (world.getGameRules().getBoolean(GameRules.DO_MOB_SPAWNING) && --this.spawnDelay <= 0) {
                Optional<BlockPos> validPos = this.getRandomValidPos(world);

                if (validPos.isEmpty()) {
                    this.spawnDelay = 20;
                    return 0;
                }

                CaravanTraderEntity spawnedEntity = ModEntityTypes.CARAVAN_TRADER.spawn(
                    world,
                    null,
                    null,
                    null,
                    validPos.get(),
                    SpawnReason.EVENT,
                    true,
                    false
                );

                if (spawnedEntity != null) {
                    this.spawnDelay = this.nextSpawnDelay(world.getRandom());
                    return 1 + this.spawnLlamas(world, spawnedEntity);
                }
            }
        }

        return 0;
    }

    protected Optional<BlockPos> getRandomValidPos(@NotNull ServerWorld world) {
        PlayerEntity player = world.getRandomAlivePlayer();

        if (player != null) {
            BlockPos alteredPos = PosUtilities.getRandomPosInArea(world, player.getBlockPos(), 128, true);
            return Optional.of(alteredPos)
                .filter(pos -> !world.isAir(pos.down()))
                .filter(pos -> SpawnHelper.isClearForSpawn(
                    world, pos,
                    world.getBlockState(pos), world.getFluidState(pos),
                    ModEntityTypes.CARAVAN_TRADER
                ));
        }

        return Optional.empty();
    }

    protected int spawnLlamas(ServerWorld world, @NotNull CaravanTraderEntity merchant) {
        BlockPos traderPos = merchant.getBlockPos();
        if (traderPos != null) {
            Random random = world.getRandom();
            int amountOfLlamas = random.nextInt(6) + 1;

            for (int i = 0; i < amountOfLlamas; i++) {
                if (!world.isClient) {
                    BlockPos randomLlamaPos = PosUtilities.getRandomPosInArea(world, traderPos, 3, false);

                    if (PosUtilities.getDistanceFrom(Vec3d.ofCenter(randomLlamaPos), merchant.getPos()) < 4) {
                        LlamaEntity llamaSpawn = (LlamaEntity) Registry.ENTITY_TYPE
                            .getOrCreateEntryList(ModEntityTags.LLAMAS)
                            .getRandom(random)
                            .orElseGet(() -> RegistryEntry.of(EntityType.LLAMA))
                            .value()
                            .create(
                                world,
                                null,
                                null,
                                null,
                                randomLlamaPos,
                                SpawnReason.EVENT,
                                false,
                                false
                            );

                        if (llamaSpawn != null) {
                            merchant.setCurrentLlama(llamaSpawn);
                            world.spawnEntityAndPassengers(llamaSpawn);
                        }
                    }
                }
            }
            return amountOfLlamas;
        }
        return 0;
    }

    private int nextSpawnDelay(@NotNull Random random) {
//        return MathHelper.nextInt(random, 40, 60); // USED FOR TESTING PURPOSES
        return MathHelper.nextInt(random, 40 * 60 * 20, 60 * 60 * 20);
    }

}
