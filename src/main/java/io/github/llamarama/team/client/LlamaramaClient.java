package io.github.llamarama.team.client;

import io.github.llamarama.team.client.item.LlamaWoolBedBlockEntityItemRenderer;
import io.github.llamarama.team.client.item.StatueItemRenderer;
import io.github.llamarama.team.client.register.ModBlockEntityRenderers;
import io.github.llamarama.team.client.register.ModEntityRenderers;
import io.github.llamarama.team.common.blockentity.LlamaWoolBedBlockEntity;
import io.github.llamarama.team.common.register.ModBlocks;
import io.github.llamarama.team.common.register.ModItems;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.BlockPos;

@Environment(EnvType.CLIENT)
public class LlamaramaClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        // Block Entity Renderers
        ModBlockEntityRenderers.init();

        // Entity Renderers
        ModEntityRenderers.init();
        ModEntityRenderers.registerLayers();

        // Register the llama wool bed item custom rendering.
        BuiltinItemRendererRegistry.INSTANCE.register(ModItems.LLAMA_WOOL_BED, new LlamaWoolBedBlockEntityItemRenderer());
        BuiltinItemRendererRegistry.INSTANCE.register(ModBlocks.STATUE.asItem(), new StatueItemRenderer());
    }

}
