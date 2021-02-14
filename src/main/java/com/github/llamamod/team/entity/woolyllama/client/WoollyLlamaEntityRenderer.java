package com.github.llamamod.team.entity.woolyllama.client;

import com.github.llamamod.team.LlamaMod;
import com.github.llamamod.team.entity.woolyllama.WoollyLlamaEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class WoollyLlamaEntityRenderer extends MobEntityRenderer<WoollyLlamaEntity, WoollyLlamaEntityModel<WoollyLlamaEntity>> {

    public WoollyLlamaEntityRenderer(EntityRenderDispatcher entityRenderDispatcher) {
        super(entityRenderDispatcher, new WoollyLlamaEntityModel<>(0.0f), 0.7F);
    }

    @Override
    public Identifier getTexture(WoollyLlamaEntity entity) {
        return new Identifier(LlamaMod.MOD_ID, "textures/entity/woolly_llama/woolly_llama.png");
    }

}
