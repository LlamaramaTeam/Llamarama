package com.github.llamarama.team.util.events;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;

import java.util.function.Function;

@FunctionalInterface
public interface BlockEntityRendererRegistryListener {

    <E extends BlockEntity> void registerRenderer(BlockEntityType<E> blockEntityType, Function<BlockEntityRenderDispatcher, BlockEntityRenderer<E>> function);

}
