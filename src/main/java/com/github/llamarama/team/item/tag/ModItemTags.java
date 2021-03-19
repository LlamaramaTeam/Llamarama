package com.github.llamarama.team.item.tag;

import com.github.llamarama.team.util.IdBuilder;
import net.fabricmc.fabric.api.tag.TagRegistry;
import net.minecraft.item.Item;
import net.minecraft.tag.Tag;

public final class ModItemTags {

    public static final Tag<Item> LLAMA_DISCS = TagRegistry.item(IdBuilder.of("llama_discs"));

    private ModItemTags() {

    }

}
