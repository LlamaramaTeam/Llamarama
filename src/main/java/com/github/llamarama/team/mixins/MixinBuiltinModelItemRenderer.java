package com.github.llamarama.team.mixins;

import com.github.llamarama.team.block.LlamaWoolBedBlock;
import com.github.llamarama.team.block.ModBlocks;
import com.github.llamarama.team.block.blockentity.LlamaWoolBedBlockEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.BuiltinModelItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(BuiltinModelItemRenderer.class)
public class MixinBuiltinModelItemRenderer {

    @Inject(method = "render", at = @At("HEAD"), cancellable = true)
    public void render(ItemStack stack, ModelTransformation.Mode mode, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay, CallbackInfo ci) {
        if (stack.getItem() instanceof BlockItem) {
            Block target = ((BlockItem) stack.getItem()).getBlock();
            BlockEntity bed = null;

            if (target instanceof LlamaWoolBedBlock) {
                bed = new LlamaWoolBedBlockEntity(BlockPos.ORIGIN, ModBlocks.LLAMA_WOOL_BED.getDefaultState());
            }

            if (bed != null) {
                MinecraftClient.getInstance().getBlockEntityRenderDispatcher()
                        .renderEntity(bed, matrices, vertexConsumers, light, overlay);
                ci.cancel();
            }
        }
    }

}
