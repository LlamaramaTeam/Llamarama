// Made with Blockbench 3.7.5
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports

package com.github.llamarama.team.client.tile;

import com.github.llamarama.team.tile.LlamaWoolBedBlockEntity;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;

public class LlamaWoolBedBlockEntityRenderer extends BlockEntityRenderer<LlamaWoolBedBlockEntity> {

    private final ModelPart bone;
    private final ModelPart leg2;
    private final ModelPart leg3;
    private final ModelPart leg0;
    private final ModelPart leg1;
    private final ModelPart bed;

    public LlamaWoolBedBlockEntityRenderer(BlockEntityRenderDispatcher dispatcher) {
        super(dispatcher);

        int textureWidth = 64;
        int textureHeight = 64;
        bone = new ModelPart();
        bone.setPivot(-8.0F, 12.0F, 8.0F);


        leg2 = new ModelPart(this);
        leg2.setPivot(2.0F, -12.0F, 0.0F);
        bone.addChild(leg2);
        leg2.setTextureOffset(0, 44).addCuboid(-5.0F, 9.0F, 6.0F, 3.0F, 3.0F, 3.0F, 0.0F, true);

        leg3 = new ModelPart(this);
        leg3.setPivot(-2.0F, -12.0F, 0.0F);
        bone.addChild(leg3);
        leg3.setTextureOffset(12, 44).addCuboid(-14.0F, 9.0F, 6.0F, 3.0F, 3.0F, 3.0F, 0.0F, true);

        leg0 = new ModelPart(this);
        leg0.setPivot(5.0F, -22.0F, 0.0F);
        bone.addChild(leg0);
        leg0.setTextureOffset(0, 38).addCuboid(-8.0F, -10.0F, 6.0F, 3.0F, 3.0F, 3.0F, 0.0F, true);

        leg1 = new ModelPart(this);
        leg1.setPivot(-5.0F, -22.0F, 0.0F);
        bone.addChild(leg1);
        leg1.setTextureOffset(12, 38).addCuboid(-11.0F, -10.0F, 6.0F, 3.0F, 3.0F, 3.0F, 0.0F, true);

        bed = new ModelPart(this);
        bed.setPivot(0.0F, -24.0F, 0.0F);
        bone.addChild(bed);
        bed.setTextureOffset(0, 0).addCuboid(-16.0F, -8.0F, 0.0F, 16.0F, 32.0F, 6.0F, 0.0F, true);
        bed.setTextureOffset(38, 2).addCuboid(-13.0F, -8.0F, 6.0F, 10.0F, 1.0F, 3.0F, 0.0F, true);
        bed.setTextureOffset(38, 38).addCuboid(-13.0F, 23.0F, 6.0F, 10.0F, 1.0F, 3.0F, 0.0F, true);
        bed.setTextureOffset(52, 6).addCuboid(-16.0F, -5.0F, 6.0F, 1.0F, 26.0F, 3.0F, 0.0F, true);
        bed.setTextureOffset(44, 6).addCuboid(-1.0F, -5.0F, 6.0F, 1.0F, 26.0F, 3.0F, 0.0F, true);
    }

    @Override
    public void render(LlamaWoolBedBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {

    }

}