package io.github.llamarama.team.client.register;

import io.github.llamarama.team.client.blockentity.LlamaWoolBedBlockEntityRenderer;
import io.github.llamarama.team.common.register.ModBlockEntityTypes;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;

@Environment(EnvType.CLIENT)
public class ModBlockEntityRenderers {

    public static void init() {
        BlockEntityRendererRegistry.register(ModBlockEntityTypes.LLAMA_WOOL_BED, LlamaWoolBedBlockEntityRenderer::new);
    }

}
