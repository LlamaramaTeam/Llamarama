package io.github.llamarama.team.common.tag;

import io.github.llamarama.team.common.util.IdBuilder;
import net.minecraft.entity.EntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.TagKey;

public final class ModEntityTags {

    public static final TagKey<EntityType<?>> LLAMAS =
        TagKey.of(Registries.ENTITY_TYPE.getKey(), IdBuilder.of("llamas"));
    public static final TagKey<EntityType<?>> NOT_SANDABLE =
        TagKey.of(Registries.ENTITY_TYPE.getKey(), IdBuilder.of("non_sandable"));
}
