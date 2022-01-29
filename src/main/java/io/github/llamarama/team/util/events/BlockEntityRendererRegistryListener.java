package io.github.llamarama.team.util.events;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;

@Environment(EnvType.CLIENT)
@FunctionalInterface
public interface BlockEntityRendererRegistryListener {

    <E extends BlockEntity> void registerRenderer(BlockEntityType<E> blockEntityType, BlockEntityRendererFactory<? super E> blockEntityRendererFactory);

}
