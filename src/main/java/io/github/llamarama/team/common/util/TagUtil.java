package io.github.llamarama.team.common.util;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tag.TagKey;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;

import java.util.Optional;

public final class TagUtil {

    public static Optional<ItemStack> getRandomItem(TagKey<Item> tag, Random rng) {
        return Registry.ITEM.getOrCreateEntryList(tag)
            .getRandom(rng)
            .map(RegistryEntry::value)
            .map(Item::getDefaultStack);
    }

}
