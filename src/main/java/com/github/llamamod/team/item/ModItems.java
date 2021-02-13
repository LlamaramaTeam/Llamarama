package com.github.llamamod.team.item;

import com.github.llamamod.team.LlamaMod;
import com.github.llamamod.team.item.food.ModFoodComponents;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;
import java.util.List;


/**
 * @author PeterGamesGR
 * This is a utility class for adding items.
 * All items should be initialised in this class right after the registry list field.
 * No item needs to be registered manually, they are automatically registered.
 * Make sure all items you make are either {@link CustomItem} or extend {@link CustomItem}.
 * This class should never be extended (hence it's final).
 * This class has a private constructor because it should never be instatiated.
 */
public final class ModItems {

    private static final List<CustomItem> REGISTRY = new ArrayList<>();
    private static final Item.Settings NORMAL_SETTINGS = new Item.Settings().group(LlamaMod.LLAMA_ITEM_GROUP);

    // Instantiate Items Here!!!
    public static final Item RAW_LLAMA_MEAT = new CustomItem("raw_llama_meat", NORMAL_SETTINGS.food(ModFoodComponents.RAW_LLAMA_MEAT));
    public static final Item ROASTED_LLAMA_MEAT = new CustomItem("roasted_llama_meat", NORMAL_SETTINGS.food(ModFoodComponents.ROASTED_LLAMA_MEAT));


    private ModItems() {

    }

    public static <ITEM extends CustomItem> void addToRegistry(ITEM item) {
        REGISTRY.add(item);
    }

    public static void init() {
        for (CustomItem item : REGISTRY) {
            Registry.register(Registry.ITEM, new Identifier(LlamaMod.MOD_ID, item.getId()), item);
        }
    }

}
