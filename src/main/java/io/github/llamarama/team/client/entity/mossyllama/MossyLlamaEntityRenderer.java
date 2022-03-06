package io.github.llamarama.team.client.entity.mossyllama;

import io.github.llamarama.team.common.entity.mossyllama.MossyLlamaEntity;
import io.github.llamarama.team.common.util.IdBuilder;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class MossyLlamaEntityRenderer extends MobEntityRenderer<MossyLlamaEntity, MossyLlamaEntityModel<MossyLlamaEntity>> {

    public static final Identifier TEXTURE = IdBuilder.of("textures/entity/mossy_llama/mossy_llama.png");
    public static final EntityModelLayer MOSSY_LLAMA =
        new EntityModelLayer(IdBuilder.of("mossy_llama"), "main");

    public MossyLlamaEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new MossyLlamaEntityModel<>(context.getPart(MOSSY_LLAMA)), 0.7f);
    }

    @Override
    public Identifier getTexture(MossyLlamaEntity entity) {
        return TEXTURE;
    }

}
