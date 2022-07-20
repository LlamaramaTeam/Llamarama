package io.github.llamarama.team.common.util;

import com.mojang.serialization.Codec;
import net.minecraft.entity.EntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public final class CustomCodecs {

    public static final Codec<EntityType<?>> ENTITY_TYPE =
        Identifier.CODEC.xmap(Registry.ENTITY_TYPE::get, Registry.ENTITY_TYPE::getId);

}
