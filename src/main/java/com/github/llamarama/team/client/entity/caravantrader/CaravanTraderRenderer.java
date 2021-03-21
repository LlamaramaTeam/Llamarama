package com.github.llamarama.team.client.entity.caravantrader;

import com.github.llamarama.team.entity.caravantrader.CaravanTraderEntity;
import com.github.llamarama.team.util.IdBuilder;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.feature.HeadFeatureRenderer;
import net.minecraft.client.render.entity.feature.VillagerHeldItemFeatureRenderer;
import net.minecraft.client.render.entity.model.VillagerResemblingModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class CaravanTraderRenderer extends MobEntityRenderer<CaravanTraderEntity, VillagerResemblingModel<CaravanTraderEntity>> {

    @SuppressWarnings("unused")
    public CaravanTraderRenderer(EntityRenderDispatcher entityRenderDispatcher, EntityRendererRegistry.Context context) {
        super(entityRenderDispatcher, new VillagerResemblingModel<>(0.0f), 0.5f);
        this.addFeature(new HeadFeatureRenderer<>(this));
        this.addFeature(new VillagerHeldItemFeatureRenderer<>(this));
    }

    @Override
    public Identifier getTexture(CaravanTraderEntity entity) {
        return IdBuilder.of("textures/entity/caravan_trader/caravan_trader.png");
    }

    @Override
    protected void scale(CaravanTraderEntity entity, MatrixStack matrices, float amount) {
        float scaleFactor = 0.9375F;
        matrices.scale(scaleFactor, scaleFactor, scaleFactor);
    }

}
