package com.github.llamarama.team.client.entity.caravantrader;

import com.github.llamarama.team.entity.caravantrader.CaravanTraderEntity;
import com.github.llamarama.team.util.IDBuilder;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class CaravanTraderRenderer extends MobEntityRenderer<CaravanTraderEntity, CaravanTraderModel> {

    public CaravanTraderRenderer(EntityRenderDispatcher entityRenderDispatcher, CaravanTraderModel entityModel, float f) {
        super(entityRenderDispatcher, entityModel, f);
    }

    @SuppressWarnings("unused")
    public CaravanTraderRenderer(EntityRenderDispatcher entityRenderDispatcher, EntityRendererRegistry.Context context) {
        this(entityRenderDispatcher, new CaravanTraderModel(), 1.0f);
    }

    @Override
    public Identifier getTexture(CaravanTraderEntity entity) {
        return IDBuilder.of("textures/entity/caravan_trader/caravan_trader.png");
    }

}
