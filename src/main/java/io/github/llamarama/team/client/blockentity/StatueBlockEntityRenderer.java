package io.github.llamarama.team.client.blockentity;

import io.github.llamarama.team.common.blockentity.StatueBlockEntity;
import io.github.llamarama.team.common.util.IdBuilder;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;

import java.util.IdentityHashMap;

@Environment(EnvType.CLIENT)
public class StatueBlockEntityRenderer implements BlockEntityRenderer<StatueBlockEntity> {
    private final IdentityHashMap<EntityType<? extends LivingEntity>, LivingEntity> cache =
        new IdentityHashMap<>();

    public StatueBlockEntityRenderer(BlockEntityRendererFactory.Context ignored) {
    }

    private static final Identifier CONTINOUS_SAND = IdBuilder.of("textures/misc/continous_sand.png");

    @Override
    public void render(StatueBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        EntityType<? extends LivingEntity> imitatedType = entity.getImitatedType();
        LivingEntity dummy = cache.get(imitatedType);
        if (dummy == null) {
            dummy = imitatedType.create(entity.getWorld());
            if (dummy == null) {
                return;
            }
            cache.put(imitatedType, dummy);
        }

        LivingEntityRenderer<? super LivingEntity, ?> renderer =
            (LivingEntityRenderer<? super LivingEntity, ?>) MinecraftClient.getInstance().getEntityRenderDispatcher().getRenderer(dummy);
        matrices.push();
        matrices.translate(0.5f, 0, 0.5f);
        renderer.getModel().render(
            matrices,
            vertexConsumers.getBuffer(RenderLayer.getEntityCutoutNoCull(CONTINOUS_SAND)),
            light,
            OverlayTexture.DEFAULT_UV, 1, 1, 1, 1
        );
        matrices.pop();
    }

}
