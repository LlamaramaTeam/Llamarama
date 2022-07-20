package io.github.llamarama.team.common.entity.ai.task;

import io.github.llamarama.team.common.block.StatueBlock;
import io.github.llamarama.team.common.blockentity.StatueBlockEntity;
import io.github.llamarama.team.common.entity.sandyllama.SandyLlamaEntity;
import io.github.llamarama.team.common.register.ModBlocks;
import io.github.llamarama.team.common.register.ModMemoryModules;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.brain.MemoryModuleState;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.brain.task.Task;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.GlobalPos;
import net.minecraft.world.event.GameEvent;

import java.util.Map;

public class BuildStatueTask extends Task<SandyLlamaEntity> {

    public static final Map<MemoryModuleType<?>, MemoryModuleState> REQUIRED = Map.of(
        ModMemoryModules.LAST_VISITED_HOME, MemoryModuleState.REGISTERED,
        ModMemoryModules.PERSONAL_HOME, MemoryModuleState.VALUE_ABSENT,
        ModMemoryModules.LATEST_LIVING, MemoryModuleState.VALUE_PRESENT
    );

    public BuildStatueTask() {
        super(REQUIRED, 100);
    }

    @Override
    protected void run(ServerWorld world, SandyLlamaEntity entity, long time) {
        BlockPos.findClosest(
            entity.getBlockPos(),
            2, 2,
            pos -> world.getBlockState(pos).isAir() && world.getBlockState(pos.down()).isOf(Blocks.SAND)
        ).ifPresent(pos -> entity.getBrain().getOptionalMemory(ModMemoryModules.LATEST_LIVING).ifPresent(entityType -> {
            world.setBlockState(pos, ModBlocks.STATUE.getDefaultState().with(StatueBlock.HARDENED, false));
            world.emitGameEvent(entity, GameEvent.BLOCK_PLACE, pos);

            var statueBlockEntity = (StatueBlockEntity) world.getBlockEntity(pos);
            if (statueBlockEntity != null) {
                //noinspection unchecked
                statueBlockEntity.setImitatedType((EntityType<? extends LivingEntity>) entityType);
            }

            world.spawnParticles(
                new BlockStateParticleEffect(ParticleTypes.BLOCK, Blocks.SAND.getDefaultState()),
                pos.getX(), pos.getY(), pos.getZ(),
                24, 2, 2, 2, 0.2d
            );

            entity.getBrain().remember(ModMemoryModules.PERSONAL_HOME, GlobalPos.create(world.getRegistryKey(), pos));
        }));
    }

}
