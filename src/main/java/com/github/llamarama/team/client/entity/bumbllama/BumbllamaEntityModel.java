// Made with Blockbench 3.7.5
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports

package com.github.llamarama.team.client.entity.bumbllama;

import com.github.llamarama.team.entity.bumbllama.BumbllamaEntity;
import com.google.common.collect.ImmutableList;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;

import java.util.stream.Stream;

@Environment(EnvType.CLIENT)
public class BumbllamaEntityModel<T extends BumbllamaEntity> extends EntityModel<T> {

    private final ModelPart head;
    private final ModelPart chest1;
    private final ModelPart chest2;
    private final ModelPart body;
    private final ModelPart body_r1;
    private final ModelPart leg0;
    private final ModelPart leg1;
    private final ModelPart leg2;
    private final ModelPart leg3;
    private final ModelPart flowers1;
    private final ModelPart cube_r1;
    private final ModelPart cube_r2;
    private final ModelPart cube_r3;
    private final ModelPart cube_r4;
    private final ModelPart flowers2;
    private final ModelPart cube_r5;
    private final ModelPart cube_r6;
    private final ModelPart cube_r7;
    private final ModelPart hive;
    private final ModelPart bumblellama;
    private final ModelPart cube_r8;
    private final ModelPart cube_r9;
    private final ModelPart cube_r10;

    public BumbllamaEntityModel() {
        textureWidth = 128;
        textureHeight = 64;
        head = new ModelPart(this);
        head.setPivot(0.0F, 7.0F, -6.0F);
        head.setTextureOffset(0, 0).addCuboid(-2.0F, -14.0F, -10.0F, 4.0F, 4.0F, 9.0F, 0.0F, true);
        head.setTextureOffset(0, 14).addCuboid(-4.0F, -16.0F, -6.0F, 8.0F, 18.0F, 6.0F, 0.0F, true);
        head.setTextureOffset(17, 0).addCuboid(1.0F, -19.0F, -4.0F, 3.0F, 3.0F, 2.0F, 0.0F, true);
        head.setTextureOffset(17, 0).addCuboid(-4.0F, -19.0F, -4.0F, 3.0F, 3.0F, 2.0F, 0.0F, true);

        chest1 = new ModelPart(this);
        chest1.setPivot(8.5F, 3.0F, 3.0F);
        this.setRotationAngle(chest1, 0.0F, -1.5708F, 0.0F);
        chest1.setTextureOffset(45, 28).addCuboid(-5.0F, 0.0F, 0.0F, 8.0F, 8.0F, 3.0F, 0.0F, true);

        chest2 = new ModelPart(this);
        chest2.setPivot(-5.5F, 3.0F, 3.0F);
        setRotationAngle(chest2, 0.0F, -1.5708F, 0.0F);
        chest2.setTextureOffset(45, 41).addCuboid(-5.0F, 0.0F, 0.0F, 8.0F, 8.0F, 3.0F, 0.0F, true);

        body = new ModelPart(this);
        body.setPivot(0.0F, 5.0F, 2.0F);

        body_r1 = new ModelPart(this);
        body_r1.setPivot(0.0F, 19.0F, -1.0F);
        body.addChild(body_r1);
        setRotationAngle(body_r1, -1.5708F, 0.0F, -3.1416F);
        body_r1.setTextureOffset(29, 0).addCuboid(-6.0F, -8.0F, 12.0F, 12.0F, 18.0F, 10.0F, 0.0F, true);

        leg0 = new ModelPart(this);
        leg0.setPivot(3.5F, 10.0F, 6.0F);
        leg0.setTextureOffset(29, 29).addCuboid(-2.0F, 0.0F, -2.0F, 4.0F, 14.0F, 4.0F, 0.0F, true);

        leg1 = new ModelPart(this);
        leg1.setPivot(-3.5F, 10.0F, 6.0F);
        leg1.setTextureOffset(29, 29).addCuboid(-2.0F, 0.0F, -2.0F, 4.0F, 14.0F, 4.0F, 0.0F, true);

        leg2 = new ModelPart(this);
        leg2.setPivot(3.5F, 10.0F, -5.0F);
        leg2.setTextureOffset(29, 29).addCuboid(-2.0F, 0.0F, -2.0F, 4.0F, 14.0F, 4.0F, 0.0F, true);

        leg3 = new ModelPart(this);
        leg3.setPivot(-3.5F, 10.0F, -5.0F);
        leg3.setTextureOffset(29, 29).addCuboid(-2.0F, 0.0F, -2.0F, 4.0F, 14.0F, 4.0F, 0.0F, true);

        flowers1 = new ModelPart(this);
        flowers1.setPivot(0.0F, 6.0F, 0.0F);

        cube_r1 = new ModelPart(this);
        cube_r1.setPivot(2.0F, -3.0F, 6.0F);
        flowers1.addChild(cube_r1);
        setRotationAngle(cube_r1, 0.0F, 2.3126F, -0.0436F);
        cube_r1.setTextureOffset(27, 0).addCuboid(0.0F, -5.0F, -1.0F, 0.0F, 6.0F, 3.0F, 0.0F, false);

        cube_r2 = new ModelPart(this);
        cube_r2.setPivot(7.0F, -3.0F, 4.0F);
        flowers1.addChild(cube_r2);
        setRotationAngle(cube_r2, 0.0F, 2.3126F, 0.3054F);
        cube_r2.setTextureOffset(27, 0).addCuboid(0.0F, -5.0F, -1.0F, 0.0F, 6.0F, 3.0F, 0.0F, false);

        cube_r3 = new ModelPart(this);
        cube_r3.setPivot(-4.0F, -3.0F, 0.0F);
        flowers1.addChild(cube_r3);
        setRotationAngle(cube_r3, 0.0F, -2.618F, -0.3491F);
        cube_r3.setTextureOffset(0, 0).addCuboid(0.0F, -6.0F, -2.0F, 0.0F, 6.0F, 3.0F, 0.0F, false);

        cube_r4 = new ModelPart(this);
        cube_r4.setPivot(7.0F, -3.0F, 0.0F);
        flowers1.addChild(cube_r4);
        setRotationAngle(cube_r4, 0.0F, -2.618F, 0.3054F);
        cube_r4.setTextureOffset(0, 0).addCuboid(0.0F, -6.0F, -2.0F, 0.0F, 6.0F, 3.0F, 0.0F, false);

        flowers2 = new ModelPart(this);
        flowers2.setPivot(0.814F, 7.0F, 3.3041F);
        setRotationAngle(flowers2, 0.0F, -2.9671F, 0.0F);

        cube_r5 = new ModelPart(this);
        cube_r5.setPivot(7.0F, -3.0F, 4.0F);
        flowers2.addChild(cube_r5);
        setRotationAngle(cube_r5, 0.0F, 2.3126F, 0.3054F);
        cube_r5.setTextureOffset(64, 0).addCuboid(5.5204F, -2.4351F, -11.4856F, 0.0F, 6.0F, 3.0F, 0.0F, false);
        cube_r5.setTextureOffset(64, 0).addCuboid(0.0F, -5.0F, -2.0F, 0.0F, 6.0F, 3.0F, 0.0F, false);

        cube_r6 = new ModelPart(this);
        cube_r6.setPivot(7.0F, -3.0F, 0.0F);
        flowers2.addChild(cube_r6);
        setRotationAngle(cube_r6, 0.0F, -2.618F, 0.3054F);
        cube_r6.setTextureOffset(33, 0).addCuboid(2.2705F, -4.8677F, 1.2501F, 0.0F, 6.0F, 3.0F, 0.0F, false);
        cube_r6.setTextureOffset(33, 0).addCuboid(0.0F, -5.0F, -2.0F, 0.0F, .0F, 3.0F, 0.0F, false);

        hive = new ModelPart(this);
        hive.setPivot(4.08F, 4.0F, -4.7822F);
        setRotationAngle(hive, 0.0F, 0.48F, 0.0F);

        cube_r7 = new ModelPart(this);
        cube_r7.setPivot(-5.0F, -2.0F, 0.0F);
        hive.addChild(cube_r7);
        setRotationAngle(cube_r7, 0.0F, -0.2618F, 0.0F);
        cube_r7.setTextureOffset(78, 19).addCuboid(-3.0F, -5.0F, 1.0F, 5.0F, 5.0F, 5.0F, 0.0F, false);

        bumblellama = new ModelPart(this);
        bumblellama.setPivot(-7.0F, 17.0F, -11.0F);
        bumblellama.setTextureOffset(0, 38).addCuboid(6.0F, -28.0F, -1.0F, 3.0F, 2.0F, 3.0F, 0.0F, false);
        bumblellama.setTextureOffset(18, 40).addCuboid(7.0F, -30.0F, -2.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);

        cube_r8 = new ModelPart(this);
        cube_r8.setPivot(10.0F, -27.0F, 0.0F);
        bumblellama.addChild(cube_r8);
        setRotationAngle(cube_r8, 0.0F, 0.0F, -0.2618F);
        cube_r8.setTextureOffset(78, 39).addCuboid(-1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);

        cube_r9 = new ModelPart(this);
        cube_r9.setPivot(6.0F, -27.0F, 0.0F);
        this.bumblellama.addChild(cube_r9);
        setRotationAngle(cube_r9, 0.0F, 0.0F, 0.3927F);
        cube_r9.setTextureOffset(79, 38).addCuboid(-1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);

        cube_r10 = new ModelPart(this);
        cube_r10.setPivot(8.0F, -24.0F, 0.0F);
        this.bumblellama.addChild(cube_r10);
        this.setRotationAngle(cube_r10, 0.0F, 0.0F, 0.9163F);
        cube_r10.setTextureOffset(0, 0).addCuboid(-3.0F, -1.0F, 2.0F, 1.0F, 0.0F, 1.0F, 0.0F, false);
    }

    @Override
    public void setAngles(BumbllamaEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        this.head.pitch = headPitch * 0.017453292F;
        this.head.yaw = headYaw * 0.017453292F;

        this.bumblellama.yaw = headYaw * 0.017453292F;
        this.bumblellama.pitch = headPitch * 0.017453292F;

        this.leg0.pitch = MathHelper.cos(limbAngle * 0.6662F) * 1.4F * limbDistance;
        this.leg1.pitch = MathHelper.cos(limbAngle * 0.6662F + 3.1415927F) * 1.4F * limbDistance;
        this.leg2.pitch = MathHelper.cos(limbAngle * 0.6662F + 3.1415927F) * 1.4F * limbDistance;
        this.leg3.pitch = MathHelper.cos(limbAngle * 0.6662F) * 1.4F * limbDistance;
    }

    @Override
    public void render(MatrixStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        if (!this.child) {
            ImmutableList.of(this.hive, this.leg0, this.leg1, this.leg2, this.leg3, this.body, this.chest1, this.chest2, this.head, this.flowers1, this.flowers2, this.bumblellama).forEach(modelPart -> modelPart.render(matrixStack, buffer, packedLight, packedOverlay));
        } else {
            matrixStack.push();
            matrixStack.translate(0, 0.75, 0);
            matrixStack.scale(0.5f, 0.5f, 0.5f);
            Stream.of(this.leg0, this.leg1, this.leg2, this.leg3).forEach((modelPart) -> modelPart.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha));

            matrixStack.pop();

            matrixStack.push();
            matrixStack.scale(0.75f, 0.63f, 0.75f);
            matrixStack.translate(0, 1.05, 0);

            this.body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);

            matrixStack.pop();
        }
    }

    public void setRotationAngle(ModelPart bone, float x, float y, float z) {
        bone.pitch = x;
        bone.yaw = y;
        bone.roll = z;
    }

}