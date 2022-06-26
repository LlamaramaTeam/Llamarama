package io.github.llamarama.team.mixin;

import net.minecraft.entity.ai.brain.sensor.Sensor;
import net.minecraft.entity.ai.brain.sensor.SensorType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.function.Supplier;

@Mixin(SensorType.class)
public interface InvokerSensorType {

    @Invoker(value = "<init>")
    static <U extends Sensor<?>> SensorType<U> create(Supplier<U> factory) {
        throw new IllegalStateException("This should not happen!");
    }

}
