package com.github.llamarama.team.client.entity.bumblellama;

import com.github.llamarama.team.entity.bumbllama.BumbleLlamaEntity;
import com.github.llamarama.team.util.IdBuilder;
import com.google.common.collect.ImmutableList;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class BumbleLlamaEntityRenderer extends MobEntityRenderer<BumbleLlamaEntity, BumbleLlamaEntityModel<BumbleLlamaEntity>> {

    private final ImmutableList<Identifier> TEXTURES = ImmutableList.of(IdBuilder.of("textures/entity/bumble_llama/bumble_llama.png"), IdBuilder.of("textures/entity/bumble_llama/bumble_llama_hive_empty.png"));

    @SuppressWarnings("unused")
    public BumbleLlamaEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new BumbleLlamaEntityModel<>(), 0.5f);
    }

    @Override
    public Identifier getTexture(BumbleLlamaEntity entity) {
        return !entity.getSheared() ? TEXTURES.get(0) : TEXTURES.get(1);
    }

}
