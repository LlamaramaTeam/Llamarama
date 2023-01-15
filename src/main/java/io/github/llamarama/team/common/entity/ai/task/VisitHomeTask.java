package io.github.llamarama.team.common.entity.ai.task;

import io.github.llamarama.team.common.entity.sandyllama.SandyLlamaEntity;
import io.github.llamarama.team.common.register.ModMemoryModules;
import net.minecraft.entity.ai.brain.MemoryModuleState;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.brain.task.Task;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.GlobalPos;

import java.util.Map;
import java.util.Optional;

// see other task
public class VisitHomeTask extends Task<SandyLlamaEntity> {

    private static final Map<MemoryModuleType<?>, MemoryModuleState> REQUIRED = Map.of(
        ModMemoryModules.PERSONAL_HOME, MemoryModuleState.VALUE_PRESENT,
        ModMemoryModules.LAST_VISITED_HOME, MemoryModuleState.REGISTERED
    );

    public VisitHomeTask() {
        super(REQUIRED);
    }

    @Override
    protected void run(ServerWorld world, SandyLlamaEntity entity, long time) {
        entity.getBrain().getOptionalMemory(ModMemoryModules.PERSONAL_HOME)
            .map(GlobalPos::getPos)
            .map(pos -> entity.getNavigation().findPathTo(pos, 4))
            .ifPresent(path -> {
                entity.getBrain().remember(MemoryModuleType.PATH, path);
                entity.getNavigation().startMovingAlong(path, 1.5f);
            });
    }

    @Override
    protected void finishRunning(ServerWorld world, SandyLlamaEntity entity, long time) {
        entity.getBrain().remember(ModMemoryModules.LAST_VISITED_HOME, world.getTime());
    }

    @Override
    protected boolean shouldKeepRunning(ServerWorld world, SandyLlamaEntity entity, long time) {
        return entity.getBrain().getOptionalMemory(ModMemoryModules.PERSONAL_HOME)
            .filter(globalPos -> globalPos.getDimension().getValue() == world.getRegistryKey().getValue())
            .map(globalPos -> !globalPos.getPos().isWithinDistance(entity.getPos(), 4))
            .orElse(false);
    }

    @Override
    protected boolean shouldRun(ServerWorld world, SandyLlamaEntity entity) {
        return isSuitablePlace(world, entity) && isSuitableTime(world, entity);
    }

    private static boolean isSuitablePlace(ServerWorld world, SandyLlamaEntity entity) {
        return entity.getBrain().getOptionalMemory(ModMemoryModules.PERSONAL_HOME).stream()
            .allMatch(globalPos ->
                globalPos.getDimension().getValue() == world.getRegistryKey().getValue() &&
                    !globalPos.getPos().isWithinDistance(entity.getPos(), 4)
            );
    }

    // This task is able to run every 30 minutes!
    private static boolean isSuitableTime(ServerWorld world, SandyLlamaEntity entity) {
        Optional<Long> optionalLastVisisted = entity.getBrain().getOptionalMemory(ModMemoryModules.LAST_VISITED_HOME);
        return optionalLastVisisted.isEmpty() || world.getTime() - optionalLastVisisted.get() >= 20 * 60 * 30;
    }
}
