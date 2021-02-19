package com.github.llamarama.team;

import com.github.llamarama.team.entity.ModEntityTypes;
import com.github.llamarama.team.entity.woolyllama.client.WoollyLlamaEntityRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;


@Environment(EnvType.CLIENT)
public class LlamaramaClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.INSTANCE.register(ModEntityTypes.WOOLLY_LLAMA, (entityRenderDispatcher, context) -> new WoollyLlamaEntityRenderer(entityRenderDispatcher));
    }

}
