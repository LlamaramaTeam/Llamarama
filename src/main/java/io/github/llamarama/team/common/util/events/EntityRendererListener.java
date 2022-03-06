package io.github.llamarama.team.common.util.events;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;

@Environment(EnvType.CLIENT)
@FunctionalInterface
public interface EntityRendererListener {

    <T extends Entity> void registerRenderer(EntityType<T> entityType, EntityRendererFactory<T> factory);

}
