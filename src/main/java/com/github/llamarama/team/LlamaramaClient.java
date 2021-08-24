package com.github.llamarama.team;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendereregistry.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;


@SuppressWarnings({"UnstableApiUsage", "deprecation"})
@Environment(EnvType.CLIENT)
public class LlamaramaClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        // Block Entity Renderers
        EventHandler.getInstance().addBlockEntityRegisterListener(BlockEntityRendererRegistry.INSTANCE::register);

        // Entity Renderers
        EventHandler.getInstance().addEntityRendererListener(EntityRendererRegistry.INSTANCE::register);
        EventHandler.getInstance().addEntityModelLayers(EntityModelLayerRegistry::registerModelLayer);
    }

}
