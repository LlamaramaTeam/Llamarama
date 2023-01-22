package io.github.llamarama.team.common.register;

import com.mojang.serialization.Codec;
import io.github.llamarama.team.common.util.CustomCodecs;
import io.github.llamarama.team.common.util.IdBuilder;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.math.GlobalPos;

import java.util.Optional;

public class ModMemoryModules {

    public static final MemoryModuleType<GlobalPos> PERSONAL_HOME =
        register("personal_home", GlobalPos.CODEC);
    public static final MemoryModuleType<Long> LAST_VISITED_HOME =
        register("last_visited_home", Codec.LONG);
    public static final MemoryModuleType<EntityType<?>> LATEST_LIVING =
        register("latest_living", CustomCodecs.ENTITY_TYPE);

    private static <T> MemoryModuleType<T> register(String id, Codec<T> codec) {
        return Registry.register(
            Registries.MEMORY_MODULE_TYPE,
            IdBuilder.of(id),
            new MemoryModuleType<>(Optional.of(codec))
        );
    }

    public static void init() {

    }

}
