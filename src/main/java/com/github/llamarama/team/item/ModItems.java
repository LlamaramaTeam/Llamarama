package com.github.llamarama.team.item;

import com.github.llamarama.team.Llamarama;
import com.github.llamarama.team.client.ModSoundEvents;
import com.github.llamarama.team.entity.ModEntityTypes;
import com.github.llamarama.team.item.food.ModFoodComponents;
import com.github.llamarama.team.util.IDBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.OnAStickItem;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.Rarity;
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

    private static final Item.Settings NORMAL_SETTINGS = new Item.Settings().group(Llamarama.LLAMA_ITEM_GROUP).rarity(Rarity.COMMON);

    // Instantiate Items Here!!!
    public static final Item RAW_LLAMA_MEAT = new Item(NORMAL_SETTINGS.food(ModFoodComponents.RAW_LLAMA_MEAT));
    public static final Item ROASTED_LLAMA_MEAT = new Item(NORMAL_SETTINGS.food(ModFoodComponents.ROASTED_LLAMA_MEAT));
    public static final Item WOOLLY_LLAMA_SPAWN_EGG = new SpawnEggItem(ModEntityTypes.WOOLLY_LLAMA, 0xFDD185, 0xE9AE48, NORMAL_SETTINGS);
    public static final Item LLAMARAMA = new MusicDiscItem(5, ModSoundEvents.LLAMARAMA_DISC, NORMAL_SETTINGS.maxCount(1).rarity(Rarity.RARE).fireproof());
    public static final Item LLAMAJAMA = new MusicDiscItem(5, ModSoundEvents.LLAMAJAMA_DISC, NORMAL_SETTINGS.maxCount(1).rarity(Rarity.RARE).fireproof());
    public static final Item HAY_ON_A_STICK = new OnAStickItem<>(NORMAL_SETTINGS, ModEntityTypes.WOOLLY_LLAMA, 5);
    public static final Item LLAMA_MILK = new LlamaMilkItem(NORMAL_SETTINGS);
    public static final Item LLAMA_CHEESE = new Item(NORMAL_SETTINGS.food(ModFoodComponents.LLAMA_CHEESE));

    private static ModItems instance;


    private ModItems() {
        register(RAW_LLAMA_MEAT, "raw_llama_meat");
        register(ROASTED_LLAMA_MEAT, "roasted_llama_meat");
        register(WOOLLY_LLAMA_SPAWN_EGG, "woolly_llama_spawn_egg");
        register(LLAMARAMA, "llamarama_disc");
        register(LLAMAJAMA, "llamajama_disc");
        register(HAY_ON_A_STICK, "hay_on_a_stick");
        register(LLAMA_MILK, "llama_milk");
        register(LLAMA_CHEESE, "llama_cheese");
    }

    public static void init() {
        if (instance == null) { instance = new ModItems(); }
    }

    private void register(Item item, String id) {
        Registry.register(Registry.ITEM, IDBuilder.of(id), item);
    }

}
