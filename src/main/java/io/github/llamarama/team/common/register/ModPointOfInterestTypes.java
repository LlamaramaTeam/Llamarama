package io.github.llamarama.team.common.register;

import com.google.common.collect.ImmutableSet;
import io.github.llamarama.team.common.util.IdBuilder;
import io.github.llamarama.team.mixin.InvokerPointOfInterestTypes;
import net.minecraft.block.Block;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.poi.PointOfInterestType;

public class ModPointOfInterestTypes {

    public static final RegistryKey<PointOfInterestType> STATUE =
        register("statue", ModBlocks.STATUE, 5, 1);

    public static RegistryKey<PointOfInterestType> register(String id, Block block, int tickets,
                                                            int search) {
        RegistryKey<PointOfInterestType> ret = RegistryKey.of(Registry.POINT_OF_INTEREST_TYPE_KEY, IdBuilder.of(id));
        InvokerPointOfInterestTypes.callRegister(
            Registry.POINT_OF_INTEREST_TYPE,
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
