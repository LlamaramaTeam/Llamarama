package io.github.llamarama.team;

import io.github.llamarama.team.common.event.EventHandler;
import io.github.llamarama.team.common.register.ModBiomeModifications;
import io.github.llamarama.team.common.register.ModBlockEntityTypes;
import io.github.llamarama.team.common.register.ModBlocks;
import io.github.llamarama.team.common.register.ModEntityTypes;
import io.github.llamarama.team.common.register.ModItems;
import io.github.llamarama.team.common.register.ModMemoryModules;
import io.github.llamarama.team.common.register.ModPointOfInterestTypes;
import io.github.llamarama.team.common.register.ModSensorTypes;
import io.github.llamarama.team.common.register.ModSoundEvents;
import io.github.llamarama.team.common.util.IdBuilder;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class Llamarama implements ModInitializer {
    public static final String MOD_ID = "llamarama";
    public static final ItemGroup LLAMA_ITEM_GROUP =
        FabricItemGroup.builder(IdBuilder.of("llama_item_group"))
            .icon(() -> new ItemStack(ModItems.WOOLLY_LLAMA_SPAWN_EGG))
            .build();
    private static final Logger LOGGER = LoggerFactory.getLogger("Llamarama");

    public static @NotNull Logger getLogger() {
        return LOGGER;
    }

    @Override
    public void onInitialize() {
        // Content Registration
        ModSoundEvents.init();
        ModBlocks.init();
        ModItems.init();
        ModBlockEntityTypes.init();
        ModEntityTypes.init();
        ModPointOfInterestTypes.init();
        ModMemoryModules.init();
        ModSensorTypes.init();

        // Biome Modifications
        ModBiomeModifications.init();

        // Callback registers.
        EventHandler handler = new EventHandler();
        LootTableEvents.MODIFY.register(handler);

        // Done
        Llamarama.getLogger().info("Welcome to the world of Llamas!");
    }
}
