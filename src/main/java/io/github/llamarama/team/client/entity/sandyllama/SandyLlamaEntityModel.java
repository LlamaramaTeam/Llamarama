package io.github.llamarama.team.client.entity.sandyllama;

import io.github.llamarama.team.common.entity.sandyllama.SandyLlamaEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelPartNames;
import net.minecraft.client.util.math.MatrixStack;
import org.jetbrains.annotations.NotNull;

import java.util.stream.Stream;

import static net.minecraft.util.math.MathHelper.cos;
import static net.minecraft.util.math.MathHelper.sin;

@Environment(EnvType.CLIENT)
public class SandyLlamaEntityModel extends EntityModel<SandyLlamaEntity> {

    private static final float DEGREE = (float) Math.PI / 180;

    private final ModelPart head;
    private final ModelPart body;
    private final ModelPart hindRightLeg;
    private final ModelPart hindLeftLeg;
    private final ModelPart fronLeftLeg;
    private final ModelPart fronRightLeg;
    private final ModelPart[] chests;

    public SandyLlamaEntityModel(ModelPart root) {
        this.head = root.getChild(EntityModelPartNames.HEAD);
        this.body = root.getChild(EntityModelPartNames.BODY);
        this.hindRightLeg = root.getChild(EntityModelPartNames.RIGHT_HIND_LEG);
        this.hindLeftLeg = root.getChild(EntityModelPartNames.LEFT_HIND_LEG);
        this.fronRightLeg = root.getChild(EntityModelPartNames.RIGHT_FRONT_LEG);
        this.fronLeftLeg = root.getChild(EntityModelPartNames.LEFT_FRONT_LEG);
        this.chests = new ModelPart[]{
            this.body.getChild(EntityModelPartNames.LEFT_CHEST),
            this.body.getChild(EntityModelPartNames.RIGHT_CHEST)
        };
    }

    public static @NotNull TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();

        ModelPartData head = modelPartData.addChild(EntityModelPartNames.HEAD, ModelPartBuilder.create()
            .uv(0, 0).cuboid(-2.0F, -14.0F, -10.0F, 4.0F, 4.0F, 9.0F, new Dilation(0.0F))
            .uv(0, 14).cuboid(-4.0F, -16.0F, -6.0F, 8.0F, 18.0F, 6.0F, new Dilation(0.0F))
            .uv(17, 0).cuboid(1.0F, -19.0F, -4.0F, 3.0F, 3.0F, 2.0F, new Dilation(0.0F))
            .uv(17, 0).cuboid(-4.0F, -19.0F, -4.0F, 3.0F, 3.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 7.0F, -6.0F));

        head.addChild("bucket", ModelPartBuilder.create()
            .uv(0, 47).cuboid(-5.0F, -23.0F, -13.0F, 10.0F, 9.0F, 8.0F, new Dilation(0.0F))
            .uv(36, 53).cuboid(-5.5F, -23.5F, -13.5F, 11.0F, 2.0F, 9.0F, new Dilation(0.1F)), ModelTransform.pivot(0.0F, 17.0F, 6.0F));

        ModelPartData body = modelPartData.addChild(EntityModelPartNames.BODY, ModelPartBuilder.create()
            .uv(29, 0).cuboid(-6.0F, -10.0F, -7.0F, 12.0F, 18.0F, 10.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 5.0F, 2.0F, 1.5708F, 0.0F, 0.0F));

        body.addChild(EntityModelPartNames.LEFT_CHEST, ModelPartBuilder.create()
            .uv(45, 41).cuboid(-5.5F, -4.0F, 6.0F, 8.0F, 8.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, -2.0F, -1.5708F, 0.0F, -1.5708F));

        body.addChild(EntityModelPartNames.RIGHT_CHEST, ModelPartBuilder.create()
            .uv(45, 28).cuboid(-5.5F, -4.0F, -9.0F, 8.0F, 8.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, -2.0F, -1.5708F, 0.0F, -1.5708F));

        ModelPartData leftGrass = body.addChild("left_grass",
            ModelPartBuilder.create(),
            ModelTransform.pivot(6.0F, -1.0F, -6.0F)
        );

        leftGrass.addChild("frond_r1", ModelPartBuilder.create()
                .uv(95, 56).cuboid(0.0F, 0.0F, -4.0F, 10.0F, 0.0F, 8.0F, new Dilation(0.0F))
                .uv(95, 56).cuboid(0.0F, 0.0F, -13.0F, 10.0F, 0.0F, 8.0F, new Dilation(0.0F)),
            ModelTransform.of(-0.5F, 5.0F, 0.0F, -1.5708F, 0.829F, 0.0F)
        );

        leftGrass.addChild("frond_r2", ModelPartBuilder.create()
                .uv(95, 56).cuboid(0.0F, 0.0F, -13.0F, 10.0F, 0.0F, 8.0F, new Dilation(0.0F))
                .uv(95, 56).cuboid(0.0F, 0.0F, -4.0F, 10.0F, 0.0F, 8.0F, new Dilation(0.0F)),
            ModelTransform.of(-0.5F, 5.0F, 0.0F, -1.5708F, 0.3927F, 0.0F)
        );

        ModelPartData rightGrass = body.addChild("right_grass",
            ModelPartBuilder.create(),
            ModelTransform.pivot(-6.0F, -1.0F, -7.0F)
        );

        rightGrass.addChild("frond_r3", ModelPartBuilder.create()
                .uv(95, 56).cuboid(0.0F, 0.0F, -13.0F, 10.0F, 0.0F, 8.0F, new Dilation(0.0F))
                .uv(95, 56).cuboid(0.0F, 0.0F, -4.0F, 10.0F, 0.0F, 8.0F, new Dilation(0.0F)),
            ModelTransform.of(0.5F, -4.0F, 1.0F, -1.5708F, 0.3927F, -3.1416F)
        );

        rightGrass.addChild("frond_r4", ModelPartBuilder.create()
                .uv(95, 56).cuboid(0.0F, 0.0F, -4.0F, 10.0F, 0.0F, 8.0F, new Dilation(0.0F))
                .uv(95, 56).cuboid(0.0F, 0.0F, -13.0F, 10.0F, 0.0F, 8.0F, new Dilation(0.0F)),
            ModelTransform.of(0.5F, -4.0F, 1.0F, -1.5708F, 0.829F, -3.1416F)
        );

        modelPartData.addChild(EntityModelPartNames.RIGHT_HIND_LEG, ModelPartBuilder.create()
                .uv(29, 29).cuboid(-9.0F, 0.0F, -2.0F, 4.0F, 14.0F, 4.0F, new Dilation(0.0F)),
            ModelTransform.pivot(3.5F, 10.0F, 6.0F)
        );

        modelPartData.addChild(EntityModelPartNames.LEFT_HIND_LEG, ModelPartBuilder.create()
                .uv(29, 29).cuboid(5.0F, 0.0F, -2.0F, 4.0F, 14.0F, 4.0F, new Dilation(0.0F)),
            ModelTransform.pivot(-3.5F, 10.0F, 6.0F)
        );

        modelPartData.addChild(EntityModelPartNames.RIGHT_FRONT_LEG, ModelPartBuilder.create()
                .uv(29, 29).cuboid(-9.0F, 0.0F, -2.0F, 4.0F, 14.0F, 4.0F, new Dilation(0.0F)),
            ModelTransform.pivot(3.5F, 10.0F, -5.0F)
        );

        modelPartData.addChild(EntityModelPartNames.LEFT_FRONT_LEG, ModelPartBuilder.create()
                .uv(29, 29).cuboid(5.0F, 0.0F, -2.0F, 4.0F, 14.0F, 4.0F, new Dilation(0.0F)),
            ModelTransform.pivot(-3.5F, 10.0F, -5.0F)
        );

        return TexturedModelData.of(modelData, 128, 64);
    }

    @Override
    public void setAngles(SandyLlamaEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        this.head.pitch = headPitch * DEGREE;
        this.head.yaw = headYaw * DEGREE;
        this.hindRightLeg.pitch = cos(limbAngle * 2 / 3) * limbDistance;
        this.hindLeftLeg.pitch = sin(limbAngle * 2 / 3 - 90 * DEGREE) * limbDistance;
        this.fronRightLeg.pitch = sin(limbAngle * 2 / 3 - 90 * DEGREE) * limbDistance;
        this.fronLeftLeg.pitch = cos(limbAngle * 2 / 3) * limbDistance;

        for (var chest : this.chests) {
            chest.visible = !entity.isBaby() && entity.hasChest();
        }
    }

    @SuppressWarnings("DuplicatedCode")
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
            this.body.render(matrices, vertices, light, overlay, red, green, blue, alpha);
            matrices.pop();
            matrices.push();
            matrices.scale(0.45454544F, 0.41322312F, 0.45454544F);
            matrices.translate(0.0D, 2.0625D, 0.0D);
            Stream.of(this.fronLeftLeg, this.fronRightLeg, this.hindLeftLeg, this.hindRightLeg)
                .forEach(modelPart -> modelPart.render(matrices, vertices, light, overlay, red, green, blue, alpha));
            matrices.pop();
        } else {
            Stream.of(this.head, this.body, this.fronRightLeg, this.fronLeftLeg, this.hindLeftLeg, this.hindRightLeg)
                .forEach((modelPart) -> modelPart.render(matrices, vertices, light, overlay));
        }
    }

}
