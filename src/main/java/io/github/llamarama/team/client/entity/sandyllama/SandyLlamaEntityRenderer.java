package io.github.llamarama.team.client.entity.sandyllama;

import io.github.llamarama.team.client.entity.feature.CarpetFeatureRenderer;
import io.github.llamarama.team.common.entity.sandyllama.SandyLlamaEntity;
import io.github.llamarama.team.common.util.IdBuilder;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class SandyLlamaEntityRenderer extends MobEntityRenderer<SandyLlamaEntity, SandyLlamaEntityModel> {

    public static final EntityModelLayer SANDY_LLAMA =
        new EntityModelLayer(IdBuilder.of("sandy_llama"), "main");
    public static final Identifier TEXTURE = IdBuilder.of("textures/entity/sandy_llama/sandy_llama.png");
    public static final EntityModelLayer SANDY_LLAMA_DECOR =
        new EntityModelLayer(IdBuilder.of("sandy_llama"), "decor");

    public SandyLlamaEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new SandyLlamaEntityModel(context.getPart(SANDY_LLAMA)), 0.7f);
        this.addFeature(new CarpetFeatureRenderer<>(
            this,
            new SandyLlamaEntityModel(context.getPart(SANDY_LLAMA_DECOR))
        ));
    }

    @Override
    public Identifier getTexture(SandyLlamaEntity entity) {
        return TEXTURE;
    }
}
