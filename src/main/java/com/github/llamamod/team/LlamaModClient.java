package com.github.llamamod.team;

import com.github.llamamod.team.entity.ModEntityTypes;
import com.github.llamamod.team.entity.woolyllama.client.WoollyLlamaEntityRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;


@Environment(EnvType.CLIENT)
public class LlamaModClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.INSTANCE.register(ModEntityTypes.WOOLLY_LLAMA, (entityRenderDispatcher, context) -> new WoollyLlamaEntityRenderer(entityRenderDispatcher));
    }

}
