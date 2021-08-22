package com.github.llamarama.team.client.blockentity;

import com.github.llamarama.team.block.blockentity.LlamaWoolBedBlockEntity;
import com.github.llamarama.team.util.IdBuilder;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BedBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.ChestBlock;
import net.minecraft.block.DoubleBlockProperties;
import net.minecraft.block.entity.BedBlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.enums.BedPart;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.block.entity.LightmapCoordinatesRetriever;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3f;
import net.minecraft.world.World;

@Environment(EnvType.CLIENT)
public class LlamaWoolBedBlockEntityRenderer implements BlockEntityRenderer<LlamaWoolBedBlockEntity> {

    private final ModelPart model = new ModelPart(64, 64, 0, 0);
    private final ModelPart body;
    private final ModelPart[] legs = new ModelPart[4];

    public LlamaWoolBedBlockEntityRenderer(BlockEntityRendererFactory.Context context) {
        this.model.addCuboid(0.0F, 0.0F, 0.0F, 16.0F, 16.0F, 6.0F, 0.0F);
        this.body = new ModelPart(64, 64, 0, 22);
        this.body.addCuboid(0.0F, 0.0F, 0.0F, 16.0F, 16.0F, 6.0F, 0.0F);
        this.legs[0] = new ModelPart(64, 64, 50, 0);
        this.legs[1] = new ModelPart(64, 64, 50, 6);
        this.legs[2] = new ModelPart(64, 64, 50, 12);
        this.legs[3] = new ModelPart(64, 64, 50, 18);
        this.legs[0].addCuboid(0.0F, 6.0F, -16.0F, 3.0F, 3.0F, 3.0F);
        this.legs[1].addCuboid(0.0F, 6.0F, 0.0F, 3.0F, 3.0F, 3.0F);
        this.legs[2].addCuboid(-16.0F, 6.0F, -16.0F, 3.0F, 3.0F, 3.0F);
        this.legs[3].addCuboid(-16.0F, 6.0F, 0.0F, 3.0F, 3.0F, 3.0F);
        this.legs[0].pitch = 1.5707964F;
        this.legs[1].pitch = 1.5707964F;
        this.legs[2].pitch = 1.5707964F;
        this.legs[3].pitch = 1.5707964F;
        this.legs[0].roll = 0.0F;
        this.legs[1].roll = 1.5707964F;
        this.legs[2].roll = 4.712389F;
        this.legs[3].roll = 3.1415927F;
    }

    @Override
    public void render(LlamaWoolBedBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        Identifier identifier = IdBuilder.of("textures/block/llama_wool_bed.png");
        World world = entity.getWorld();
        if (world != null) {
            BlockState blockState = entity.getCachedState();
            DoubleBlockProperties.PropertySource<? extends BedBlockEntity> propertySource = DoubleBlockProperties.toPropertySource(BlockEntityType.BED, BedBlock::getBedPart, BedBlock::getOppositePartDirection, ChestBlock.FACING, blockState, world, entity.getPos(), (worldAccess, blockPos) -> false);
            int k = propertySource.apply(new LightmapCoordinatesRetriever<>()).get(light);
            this.renderDirectional(matrices, vertexConsumers, blockState.get(BedBlock.PART) == BedPart.HEAD, blockState.get(BedBlock.FACING), identifier, k, overlay, false);
        } else {
            this.renderDirectional(matrices, vertexConsumers, true, Direction.SOUTH, identifier, light, overlay, false);
            this.renderDirectional(matrices, vertexConsumers, false, Direction.SOUTH, identifier, light, overlay, true);
        }
    }

    public void renderDirectional(MatrixStack matrices, VertexConsumerProvider vertexConsumers, boolean bl, Direction direction, Identifier identifier, int light, int overlay, boolean bl2) {
        this.model.visible = bl;
        this.body.visible = !bl;
        this.legs[0].visible = !bl;
        this.legs[1].visible = bl;
        this.legs[2].visible = !bl;
        this.legs[3].visible = bl;
        matrices.push();
        matrices.translate(0.0D, 0.5625D, bl2 ? -1.0D : 0.0D);
        matrices.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(90.0F));
        matrices.translate(0.5D, 0.5D, 0.5D);
        matrices.multiply(Vec3f.POSITIVE_Z.getDegreesQuaternion(180.0F + direction.asRotation()));
        matrices.translate(-0.5D, -0.5D, -0.5D);
        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(RenderLayer.getEntitySolid(identifier));
        this.model.render(matrices, vertexConsumer, light, overlay);
        this.body.render(matrices, vertexConsumer, light, overlay);
        this.legs[0].render(matrices, vertexConsumer, light, overlay);
        this.legs[1].render(matrices, vertexConsumer, light, overlay);
        this.legs[2].render(matrices, vertexConsumer, light, overlay);
        this.legs[3].render(matrices, vertexConsumer, light, overlay);
        matrices.pop();
    }

}
