// Made with Blockbench 3.8.2
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports

package com.github.llamarama.team.client.entity.caravantrader;

import com.github.llamarama.team.entity.caravantrader.CaravanTraderEntity;
import com.google.common.collect.ImmutableList;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.CompositeEntityModel;
import net.minecraft.client.render.entity.model.ModelWithHead;
import net.minecraft.util.math.MathHelper;

@Environment(EnvType.CLIENT)
public class CaravanTraderModel extends CompositeEntityModel<CaravanTraderEntity> implements ModelWithHead {

    private final ModelPart head;
    private final ModelPart body;
    private final ModelPart leg0;
    private final ModelPart leg1;
    private final ModelPart leftArm;
    private final ModelPart rightArm;

    public CaravanTraderModel() {
        textureWidth = 64;
        textureHeight = 64;
        head = new ModelPart(this);
        head.setPivot(0.0F, 0.0F, 0.0F);
        head.setTextureOffset(24, 0).addCuboid(-1.0F, -4.0F, -6.0F, 2.0F, 4.0F, 2.0F, 0.0F, true);
        head.setTextureOffset(24, 3).addCuboid(-1.0F, -1.2749F, -6.3587F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        head.setTextureOffset(0, 0).addCuboid(-4.0F, -10.0F, -4.0F, 8.0F, 10.0F, 8.0F, 0.0F, true);

        body = new ModelPart(this);
        body.setPivot(0.0F, 9.0F, 0.0F);
        body.setTextureOffset(16, 20).addCuboid(-4.0F, -9.0F, -3.0F, 8.0F, 12.0F, 6.0F, 0.0F, true);
        body.setTextureOffset(0, 38).addCuboid(-4.0F, -9.0F, -3.0F, 8.0F, 18.0F, 6.0F, 0.5F, true);

        leg0 = new ModelPart(this);
        leg0.setPivot(2.0F, 12.0F, 0.0F);
        leg0.setTextureOffset(0, 22).addCuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, true);

        leg1 = new ModelPart(this);
        leg1.setPivot(-2.0F, 12.0F, 0.0F);
        leg1.setTextureOffset(0, 22).addCuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, true);

        rightArm = new ModelPart(this);
        rightArm.setPivot(4.0F, 4.0F, -5.0F);

        ModelPart arms_r1 = new ModelPart(this);
        arms_r1.setPivot(-2.0F, 1.7071F, 1.5355F);
        rightArm.addChild(arms_r1);
        setRotationAngle(arms_r1, -0.7854F, 0.0F, -3.1416F);
        arms_r1.setTextureOffset(40, 38).addCuboid(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, 0.0F, true);

        ModelPart arms_r2 = new ModelPart(this);
        arms_r2.setPivot(-9.0F, -4.0F, 3.0F);
        rightArm.addChild(arms_r2);
        setRotationAngle(arms_r2, -0.7854F, 0.0F, 0.0F);
        arms_r2.setTextureOffset(44, 22).addCuboid(-3.0F, -1.0F, 1.0F, 4.0F, 8.0F, 4.0F, 0.0F, true);

        leftArm = new ModelPart(this);
        leftArm.setPivot(4.0F, 4.0F, -5.0F);

        ModelPart arms_r3 = new ModelPart(this);
        arms_r3.setPivot(3.0F, -4.0F, 3.0F);
        leftArm.addChild(arms_r3);
        setRotationAngle(arms_r3, -0.7854F, 0.0F, 0.0F);
        arms_r3.setTextureOffset(44, 22).addCuboid(-3.0F, -1.0F, 1.0F, 4.0F, 8.0F, 4.0F, 0.0F, true);

        ModelPart arms_r4 = new ModelPart(this);
        arms_r4.setPivot(-4.0F, 1.0F, -2.0F);
        leftArm.addChild(arms_r4);
        setRotationAngle(arms_r4, 0.7854F, 0.0F, 0.0F);
        arms_r4.setTextureOffset(40, 38).addCuboid(-4.0F, 1.0F, 0.0F, 4.0F, 4.0F, 4.0F, 0.0F, true);
    }

    @Override
    public void setAngles(CaravanTraderEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        boolean hasRoll = false;
        if (entity != null) {
            hasRoll = entity.getHeadRollingTimeLeft() > 0;
        }

        this.head.yaw = headYaw * 0.017453292F;
        this.head.pitch = headPitch * 0.017453292F;
        if (hasRoll) {
            this.head.roll = 0.3F * MathHelper.sin(0.45F * animationProgress);
            this.head.pitch = 0.4F;
        } else {
            this.head.roll = 0.0F;
        }

        this.leg0.pitch = MathHelper.cos(limbAngle * 0.6662F) * 1.4F * limbDistance * 0.5F;
        this.leg1.pitch = MathHelper.cos(limbAngle * 0.6662F + 3.1415927F) * 1.4F * limbDistance * 0.5F;
        this.leg0.yaw = 0.0F;
        this.leg1.yaw = 0.0F;
    }

    @Override
    public Iterable<ModelPart> getParts() {
        return ImmutableList.of(this.leftArm, this.rightArm, this.body, this.leg0, this.leg1, this.head);
    }

    public void setRotationAngle(ModelPart bone, float x, float y, float z) {
        bone.pitch = x;
        bone.yaw = y;
        bone.roll = z;
    }

    @Override
    public ModelPart getHead() {
        return this.head;
    }

}