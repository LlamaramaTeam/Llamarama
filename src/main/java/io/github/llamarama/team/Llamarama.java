package io.github.llamarama.team;

import io.github.llamarama.team.client.ModSoundEvents;
import io.github.llamarama.team.common.block.ModBlocks;
import io.github.llamarama.team.common.block.blockentity.ModBlockEntityTypes;
import io.github.llamarama.team.common.entity.ModEntityTags;
import io.github.llamarama.team.common.entity.ModEntityTypes;
import io.github.llamarama.team.common.event.EventHandler;
import io.github.llamarama.team.common.item.ModItems;
import io.github.llamarama.team.common.util.IdBuilder;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Llamarama implements ModInitializer {

    public static final String MOD_ID = "llamarama";
    public static final ItemGroup LLAMA_ITEM_GROUP =
        FabricItemGroupBuilder.create(IdBuilder.of("llama_item_group"))
            .icon(() -> new ItemStack(ModItems.WOOLLY_LLAMA_SPAWN_EGG))
            .build();
    private static final Logger LOGGER = LoggerFactory.getLogger("Llamarama");

    public static @NotNull Logger getLogger() {
        return LOGGER;
    }

    @Override
    public void onInitialize() {
        ModSoundEvents.init();
        ModItems.init();
        ModBlockEntityTypes.init();
        ModBlocks.init();
        ModEntityTypes.init();
        ModEntityTags.init();

        // Callback registers.
        EventHandler.getInstance().addSpawnsListener(BiomeModifications::addSpawn);
        EventHandler.getInstance().registerSpawnRestrictions();
        LootTableLoadingCallback.EVENT.register(EventHandler.getInstance()::lootTableListener);
        Llamarama.getLogger().info("Welcome to the world of Llamas!!!");
    }

}
