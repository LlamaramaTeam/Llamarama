package io.github.llamarama.team.client.entity.woollyllama;

import com.google.common.collect.ImmutableList;
import io.github.llamarama.team.client.entity.feature.CarpetFeatureRenderer;
import io.github.llamarama.team.common.entity.woolyllama.WoollyLlamaEntity;
import io.github.llamarama.team.common.util.IdBuilder;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

@SuppressWarnings("unused")
@Environment(EnvType.CLIENT)
public class WoollyLlamaEntityRenderer extends MobEntityRenderer<WoollyLlamaEntity, WoollyLlamaEntityModel> {

    public static final EntityModelLayer WOOLLY_LLAMA =
        new EntityModelLayer(IdBuilder.of("woolly_llama"), "main");
    public static final EntityModelLayer WOOLLY_LLAMA_DECOR =
        new EntityModelLayer(IdBuilder.of("woolly_llama"), "decor");

    private final ImmutableList<Identifier> TEXTURES = ImmutableList.of(
        IdBuilder.of("textures/entity/woolly_llama/woolly_llama.png"),
        IdBuilder.of("textures/entity/woolly_llama/woolly_llama_sheared.png")
    );

    public WoollyLlamaEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new WoollyLlamaEntityModel(context.getPart(WOOLLY_LLAMA)), 0.7F);
        this.addFeature(new CarpetFeatureRenderer<>(
            this,
            new WoollyLlamaEntityModel(context.getPart(WOOLLY_LLAMA_DECOR))
        ));
    }

    @Override
    public Identifier getTexture(WoollyLlamaEntity entity) {
        return entity.getSheared() ? TEXTURES.get(1) : TEXTURES.get(0);
    }

}
