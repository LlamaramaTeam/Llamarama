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
import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.block.entity.LightmapCoordinatesRetriever;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.EntityModelPartNames;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3f;
import net.minecraft.world.World;

@Environment(EnvType.CLIENT)
public class LlamaWoolBedBlockEntityRenderer implements BlockEntityRenderer<LlamaWoolBedBlockEntity> {

    public static final EntityModelLayer LLAMA_BED_HEAD =
            new EntityModelLayer(IdBuilder.of("llama_bed_head"), "main");
    public static final EntityModelLayer LLAMA_BED_FOOT =
            new EntityModelLayer(IdBuilder.of("llama_bed_foot"), "main");
    private final ModelPart bedHead;
    private final ModelPart bedFoot;


    public LlamaWoolBedBlockEntityRenderer(BlockEntityRendererFactory.Context context) {
        this.bedHead = context.getLayerModelPart(LLAMA_BED_HEAD);
        this.bedFoot = context.getLayerModelPart(LLAMA_BED_FOOT);
    }

    public static TexturedModelData getHeadTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        modelPartData.addChild("main", ModelPartBuilder.create().uv(0, 0).cuboid(0.0F, 0.0F, 0.0F, 16.0F, 16.0F, 6.0F), ModelTransform.NONE);
        modelPartData.addChild(EntityModelPartNames.LEFT_LEG, ModelPartBuilder.create().uv(50, 6).cuboid(0.0F, 6.0F, 0.0F, 3.0F, 3.0F, 3.0F), ModelTransform.rotation(1.5707964F, 0.0F, 1.5707964F));
        modelPartData.addChild(EntityModelPartNames.RIGHT_LEG, ModelPartBuilder.create().uv(50, 18).cuboid(-16.0F, 6.0F, 0.0F, 3.0F, 3.0F, 3.0F), ModelTransform.rotation(1.5707964F, 0.0F, 3.1415927F));
        return TexturedModelData.of(modelData, 64, 64);
    }

    public static TexturedModelData getFootTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        modelPartData.addChild("main", ModelPartBuilder.create().uv(0, 22).cuboid(0.0F, 0.0F, 0.0F, 16.0F, 16.0F, 6.0F), ModelTransform.NONE);
        modelPartData.addChild(EntityModelPartNames.LEFT_LEG, ModelPartBuilder.create().uv(50, 0).cuboid(0.0F, 6.0F, -16.0F, 3.0F, 3.0F, 3.0F), ModelTransform.rotation(1.5707964F, 0.0F, 0.0F));
        modelPartData.addChild(EntityModelPartNames.RIGHT_LEG, ModelPartBuilder.create().uv(50, 12).cuboid(-16.0F, 6.0F, -16.0F, 3.0F, 3.0F, 3.0F), ModelTransform.rotation(1.5707964F, 0.0F, 4.712389F));
        return TexturedModelData.of(modelData, 64, 64);
    }

    @Override
    public void render(LlamaWoolBedBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        Identifier identifier = IdBuilder.of("textures/block/llama_wool_bed.png");
        World world = entity.getWorld();
        if (world != null) {
            BlockState blockState = entity.getCachedState();
            DoubleBlockProperties.PropertySource<? extends BedBlockEntity> propertySource = DoubleBlockProperties.toPropertySource(BlockEntityType.BED, BedBlock::getBedPart, BedBlock::getOppositePartDirection, ChestBlock.FACING, blockState, world, entity.getPos(), (worldAccess, blockPos) -> false);
            int k = propertySource.apply(new LightmapCoordinatesRetriever<>()).get(light);
            this.renderPart(matrices, vertexConsumers, blockState.get(BedBlock.PART) == BedPart.HEAD ? this.bedHead : this.bedFoot,
                    blockState.get(BedBlock.FACING), identifier, k, overlay, false);
        } else {
            this.renderPart(matrices, vertexConsumers, this.bedHead, Direction.SOUTH, identifier, light, overlay, false);
            this.renderPart(matrices, vertexConsumers, this.bedFoot, Direction.SOUTH, identifier, light, overlay, true);
        }
    }

    public void renderPart(MatrixStack matrices, VertexConsumerProvider vertexConsumers, ModelPart part,
                           Direction direction, Identifier texture, int light, int overlay, boolean isFoot) {
        matrices.push();
        matrices.translate(0.0D, 0.5625D, isFoot ? -1.0D : 0.0D);
        matrices.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(90.0F));
        matrices.translate(0.5D, 0.5D, 0.5D);
        matrices.multiply(Vec3f.POSITIVE_Z.getDegreesQuaternion(180.0F + direction.asRotation()));
        matrices.translate(-0.5D, -0.5D, -0.5D);
        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(RenderLayer.getEntitySolid(texture));
        part.render(matrices, vertexConsumer, light, overlay);
        matrices.pop();
    }

}
