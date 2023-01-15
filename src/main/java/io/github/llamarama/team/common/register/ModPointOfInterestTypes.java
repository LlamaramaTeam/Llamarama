package io.github.llamarama.team.common.register;

import com.google.common.collect.ImmutableSet;
import io.github.llamarama.team.common.util.IdBuilder;
import io.github.llamarama.team.mixin.InvokerPointOfInterestTypes;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.world.poi.PointOfInterestType;

public class ModPointOfInterestTypes {

    public static final RegistryKey<PointOfInterestType> STATUE =
        register("statue", ModBlocks.STATUE, 5, 1);

    public static RegistryKey<PointOfInterestType> register(String id, Block block, int tickets,
                                                            int search) {
        RegistryKey<PointOfInterestType> ret = RegistryKey.of(RegistryKeys.POINT_OF_INTEREST_TYPE, IdBuilder.of(id));
        InvokerPointOfInterestTypes.callRegister(
            Registries.POINT_OF_INTEREST_TYPE,
            // idk what the error is here
            ret,
            ImmutableSet.copyOf(block.getStateManager().getStates()),
            tickets,
            search
        );
        return ret;
    }

    public static void init() {

    }
}
