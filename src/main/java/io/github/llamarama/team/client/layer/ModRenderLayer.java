package io.github.llamarama.team.client.layer;

import com.mojang.blaze3d.systems.RenderSystem;
import io.github.llamarama.team.common.util.IdBuilder;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexFormat;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.util.Identifier;
import org.apache.commons.lang3.mutable.MutableObject;

public class ModRenderLayer extends RenderLayer {

    private static final MutableObject<SandingState> STATE = new MutableObject<>(SandingState.DEFAULT);

    public static final Identifier SANDSTONE_TEXTURE = IdBuilder.of("textures/continuous_sandstone.png");
    public static final RenderLayer SANDSTONE_LAYER = createSandLayer("sandstone", SANDSTONE_TEXTURE);
    public static final Identifier SAND_TEXTURE = IdBuilder.of("textures/continuous_sand.png");
    public static final RenderLayer SAND_LAYER = createSandLayer("sand", SAND_TEXTURE);

    public ModRenderLayer(String name, VertexFormat vertexFormat, VertexFormat.DrawMode drawMode, int expectedBufferSize, boolean hasCrumbling, boolean translucent, Runnable startAction, Runnable endAction) {
        super(name, vertexFormat, drawMode, expectedBufferSize, hasCrumbling, translucent, startAction, endAction);
    }

    private static RenderLayer createSandLayer(String name, Identifier texture) {
        return new ModRenderLayer(
            name,
            VertexFormats.POSITION_COLOR_TEXTURE_OVERLAY_LIGHT_NORMAL,
            VertexFormat.DrawMode.QUADS,
            256,
            true,
            false,
            () -> {
                RenderSystem.disableBlend();
                RenderSystem.disableCull();
                RenderSystem.enableTexture();
                RenderSystem.setShaderTexture(0, texture);
                MinecraftClient.getInstance().gameRenderer.getOverlayTexture().setupOverlayColor();
                MinecraftClient.getInstance().gameRenderer.getLightmapTextureManager().enable();
                RenderSystem.setShader(GameRenderer::getRenderTypeEntityCutoutNoNullShader);
            },
            () -> {
                RenderSystem.enableCull();
                MinecraftClient.getInstance().gameRenderer.getOverlayTexture().teardownOverlayColor();
                MinecraftClient.getInstance().gameRenderer.getLightmapTextureManager().disable();
            }
        );
    }

    public static void renderSandedInScope(boolean isSandstone, Runnable scope) {
        STATE.setValue(isSandstone ? SandingState.SANDSTONE : SandingState.SAND);
        scope.run();
        STATE.setValue(SandingState.DEFAULT);
    }

    public static boolean renderingSanded() {
        return STATE.getValue() != SandingState.DEFAULT;
    }

    public static RenderLayer getCurrentLayer() {
        return switch (STATE.getValue()) {
            case SAND -> SAND_LAYER;
            case SANDSTONE -> SANDSTONE_LAYER;
            default -> throw new IllegalStateException("This should never happen!");
        };
    }

    public enum SandingState {
        SAND,
        SANDSTONE,
        DEFAULT
    }

}
