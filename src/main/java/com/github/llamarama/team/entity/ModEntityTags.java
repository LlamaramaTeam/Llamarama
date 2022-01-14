package com.github.llamarama.team.entity;

import com.github.llamarama.team.util.IdBuilder;
import net.fabricmc.fabric.api.tag.TagFactory;
import net.minecraft.entity.EntityType;
import net.minecraft.tag.Tag;
import org.jetbrains.annotations.NotNull;

public final class ModEntityTags {

    public static final Tag<EntityType<?>> LLAMAS = register("llamas");

    private ModEntityTags() {
    }

    @SuppressWarnings("SameParameterValue")
    @NotNull
    private static Tag<EntityType<?>> register(@NotNull String id) {
        return TagFactory.ENTITY_TYPE.create(IdBuilder.of(id));
    }

    public static void init() {

    }

}
