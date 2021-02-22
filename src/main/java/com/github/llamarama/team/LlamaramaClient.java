package com.github.llamarama.team;

import com.github.llamarama.team.block.blockentity.ModBlockEntityTypes;
import com.github.llamarama.team.client.blockentity.LlamaWoolBedBlockEntityRenderer;
import com.github.llamarama.team.client.entity.woollyllama.WoollyLlamaEntityRenderer;
import com.github.llamarama.team.entity.ModEntityTypes;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendereregistry.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;


@Environment(EnvType.CLIENT)
public class LlamaramaClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        // Block Entity Renderers
        BlockEntityRendererRegistry.INSTANCE.register(ModBlockEntityTypes.LLAMA_WOOL_BED_BLOCK_ENTITY_BLOCK_ENTITY_TYPE, LlamaWoolBedBlockEntityRenderer::new);


        // Entity Renderers
        EntityRendererRegistry.INSTANCE.register(ModEntityTypes.WOOLLY_LLAMA, (entityRenderDispatcher, context) -> new WoollyLlamaEntityRenderer(entityRenderDispatcher));
    }

}
