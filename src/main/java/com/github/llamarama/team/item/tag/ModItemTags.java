package com.github.llamarama.team.item.tag;

import com.github.llamarama.team.util.IDBuilder;
import net.fabricmc.fabric.api.tag.TagRegistry;
import net.minecraft.item.Item;
import net.minecraft.tag.Tag;

public final class ModItemTags {

    public static final Tag<Item> LLAMA_DISCS = TagRegistry.item(IDBuilder.of("llama_discs"));

    private ModItemTags() {

    }

}
