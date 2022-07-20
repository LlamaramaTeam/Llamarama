package io.github.llamarama.team.common.register;

import io.github.llamarama.team.common.entity.ai.sensor.LivingSensor;
import io.github.llamarama.team.common.entity.ai.sensor.StatueSensor;
import io.github.llamarama.team.common.util.IdBuilder;
import io.github.llamarama.team.mixin.InvokerSensorType;
import net.minecraft.entity.ai.brain.sensor.Sensor;
import net.minecraft.entity.ai.brain.sensor.SensorType;
import net.minecraft.util.registry.Registry;

import java.util.function.Supplier;

public class ModSensorTypes {

    public static final SensorType<StatueSensor> STATUE = register("statue", StatueSensor::new);
    public static final SensorType<LivingSensor> LIVING = register("living", LivingSensor::new);

    public static <T extends Sensor<?>> SensorType<T> register(String id, Supplier<T> factory) {
        return Registry.register(Registry.SENSOR_TYPE, IdBuilder.of(id), InvokerSensorType.create(factory));
    }

    public static void init() {

    }
}
