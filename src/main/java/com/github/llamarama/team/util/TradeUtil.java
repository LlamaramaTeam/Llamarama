package com.github.llamarama.team.util;

import com.github.llamarama.team.item.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOffers;

public final class TradeUtil {

    public static final TradeOffers.Factory[] FACTORY = new TradeOffers.Factory[]{(entity, random) -> new TradeOffer(new ItemStack(ModItems.FLIGHT_OF_THE_BUMBLE_LLAMA), ModItems.LLAMAJAMA.getDefaultStack(), 4, 2, 2.0f), (entity, random) -> new TradeOffer(new ItemStack(ModItems.LLAMA_MILK), new ItemStack(ModItems.LLAMA_CHEESE, 4), 8, 2, 1.0f), (entity, random) -> new TradeOffer(new ItemStack(Items.EMERALD, random.nextInt(5)), new ItemStack(ModItems.RAW_LLAMA_MEAT, random.nextInt(5) + 1), 8, 1, 2.0f)};

}
