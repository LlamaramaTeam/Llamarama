package io.github.llamarama.team.common.tag;

import io.github.llamarama.team.common.util.IdBuilder;
import net.minecraft.entity.EntityType;
import net.minecraft.tag.TagKey;
import net.minecraft.util.registry.Registry;

public final class ModEntityTags {

    public static final TagKey<EntityType<?>> LLAMAS = TagKey.of(Registry.ENTITY_TYPE_KEY, IdBuilder.of("llamas"));

    public static void init() {

    }

}
