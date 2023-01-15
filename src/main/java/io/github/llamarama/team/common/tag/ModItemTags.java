package io.github.llamarama.team.common.tag;

import io.github.llamarama.team.common.util.IdBuilder;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.registry.Registry;

public final class ModItemTags {

    public static final TagKey<Item> LLAMA_DISCS = TagKey.of(RegistryKeys.ITEM, IdBuilder.of("llama_discs"));

}
