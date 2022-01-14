package com.github.llamarama.team.util.events;

import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityModelLayerRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;

@SuppressWarnings("deprecation")
public interface EntityLayerRegistryListener {

    void register(EntityModelLayer modelLayer, EntityModelLayerRegistry.TexturedModelDataProvider provider);

}
