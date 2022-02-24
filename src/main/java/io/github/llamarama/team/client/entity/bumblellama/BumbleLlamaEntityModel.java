// Made with Blockbench 3.7.5
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports

package io.github.llamarama.team.client.entity.bumblellama;

import io.github.llamarama.team.entity.bumbllama.BumbleLlamaEntity;
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
    private final ModelPart body;
    private final ModelPart hindLeftLeg;
    private final ModelPart hindRightLeg;
    private final ModelPart frontLeftLeg;
    private final ModelPart frontRightLeg;

    public BumbleLlamaEntityModel(ModelPart root) {
        ModelPart main = root.getChild("main");
        this.head = main.getChild(EntityModelPartNames.HEAD);
        this.body = main.getChild(EntityModelPartNames.BODY);
        this.hindLeftLeg = main.getChild(EntityModelPartNames.LEFT_HIND_LEG);
        this.hindRightLeg = main.getChild(EntityModelPartNames.RIGHT_HIND_LEG);
        this.frontLeftLeg = main.getChild(EntityModelPartNames.LEFT_FRONT_LEG);
        this.frontRightLeg = main.getChild(EntityModelPartNames.RIGHT_FRONT_LEG);
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData data = new ModelData();
        ModelPartData root = data.getRoot();

        ModelPartData main = root.addChild(
            "main",
            ModelPartBuilder.create(),
            ModelTransform.of(0.0F, 24.0F, 0.0F, 0.0F, 0.0F, 0.0F)
        );

        ModelPartData body = main.addChild(
            EntityModelPartNames.BODY,
            ModelPartBuilder.create(),
            ModelTransform.of(0.0F, -19.0F, 2.0F, 0.0F, 0.0F, 0.0F)
        );

        body.addChild(
            "body_r1",
            ModelPartBuilder.create()
                .uv(29, 0)
                .mirrored(true)
                .cuboid(-6.0F, -8.0F, 12.0F, 12.0F, 18.0F, 10.0F, new Dilation(0.0F)),
            ModelTransform.of(0.0F, 19.0F, -1.0F, -1.5708F, 0.0F, -3.1416F)
        );

        ModelPartData flowers = body.addChild(
            "flowers",
            ModelPartBuilder.create(),
            ModelTransform.of(0.0F, 1.0F, -2.0F, 0.0F, 0.0F, 0.0F)
        );

        flowers.addChild(
            "cube_r1",
            ModelPartBuilder.create()
                .uv(64, 0)
                .mirrored(false)
                .cuboid(5.5204F, -2.4351F, -11.4856F, 0.0F, 6.0F, 3.0F, new Dilation(0.0F))
                .uv(64, 0)
                .mirrored(false)
                .cuboid(0.0F, -5.0F, -2.0F, 0.0F, 6.0F, 3.0F, new Dilation(0.0F)),
            ModelTransform.of(-6.7742F, -2.0F, 0.5804F, -0.0662F, -0.6614F, -0.2604F)
        );

        flowers.addChild(
            "cube_r2",
            ModelPartBuilder.create()
                .uv(33, 0)
                .mirrored(false)
                .cuboid(2.2705F, -4.8677F, 1.2501F, 0.0F, 6.0F, 3.0F, new Dilation(0.0F))
                .uv(33, 0)
                .mirrored(false)
                .cuboid(0.0F, -6.0F, -2.0F, 0.0F, 6.0F, 3.0F, new Dilation(0.0F)),
            ModelTransform.of(-6.0796F, -2.0F, 4.5197F, -0.0677F, 0.6891F, -0.3442F)
        );

        flowers.addChild(
            "cube_r3",
            ModelPartBuilder.create()
                .uv(27, 0)
                .mirrored(false)
                .cuboid(0.0F, -5.0F, -1.0F, 0.0F, 6.0F, 3.0F, new Dilation(0.0F)),
            ModelTransform.of(2.0F, -3.0F, 6.0F, 0.0F, 2.3126F, -0.0436F)
        );

        flowers.addChild(
            "cube_r4",
            ModelPartBuilder.create()
                .uv(27, 0)
                .mirrored(false)
                .cuboid(0.0F, -5.0F, -1.0F, 0.0F, 6.0F, 3.0F, new Dilation(0.0F)),
            ModelTransform.of(7.0F, -3.0F, 4.0F, 0.0F, 2.3126F, 0.3054F)
        );

        flowers.addChild(
            "cube_r5",
            ModelPartBuilder.create()
                .uv(0, 0)
                .mirrored(false)
                .cuboid(0.0F, -6.0F, -2.0F, 0.0F, 6.0F, 3.0F, new Dilation(0.0F)),
            ModelTransform.of(-4.0F, -3.0F, 0.0F, 0.0F, -2.618F, -0.3491F)
        );

        flowers.addChild(
            "cube_r6",
            ModelPartBuilder.create()
                .uv(0, 0)
                .mirrored(false)
                .cuboid(0.0F, -6.0F, -2.0F, 0.0F, 6.0F, 3.0F, new Dilation(0.0F)),
            ModelTransform.of(7.0F, -3.0F, 0.0F, 0.0F, -2.618F, 0.3054F)
        );

        ModelPartData hive = body.addChild(
            "hive",
            ModelPartBuilder.create(),
            ModelTransform.of(4.08F, -1.0F, -6.7822F, 0.0F, 0.48F, 0.0F)
        );

        hive.addChild(
            "cube_r7",
            ModelPartBuilder.create()
                .uv(78, 19)
                .mirrored(false)
                .cuboid(-3.0F, -5.0F, 1.0F, 5.0F, 5.0F, 5.0F, new Dilation(0.0F)),
            ModelTransform.of(-5.0F, -2.0F, 0.0F, 0.0F, -0.2618F, 0.0F)
        );

        ModelPartData pots = body.addChild(
            "pots",
            ModelPartBuilder.create(),
            ModelTransform.of(0.0F, 19.0F, -2.0F, 0.0F, 0.0F, 0.0F)
        );

        pots.addChild(
            "pot2_r1",
            ModelPartBuilder.create()
                .uv(45, 41)
                .mirrored(true)
                .cuboid(-10.5F, -21.0F, 3.0F, 8.0F, 8.0F, 3.0F, new Dilation(0.0F)),
            ModelTransform.of(-2.5F, 0.0F, 8.5F, 0.0F, -1.5708F, 0.0F)
        );

        pots.addChild(
            "pot1_r1",
            ModelPartBuilder.create()
                .uv(45, 28)
                .mirrored(true)
                .cuboid(3.5F, -21.0F, 3.0F, 8.0F, 8.0F, 3.0F, new Dilation(0.0F)),
            ModelTransform.of(11.5F, 0.0F, -5.5F, 0.0F, -1.5708F, 0.0F)
        );

        ModelPartData head = main.addChild(
            EntityModelPartNames.HEAD,
            ModelPartBuilder.create()
                .uv(0, 0)
                .mirrored(true)
                .cuboid(-2.0F, -14.0F, -9.0F, 4.0F, 4.0F, 9.0F, new Dilation(0.0F))
                .uv(0, 14)
                .mirrored(true)
                .cuboid(-4.0F, -16.0F, -5.0F, 8.0F, 18.0F, 6.0F, new Dilation(0.0F))
                .uv(17, 0)
                .mirrored(true)
                .cuboid(1.0F, -19.0F, -3.0F, 3.0F, 3.0F, 2.0F, new Dilation(0.0F))
                .uv(17, 0)
                .mirrored(true)
                .cuboid(-4.0F, -19.0F, -3.0F, 3.0F, 3.0F, 2.0F, new Dilation(0.0F)),
            ModelTransform.of(0.0F, -17.0F, -7.0F, 0.0F, 0.0F, 0.0F)
        );

        ModelPartData bumblellama = head.addChild(
            "bumblellama",
            ModelPartBuilder.create()
                .uv(0, 38)
                .mirrored(false)
                .cuboid(6.0F, -28.0F, -1.0F, 3.0F, 2.0F, 3.0F, new Dilation(0.0F))
                .uv(18, 40)
                .mirrored(false)
                .cuboid(7.0F, -30.0F, -2.0F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)),
            ModelTransform.of(-7.0F, 10.0F, -4.0F, 0.0F, 0.0F, 0.0F)
        );

        bumblellama.addChild(
            "cube_r8",
            ModelPartBuilder.create()
                .uv(78, 39)
                .mirrored(false)
                .cuboid(-1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)),
            ModelTransform.of(10.0F, -27.0F, 0.0F, 0.0F, 0.0F, -0.2618F)
        );

        bumblellama.addChild(
            "cube_r9",
            ModelPartBuilder.create()
                .uv(79, 38)
                .mirrored(false)
                .cuboid(-1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)),
            ModelTransform.of(6.0F, -27.0F, 0.0F, 0.0F, 0.0F, 0.3927F)
        );

        bumblellama.addChild(
            "cube_r10",
            ModelPartBuilder.create()
                .uv(0, 0)
                .mirrored(false)
                .cuboid(-3.0F, -1.0F, 2.0F, 1.0F, 0.0F, 1.0F, new Dilation(0.0F)),
            ModelTransform.of(8.0F, -24.0F, 0.0F, 0.0F, 0.0F, 0.9163F)
        );

        main.addChild(
            EntityModelPartNames.LEFT_FRONT_LEG,
            ModelPartBuilder.create()
                .uv(29, 29)
                .mirrored(true)
                .cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 14.0F, 4.0F, new Dilation(0.0F)),
            ModelTransform.of(3.5F, -14.0F, -5.0F, 0.0F, 0.0F, 0.0F)
        );

        main.addChild(
            EntityModelPartNames.RIGHT_FRONT_LEG,
            ModelPartBuilder.create()
                .uv(29, 29)
                .mirrored(true)
                .cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 14.0F, 4.0F, new Dilation(0.0F)),
            ModelTransform.of(-3.5F, -14.0F, -5.0F, 0.0F, 0.0F, 0.0F)
        );

        main.addChild(
            EntityModelPartNames.RIGHT_HIND_LEG,
            ModelPartBuilder.create()
                .uv(29, 29)
                .mirrored(true)
                .cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 14.0F, 4.0F, new Dilation(0.0F)),
            ModelTransform.of(-3.5F, -14.0F, 6.0F, 0.0F, 0.0F, 0.0F)
        );

        main.addChild(
            EntityModelPartNames.LEFT_HIND_LEG,
            ModelPartBuilder.create()
                .uv(29, 29)
                .mirrored(true)
                .cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 14.0F, 4.0F, new Dilation(0.0F)),
            ModelTransform.of(3.5F, -14.0F, 6.0F, 0.0F, 0.0F, 0.0F)
        );

        return TexturedModelData.of(data, 128, 64);
    }

    @Override
    public void setAngles(BumbleLlamaEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        this.head.pitch = headPitch * 0.017453292F;
        this.head.yaw = headYaw * 0.017453292F;

        this.hindLeftLeg.pitch = MathHelper.cos(limbAngle * 0.6662F) * 1.4F * limbDistance;
        this.hindRightLeg.pitch = MathHelper.cos(limbAngle * 0.6662F + 3.1415927F) * 1.4F * limbDistance;
        this.frontLeftLeg.pitch = MathHelper.cos(limbAngle * 0.6662F + 3.1415927F) * 1.4F * limbDistance;
        this.frontRightLeg.pitch = MathHelper.cos(limbAngle * 0.6662F) * 1.4F * limbDistance;

        this.body.getChild("flowers").visible = !entity.isBaby();
        this.body.getChild("pots").visible = !entity.isBaby();
        this.body.getChild("hive").visible = !entity.isBaby();
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        if (!this.child) {
            matrices.push();
            matrices.translate(0, 1.5d, 0);
            Stream.of(this.hindLeftLeg,
                this.hindRightLeg,
                this.frontLeftLeg,
                this.frontRightLeg,
                this.body,
                this.head).forEach(modelPart ->
                modelPart.render(matrices, vertices, packedLight, packedOverlay)
            );
            matrices.pop();
        } else {
            // Leg Rendering
            matrices.push();
            matrices.scale(0.4f, 0.4f, 0.4f);
            matrices.translate(0, 3.7f, 0);
            Stream.of(this.hindLeftLeg,
                this.hindRightLeg,
                this.frontLeftLeg,
                this.frontRightLeg).forEach((modelPart) ->
                modelPart.render(matrices, vertices, packedLight, packedOverlay, red, green, blue, alpha)
            );

            matrices.pop();

            // Body Rendering

            matrices.push();
            matrices.scale(0.576f, 0.4f, 0.4f);
            matrices.translate(0, 3.8, 0);

            this.body.render(matrices, vertices, packedLight, packedOverlay, red, green, blue, alpha);

            matrices.pop();

            // Head Rendering

            matrices.push();

            matrices.scale(0.7f, 0.6f, 0.6f);
            matrices.translate(0d, 2.797d, 0.2d);

            this.head.render(matrices, vertices, packedLight, packedOverlay, red, green, blue, alpha);

            matrices.pop();
        }
    }

}