package com.github.llamarama.team.util.events;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.minecraft.entity.EntityType;

@Environment(EnvType.CLIENT)
@FunctionalInterface
public interface EntityRendererListener {

    void registerRenderer(EntityType<?> entityType, EntityRendererRegistry.Factory factory);

}
