package com.github.llamarama.team.client.entity.woollyllama;

import com.github.llamarama.team.entity.woolyllama.WoollyLlamaEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;

import java.util.stream.Stream;

/**
 * @author 0xJoeMama mess fixed.
 */
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


    public WoollyLlamaEntityModel() {
        textureWidth = 128;
        textureHeight = 64;

        torso = new ModelPart(this);
        torso.setPivot(0.0F, 24.0F, 0.0F);
        setRotationAngle(torso, 1.5708F, 0.0F, 0.0F);
        torso.setTextureOffset(29, 0).addCuboid(-6.0F, -8.0F, 12.0F, 12.0F, 18.0F, 10.0F, 0.0F, true);

        head = new ModelPart(this);
        head.setPivot(0.0F, 7.0F, -6.0F);
        head.setTextureOffset(0, 0).addCuboid(-2.0F, -14.0F, -10.0F, 4.0F, 4.0F, 9.0F, 0.0F, true);
        head.setTextureOffset(0, 14).addCuboid(-4.0F, -16.0F, -6.0F, 8.0F, 18.0F, 6.0F, 0.0F, true);
        head.setTextureOffset(17, 0).addCuboid(1.0F, -19.0F, -4.0F, 3.0F, 3.0F, 2.0F, 0.0F, true);
        head.setTextureOffset(17, 0).addCuboid(-4.0F, -19.0F, -4.0F, 3.0F, 3.0F, 2.0F, 0.0F, true);

        chest1 = new ModelPart(this);
        chest1.setPivot(8.5F, 3.0F, 3.0F);
        setRotationAngle(chest1, 0.0F, -1.5708F, 0.0F);


        chest2 = new ModelPart(this);
        chest2.setPivot(-5.5F, 3.0F, 3.0F);
        setRotationAngle(chest2, 0.0F, -1.5708F, 0.0F);


        hair = new ModelPart(this);
        hair.setPivot(0.0F, 5.0F, 2.0F);
        hair.setTextureOffset(46, 13).addCuboid(-6.0F, 7.0F, -10.0F, 0.0F, 3.0F, 18.0F, 0.0F, false);
        hair.setTextureOffset(47, 13).addCuboid(6.0F, 7.0F, -10.0F, 0.0F, 3.0F, 18.0F, 0.0F, false);
        hair.setTextureOffset(47, 31).addCuboid(-6.0F, 7.0F, 8.0F, 12.0F, 3.0F, 0.0F, 0.0F, false);
        hair.setTextureOffset(47, 31).addCuboid(-6.0F, 7.0F, -10.0F, 12.0F, 3.0F, 0.0F, 0.0F, false);

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
    }

    private void setRotationAngle(ModelPart bone, float x, float y, float z) {
        bone.pitch = x;
        bone.yaw = y;
        bone.roll = z;
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

}
