package io.github.llamarama.team.client.entity.feature;

import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.passive.LlamaEntity;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;

public class CarpetFeatureRenderer<T extends LlamaEntity, M extends EntityModel<T>> extends FeatureRenderer<T, M> {

    public static final Identifier[] CARPET_IDS = new Identifier[]{
        new Identifier("textures/entity/llama/decor/white.png"),
        new Identifier("textures/entity/llama/decor/orange.png"),
        new Identifier("textures/entity/llama/decor/magenta.png"),
        new Identifier("textures/entity/llama/decor/light_blue.png"),
        new Identifier("textures/entity/llama/decor/yellow.png"),
        new Identifier("textures/entity/llama/decor/lime.png"),
        new Identifier("textures/entity/llama/decor/pink.png"),
        new Identifier("textures/entity/llama/decor/gray.png"),
        new Identifier("textures/entity/llama/decor/light_gray.png"),
        new Identifier("textures/entity/llama/decor/cyan.png"),
        new Identifier("textures/entity/llama/decor/purple.png"),
        new Identifier("textures/entity/llama/decor/blue.png"),
        new Identifier("textures/entity/llama/decor/brown.png"),
        new Identifier("textures/entity/llama/decor/green.png"),
        new Identifier("textures/entity/llama/decor/red.png"),
        new Identifier("textures/entity/llama/decor/black.png")
    };
    private final M model;

    public CarpetFeatureRenderer(FeatureRendererContext<T, M> context, M model) {
        super(context);
        this.model = model;
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, T entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        DyeColor carpetColor = entity.getCarpetColor();
        if (carpetColor != null) {
            var carpetId = CARPET_IDS[carpetColor.getId()];
            this.getContextModel().copyStateTo(this.model);
            this.model.setAngles(entity, limbAngle, limbDistance, animationProgress, headYaw, headPitch);
            VertexConsumer consumer = vertexConsumers.getBuffer(RenderLayer.getEntityCutoutNoCull(carpetId));
            this.model.render(matrices, consumer, light, OverlayTexture.DEFAULT_UV, 1, 1, 1, 1);
        }
    }
}
