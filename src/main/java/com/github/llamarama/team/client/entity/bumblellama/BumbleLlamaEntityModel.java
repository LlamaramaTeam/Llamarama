// Made with Blockbench 3.7.5
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports

package com.github.llamarama.team.client.entity.bumblellama;

import com.github.llamarama.team.entity.bumbllama.BumbleLlamaEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelPartNames;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;

import java.util.stream.Stream;

@Environment(EnvType.CLIENT)
public class BumbleLlamaEntityModel<T extends BumbleLlamaEntity> extends EntityModel<T> {

    private final ModelPart head;
    private final ModelPart chest1;
    private final ModelPart chest2;
    private final ModelPart body;
    private final ModelPart leg0;
    private final ModelPart leg1;
    private final ModelPart leg2;
    private final ModelPart leg3;
    private final ModelPart flowers1;
    private final ModelPart flowers2;
    private final ModelPart hive;

    public BumbleLlamaEntityModel(ModelPart root) {
        this.head = root.getChild("head");
        this.chest1 = root.getChild("chest1");
        this.chest2 = root.getChild("chest2");
        this.body = root.getChild("body");
        this.leg0 = root.getChild("leg0");
        this.leg1 = root.getChild("leg1");
        this.leg2 = root.getChild("leg2");
        this.leg3 = root.getChild("leg3");
        this.flowers1 = root.getChild("flowers1");
        this.flowers2 = root.getChild("flowers2");
        this.hive = root.getChild("hive");
    }

    public BumbleLlamaEntityModel() {
        textureWidth = 128;
        textureHeight = 64;
        head = new ModelPart(this);
        head.setPivot(0.0F, 7.0F, -6.0F);
        head.setTextureOffset(0, 0).addCuboid(-2.0F, -14.0F, -10.0F, 4.0F, 4.0F, 9.0F, 0.0F, true);
        head.setTextureOffset(0, 14).addCuboid(-4.0F, -16.0F, -6.0F, 8.0F, 18.0F, 6.0F, 0.0F, true);
        head.setTextureOffset(17, 0).addCuboid(1.0F, -19.0F, -4.0F, 3.0F, 3.0F, 2.0F, 0.0F, true);
        head.setTextureOffset(17, 0).addCuboid(-4.0F, -19.0F, -4.0F, 3.0F, 3.0F, 2.0F, 0.0F, true);

        ModelPart bumblellama = new ModelPart(this);
        bumblellama.setPivot(0.0F, 0.0F, -5.0F);
        head.addChild(bumblellama);
        bumblellama.setTextureOffset(0, 38).addCuboid(-1.0F, -18.0F, -1.0F, 3.0F, 2.0F, 3.0F, 0.0F, false);
        bumblellama.setTextureOffset(18, 40).addCuboid(0.0F, -20.0F, -2.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);

        ModelPart cube_r1 = new ModelPart(this);
        cube_r1.setPivot(3.0F, -17.0F, 0.0F);
        bumblellama.addChild(cube_r1);
        this.setRotationAngle(cube_r1, 0.0F, 0.0F, -0.2618F);
        cube_r1.setTextureOffset(78, 39).addCuboid(-1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);

        ModelPart cube_r2 = new ModelPart(this);
        cube_r2.setPivot(-1.0F, -17.0F, 0.0F);
        bumblellama.addChild(cube_r2);
        this.setRotationAngle(cube_r2, 0.0F, 0.0F, 0.3927F);
        cube_r2.setTextureOffset(79, 38).addCuboid(-1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);

        ModelPart cube_r3 = new ModelPart(this);
        cube_r3.setPivot(1.0F, -14.0F, 0.0F);
        bumblellama.addChild(cube_r3);
        this.setRotationAngle(cube_r3, 0.0F, 0.0F, 0.9163F);
        cube_r3.setTextureOffset(0, 0).addCuboid(-3.0F, -1.0F, 2.0F, 1.0F, 0.0F, 1.0F, 0.0F, false);

        this.chest1 = new ModelPart(this);
        this.chest1.setPivot(8.5F, 3.0F, 3.0F);
        this.setRotationAngle(chest1, 0.0F, -1.5708F, 0.0F);
        this.chest1.setTextureOffset(45, 28).addCuboid(-5.0F, 0.0F, 0.0F, 8.0F, 8.0F, 3.0F, 0.0F, true);

        this.chest2 = new ModelPart(this);
        this.chest2.setPivot(-5.5F, 3.0F, 3.0F);
        this.setRotationAngle(this.chest2, 0.0F, -1.5708F, 0.0F);
        this.chest2.setTextureOffset(45, 41).addCuboid(-5.0F, 0.0F, 0.0F, 8.0F, 8.0F, 3.0F, 0.0F, true);

        this.body = new ModelPart(this);
        this.body.setPivot(0.0F, 5.0F, 2.0F);

        ModelPart body_r1 = new ModelPart(this);
        body_r1.setPivot(0.0F, 19.0F, -1.0F);
        body.addChild(body_r1);
        this.setRotationAngle(body_r1, -1.5708F, 0.0F, -3.1416F);
        body_r1.setTextureOffset(29, 0).addCuboid(-6.0F, -8.0F, 12.0F, 12.0F, 18.0F, 10.0F, 0.0F, true);

        this.leg0 = new ModelPart(this);
        this.leg0.setPivot(3.5F, 10.0F, 6.0F);
        this.leg0.setTextureOffset(29, 29).addCuboid(-2.0F, 0.0F, -2.0F, 4.0F, 14.0F, 4.0F, 0.0F, true);

        this.leg1 = new ModelPart(this);
        this.leg1.setPivot(-3.5F, 10.0F, 6.0F);
        this.leg1.setTextureOffset(29, 29).addCuboid(-2.0F, 0.0F, -2.0F, 4.0F, 14.0F, 4.0F, 0.0F, true);

        this.leg2 = new ModelPart(this);
        this.leg2.setPivot(3.5F, 10.0F, -5.0F);
        this.leg2.setTextureOffset(29, 29).addCuboid(-2.0F, 0.0F, -2.0F, 4.0F, 14.0F, 4.0F, 0.0F, true);

        this.leg3 = new ModelPart(this);
        this.leg3.setPivot(-3.5F, 10.0F, -5.0F);
        this.leg3.setTextureOffset(29, 29).addCuboid(-2.0F, 0.0F, -2.0F, 4.0F, 14.0F, 4.0F, 0.0F, true);

        this.flowers1 = new ModelPart(this);
        this.flowers1.setPivot(0.0F, 6.0F, 0.0F);

        ModelPart cube_r4 = new ModelPart(this);
        cube_r4.setPivot(2.0F, -3.0F, 6.0F);
        this.flowers1.addChild(cube_r4);
        this.setRotationAngle(cube_r4, 0.0F, 2.3126F, -0.0436F);
        cube_r4.setTextureOffset(27, 0).addCuboid(0.0F, -5.0F, -1.0F, 0.0F, 6.0F, 3.0F, 0.0F, false);

        ModelPart cube_r5 = new ModelPart(this);
        cube_r5.setPivot(7.0F, -3.0F, 4.0F);
        this.flowers1.addChild(cube_r5);
        this.setRotationAngle(cube_r5, 0.0F, 2.3126F, 0.3054F);
        cube_r5.setTextureOffset(27, 0).addCuboid(0.0F, -5.0F, -1.0F, 0.0F, 6.0F, 3.0F, 0.0F, false);

        ModelPart cube_r6 = new ModelPart(this);
        cube_r6.setPivot(-4.0F, -3.0F, 0.0F);
        this.flowers1.addChild(cube_r6);
        this.setRotationAngle(cube_r6, 0.0F, -2.618F, -0.3491F);
        cube_r6.setTextureOffset(0, 0).addCuboid(0.0F, -6.0F, -2.0F, 0.0F, 6.0F, 3.0F, 0.0F, false);

        ModelPart cube_r7 = new ModelPart(this);
        cube_r7.setPivot(7.0F, -3.0F, 0.0F);
        this.flowers1.addChild(cube_r7);
        this.setRotationAngle(cube_r7, 0.0F, -2.618F, 0.3054F);
        cube_r7.setTextureOffset(0, 0).addCuboid(0.0F, -6.0F, -2.0F, 0.0F, 6.0F, 3.0F, 0.0F, false);

        this.flowers2 = new ModelPart(this);
        this.flowers2.setPivot(0.814F, 7.0F, 3.3041F);
        this.setRotationAngle(flowers2, 0.0F, -2.9671F, 0.0F);

        ModelPart cube_r8 = new ModelPart(this);
        cube_r8.setPivot(7.0F, -3.0F, 4.0F);
        this.flowers2.addChild(cube_r8);
        this.setRotationAngle(cube_r8, 0.0F, 2.3126F, 0.3054F);
        cube_r8.setTextureOffset(64, 0).addCuboid(5.5204F, -2.4351F, -11.4856F, 0.0F, 6.0F, 3.0F, 0.0F, false);
        cube_r8.setTextureOffset(64, 0).addCuboid(0.0F, -5.0F, -2.0F, 0.0F, 6.0F, 3.0F, 0.0F, false);

        ModelPart cube_r9 = new ModelPart(this);
        cube_r9.setPivot(7.0F, -3.0F, 0.0F);
        this.flowers2.addChild(cube_r9);
        this.setRotationAngle(cube_r9, 0.0F, -2.618F, 0.3054F);
        cube_r9.setTextureOffset(33, 0).addCuboid(2.2705F, -4.8677F, 1.2501F, 0.0F, 6.0F, 3.0F, 0.0F, false);
        cube_r9.setTextureOffset(33, 0).addCuboid(0.0F, -6.0F, -2.0F, 0.0F, 6.0F, 3.0F, 0.0F, false);

        this.hive = new ModelPart(this);
        this.hive.setPivot(4.08F, 4.0F, -4.7822F);
        this.setRotationAngle(hive, 0.0F, 0.48F, 0.0F);

        ModelPart cube_r10 = new ModelPart(this);
        cube_r10.setPivot(-5.0F, -2.0F, 0.0F);
        this.hive.addChild(cube_r10);
        this.setRotationAngle(cube_r10, 0.0F, -0.2618F, 0.0F);
        cube_r10.setTextureOffset(78, 19).addCuboid(-3.0F, -5.0F, 1.0F, 5.0F, 5.0F, 5.0F, 0.0F, false);
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        this.setRotationAngle(cube_r1, 0.0F, 0.0F, -0.2618F);
        bumblellama.addChild(cube_r2);
        this.setRotationAngle(cube_r2, 0.0F, 0.0F, 0.3927F);
        bumblellama.addChild(cube_r3);
        this.setRotationAngle(cube_r3, 0.0F, 0.0F, 0.9163F);
        this.setRotationAngle(chest1, 0.0F, -1.5708F, 0.0F);
        this.setRotationAngle(this.chest2, 0.0F, -1.5708F, 0.0F);
        body.addChild(body_r1);
        this.setRotationAngle(body_r1, -1.5708F, 0.0F, -3.1416F);
        this.flowers1.addChild(cube_r4);
        this.setRotationAngle(cube_r4, 0.0F, 2.3126F, -0.0436F);
        this.flowers1.addChild(cube_r5);
        this.setRotationAngle(cube_r5, 0.0F, 2.3126F, 0.3054F);
        this.flowers1.addChild(cube_r6);
        this.setRotationAngle(cube_r6, 0.0F, -2.618F, -0.3491F);
        this.flowers1.addChild(cube_r7);
        this.setRotationAngle(cube_r7, 0.0F, -2.618F, 0.3054F);
        this.setRotationAngle(flowers2, 0.0F, -2.9671F, 0.0F);
        this.flowers2.addChild(cube_r8);
        this.setRotationAngle(cube_r8, 0.0F, 2.3126F, 0.3054F);
        this.flowers2.addChild(cube_r9);
        this.setRotationAngle(cube_r9, 0.0F, -2.618F, 0.3054F);
        this.setRotationAngle(hive, 0.0F, 0.48F, 0.0F);
        this.hive.addChild(cube_r10);
        this.setRotationAngle(cube_r10, 0.0F, -0.2618F, 0.0F);
        modelPartData.addChild(EntityModelPartNames.HEAD,
                ModelPartBuilder.create()
                        .uv(0, 0)
                        .cuboid(-2.0F, -14.0F, -10.0F, 4.0F, 4.0F, 9.0F, true)
                        .uv(0, 14)
                        .cuboid(-4.0F, -16.0F, -6.0F, 8.0F, 18.0F, 6.0F, true)
                        .uv(17, 0)
                        .cuboid(1.0F, -19.0F, -4.0F, 3.0F, 3.0F, 2.0F, true)
                        .uv(17, 0)
                        .cuboid(-4.0F, -19.0F, -4.0F, 3.0F, 3.0F, 2.0F, true),
                ModelTransform.pivot(0.0F, 7.0F, -6.0F));
        modelPartData.getChild(EntityModelPartNames.HEAD).addChild("bumblellama",
                ModelPartBuilder.create()
                        .uv(0, 38)
                        .cuboid(-1.0F, -18.0F, -1.0F, 3.0F, 2.0F, 3.0F)
                        .uv(18, 40)
                        .cuboid(0.0F, -20.0F, -2.0F, 1.0F, 3.0F, 1.0F),
                ModelTransform.pivot(0.0F, 0.0F, -5.0F));
        modelPartData.getChild(EntityModelPartNames.HEAD).getChild("bumblellama").addChild("cube_r1",
                ModelPartBuilder.create()
                        .uv(78, 39)
                        .cuboid(-1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F),
                ModelTransform.pivot(3.0F, -17.0F, 0.0F));
        modelPartData.addChild("cube_r2",
                ModelPartBuilder.create()
                        .uv(79, 38)
                        .cuboid(-1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F),
                ModelTransform.pivot(-1.0F, -17.0F, 0.0F));
        modelPartData.addChild("cube_r3", ModelPartBuilder.create().uv(0, 0).cuboid(-3.0F, -1.0F, 2.0F, 1.0F, 0.0F, 1.0F), ModelTransform.pivot(1.0F, -14.0F, 0.0F));
        modelPartData.addChild("chest1", ModelPartBuilder.create().uv(45, 28).cuboid(-5.0F, 0.0F, 0.0F, 8.0F, 8.0F, 3.0F, true), ModelTransform.pivot(8.5F, 3.0F, 3.0F));
        modelPartData.addChild("chest2", ModelPartBuilder.create().uv(45, 41).cuboid(-5.0F, 0.0F, 0.0F, 8.0F, 8.0F, 3.0F, true), ModelTransform.pivot(-5.5F, 3.0F, 3.0F));
        modelPartData.addChild("body", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 5.0F, 2.0F));
        modelPartData.addChild("body_r1", ModelPartBuilder.create().uv(29, 0).cuboid(-6.0F, -8.0F, 12.0F, 12.0F, 18.0F, 10.0F, true), ModelTransform.pivot(0.0F, 19.0F, -1.0F));
        modelPartData.addChild("leg0", ModelPartBuilder.create().uv(29, 29).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 14.0F, 4.0F, true), ModelTransform.pivot(3.5F, 10.0F, 6.0F));
        modelPartData.addChild("leg1", ModelPartBuilder.create().uv(29, 29).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 14.0F, 4.0F, true), ModelTransform.pivot(-3.5F, 10.0F, 6.0F));
        modelPartData.addChild("leg2", ModelPartBuilder.create().uv(29, 29).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 14.0F, 4.0F, true), ModelTransform.pivot(3.5F, 10.0F, -5.0F));
        modelPartData.addChild("leg3", ModelPartBuilder.create().uv(29, 29).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 14.0F, 4.0F, true), ModelTransform.pivot(-3.5F, 10.0F, -5.0F));
        modelPartData.addChild("flowers1", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 6.0F, 0.0F));
        modelPartData.addChild("cube_r4", ModelPartBuilder.create().uv(27, 0).cuboid(0.0F, -5.0F, -1.0F, 0.0F, 6.0F, 3.0F), ModelTransform.pivot(2.0F, -3.0F, 6.0F));
        modelPartData.addChild("cube_r5", ModelPartBuilder.create().uv(27, 0).cuboid(0.0F, -5.0F, -1.0F, 0.0F, 6.0F, 3.0F), ModelTransform.pivot(7.0F, -3.0F, 4.0F));
        modelPartData.addChild("cube_r6", ModelPartBuilder.create().uv(0, 0).cuboid(0.0F, -6.0F, -2.0F, 0.0F, 6.0F, 3.0F), ModelTransform.pivot(-4.0F, -3.0F, 0.0F));
        modelPartData.addChild("cube_r7", ModelPartBuilder.create().uv(0, 0).cuboid(0.0F, -6.0F, -2.0F, 0.0F, 6.0F, 3.0F), ModelTransform.pivot(7.0F, -3.0F, 0.0F));
        modelPartData.addChild("flowers2", ModelPartBuilder.create(), ModelTransform.pivot(0.814F, 7.0F, 3.3041F));
        modelPartData.addChild("cube_r8", ModelPartBuilder.create().uv(64, 0).cuboid(5.5204F, -2.4351F, -11.4856F, 0.0F, 6.0F, 3.0F).uv(64, 0).cuboid(0.0F, -5.0F, -2.0F, 0.0F, 6.0F, 3.0F), ModelTransform.pivot(7.0F, -3.0F, 4.0F));
        modelPartData.addChild("cube_r9", ModelPartBuilder.create().uv(33, 0).cuboid(2.2705F, -4.8677F, 1.2501F, 0.0F, 6.0F, 3.0F).uv(33, 0).cuboid(0.0F, -6.0F, -2.0F, 0.0F, 6.0F, 3.0F), ModelTransform.pivot(7.0F, -3.0F, 0.0F));
        modelPartData.addChild("hive", ModelPartBuilder.create(), ModelTransform.pivot(4.08F, 4.0F, -4.7822F));
        modelPartData.addChild("cube_r10", ModelPartBuilder.create().uv(78, 19).cuboid(-3.0F, -5.0F, 1.0F, 5.0F, 5.0F, 5.0F), ModelTransform.pivot(-5.0F, -2.0F, 0.0F));
        return TexturedModelData.of(modelData, 128, 64);
    }

    @Override
    public void setAngles(BumbleLlamaEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        this.head.pitch = headPitch * 0.017453292F;
        this.head.yaw = headYaw * 0.017453292F;

        this.leg0.pitch = MathHelper.cos(limbAngle * 0.6662F) * 1.4F * limbDistance;
        this.leg1.pitch = MathHelper.cos(limbAngle * 0.6662F + 3.1415927F) * 1.4F * limbDistance;
        this.leg2.pitch = MathHelper.cos(limbAngle * 0.6662F + 3.1415927F) * 1.4F * limbDistance;
        this.leg3.pitch = MathHelper.cos(limbAngle * 0.6662F) * 1.4F * limbDistance;
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        if (!this.child) {
            Stream.of(this.hive, this.leg0, this.leg1, this.leg2, this.leg3, this.body, this.chest1, this.chest2, this.head, this.flowers1, this.flowers2).forEach(modelPart -> modelPart.render(matrices, vertices, packedLight, packedOverlay));
        } else {
            // Leg Rendering

            matrices.push();
            matrices.scale(0.4f, 0.4f, 0.4f);
            matrices.translate(0, 2.3f, 0);
            Stream.of(this.leg0, this.leg1, this.leg2, this.leg3).forEach((modelPart) -> modelPart.render(matrices, vertices, packedLight, packedOverlay, red, green, blue, alpha));

            matrices.pop();

            // Body Rendering

            matrices.push();
            matrices.scale(0.576f, 0.4f, 0.4f);
            matrices.translate(0, 2.5f, 0);

            this.body.render(matrices, vertices, packedLight, packedOverlay, red, green, blue, alpha);

            matrices.pop();

            // Head Rendering

            matrices.push();

            matrices.scale(0.7f, 0.6f, 0.6f);
            matrices.translate(0d, 1.497d, 0.2d);

            this.head.render(matrices, vertices, packedLight, packedOverlay, red, green, blue, alpha);

            matrices.pop();
        }
    }

    public void setRotationAngle(ModelPart bone, float x, float y, float z) {
        bone.pitch = x;
        bone.yaw = y;
        bone.roll = z;
    }

}