package com.github.llamamod.team;

import com.github.llamamod.team.block.ModBlocks;
import com.github.llamamod.team.entity.ModEntityTypes;
import com.github.llamamod.team.item.ModItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LlamaMod implements ModInitializer {

    public static final String MOD_ID = "llamamod";
    public static final String MOD_NAME = "Llama Mod";
    public static final Logger LOGGER = LogManager.getLogger(MOD_NAME);

    public static final ItemGroup LLAMA_ITEM_GROUP = FabricItemGroupBuilder.create(new Identifier(MOD_ID, "llama_item_group")).icon(() -> new ItemStack(ModItems.RAW_LLAMA_MEAT)).build();

    @Override
    public void onInitialize() {
        LOGGER.info("Welcome to the world of Llamas!!!");
        ModItems.init();
        ModBlocks.init();
        ModEntityTypes.init();
    }

}
