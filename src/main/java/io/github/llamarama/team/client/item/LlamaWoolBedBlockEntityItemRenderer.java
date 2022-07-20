package io.github.llamarama.team.client.item;

import io.github.llamarama.team.common.blockentity.LlamaWoolBedBlockEntity;
import io.github.llamarama.team.common.register.ModBlocks;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;

public class LlamaWoolBedBlockEntityItemRenderer implements BuiltinItemRendererRegistry.DynamicItemRenderer {

    private static final LlamaWoolBedBlockEntity LLAMA_WOOL_BED_RENDERER_BE =
        new LlamaWoolBedBlockEntity(BlockPos.ORIGIN, ModBlocks.LLAMA_WOOL_BED.getDefaultState());

    @Override
    public void render(ItemStack stack, ModelTransformation.Mode mode, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        MinecraftClient.getInstance().getBlockEntityRenderDispatcher().renderEntity(
            LLAMA_WOOL_BED_RENDERER_BE,
            matrices,
            vertexConsumers,
            light,
            overlay
        );
    }

}
