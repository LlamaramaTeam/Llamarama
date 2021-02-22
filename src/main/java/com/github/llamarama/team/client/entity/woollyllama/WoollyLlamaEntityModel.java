package com.github.llamarama.team.client.entity.woollyllama;

import com.github.llamarama.team.entity.woolyllama.WoollyLlamaEntity;
import com.google.common.collect.ImmutableList;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;

@Environment(EnvType.CLIENT)
public class WoollyLlamaEntityModel<T extends WoollyLlamaEntity> extends EntityModel<T> {

    private final ModelPart head;
    private final ModelPart torso;
    private final ModelPart rightBackLeg;
    private final ModelPart leftBackLeg;
    private final ModelPart rightFrontLeg;
    private final ModelPart leftFrontLeg;
    private final ModelPart rightChest;
    private final ModelPart leftChest;

    public WoollyLlamaEntityModel(float scale) {
        this.textureWidth = 128;
        this.textureHeight = 64;
        this.head = new ModelPart(this, 0, 0);
        this.head.addCuboid(-2.0F, -14.0F, -10.0F, 4.0F, 4.0F, 9.0F, scale);
        this.head.setPivot(0.0F, 7.0F, -6.0F);
        this.head.setTextureOffset(0, 14).addCuboid(-4.0F, -16.0F, -6.0F, 8.0F, 18.0F, 6.0F, scale);
        this.head.setTextureOffset(17, 0).addCuboid(-4.0F, -19.0F, -4.0F, 3.0F, 3.0F, 2.0F, scale);
        this.head.setTextureOffset(17, 0).addCuboid(1.0F, -19.0F, -4.0F, 3.0F, 3.0F, 2.0F, scale);
        this.torso = new ModelPart(this, 29, 0);
        this.torso.addCuboid(-6.0F, -10.0F, -7.0F, 12.0F, 18.0F, 10.0F, scale);
        this.torso.setPivot(0.0F, 5.0F, 2.0F);
        this.rightChest = new ModelPart(this, 45, 28);
        this.rightChest.addCuboid(-3.0F, 0.0F, 0.0F, 8.0F, 8.0F, 3.0F, scale);
        this.rightChest.setPivot(-8.5F, 3.0F, 3.0F);
        this.rightChest.yaw = 1.5707964F;
        this.leftChest = new ModelPart(this, 45, 41);
        this.leftChest.addCuboid(-3.0F, 0.0F, 0.0F, 8.0F, 8.0F, 3.0F, scale);
        this.leftChest.setPivot(5.5F, 3.0F, 3.0F);
        this.leftChest.yaw = 1.5707964F;
        this.rightBackLeg = new ModelPart(this, 29, 29);
        this.rightBackLeg.addCuboid(-2.0F, 0.0F, -2.0F, 4.0F, 14.0F, 4.0F, scale);
        this.rightBackLeg.setPivot(-2.5F, 10.0F, 6.0F);
        this.leftBackLeg = new ModelPart(this, 29, 29);
        this.leftBackLeg.addCuboid(-2.0F, 0.0F, -2.0F, 4.0F, 14.0F, 4.0F, scale);
        this.leftBackLeg.setPivot(2.5F, 10.0F, 6.0F);
        this.rightFrontLeg = new ModelPart(this, 29, 29);
        this.rightFrontLeg.addCuboid(-2.0F, 0.0F, -2.0F, 4.0F, 14.0F, 4.0F, scale);
        this.rightFrontLeg.setPivot(-2.5F, 10.0F, -4.0F);
        this.leftFrontLeg = new ModelPart(this, 29, 29);
        this.leftFrontLeg.addCuboid(-2.0F, 0.0F, -2.0F, 4.0F, 14.0F, 4.0F, scale);
        this.leftFrontLeg.setPivot(2.5F, 10.0F, -4.0F);
        --this.rightBackLeg.pivotX;
        ++this.leftBackLeg.pivotX;
        ModelPart backLeg = this.rightBackLeg;
        backLeg.pivotZ += 0.0F;
        backLeg = this.leftBackLeg;
        backLeg.pivotZ += 0.0F;
        --this.rightFrontLeg.pivotX;
        ++this.leftFrontLeg.pivotX;
        --this.rightFrontLeg.pivotZ;
        --this.leftFrontLeg.pivotZ;
    }

    @Override
    public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        this.head.pitch = headPitch * 0.017453292F;
        this.head.yaw = headYaw * 0.017453292F;
        this.torso.pitch = 1.5707964F;
        this.rightBackLeg.pitch = MathHelper.cos(limbAngle * 0.6662F) * 1.4F * limbDistance;
        this.leftBackLeg.pitch = MathHelper.cos(limbAngle * 0.6662F + 3.1415927F) * 1.4F * limbDistance;
        this.rightFrontLeg.pitch = MathHelper.cos(limbAngle * 0.6662F + 3.1415927F) * 1.4F * limbDistance;
        this.leftFrontLeg.pitch = MathHelper.cos(limbAngle * 0.6662F) * 1.4F * limbDistance;
        boolean bl = !entity.isBaby() && entity.hasChest();
        this.rightChest.visible = bl;
        this.leftChest.visible = bl;
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
        if (this.child) {
            matrices.push();
            matrices.scale(0.71428573F, 0.64935064F, 0.7936508F);
            matrices.translate(0.0D, 1.3125D, 0.2199999988079071D);
            this.head.render(matrices, vertices, light, overlay, red, green, blue, alpha);
            matrices.pop();
            matrices.push();
            matrices.scale(0.625F, 0.45454544F, 0.45454544F);
            matrices.translate(0.0D, 2.0625D, 0.0D);
            this.torso.render(matrices, vertices, light, overlay, red, green, blue, alpha);
            matrices.pop();
            matrices.push();
            matrices.scale(0.45454544F, 0.41322312F, 0.45454544F);
            matrices.translate(0.0D, 2.0625D, 0.0D);
            ImmutableList.of(this.rightBackLeg, this.leftBackLeg, this.rightFrontLeg, this.leftFrontLeg, this.rightChest, this.leftChest).forEach(modelPart -> modelPart.render(matrices, vertices, light, overlay));
            matrices.pop();
        } else {
            ImmutableList.of(this.head, this.torso, this.rightBackLeg, this.leftBackLeg, this.rightFrontLeg, this.leftFrontLeg, this.rightChest, this.leftChest).forEach((modelPart) -> modelPart.render(matrices, vertices, light, overlay, red, green, blue, alpha));
        }
    }

}
