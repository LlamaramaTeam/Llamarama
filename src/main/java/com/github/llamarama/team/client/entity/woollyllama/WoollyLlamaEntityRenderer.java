package com.github.llamarama.team.client.entity.woollyllama;

import com.github.llamarama.team.Llamarama;
import com.github.llamarama.team.entity.woolyllama.WoollyLlamaEntity;
import com.google.common.collect.ImmutableList;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;

@SuppressWarnings("unused")
@Environment(EnvType.CLIENT)
public class WoollyLlamaEntityRenderer extends MobEntityRenderer<WoollyLlamaEntity, WoollyLlamaEntityModel<WoollyLlamaEntity>> {

    private final ImmutableList<Identifier> TEXTURES;

    public WoollyLlamaEntityRenderer(EntityRenderDispatcher entityRenderDispatcher, EntityRendererRegistry.Context context) {
        super(entityRenderDispatcher, new WoollyLlamaEntityModel<>(0.0f), 0.7F);
        this.TEXTURES = ImmutableList.of(new Identifier(Llamarama.MOD_ID, "textures/entity/woolly_llama/woolly_llama" + ".png"), new Identifier(Llamarama.MOD_ID, "textures/entity/woolly_llama/woolly_llama_sheared.png"));
        this.addFeature(new WoollyLlamaDecorRenderer(this));
    }

    @Override
    public Identifier getTexture(WoollyLlamaEntity entity) {
        return entity.getSheared() ? TEXTURES.get(1) : TEXTURES.get(0);
    }

    /**
     * @author PeterGamesGR
     * Adds carpet rendering.
     */
    public static class WoollyLlamaDecorRenderer extends FeatureRenderer<WoollyLlamaEntity, WoollyLlamaEntityModel<WoollyLlamaEntity>> {

        private static final Identifier[] LLAMA_DECOR = new Identifier[]{new Identifier("textures/entity/llama/decor/white.png"), new Identifier("textures/entity/llama/decor/orange.png"), new Identifier("textures/entity/llama/decor/magenta.png"), new Identifier("textures/entity/llama/decor/light_blue.png"), new Identifier("textures/entity/llama/decor/yellow.png"), new Identifier("textures/entity/llama/decor/lime.png"), new Identifier("textures/entity/llama/decor/pink.png"), new Identifier("textures/entity/llama/decor/gray.png"), new Identifier("textures/entity/llama/decor/light_gray.png"), new Identifier("textures/entity/llama/decor/cyan.png"), new Identifier("textures/entity/llama/decor/purple.png"), new Identifier("textures/entity/llama/decor/blue.png"), new Identifier("textures/entity/llama/decor/brown.png"), new Identifier("textures/entity/llama/decor/green.png"), new Identifier("textures/entity/llama/decor/red.png"), new Identifier("textures/entity/llama/decor/black.png")};
        private final WoollyLlamaEntityModel<WoollyLlamaEntity> model = new WoollyLlamaEntityModel<>(0.5F);

        public WoollyLlamaDecorRenderer(FeatureRendererContext<WoollyLlamaEntity, WoollyLlamaEntityModel<WoollyLlamaEntity>> context) {
            super(context);
        }

        @Override
        public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, WoollyLlamaEntity entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {

            // Mostly Copied from LlamaFeatureDecorRenderer.
            DyeColor dyeColor = entity.getCarpetColor();
            Identifier carpetId;
            if (dyeColor != null) { carpetId = LLAMA_DECOR[dyeColor.getId()]; } else { return; }

            this.getContextModel().copyStateTo(this.model);
            this.model.setAngles(entity, limbAngle, limbDistance, animationProgress, headYaw, headPitch);
            VertexConsumer vertexConsumer = vertexConsumers.getBuffer(RenderLayer.getEntityCutoutNoCull(carpetId));
            this.model.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0F);
        }

    }

}
