package io.github.llamarama.team.client.entity.mossyllama;

import io.github.llamarama.team.entity.mossyllama.MossyLlamaEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelPartNames;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Shearable;
import net.minecraft.util.math.MathHelper;

import java.util.stream.Stream;

public class MossyLlamaEntityModel<T extends MossyLlamaEntity> extends EntityModel<T> {

    private static final float DEGREE = (float) (Math.PI / 180f);

    private final ModelPart head;
    private final ModelPart hat;
    private final ModelPart body;
    private final ModelPart leg1;
    private final ModelPart leg2;
    private final ModelPart leg3;
    private final ModelPart leg4;
    private final ModelPart saplings;
    private final ModelPart[] ears;

    public MossyLlamaEntityModel(ModelPart root) {
        ModelPart main = root.getChild("main");

        this.head = main.getChild(EntityModelPartNames.HEAD);
        this.hat = this.head.getChild(EntityModelPartNames.HAT);
        this.body = main.getChild(EntityModelPartNames.BODY);
        this.leg1 = main.getChild(EntityModelPartNames.RIGHT_HIND_LEG);
        this.leg2 = main.getChild(EntityModelPartNames.LEFT_FRONT_LEG);
        this.leg3 = main.getChild(EntityModelPartNames.RIGHT_FRONT_LEG);
        this.leg4 = main.getChild(EntityModelPartNames.LEFT_HIND_LEG);
        this.saplings = main.getChild("saplings");
        this.ears = new ModelPart[]{this.head.getChild("left_ear"), this.head.getChild("right_ear")};
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData data = new ModelData();
        ModelPartData root = data.getRoot();

        ModelPartData main = root.addChild(
            "main",
            ModelPartBuilder.create(),
            ModelTransform.of(0.0F, 24.0F, 0.0F, 0.0F, 0.0F, 0.0F)
        );

        ModelPartData head = main.addChild(
            "head",
            ModelPartBuilder.create()
                .uv(0, 0)
                .mirrored(false)
                .cuboid(-2.0F, -14.0F, -10.0F, 4.0F, 4.0F, 9.0F, new Dilation(0.0F))
                .uv(0, 14)
                .mirrored(false)
                .cuboid(-4.0F, -16.0F, -6.0F, 8.0F, 18.0F, 6.0F, new Dilation(0.0F))
                .uv(0, 39)
                .mirrored(false)
                .cuboid(-2.0F, -14.0F, -10.0F, 4.0F, 6.0F, 0.0F, new Dilation(0.0F)),
            ModelTransform.of(0.0F, -17.0F, -7.0F, 0.0F, 0.0F, 0.0F)
        );

        head.addChild(
            "cube_r1",
            ModelPartBuilder.create()
                .uv(64, 0)
                .mirrored(false)
                .cuboid(-3.0F, 1.0F, -1.0F, 0.0F, 3.0F, 6.0F, new Dilation(0.0F)),
            ModelTransform.of(0.0F, 0.0F, 0.0F, -3.1416F, -0.7854F, 3.1416F)
        );

        head.addChild(
            "cube_r2",
            ModelPartBuilder.create()
                .uv(64, 0)
                .mirrored(false)
                .cuboid(-3.0F, 1.0F, -5.0F, 0.0F, 3.0F, 6.0F, new Dilation(0.0F)),
            ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F)
        );

        ModelPartData left_ear = head.addChild(
            "left_ear",
            ModelPartBuilder.create(),
            ModelTransform.of(1.5F, -15.0F, -3.0F, 0.0F, 0.0F, 0.0F)
        );

        left_ear.addChild(
            "head_r1",
            ModelPartBuilder.create()
                .uv(17, 0)
                .mirrored(false)
                .cuboid(-0.5F, -5.0F, -1.0F, 3.0F, 4.0F, 2.0F, new Dilation(0.0F)),
            ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.7854F)
        );

        ModelPartData right_ear = head.addChild(
            "right_ear",
            ModelPartBuilder.create(),
            ModelTransform.of(-1.5F, -15.0F, -3.0F, 0.0F, 0.0F, 0.0F)
        );

        right_ear.addChild(
            "head_r2",
            ModelPartBuilder.create()
                .uv(17, 0)
                .mirrored(false)
                .cuboid(-2.5F, -5.0F, -1.0F, 3.0F, 4.0F, 2.0F, new Dilation(0.0F)),
            ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.7854F)
        );

        ModelPartData hat = head.addChild(
            "hat",
            ModelPartBuilder.create()
                .uv(0, 48)
                .mirrored(false)
                .cuboid(-5.0F, -34.0F, -42.0F, 10.0F, 6.0F, 8.0F, new Dilation(-0.5F))
                .uv(38, 101)
                .mirrored(false)
                .cuboid(-1.0F, -34.0F, -41.0F, 8.0F, 0.0F, 8.0F, new Dilation(0.0F))
                .uv(38, 101)
                .mirrored(false)
                .cuboid(-8.0F, -34.0F, -41.0F, 8.0F, 0.0F, 8.0F, new Dilation(0.0F)),
            ModelTransform.of(0.0F, 17.0F, 35.0F, 0.0F, 0.0F, 0.0F)
        );

        hat.addChild(
            "cube_r3",
            ModelPartBuilder.create()
                .uv(57, 111)
                .mirrored(false)
                .cuboid(-4.0F, -8.0F, 1.0F, 8.0F, 8.0F, 0.0F, new Dilation(0.0F)),
            ModelTransform.of(0.0F, -34.0F, -38.0F, 0.0F, -1.5708F, -0.7854F)
        );

        hat.addChild(
            "cube_r4",
            ModelPartBuilder.create()
                .uv(57, 111)
                .mirrored(false)
                .cuboid(-4.0F, -7.4142F, 0.4142F, 8.0F, 8.0F, 0.0F, new Dilation(0.0F)),
            ModelTransform.of(0.0F, -34.0F, -38.0F, -0.7854F, 0.0F, 0.0F)
        );

        hat.addChild(
            "cube_r5",
            ModelPartBuilder.create()
                .uv(57, 111)
                .mirrored(false)
                .cuboid(-4.0F, -7.5858F, -0.5858F, 8.0F, 8.0F, 0.0F, new Dilation(0.0F)),
            ModelTransform.of(0.0F, -34.0F, -38.0F, 0.7854F, 0.0F, 0.0F)
        );

        hat.addChild(
            "cube_r6",
            ModelPartBuilder.create()
                .uv(57, 111)
                .mirrored(false)
                .cuboid(-4.0F, -8.0F, -1.0F, 8.0F, 8.0F, 0.0F, new Dilation(0.0F)),
            ModelTransform.of(0.0F, -34.0F, -38.0F, 0.0F, -1.5708F, 0.7854F)
        );

        ModelPartData saplings = main.addChild(
            "saplings",
            ModelPartBuilder.create(),
            ModelTransform.of(-4.0F, -15.0F, 6.0F, 0.0F, 0.0F, 0.0F)
        );

        saplings.addChild(
            "cube_r7",
            ModelPartBuilder.create()
                .uv(26, 84)
                .mirrored(false)
                .cuboid(1.0F, -16.0F, -10.0F, 0.0F, 11.0F, 10.0F, new Dilation(0.0F)),
            ModelTransform.of(0.0F, 0.0F, 0.0F, -0.1267F, -0.7514F, -0.2583F)
        );

        saplings.addChild(
            "cube_r8",
            ModelPartBuilder.create()
                .uv(94, 74)
                .mirrored(false)
                .cuboid(0.0F, -16.0F, -7.0F, 8.0F, 11.0F, 8.0F, new Dilation(0.0F)),
            ModelTransform.of(0.0F, 0.0F, 0.0F, -0.0925F, 0.0298F, -0.3478F)
        );

        saplings.addChild(
            "cube_r9",
            ModelPartBuilder.create()
                .uv(94, 74)
                .mirrored(false)
                .cuboid(0.0F, -16.0F, -7.0F, 8.0F, 11.0F, 8.0F, new Dilation(0.0F)),
            ModelTransform.of(4.0F, -3.0F, -2.0F, 0.1241F, -0.0651F, 0.1211F)
        );

        saplings.addChild(
            "cube_r10",
            ModelPartBuilder.create()
                .uv(26, 84)
                .mirrored(false)
                .cuboid(-5.0F, -16.0F, -6.0F, 0.0F, 11.0F, 10.0F, new Dilation(0.0F)),
            ModelTransform.of(4.0F, -3.0F, -2.0F, 2.9776F, -0.7131F, -2.9045F)
        );

        saplings.addChild(
            "cube_r11",
            ModelPartBuilder.create()
                .uv(26, 84)
                .mirrored(false)
                .cuboid(1.0F, -16.0F, -10.0F, 0.0F, 11.0F, 10.0F, new Dilation(0.0F)),
            ModelTransform.of(4.0F, -3.0F, -2.0F, 0.1866F, -0.8423F, -0.0107F)
        );

        saplings.addChild(
            "cube_r12",
            ModelPartBuilder.create()
                .uv(26, 84)
                .mirrored(false)
                .cuboid(-5.0F, -16.0F, -6.0F, 0.0F, 11.0F, 10.0F, new Dilation(0.0F)),
            ModelTransform.of(0.0F, 0.0F, 0.0F, -3.0072F, -0.8108F, 2.6988F)
        );

        ModelPartData body = main.addChild(
            "body",
            ModelPartBuilder.create()
                .uv(47, 29)
                .mirrored(false)
                .cuboid(-7.0F, -5.0F, -11.0F, 14.0F, 14.0F, 20.0F, new Dilation(0.25F)),
            ModelTransform.of(0.0F, -17.0F, 1.0F, 0.0F, 0.0F, 0.0F)
        );

        body.addChild(
            "cube_r13",
            ModelPartBuilder.create()
                .uv(57, 30)
                .mirrored(false)
                .cuboid(-2.0F, -3.0F, -8.0F, 0.0F, 15.0F, 19.0F, new Dilation(0.0F)),
            ModelTransform.of(0.0F, 0.0F, 0.0F, -3.1416F, -0.7854F, 3.1416F)
        );

        body.addChild(
            "cube_r14",
            ModelPartBuilder.create()
                .uv(57, 30)
                .mirrored(false)
                .cuboid(-2.0F, -3.0F, -11.0F, 0.0F, 15.0F, 19.0F, new Dilation(0.0F)),
            ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F)
        );

        body.addChild(
            "body_r1",
            ModelPartBuilder.create()
                .uv(29, 0)
                .mirrored(false)
                .cuboid(-6.0F, -11.0F, 14.0F, 12.0F, 18.0F, 10.0F, new Dilation(0.0F)),
            ModelTransform.of(0.0F, 19.0F, 1.0F, 1.5708F, 0.0F, 0.0F)
        );

        ModelPartData neckLeaves = body.addChild(
            "neck_leaves",
            ModelPartBuilder.create(),
            ModelTransform.of(0.0F, 0.0F, -6.0F, 0.0F, 0.0F, 0.0F)
        );

        neckLeaves.addChild(
            "cube_r15",
            ModelPartBuilder.create()
                .uv(10, 76)
                .mirrored(false)
                .cuboid(-7.0F, -5.0F, -9.0F, 15.0F, 0.0F, 15.0F, new Dilation(0.0F)),
            ModelTransform.of(0.0F, 0.0F, 0.0F, 0.262F, 0.0421F, 0.0113F)
        );

        neckLeaves.addChild(
            "cube_r16",
            ModelPartBuilder.create()
                .uv(10, 76)
                .mirrored(false)
                .cuboid(-7.0F, -6.0F, -9.0F, 15.0F, 0.0F, 15.0F, new Dilation(0.0F)),
            ModelTransform.of(0.0F, 0.0F, 0.0F, 0.394F, -0.0806F, -0.0335F)
        );

        neckLeaves.addChild(
            "cube_r17",
            ModelPartBuilder.create()
                .uv(10, 76)
                .mirrored(false)
                .cuboid(-7.0F, -7.0F, -9.0F, 15.0F, 0.0F, 15.0F, new Dilation(0.0F)),
            ModelTransform.of(0.0F, 0.0F, 0.0F, 0.4804F, 0.0387F, 0.0202F)
        );

        main.addChild(
            "right_hind_leg",
            ModelPartBuilder.create()
                .uv(29, 29)
                .mirrored(false)
                .cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 14.0F, 4.0F, new Dilation(0.0F)),
            ModelTransform.of(-3.5F, -14.0F, 5.0F, 0.0F, 0.0F, 0.0F)
        );

        main.addChild(
            "left_front_leg",
            ModelPartBuilder.create()
                .uv(29, 29)
                .mirrored(false)
                .cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 14.0F, 4.0F, new Dilation(0.0F)),
            ModelTransform.of(3.5F, -14.0F, -6.0F, 0.0F, 0.0F, 0.0F)
        );

        main.addChild(
            "right_front_leg",
            ModelPartBuilder.create()
                .uv(29, 29)
                .mirrored(false)
                .cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 14.0F, 4.0F, new Dilation(0.0F)),
            ModelTransform.of(-3.5F, -14.0F, -6.0F, 0.0F, 0.0F, 0.0F)
        );

        main.addChild(
            "left_hind_leg",
            ModelPartBuilder.create()
                .uv(29, 29)
                .mirrored(false)
                .cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 14.0F, 4.0F, new Dilation(0.0F)),
            ModelTransform.of(3.5F, -14.0F, 5.0F, 0.0F, 0.0F, 0.0F)
        );

        return TexturedModelData.of(data, 128, 128);
    }

    @Override
    public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        this.head.pitch = headPitch * DEGREE;
        this.head.yaw = headYaw * DEGREE;
        this.body.pitch = 0;
        this.leg1.pitch = MathHelper.cos(limbAngle * 0.6662F) * 1.4F * limbDistance;
        this.leg4.pitch = MathHelper.cos(limbAngle * 0.6662F + 3.1415927F) * 1.4F * limbDistance;
        this.leg3.pitch = MathHelper.cos(limbAngle * 0.6662F + 3.1415927F) * 1.4F * limbDistance;
        this.leg2.pitch = MathHelper.cos(limbAngle * 0.6662F) * 1.4F * limbDistance;
        boolean shearable = ((Shearable) entity).isShearable();
        this.hat.visible = shearable;
        this.saplings.visible = shearable;

        this.ears[0].roll = shearable ? 0 : -45 * DEGREE;
        this.ears[1].roll = shearable ? 0 : 45 * DEGREE;
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
        matrices.push();
        matrices.translate(0, 1.5d, 0);
        Stream.of(this.head, this.body, this.leg1, this.leg2, this.leg3, this.leg4, this.saplings)
            .forEach(modelPart -> modelPart.render(matrices, vertices, light, overlay, red, green, blue, alpha));
        matrices.pop();
    }

}
