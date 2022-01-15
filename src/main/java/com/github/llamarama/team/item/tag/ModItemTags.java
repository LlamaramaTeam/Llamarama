package com.github.llamarama.team.item.tag;

import com.github.llamarama.team.util.IdBuilder;
import net.fabricmc.fabric.api.tag.TagFactory;
import net.minecraft.item.Item;
import net.minecraft.tag.Tag;

public final class ModItemTags {

    public static final Tag<Item> LLAMA_DISCS = TagFactory.ITEM.create(IdBuilder.of("llama_discs"));

    public static void init() {

    }

}
