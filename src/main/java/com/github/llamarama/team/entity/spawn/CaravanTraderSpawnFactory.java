package com.github.llamarama.team.entity.spawn;

import com.github.llamarama.team.Llamarama;
import com.github.llamarama.team.entity.ModEntityTypes;
import com.github.llamarama.team.entity.caravantrader.CaravanTraderEntity;
import com.google.common.collect.Sets;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.passive.LlamaEntity;
import net.minecraft.entity.passive.MerchantEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.GameRules;
import net.minecraft.world.Heightmap;
import net.minecraft.world.LightType;
import net.minecraft.world.gen.Spawner;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Random;
import java.util.Set;

public class CaravanTraderSpawnFactory implements Spawner {

    private int spawnDelay;

    public CaravanTraderSpawnFactory() {
        this.spawnDelay = 100;

        Llamarama.LOGGER.info("caravan trader factory added");
    }

    @Override
    public int spawn(ServerWorld world, boolean spawnMonsters, boolean spawnAnimals) {
        if (world.getGameRules().getBoolean(GameRules.DO_MOB_SPAWNING) && --this.spawnDelay <= 0) {
            BlockPos validPos = this.getRandomValidPos(world);

            if (validPos == null) {
                this.spawnDelay += 200;
                return 0;
            }


            CaravanTraderEntity spawnedEntity = ModEntityTypes.get().CARAVAN_TRADER.spawn(world, null, null, null, validPos, SpawnReason.EVENT, false, false);

            if (spawnedEntity != null) {
                spawnedEntity.initialize(world, world.getLocalDifficulty(validPos), SpawnReason.EVENT, null, null);

                spawnedEntity.setPos(validPos.getX(), validPos.getY(), validPos.getZ());
                spawnedEntity.updateTrackedPosition(validPos.getX(), validPos.getY(), validPos.getZ());

                Llamarama.LOGGER.info("spawned a trader at " + validPos);

                this.spawnDelay = 100;

                return this.spawnLlamas(world, validPos, spawnedEntity) + 1;
            }

            this.spawnDelay += 2 * 60;
            Llamarama.LOGGER.info(validPos + " was not a valid position.");
        }
        return 0;
    }

    @Nullable
    protected BlockPos getRandomValidPos(ServerWorld world) {
        PlayerEntity player = world.getRandomAlivePlayer();

        if (player != null) {
            int maxSpawnRadius = 128;

            BlockPos playerPos = player.getBlockPos();

            int extraX = world.getRandom().nextInt(2 * maxSpawnRadius);
            int extraZ = world.getRandom().nextInt(2 * maxSpawnRadius);

            int actualXX = playerPos.getX() + extraX - maxSpawnRadius;
            int actualXZ = playerPos.getZ() + extraZ - maxSpawnRadius;

            BlockPos.Mutable alteredPos = new BlockPos.Mutable(actualXX, 0.0f, actualXZ);

            alteredPos.setY(world.getTopY(Heightmap.Type.MOTION_BLOCKING, alteredPos.getX(), alteredPos.getX()));

            if (Arrays.stream(LightType.values()).allMatch((lightType) -> world.getLightLevel(lightType, alteredPos) >= 10)) {
                return alteredPos.toImmutable();
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    protected int spawnLlamas(ServerWorld world, BlockPos traderPos, MerchantEntity merchant) {
        if (traderPos != null) {
            Random random = world.getRandom();

            boolean canLeash = true;
            final EntityType<?>[] LLAMA_TYPES = new EntityType[]{EntityType.LLAMA, EntityType.TRADER_LLAMA, ModEntityTypes.get().BUMBLE_LLAMA, ModEntityTypes.get().WOOLLY_LLAMA};
            Set<Entity> llamas = Sets.newHashSet();

            int amountOfLlamas = random.nextInt(6) + 1;

            for (int i = 0; i < amountOfLlamas; i++) {
                LlamaEntity llamaEntity = (LlamaEntity) LLAMA_TYPES[random.nextInt(LLAMA_TYPES.length)].create(world);

                if (llamaEntity != null) {

                    Vec3d llamaPos = merchant.getPos();

                    llamaEntity.setPos(llamaPos.getX(), llamaPos.getY(), llamaPos.getZ());
                    llamaEntity.updateTrackedPosition(llamaPos);

                    llamas.add(llamaEntity);

                    if (canLeash) {
                        llamaEntity.attachLeash(merchant, true);
                        canLeash = false;
                    }
                }
            }

            llamas.forEach(world::spawnEntityAndPassengers);

            return amountOfLlamas;
        }
        return 0;
    }

}
