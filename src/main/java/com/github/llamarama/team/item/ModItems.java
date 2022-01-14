package com.github.llamarama.team.item;

import com.github.llamarama.team.Llamarama;
import com.github.llamarama.team.block.ModBlocks;
import com.github.llamarama.team.client.ModSoundEvents;
import com.github.llamarama.team.entity.ModEntityTypes;
import com.github.llamarama.team.item.food.ModFoodComponents;
import com.github.llamarama.team.util.IdBuilder;
import net.minecraft.item.BedItem;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.registry.Registry;


@SuppressWarnings("unused")
public final class ModItems {

    public static final Item RAW_LLAMA_MEAT = register("raw_llama_meat",
            new Item(getBaseSettings().food(ModFoodComponents.RAW_LLAMA_MEAT)));
    public static final Item ROASTED_LLAMA_MEAT = register("roasted_llama_meat",
            new Item(getBaseSettings().food(ModFoodComponents.ROASTED_LLAMA_MEAT)));
    public static final Item WOOLLY_LLAMA_SPAWN_EGG = register("woolly_llama_spawn_egg",
            new SpawnEggItem(ModEntityTypes.WOOLLY_LLAMA, 0xFDD185, 0xE9AE48, getBaseSettings()));
    public static final Item HAY_ON_A_STICK = register("hay_on_a_stick",
            new HayOnAStickItem(getUnstackableSettings()));
    public static final Item LLAMA_MILK = register("llama_milk",
            new LlamaMilkItem(getUnstackableSettings()));
    public static final Item LLAMA_CHEESE = register("llama_cheese",
            new Item(getBaseSettings().food(ModFoodComponents.LLAMA_CHEESE)));
    public static final Item LLAMARAMA = register("llamarama_disc",
            new MusicDiscItem(5, ModSoundEvents.LLAMARAMA_DISC, getDiscSettings()));
    public static final Item LLAMAJAMA = register("llamajama_disc",
            new MusicDiscItem(5, ModSoundEvents.LLAMAJAMA_DISC, getDiscSettings()));
    public static final Item FLIGHT_OF_THE_BUMBLE_LLAMA = register("flight_of_the_bumble_llama",
            new MusicDiscItem(5, ModSoundEvents.BUMBLLAMA_DISC, getDiscSettings()));
    public static final Item BUMBLE_LLAMA_SPAWN_EGG = register("bumble_llama_spawn_egg",
            new SpawnEggItem(ModEntityTypes.BUMBLE_LLAMA, 0xEDEDED, 0x4A6424, getBaseSettings()));
    public static final Item CARAVAN_TRADER_SPAWN_EGG = register("caravan_trader_spawn_egg",
            new SpawnEggItem(ModEntityTypes.CARAVAN_TRADER, 0x7B857F, 0x6E3302, getBaseSettings()));
    public static final Item LLAMA_WOOL_BED = register("llama_wool_bed",
            new BedItem(ModBlocks.LLAMA_WOOL_BED, getUnstackableSettings()));
    public static final Item MOSSY_LLAMA_SPAWN_EGG = register("mossy_llama_spawn_egg",
            new SpawnEggItem(ModEntityTypes.MOSSY_LLAMA, 0x5F833F, 0xBA62CE, getBaseSettings()));
    public static final Item MOSSED_UP = register("mossed_up_disc",
            new MusicDiscItem(11, ModSoundEvents.MOSSED_UP_DISC, getDiscSettings()));

    private ModItems() {
    }

    @SuppressWarnings("EmptyMethod")
    public static void init() {
    }

    private static Item.Settings getBaseSettings() {
        return new Item.Settings().group(Llamarama.LLAMA_ITEM_GROUP);
    }

    private static Item.Settings getUnstackableSettings() {
        return getBaseSettings().maxCount(1);
    }

    private static Item.Settings getDiscSettings() {
        return getUnstackableSettings().fireproof();
    }

    private static Item register(String id, Item item) {
        return Registry.register(Registry.ITEM, IdBuilder.of(id), item);
    }

}
