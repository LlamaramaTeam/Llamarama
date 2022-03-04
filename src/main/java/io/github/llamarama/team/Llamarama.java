package io.github.llamarama.team;

import io.github.llamarama.team.block.ModBlockTags;
import io.github.llamarama.team.block.ModBlocks;
import io.github.llamarama.team.block.blockentity.ModBlockEntityTypes;
import io.github.llamarama.team.client.ModSoundEvents;
import io.github.llamarama.team.entity.ModEntityTags;
import io.github.llamarama.team.entity.ModEntityTypes;
import io.github.llamarama.team.item.ModItems;
import io.github.llamarama.team.item.tag.ModItemTags;
import io.github.llamarama.team.util.IdBuilder;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import com.mojang.logging.LogUtils;
import org.slf4j.Logger;

public class Llamarama implements ModInitializer {

    public static final String MOD_ID = "llamarama";
    public static final String MOD_NAME = "Llamarama";
    public static final Logger LOGGER = LogUtils.getLogger();

    public static final ItemGroup LLAMA_ITEM_GROUP =
        FabricItemGroupBuilder.create(IdBuilder.of("llama_item_group"))
            .icon(() -> new ItemStack(ModItems.WOOLLY_LLAMA_SPAWN_EGG))
            .build();

    @Override
    public void onInitialize() {
        LOGGER.info("Welcome to the world of Llamas!!!");
        ModSoundEvents.init();
        ModItems.init();
        ModBlockEntityTypes.init();
        ModBlocks.init();
        ModEntityTypes.init();
        ModItemTags.init();
        ModEntityTags.init();
        ModBlockTags.init();

        // Callback registers.
        EventHandler.getInstance().addSpawnsListener(BiomeModifications::addSpawn);
        EventHandler.getInstance().registerSpawnRestrictions();
        LootTableLoadingCallback.EVENT.register(EventHandler.getInstance()::lootTableListener);
    }

}
