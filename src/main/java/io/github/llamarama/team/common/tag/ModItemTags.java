package io.github.llamarama.team.common.tag;

import io.github.llamarama.team.common.util.IdBuilder;
import net.minecraft.item.Item;
import net.minecraft.tag.TagKey;
import net.minecraft.util.registry.Registry;

public final class ModItemTags {

    public static final TagKey<Item> LLAMA_DISCS = TagKey.of(Registry.ITEM_KEY, IdBuilder.of("llama_discs"));

}
