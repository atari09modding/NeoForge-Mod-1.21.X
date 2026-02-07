package net.atari09.atarisnewmegamodproject.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.atari09.atarisnewmegamodproject.AtariMod;
import net.atari09.atarisnewmegamodproject.entity.custom.BrokEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;


public class BrokRenderer extends MobRenderer<BrokEntity, BrokModel<BrokEntity>> {
    private static final ResourceLocation TEXTURE_LOCATION = AtariMod.res("textures/entity/brok/brok.png");

    public BrokRenderer(EntityRendererProvider.Context context) {
        super(context, new BrokModel<>(context.bakeLayer(BrokModel.LAYER_LOCATION)), 1f);
    }


    @Override
    public ResourceLocation getTextureLocation(BrokEntity entity) {
        return TEXTURE_LOCATION;
    }


    @Override
    public void render(BrokEntity entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {

        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }


}
