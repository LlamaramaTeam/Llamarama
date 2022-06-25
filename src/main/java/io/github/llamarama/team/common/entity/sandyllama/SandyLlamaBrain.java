package io.github.llamarama.team.common.entity.sandyllama;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.brain.Activity;
import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.brain.sensor.Sensor;
import net.minecraft.entity.ai.brain.sensor.SensorType;

import java.util.List;

public final class SandyLlamaBrain {
    public static final List<MemoryModuleType<?>> MEMORY_MODULES = List.of();
    private static final List<SensorType<? extends Sensor<? super LivingEntity>>> SENSORS = List.of();

    public static Brain.Profile<SandyLlamaEntity> createProfile() {
        return Brain.createProfile(MEMORY_MODULES, SENSORS);
    }

    public static Brain<SandyLlamaEntity> create(Brain<SandyLlamaEntity> brain) {
        brain.setDefaultActivity(Activity.IDLE);
        initCoreTasks(brain);
        return brain;
    }

    private static void initCoreTasks(Brain<SandyLlamaEntity> brain) {
    }

}
