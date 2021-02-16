package com.github.llamamod.team.item;

import com.github.llamamod.team.LlamaMod;
import com.github.llamamod.team.util.IDBuilder;
import com.github.llamamod.team.item.food.ModFoodComponents;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;


/**
 * @author PeterGamesGR
 * This is a utility class for adding items.
 * All items should be initialised in this class right after the registry list field.
 * No item needs to be registered manually, they are automatically registered.
 * This class should never be extended (hence it's final).
 * This class has a private constructor because it should never be instatiated.
 */
public final class ModItems {

    private static ModItems instance;

    private static final Item.Settings NORMAL_SETTINGS = new Item.Settings().group(LlamaMod.LLAMA_ITEM_GROUP);

    // Instantiate Items Here!!!
    public static final Item RAW_LLAMA_MEAT = new Item(NORMAL_SETTINGS.food(ModFoodComponents.RAW_LLAMA_MEAT));
    public static final Item ROASTED_LLAMA_MEAT = new Item(NORMAL_SETTINGS.food(ModFoodComponents.ROASTED_LLAMA_MEAT));


    private ModItems() {
        register(RAW_LLAMA_MEAT, "raw_llama_meat");
        register(ROASTED_LLAMA_MEAT, "roasted_llama_meat");
    }

    public static void init() {
        if (instance == null) { instance = new ModItems(); }
    }

    private void register(Item item, String id) {
        Registry.register(Registry.ITEM, IDBuilder.of(id), item);
    }

}
