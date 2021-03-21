package com.github.llamarama.team.entity.spawn;

import com.github.llamarama.team.entity.ModEntityTypes;
import com.github.llamarama.team.entity.caravantrader.CaravanTraderEntity;
import com.github.llamarama.team.util.PosUtilities;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.passive.LlamaEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.GameRules;
import net.minecraft.world.SpawnHelper;
import net.minecraft.world.gen.Spawner;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class CaravanTraderSpawnFactory implements Spawner {

    private int spawnDelay;

    public CaravanTraderSpawnFactory() {
        this.spawnDelay = MathHelper.nextInt(new Random(), 5 * 60 * 20, 40 * 60 * 20);
    }

    @Override
    public int spawn(ServerWorld world, boolean spawnMonsters, boolean spawnAnimals) {
        if (!world.isClient) {
            if (world.getGameRules().getBoolean(GameRules.DO_MOB_SPAWNING) && --this.spawnDelay <= 0) {
                BlockPos validPos = this.getRandomValidPos(world);

                if (validPos == null) {
                    this.spawnDelay = 20 * 40;
                    return 0;
                }

                CaravanTraderEntity spawnedEntity = ModEntityTypes.CARAVAN_TRADER.spawn(world, null, null, null, validPos, SpawnReason.EVENT, false, false);

                if (spawnedEntity != null) {
                    spawnedEntity.initialize(world, world.getLocalDifficulty(validPos), SpawnReason.EVENT, null, null);

                    spawnedEntity.setPos(validPos.getX(), validPos.getY(), validPos.getZ());
                    spawnedEntity.updateTrackedPosition(validPos.getX(), validPos.getY(), validPos.getZ());

                    this.spawnDelay = MathHelper.nextInt(world.random, 40 * 40 * 20, 40 * 60 * 20);

                    return this.spawnLlamas(world, spawnedEntity) + 1;
                }
            }
        }
        return 0;
    }

    @Nullable
    protected BlockPos getRandomValidPos(ServerWorld world) {
        PlayerEntity player = world.getRandomAlivePlayer();

        if (player != null) {
            BlockPos alteredPos = PosUtilities.getRandomPosInArea(world, player.getBlockPos(), 128, false);

            return SpawnHelper.canSpawn(SpawnRestriction.Location.ON_GROUND, world, alteredPos, ModEntityTypes.CARAVAN_TRADER) ? alteredPos.toImmutable() : null;
        } else {
            return null;
        }
    }

    protected int spawnLlamas(ServerWorld world, CaravanTraderEntity merchant) {
        BlockPos traderPos = merchant.getBlockPos();
        if (traderPos != null) {
            Random random = world.getRandom();

            EntityType<?>[] llamas = {EntityType.LLAMA, EntityType.TRADER_LLAMA, ModEntityTypes.WOOLLY_LLAMA, ModEntityTypes.BUMBLE_LLAMA};

            int amountOfLlamas = random.nextInt(6) + 1;

            for (int i = 0; i < amountOfLlamas; i++) {
                if (!world.isClient) {
                    BlockPos randomLlamaPos = PosUtilities.getRandomPosInArea(world, traderPos, 3, false);

                    if (PosUtilities.getDistanceFrom(Vec3d.ofCenter(randomLlamaPos), merchant.getPos()) < 4) {
                        LlamaEntity llamaSpawn = (LlamaEntity) llamas[random.nextInt(llamas.length)].create(world, null, null, null, randomLlamaPos, SpawnReason.EVENT, false, false);

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

}
