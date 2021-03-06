package com.github.llamarama.team;

import com.github.llamarama.team.block.ModBlocks;
import com.github.llamarama.team.block.blockentity.ModBlockEntityTypes;
import com.github.llamarama.team.client.ModSoundEvents;
import com.github.llamarama.team.entity.ModEntityTypes;
import com.github.llamarama.team.item.ModItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("deprecation")
public class Llamarama implements ModInitializer {

    public static final String MOD_ID = "llamarama";
    public static final String MOD_NAME = "Llamarama";
    public static final Logger LOGGER = LogManager.getLogger(MOD_NAME);

    public static final ItemGroup LLAMA_ITEM_GROUP = FabricItemGroupBuilder.create(new Identifier(MOD_ID, "llama_item_group")).icon(() -> new ItemStack(ModItems.WOOLLY_LLAMA_SPAWN_EGG)).build();

    @Override
    public void onInitialize() {
        LOGGER.info("Welcome to the world of Llamas!!!");
        ModSoundEvents.init();
        ModItems.init();
        ModBlocks.init();
        ModEntityTypes.init();
        ModBlockEntityTypes.init();

        // Callback registers.
        EventHandler.getInstance().addSpawnsListener(BiomeModifications::addSpawn);
        EventHandler.getInstance().addSpawnRestrictionListener();
        LootTableLoadingCallback.EVENT.register(EventHandler.getInstance()::lootTableListener);
    }

}
