package io.github.llamarama.team.common.entity.sandyllama;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import io.github.llamarama.team.common.entity.ai.task.VisitHomeTask;
import io.github.llamarama.team.common.register.ModMemoryModules;
import io.github.llamarama.team.common.register.ModSensorTypes;
import net.minecraft.entity.ai.brain.Activity;
import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.brain.sensor.Sensor;
import net.minecraft.entity.ai.brain.sensor.SensorType;

import java.util.List;
import java.util.Set;

public final class SandyLlamaBrain {
    public static final List<MemoryModuleType<?>> MEMORY_MODULES = List.of(
        ModMemoryModules.PERSONAL_HOME,
        ModMemoryModules.LAST_VISITED_HOME,
        MemoryModuleType.PATH
    );
    private static final List<SensorType<? extends Sensor<SandyLlamaEntity>>> SENSORS = List.of(
        ModSensorTypes.STATUE
    );

    public static Brain.Profile<SandyLlamaEntity> createProfile() {
        return Brain.createProfile(MEMORY_MODULES, SENSORS);
    }

    public static Brain<SandyLlamaEntity> create(Brain<SandyLlamaEntity> brain) {
        brain.setCoreActivities(Set.of(Activity.CORE));
        brain.setDefaultActivity(Activity.CORE);
        initCoreTasks(brain);
        return brain;
    }

    private static void initCoreTasks(Brain<SandyLlamaEntity> brain) {
        brain.setTaskList(Activity.CORE, ImmutableList.of(
            new Pair<>(0, new VisitHomeTask())
        ));
    }

}
