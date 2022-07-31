package io.github.llamarama.team.client.register;

import io.github.llamarama.team.client.blockentity.LlamaWoolBedBlockEntityRenderer;
import io.github.llamarama.team.client.entity.bumblellama.BumbleLlamaEntityModel;
import io.github.llamarama.team.client.entity.bumblellama.BumbleLlamaEntityRenderer;
import io.github.llamarama.team.client.entity.caravantrader.CaravanTraderRenderer;
import io.github.llamarama.team.client.entity.mossyllama.MossyLlamaEntityModel;
import io.github.llamarama.team.client.entity.mossyllama.MossyLlamaEntityRenderer;
import io.github.llamarama.team.client.entity.sandyllama.SandyLlamaEntityModel;
import io.github.llamarama.team.client.entity.sandyllama.SandyLlamaEntityRenderer;
import io.github.llamarama.team.client.entity.woollyllama.WoollyLlamaEntityModel;
import io.github.llamarama.team.client.entity.woollyllama.WoollyLlamaEntityRenderer;
import io.github.llamarama.team.common.register.ModEntityTypes;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.entity.model.VillagerResemblingModel;

@Environment(EnvType.CLIENT)
public class ModEntityRenderers {

    public static void init() {
        EntityRendererRegistry.register(ModEntityTypes.WOOLLY_LLAMA, WoollyLlamaEntityRenderer::new);
        EntityRendererRegistry.register(ModEntityTypes.BUMBLE_LLAMA, BumbleLlamaEntityRenderer::new);
        EntityRendererRegistry.register(ModEntityTypes.CARAVAN_TRADER, CaravanTraderRenderer::new);
        EntityRendererRegistry.register(ModEntityTypes.MOSSY_LLAMA, MossyLlamaEntityRenderer::new);
        EntityRendererRegistry.register(ModEntityTypes.SANDY_LLAMA, SandyLlamaEntityRenderer::new);
    }

    public static void registerLayers() {
        EntityModelLayerRegistry.registerModelLayer(LlamaWoolBedBlockEntityRenderer.LLAMA_BED_HEAD,
            LlamaWoolBedBlockEntityRenderer::getHeadTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(LlamaWoolBedBlockEntityRenderer.LLAMA_BED_FOOT,
            LlamaWoolBedBlockEntityRenderer::getFootTexturedModelData);

        EntityModelLayerRegistry.registerModelLayer(WoollyLlamaEntityRenderer.WOOLLY_LLAMA,
            WoollyLlamaEntityModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(WoollyLlamaEntityRenderer.WOOLLY_LLAMA_DECOR,
            WoollyLlamaEntityModel::getTexturedModelData);

        EntityModelLayerRegistry.registerModelLayer(BumbleLlamaEntityRenderer.BUMBLE_LLAMA,
            BumbleLlamaEntityModel::getTexturedModelData);

        EntityModelLayerRegistry.registerModelLayer(CaravanTraderRenderer.CARAVAN_TRADER,
            () -> TexturedModelData.of(VillagerResemblingModel.getModelData(), 64, 64));

        EntityModelLayerRegistry.registerModelLayer(MossyLlamaEntityRenderer.MOSSY_LLAMA,
            MossyLlamaEntityModel::getTexturedModelData);

        EntityModelLayerRegistry.registerModelLayer(SandyLlamaEntityRenderer.SANDY_LLAMA,
            SandyLlamaEntityModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(SandyLlamaEntityRenderer.SANDY_LLAMA_DECOR,
            SandyLlamaEntityModel::getTexturedModelData);
    }

}
