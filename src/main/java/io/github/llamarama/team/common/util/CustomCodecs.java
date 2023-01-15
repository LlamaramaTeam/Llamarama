package io.github.llamarama.team.common.util;

import com.mojang.serialization.Codec;
import net.minecraft.entity.EntityType;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registry;

public final class CustomCodecs {

    public static final Codec<EntityType<?>> ENTITY_TYPE =
        Identifier.CODEC.xmap(Registries.ENTITY_TYPE::get, Registries.ENTITY_TYPE::getId);

}
