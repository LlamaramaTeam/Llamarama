package io.github.llamarama.team.common.entity.ai.sensor;

import io.github.llamarama.team.common.entity.sandyllama.SandyLlamaEntity;
import io.github.llamarama.team.common.register.ModMemoryModules;
import io.github.llamarama.team.common.register.ModPointOfInterestTypes;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.brain.sensor.Sensor;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.GlobalPos;
import net.minecraft.world.poi.PointOfInterestStorage;

import java.util.Optional;
import java.util.Set;

public class StatueSensor extends Sensor<SandyLlamaEntity> {

    public StatueSensor() {
        super(100);
    }

    @Override
    public Set<MemoryModuleType<?>> getOutputMemoryModules() {
        return Set.of(ModMemoryModules.PERSONAL_HOME);
    }

    @Override
    protected void sense(ServerWorld world, SandyLlamaEntity entity) {
        Optional<GlobalPos> memory = entity.getBrain().getOptionalMemory(ModMemoryModules.PERSONAL_HOME);
        if (memory.isEmpty()) {
            Optional<BlockPos> nearStatue = world.getPointOfInterestStorage().getNearestPosition(
                entry -> entry.matchesKey(ModPointOfInterestTypes.STATUE),
                entity.getBlockPos(),
                32,
                PointOfInterestStorage.OccupationStatus.ANY
            );

            entity.getBrain().remember(ModMemoryModules.PERSONAL_HOME,
                nearStatue.map(pointOfInterestPos -> GlobalPos.create(world.getRegistryKey(), pointOfInterestPos))
            );
        } else {
            BlockPos poiPos = memory.get().getPos();
            if (!world.getPointOfInterestStorage().hasTypeAt(ModPointOfInterestTypes.STATUE, poiPos)) {
                entity.getBrain().forget(ModMemoryModules.PERSONAL_HOME);
            }
        }
    }
}
