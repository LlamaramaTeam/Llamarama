package io.github.llamarama.team.client;

import io.github.llamarama.team.client.register.ModBlockEntityRenderers;
import io.github.llamarama.team.client.register.ModEntityRenderers;
import io.github.llamarama.team.common.block.blockentity.LlamaWoolBedBlockEntity;
import io.github.llamarama.team.common.register.ModBlocks;
import io.github.llamarama.team.common.register.ModItems;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.math.BlockPos;

@Environment(EnvType.CLIENT)
public class LlamaramaClient implements ClientModInitializer {

    private static final LlamaWoolBedBlockEntity LLAMA_WOOL_BED_RENDERER_BE =
        new LlamaWoolBedBlockEntity(BlockPos.ORIGIN, ModBlocks.LLAMA_WOOL_BED.getDefaultState());

    @Override
    public void onInitializeClient() {
        // Block Entity Renderers
        ModBlockEntityRenderers.init();

        // Entity Renderers
        ModEntityRenderers.init();
        ModEntityRenderers.registerLayers();

        // Register the llama wool bed item custom rendering.
        BuiltinItemRendererRegistry.INSTANCE.register(ModItems.LLAMA_WOOL_BED,
            (stack, mode, matrices, vertexConsumers, light, overlay) ->
                MinecraftClient.getInstance().getBlockEntityRenderDispatcher().renderEntity(
                    LLAMA_WOOL_BED_RENDERER_BE,
                    matrices,
                    vertexConsumers,
                    light,
                    overlay
                )
        );

        // Set the rug block to render as cutout
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.RUG, RenderLayer.getCutout());
    }

}
