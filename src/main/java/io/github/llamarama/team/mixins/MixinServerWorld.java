package io.github.llamarama.team.mixins;

import io.github.llamarama.team.common.entity.spawn.CaravanTraderSpawnFactory;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.profiler.Profiler;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.MutableWorldProperties;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.spawner.Spawner;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

@Mixin(ServerWorld.class)
public abstract class MixinServerWorld extends World implements StructureWorldAccess {

    protected MixinServerWorld(MutableWorldProperties properties, RegistryKey<World> registryRef, RegistryEntry<DimensionType> registryEntry, Supplier<Profiler> profiler, boolean isClient, boolean debugWorld, long seed) {
        super(properties, registryRef, registryEntry, profiler, isClient, debugWorld, seed);
    }

    @ModifyVariable(
        method = "<init>",
        at = @At("HEAD"),
        argsOnly = true
    )
    private static @NotNull List<Spawner> modifySpawnsList(List<Spawner> list) {
        if (!list.isEmpty()) {
            var newList = new ArrayList<>(list);
            newList.add(new CaravanTraderSpawnFactory());

            return newList;
        }

        return list;
    }


}
