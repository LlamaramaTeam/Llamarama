package com.github.llamarama.team.client.entity.woollyllama;

import com.github.llamarama.team.entity.woolyllama.WoollyLlamaEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;

import java.util.stream.Stream;

@Environment(EnvType.CLIENT)
public class WoollyLlamaEntityModel extends EntityModel<WoollyLlamaEntity> {

    private final ModelPart head;
    private final ModelPart chest2;
    private final ModelPart chest1;
    private final ModelPart hair;
    private final ModelPart leg0;
    private final ModelPart leg1;
    private final ModelPart leg2;
    private final ModelPart leg3;
    private final ModelPart torso;

    public WoollyLlamaEntityModel(ModelPart root) {
        this.head = root.getChild("head");
        this.chest2 = root.getChild("chest2");
        this.chest1 = root.getChild("chest1");
        this.hair = root.getChild("hair");
        this.leg0 = root.getChild("leg0");
        this.leg1 = root.getChild("leg1");
        this.leg2 = root.getChild("leg2");
        this.leg3 = root.getChild("leg3");
        this.torso = root.getChild("torso");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        modelPartData.addChild("torso", ModelPartBuilder.create().uv(29, 0).cuboid(-6.0F, -8.0F, 12.0F, 12.0F, 18.0F, 10.0F, true), ModelTransform.pivot(0.0F, 24.0F, 0.0F));
        modelPartData.addChild("head", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -14.0F, -10.0F, 4.0F, 4.0F, 9.0F, true).uv(0, 14).cuboid(-4.0F, -16.0F, -6.0F, 8.0F, 18.0F, 6.0F, true).uv(17, 0).cuboid(1.0F, -19.0F, -4.0F, 3.0F, 3.0F, 2.0F, true).uv(17, 0).cuboid(-4.0F, -19.0F, -4.0F, 3.0F, 3.0F, 2.0F, true), ModelTransform.pivot(0.0F, 7.0F, -6.0F));
        modelPartData.addChild("chest1", ModelPartBuilder.create(), ModelTransform.pivot(8.5F, 3.0F, 3.0F));
        modelPartData.addChild("chest2", ModelPartBuilder.create(), ModelTransform.pivot(-5.5F, 3.0F, 3.0F));
        modelPartData.addChild("hair", ModelPartBuilder.create().uv(46, 13).cuboid(-6.0F, 7.0F, -10.0F, 0.0F, 3.0F, 18.0F).uv(47, 13).cuboid(6.0F, 7.0F, -10.0F, 0.0F, 3.0F, 18.0F).uv(47, 31).cuboid(-6.0F, 7.0F, 8.0F, 12.0F, 3.0F, 0.0F).uv(47, 31).cuboid(-6.0F, 7.0F, -10.0F, 12.0F, 3.0F, 0.0F), ModelTransform.pivot(0.0F, 5.0F, 2.0F));
        modelPartData.addChild("leg0", ModelPartBuilder.create().uv(29, 29).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 14.0F, 4.0F, true), ModelTransform.pivot(3.5F, 10.0F, 6.0F));
        modelPartData.addChild("leg1", ModelPartBuilder.create().uv(29, 29).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 14.0F, 4.0F, true), ModelTransform.pivot(-3.5F, 10.0F, 6.0F));
        modelPartData.addChild("leg2", ModelPartBuilder.create().uv(29, 29).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 14.0F, 4.0F, true), ModelTransform.pivot(3.5F, 10.0F, -5.0F));
        modelPartData.addChild("leg3", ModelPartBuilder.create().uv(29, 29).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 14.0F, 4.0F, true), ModelTransform.pivot(-3.5F, 10.0F, -5.0F));
        return TexturedModelData.of(modelData, 128, 64);
    }

    @Override
    public void setAngles(WoollyLlamaEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        this.head.pitch = headPitch * 0.017453292F;
        this.head.yaw = headYaw * 0.017453292F;
        this.torso.pitch = 1.5707964F;
        this.leg0.pitch = MathHelper.cos(limbAngle * 0.6662F) * 1.4F * limbDistance;
        this.leg1.pitch = MathHelper.cos(limbAngle * 0.6662F + 3.1415927F) * 1.4F * limbDistance;
        this.leg2.pitch = MathHelper.cos(limbAngle * 0.6662F + 3.1415927F) * 1.4F * limbDistance;
        this.leg3.pitch = MathHelper.cos(limbAngle * 0.6662F) * 1.4F * limbDistance;
        boolean bl = !entity.isBaby() && entity.hasChest();
        this.chest1.visible = bl;
        this.chest2.visible = bl;

        this.hair.visible = !entity.getSheared();
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
            this.hair.render(matrices, vertices, light, overlay, red, green, blue, alpha);
            this.torso.render(matrices, vertices, light, overlay, red, green, blue, alpha);
            matrices.pop();
            matrices.push();
            matrices.scale(0.45454544F, 0.41322312F, 0.45454544F);
            matrices.translate(0.0D, 2.0625D, 0.0D);
            Stream.of(this.leg0, this.leg1, this.leg2, this.leg3, this.chest1, this.chest2).forEach(modelPart -> modelPart.render(matrices, vertices, light, overlay, red, green, blue, alpha));
            matrices.pop();
        } else {
            Stream.of(this.leg0, this.leg1, this.leg2, this.leg3, this.head, this.chest1, this.chest2, this.torso, this.hair).forEach((modelPart) -> modelPart.render(matrices, vertices, light, overlay));
        }
    }

    private void setRotationAngle(ModelPart bone, float x, float y) {
        bone.pitch = x;
        bone.yaw = y;
        bone.roll = (float) 0.0;
    }

}
