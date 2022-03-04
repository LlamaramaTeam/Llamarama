package io.github.llamarama.team.mixins;

import com.google.common.collect.Sets;
import io.github.llamarama.team.entity.spawn.CaravanTraderSpawnFactory;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.profiler.Profiler;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.MutableWorldProperties;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.spawner.Spawner;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

@Mixin(ServerWorld.class)
public abstract class MixinServerWorld extends World implements StructureWorldAccess {

    protected MixinServerWorld(MutableWorldProperties properties, RegistryKey<World> registryRef, RegistryEntry<DimensionType> registryEntry, Supplier<Profiler> profiler, boolean isClient, boolean debugWorld, long seed) {
        super(properties, registryRef, registryEntry, profiler, isClient, debugWorld, seed);
    }

    @SuppressWarnings("ModifyVariableMayBeArgsOnly")
    @ModifyVariable(
        method = "<init>",
        at = @At("HEAD")
    )
    private static List<Spawner> modifySpawnsList(List<Spawner> list) {
        Set<Spawner> usedSet = Sets.newHashSet();
        usedSet.addAll(list);
        usedSet.add(new CaravanTraderSpawnFactory());

        return new ArrayList<>(usedSet);
    }


}
