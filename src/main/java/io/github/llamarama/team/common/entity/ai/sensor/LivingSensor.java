package io.github.llamarama.team.common.entity.ai.sensor;

import io.github.llamarama.team.common.entity.sandyllama.SandyLlamaEntity;
import io.github.llamarama.team.common.register.ModMemoryModules;
import io.github.llamarama.team.common.tag.ModEntityTags;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.brain.sensor.Sensor;
import net.minecraft.server.world.ServerWorld;

import java.util.Set;

public class LivingSensor extends Sensor<SandyLlamaEntity> {

    public LivingSensor() {
        super(1200);
    }

    @Override
    public Set<MemoryModuleType<?>> getOutputMemoryModules() {
        return Set.of(ModMemoryModules.LATEST_LIVING);
    }

    @Override
    protected void sense(ServerWorld world, SandyLlamaEntity entity) {
        world.getOtherEntities(
                entity, entity.getBoundingBox().expand(10),
                other -> other instanceof LivingEntity && !other.getType().isIn(ModEntityTags.NOT_SANDABLE)
            ).stream()
            .findAny()
            .map(Entity::getType)
            .ifPresent(other -> entity.getBrain().remember(ModMemoryModules.LATEST_LIVING, other));
    }
}
