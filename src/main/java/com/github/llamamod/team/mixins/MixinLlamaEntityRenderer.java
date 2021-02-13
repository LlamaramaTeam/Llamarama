package com.github.llamamod.team.mixins;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.LlamaEntityRenderer;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.LlamaEntityModel;
import net.minecraft.entity.passive.LlamaEntity;
import org.spongepowered.asm.mixin.Mixin;

@Environment(EnvType.CLIENT)
@Mixin(LlamaEntityRenderer.class)
public abstract class MixinLlamaEntityRenderer extends MobEntityRenderer<LlamaEntity, LlamaEntityModel<LlamaEntity>> {

    public MixinLlamaEntityRenderer(EntityRenderDispatcher entityRenderDispatcher, LlamaEntityModel<LlamaEntity> entityModel, float f) {
        super(entityRenderDispatcher, entityModel, f);
    }

}
