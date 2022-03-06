package io.github.llamarama.team.client.entity.caravantrader;

import io.github.llamarama.team.common.entity.caravantrader.CaravanTraderEntity;
import io.github.llamarama.team.common.util.IdBuilder;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.feature.HeadFeatureRenderer;
import net.minecraft.client.render.entity.feature.VillagerHeldItemFeatureRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.VillagerResemblingModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class CaravanTraderRenderer extends MobEntityRenderer<CaravanTraderEntity, VillagerResemblingModel<CaravanTraderEntity>> {

    public static final EntityModelLayer CARAVAN_TRADER =
        new EntityModelLayer(IdBuilder.of("caravan_trader"), "main");

    public CaravanTraderRenderer(EntityRendererFactory.Context context) {
        super(context, new VillagerResemblingModel<>(context.getPart(CARAVAN_TRADER)), 0.5f);
        this.addFeature(new HeadFeatureRenderer<>(this, context.getModelLoader()));
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
