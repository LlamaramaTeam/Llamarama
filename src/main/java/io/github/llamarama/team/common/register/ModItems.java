package io.github.llamarama.team.common.register;

import io.github.llamarama.team.Llamarama;
import io.github.llamarama.team.common.entity.bumbllama.BumbleLlamaEntity;
import io.github.llamarama.team.common.entity.caravantrader.CaravanTraderEntity;
import io.github.llamarama.team.common.entity.mossyllama.MossyLlamaEntity;
import io.github.llamarama.team.common.entity.sandyllama.SandyLlamaEntity;
import io.github.llamarama.team.common.entity.woolyllama.WoollyLlamaEntity;
import io.github.llamarama.team.common.item.HayOnAStickItem;
import io.github.llamarama.team.common.item.LlamaMilkItem;
import io.github.llamarama.team.common.item.MusicDiscItem;
import io.github.llamarama.team.common.item.food.ModFoodComponents;
import io.github.llamarama.team.common.util.IdBuilder;

import net.minecraft.item.BedItem;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;


@SuppressWarnings("unused")
public final class ModItems {

    public static final Item RAW_LLAMA_MEAT = register("raw_llama_meat",
        new Item(getBaseSettings().food(ModFoodComponents.RAW_LLAMA_MEAT)));
    public static final Item ROASTED_LLAMA_MEAT = register("roasted_llama_meat",
        new Item(getBaseSettings().food(ModFoodComponents.ROASTED_LLAMA_MEAT)));
    public static final Item WOOLLY_LLAMA_SPAWN_EGG = register("woolly_llama_spawn_egg",
        WoollyLlamaEntity.createSpawnEggData().create(getBaseSettings()));
    public static final Item HAY_ON_A_STICK = register("hay_on_a_stick",
        new HayOnAStickItem(getUnstackableSettings()));
    public static final Item LLAMA_MILK = register("llama_milk",
        new LlamaMilkItem(getUnstackableSettings()));
    public static final Item LLAMA_CHEESE = register("llama_cheese",
        new Item(getBaseSettings().food(ModFoodComponents.LLAMA_CHEESE)));
    public static final Item LLAMARAMA = register("llamarama_disc",
        new MusicDiscItem(5, ModSoundEvents.LLAMARAMA_DISC, getDiscSettings(), 142));
    public static final Item LLAMAJAMA = register("llamajama_disc",
        new MusicDiscItem(5, ModSoundEvents.LLAMAJAMA_DISC, getDiscSettings(), 132));
    public static final Item FLIGHT_OF_THE_BUMBLE_LLAMA = register("flight_of_the_bumble_llama",
        new MusicDiscItem(5, ModSoundEvents.BUMBLLAMA_DISC, getDiscSettings(), 82));
    public static final Item BUMBLE_LLAMA_SPAWN_EGG = register("bumble_llama_spawn_egg",
        BumbleLlamaEntity.createSpawnEggData().create(getBaseSettings()));
    public static final Item CARAVAN_TRADER_SPAWN_EGG = register("caravan_trader_spawn_egg",
        CaravanTraderEntity.createSpawnEggData().create(getBaseSettings()));
    public static final Item LLAMA_WOOL_BED = register("llama_wool_bed",
        new BedItem(ModBlocks.LLAMA_WOOL_BED, getUnstackableSettings()));
    public static final Item MOSSY_LLAMA_SPAWN_EGG = register("mossy_llama_spawn_egg",
        MossyLlamaEntity.createSpawnEggData().create(getBaseSettings()));
    public static final Item MOSSED_UP = register("mossed_up_disc",
        new MusicDiscItem(11, ModSoundEvents.MOSSED_UP_DISC, getDiscSettings(), 134));
    public static final Item SANDY_LLAMA = register("sandy_llama_spawn_egg",
        SandyLlamaEntity.createSpawnEggData().create(getBaseSettings()));

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
