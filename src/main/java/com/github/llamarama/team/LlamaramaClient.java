package com.github.llamarama.team;

import com.github.llamarama.team.block.ModBlocks;
import com.github.llamarama.team.block.blockentity.LlamaWoolBedBlockEntity;
import com.github.llamarama.team.item.ModItems;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendereregistry.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.BlockPos;

@SuppressWarnings("deprecation")
@Environment(EnvType.CLIENT)
public class LlamaramaClient implements ClientModInitializer {

    private static final LlamaWoolBedBlockEntity LLAMA_WOOL_BED_RENDERER_BE =
            new LlamaWoolBedBlockEntity(BlockPos.ORIGIN, ModBlocks.LLAMA_WOOL_BED.getDefaultState());

    @Override
    public void onInitializeClient() {
        // Block Entity Renderers
        EventHandler.getInstance().addBlockEntityRegisterListener(BlockEntityRendererRegistry.INSTANCE::register);

        // Entity Renderers
        EventHandler.getInstance().addEntityRendererListener(EntityRendererRegistry.INSTANCE::register);
        EventHandler.getInstance().addEntityModelLayers(EntityModelLayerRegistry::registerModelLayer);

        // Register the llama wool bed item custom rendering.
        BuiltinItemRendererRegistry.INSTANCE.register(ModItems.LLAMA_WOOL_BED,
                (stack, mode, matrices, vertexConsumers, light, overlay) ->
                        MinecraftClient.getInstance().getBlockEntityRenderDispatcher()
                                .renderEntity(LLAMA_WOOL_BED_RENDERER_BE, matrices, vertexConsumers, light, overlay));
    }

}
