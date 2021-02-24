package com.github.llamarama.team.util.events;

import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.minecraft.entity.EntityType;

@FunctionalInterface
public interface EntityRendererListener {

    void registerRenderer(EntityType<?> entityType, EntityRendererRegistry.Factory factory);

}
