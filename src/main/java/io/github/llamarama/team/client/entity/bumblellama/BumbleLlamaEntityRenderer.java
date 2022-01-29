package io.github.llamarama.team.client.entity.bumblellama;

import io.github.llamarama.team.entity.bumbllama.BumbleLlamaEntity;
import io.github.llamarama.team.util.IdBuilder;
import com.google.common.collect.ImmutableList;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class BumbleLlamaEntityRenderer extends MobEntityRenderer<BumbleLlamaEntity, BumbleLlamaEntityModel<BumbleLlamaEntity>> {

    public static final EntityModelLayer BUMBLE_LLAMA =
            new EntityModelLayer(IdBuilder.of("bumble_llama"), "main");
    private final ImmutableList<Identifier> TEXTURES =
            ImmutableList.of(
                    IdBuilder.of("textures/entity/bumble_llama/bumble_llama.png"),
                    IdBuilder.of("textures/entity/bumble_llama/bumble_llama_hive_empty.png")
            );

    public BumbleLlamaEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new BumbleLlamaEntityModel<>(context.getPart(BUMBLE_LLAMA)), 0.5f);
    }

    @Override
    public Identifier getTexture(BumbleLlamaEntity entity) {
        return !entity.getSheared() ? TEXTURES.get(0) : TEXTURES.get(1);
    }

}
