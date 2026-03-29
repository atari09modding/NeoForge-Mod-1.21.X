package net.atari09.atarisnewmegamodproject.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.atari09.atarisnewmegamodproject.AtariMod;
import net.atari09.atarisnewmegamodproject.entity.custom.NuclearGrenadeEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class NuclearGrenadeProjectileRenderer extends EntityRenderer<NuclearGrenadeEntity> {
    private NuclearGrenadeProjectileModel model;
    public NuclearGrenadeProjectileRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model =  new NuclearGrenadeProjectileModel(context.bakeLayer(NuclearGrenadeProjectileModel.LAYER_LOCATION));
    }

    @Override
    public ResourceLocation getTextureLocation(NuclearGrenadeEntity entity) {
        return AtariMod.res("textures/entity/nuclear_grenade/nuclear_grenade_projectile.png");
    }

    @Override
    public void render(NuclearGrenadeEntity pEntity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        VertexConsumer vertexconsumer = ItemRenderer.getFoilBufferDirect(
                buffer, this.model.renderType(this.getTextureLocation(pEntity)),false, false);
        poseStack.mulPose(Axis.XP.rotationDegrees(-90f));
        this.model.renderToBuffer(poseStack, vertexconsumer, packedLight, OverlayTexture.NO_OVERLAY);
        super.render(pEntity, entityYaw, partialTick, poseStack, buffer, packedLight);
    }
}
