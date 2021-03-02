package com.github.llamarama.team.client.entity.bumbllama;

import com.github.llamarama.team.entity.bumbllama.BumbllamaEntity;
import com.github.llamarama.team.util.IDBuilder;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class BumbllamaEntityRenderer extends MobEntityRenderer<BumbllamaEntity, BumbllamaEntityModel<BumbllamaEntity>> {

    @SuppressWarnings("unused")
    public BumbllamaEntityRenderer(EntityRenderDispatcher dispatcher, EntityRendererRegistry.Context context) {
        super(dispatcher, new BumbllamaEntityModel<>(), 0.5f);
//        this.addFeature(new WoollyLlamaEntityRenderer.WoollyLlamaDecorRenderer(this));
    }

    @Override
    public Identifier getTexture(BumbllamaEntity entity) {
        return IDBuilder.of("textures/entity/bumbllama/bumbllama.png");
    }

}
