package net.atari09.atarisnewmegamodproject.entity.client;// Made with Blockbench 5.0.7
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.atari09.atarisnewmegamodproject.AtariMod;
import net.atari09.atarisnewmegamodproject.entity.custom.BrokEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class BrokModel<T extends BrokEntity> extends HierarchicalModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(AtariMod.res("brok"), "main");
	private final ModelPart body;
	private final ModelPart leg_r;
	private final ModelPart leg_l;
	private final ModelPart head;
	private final ModelPart jaw_low;
	private final ModelPart arms;
	private final ModelPart arm_l;
	private final ModelPart arm_r;

	public BrokModel(ModelPart root) {
		this.body = root.getChild("body");
		this.leg_r = this.body.getChild("leg_r");
		this.leg_l = this.body.getChild("leg_l");
		this.head = this.body.getChild("head");
		this.jaw_low = this.head.getChild("jaw_low");
		this.arms = this.body.getChild("arms");
		this.arm_l = this.arms.getChild("arm_l");
		this.arm_r = this.arms.getChild("arm_r");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition leg_r = body.addOrReplaceChild("leg_r", CubeListBuilder.create().texOffs(14, 66).addBox(-2.0F, 8.0F, -4.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(56, 0).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 10.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, -10.0F, 0.0F));

		PartDefinition leg_l = body.addOrReplaceChild("leg_l", CubeListBuilder.create().texOffs(54, 66).addBox(-2.0F, 7.9905F, -3.5638F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(60, 33).addBox(-2.0F, -0.0095F, -1.5638F, 4.0F, 10.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, -10.0F, 0.0F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 17).addBox(-7.0F, -6.0F, -5.0F, 14.0F, 7.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(66, 66).addBox(-5.0F, -4.0F, -6.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(68, 62).addBox(3.0F, -4.0F, -6.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -16.0F, 0.0F));

		PartDefinition cube_r1 = head.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 66).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, -3.0F, -5.0F, 0.0F, 0.0F, 0.3491F));

		PartDefinition cube_r2 = head.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(54, 62).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, -3.0F, -5.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition jaw_low = head.addOrReplaceChild("jaw_low", CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, -3.0F, -12.0F, 16.0F, 5.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(60, 47).addBox(4.0F, -5.0F, -11.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(62, 14).addBox(1.0F, -4.0F, -11.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(66, 47).addBox(-3.0F, -4.0F, -11.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(56, 14).addBox(-6.0F, -5.0F, -11.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 4.0F, 5.0F));

		PartDefinition arms = body.addOrReplaceChild("arms", CubeListBuilder.create(), PartPose.offset(-1.0F, -18.0F, 0.0F));

		PartDefinition arm_l = arms.addOrReplaceChild("arm_l", CubeListBuilder.create().texOffs(0, 34).addBox(-1.0F, -19.0F, -4.0F, 7.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(30, 62).addBox(-3.0F, -17.0F, -2.0F, 2.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(30, 50).addBox(1.0F, -11.0F, -3.0F, 4.0F, 6.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(30, 34).addBox(-1.0F, -5.0F, -4.0F, 7.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(11.0F, 15.0F, 0.0F));

		PartDefinition arm_r = arms.addOrReplaceChild("arm_r", CubeListBuilder.create().texOffs(0, 50).addBox(-1.0F, -19.0F, -4.0F, 7.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(42, 62).addBox(6.0F, -17.0F, -2.0F, 2.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(50, 50).addBox(0.0F, -11.0F, -3.0F, 4.0F, 6.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(48, 17).addBox(-1.0F, -5.0F, -4.0F, 7.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(-14.0F, 15.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
	}

	@Override
	public ModelPart root() {
		return body;
	}


	@Override
	public void setupAnim(BrokEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);

		this.animateWalk(BrokAnimations.walk, limbSwing, limbSwingAmount, 4f, 1f);
		this.animate(entity.idleAnimationState, BrokAnimations.IDLE, ageInTicks, 1f);
		this.animate(entity.attackAnimationState, BrokAnimations.attack, ageInTicks, 1f);

	}
}