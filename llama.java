// Made with Blockbench 4.2.5
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class custom_model extends EntityModel<Entity> {
	private final ModelPart head;
	private final ModelPart bucket;
	private final ModelPart body;
	private final ModelPart chest_left_rotation_r1;
	private final ModelPart chest_right_rotation_r1;
	private final ModelPart left_grass;
	private final ModelPart frond_r1;
	private final ModelPart frond_r2;
	private final ModelPart right_grass;
	private final ModelPart frond_r3;
	private final ModelPart frond_r4;
	private final ModelPart hind_right_leg;
	private final ModelPart hind_left_leg;
	private final ModelPart front_right_leg;
	private final ModelPart front_left_leg;
	public custom_model(ModelPart root) {
		this.head = root.getChild("head");
		this.body = root.getChild("body");
		this.hind_right_leg = root.getChild("hind_right_leg");
		this.hind_left_leg = root.getChild("hind_left_leg");
		this.front_right_leg = root.getChild("front_right_leg");
		this.front_left_leg = root.getChild("front_left_leg");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData head = modelPartData.addChild("head", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -14.0F, -10.0F, 4.0F, 4.0F, 9.0F, new Dilation(0.0F))
		.uv(0, 14).cuboid(-4.0F, -16.0F, -6.0F, 8.0F, 18.0F, 6.0F, new Dilation(0.0F))
		.uv(17, 0).cuboid(1.0F, -19.0F, -4.0F, 3.0F, 3.0F, 2.0F, new Dilation(0.0F))
		.uv(17, 0).cuboid(-4.0F, -19.0F, -4.0F, 3.0F, 3.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 7.0F, -6.0F));

		ModelPartData bucket = head.addChild("bucket", ModelPartBuilder.create().uv(0, 47).cuboid(-5.0F, -23.0F, -13.0F, 10.0F, 9.0F, 8.0F, new Dilation(0.0F))
		.uv(36, 53).cuboid(-5.5F, -23.5F, -13.5F, 11.0F, 2.0F, 9.0F, new Dilation(0.1F)), ModelTransform.pivot(0.0F, 17.0F, 6.0F));

		ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create().uv(29, 0).cuboid(-6.0F, -10.0F, -7.0F, 12.0F, 18.0F, 10.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 5.0F, 2.0F, 1.5708F, 0.0F, 0.0F));

		ModelPartData chest_left_rotation_r1 = body.addChild("chest_left_rotation_r1", ModelPartBuilder.create().uv(45, 41).cuboid(-5.5F, -4.0F, 6.0F, 8.0F, 8.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, -2.0F, -1.5708F, 0.0F, -1.5708F));

		ModelPartData chest_right_rotation_r1 = body.addChild("chest_right_rotation_r1", ModelPartBuilder.create().uv(45, 28).cuboid(-5.5F, -4.0F, -9.0F, 8.0F, 8.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, -2.0F, -1.5708F, 0.0F, -1.5708F));

		ModelPartData left_grass = body.addChild("left_grass", ModelPartBuilder.create(), ModelTransform.pivot(6.0F, -1.0F, -6.0F));

		ModelPartData frond_r1 = left_grass.addChild("frond_r1", ModelPartBuilder.create().uv(95, 56).cuboid(0.0F, 0.0F, -4.0F, 10.0F, 0.0F, 8.0F, new Dilation(0.0F))
		.uv(95, 56).cuboid(0.0F, 0.0F, -13.0F, 10.0F, 0.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(-0.5F, 5.0F, 0.0F, -1.5708F, 0.829F, 0.0F));

		ModelPartData frond_r2 = left_grass.addChild("frond_r2", ModelPartBuilder.create().uv(95, 56).cuboid(0.0F, 0.0F, -13.0F, 10.0F, 0.0F, 8.0F, new Dilation(0.0F))
		.uv(95, 56).cuboid(0.0F, 0.0F, -4.0F, 10.0F, 0.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(-0.5F, 5.0F, 0.0F, -1.5708F, 0.3927F, 0.0F));

		ModelPartData right_grass = body.addChild("right_grass", ModelPartBuilder.create(), ModelTransform.pivot(-6.0F, -1.0F, -7.0F));

		ModelPartData frond_r3 = right_grass.addChild("frond_r3", ModelPartBuilder.create().uv(95, 56).cuboid(0.0F, 0.0F, -13.0F, 10.0F, 0.0F, 8.0F, new Dilation(0.0F))
		.uv(95, 56).cuboid(0.0F, 0.0F, -4.0F, 10.0F, 0.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(0.5F, -4.0F, 1.0F, -1.5708F, 0.3927F, -3.1416F));

		ModelPartData frond_r4 = right_grass.addChild("frond_r4", ModelPartBuilder.create().uv(95, 56).cuboid(0.0F, 0.0F, -4.0F, 10.0F, 0.0F, 8.0F, new Dilation(0.0F))
		.uv(95, 56).cuboid(0.0F, 0.0F, -13.0F, 10.0F, 0.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(0.5F, -4.0F, 1.0F, -1.5708F, 0.829F, -3.1416F));

		ModelPartData hind_right_leg = modelPartData.addChild("hind_right_leg", ModelPartBuilder.create().uv(29, 29).cuboid(-9.0F, 0.0F, -2.0F, 4.0F, 14.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(3.5F, 10.0F, 6.0F));

		ModelPartData hind_left_leg = modelPartData.addChild("hind_left_leg", ModelPartBuilder.create().uv(29, 29).cuboid(5.0F, 0.0F, -2.0F, 4.0F, 14.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-3.5F, 10.0F, 6.0F));

		ModelPartData front_right_leg = modelPartData.addChild("front_right_leg", ModelPartBuilder.create().uv(29, 29).cuboid(-9.0F, 0.0F, -2.0F, 4.0F, 14.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(3.5F, 10.0F, -5.0F));

		ModelPartData front_left_leg = modelPartData.addChild("front_left_leg", ModelPartBuilder.create().uv(29, 29).cuboid(5.0F, 0.0F, -2.0F, 4.0F, 14.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-3.5F, 10.0F, -5.0F));
		return TexturedModelData.of(modelData, 128, 64);
	}
	@Override
	public void setAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}
	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		head.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		body.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		hind_right_leg.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		hind_left_leg.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		front_right_leg.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		front_left_leg.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}
}