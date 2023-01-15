package io.github.llamarama.team.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.poi.PointOfInterestType;
import net.minecraft.world.poi.PointOfInterestTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.Set;

@Mixin(PointOfInterestTypes.class)
public interface InvokerPointOfInterestTypes {

    @Invoker
    static PointOfInterestType callRegister(Registry<PointOfInterestType> registry,
                                            RegistryKey<PointOfInterestType> key,
                                            Set<BlockState> states,
                                            int ticketCount,
                                            int searchDistance) {
        throw new IllegalStateException("This should never happen!");
    }

}
