package io.github.llamarama.team.client.blockentity;

import io.github.llamarama.team.common.blockentity.StatueBlockEntity;
import io.github.llamarama.team.common.util.IdBuilder;
import io.github.llamarama.team.mixin.client.AccessorEntityRenderDispatcher;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Quaternion;
import net.minecraft.util.math.Vec3f;

import java.util.IdentityHashMap;
import java.util.Map;

@Environment(EnvType.CLIENT)
public class StatueBlockEntityRenderer implements BlockEntityRenderer<StatueBlockEntity> {

    private static final Map<EntityType<? extends LivingEntity>, LivingEntity> CACHE = new IdentityHashMap<>();
    private static final Identifier SAND = IdBuilder.of("textures/continuous_sand.png");
    private static final Identifier SANDSTONE = IdBuilder.of("textures/continuous_sandstone.png");

    public StatueBlockEntityRenderer(BlockEntityRendererFactory.Context ignored) { }

    @Override
    public void render(StatueBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        EntityType<? extends LivingEntity> imitatedType = entity.getImitatedType();
        var renderedEntity = CACHE.computeIfAbsent(imitatedType, entityType -> entityType.create(MinecraftClient.getInstance().world));
        EntityRenderer<?> renderer =
            ((AccessorEntityRenderDispatcher) MinecraftClient.getInstance().getEntityRenderDispatcher()).getRenderers().get(imitatedType);

        var livingRenderer = this.castRenderer(renderer, renderedEntity);

        matrices.push();
        matrices.translate(0.5f, 0, 0.5f);
        matrices.multiply(new Quaternion(Vec3f.POSITIVE_Z, MathHelper.PI, false));
        matrices.translate(0, -1.6, 0);
        var model = livingRenderer.getModel();

        model.child = false;
        model.setAngles(renderedEntity, 0, 0, 0, 0, 0);
        model.render(
            matrices,
            vertexConsumers.getBuffer(RenderLayer.getEntityCutout(entity.isHardened() ? SANDSTONE : SAND)),
            light,
            OverlayTexture.DEFAULT_UV, 1, 1, 1, 1
        );
        matrices.pop();
    }

    @SuppressWarnings("unchecked")
    private <T extends LivingEntity> LivingEntityRenderer<T, ? extends EntityModel<T>> castRenderer(
        EntityRenderer<?> renderer,
        T ignored // only used for the T type
    ) {
        // unsafe cast, here be dragons!!
        return (LivingEntityRenderer<T, ? extends EntityModel<T>>) renderer;
    }
}
