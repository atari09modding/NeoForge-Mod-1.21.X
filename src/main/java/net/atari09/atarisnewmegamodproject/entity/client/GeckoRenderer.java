package net.atari09.atarisnewmegamodproject.entity.client;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.atari09.atarisnewmegamodproject.AtariMod;
import net.atari09.atarisnewmegamodproject.entity.GeckoVariant;
import net.atari09.atarisnewmegamodproject.entity.custom.GeckoEntity;
import net.minecraft.Util;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.joml.Quaternionf;

import java.util.Map;

public class GeckoRenderer extends MobRenderer<GeckoEntity, GeckoModel<GeckoEntity>> {
    private static final Map<GeckoVariant, ResourceLocation> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(GeckoVariant.class), map -> {
                map.put(GeckoVariant.BLUE,
                        ResourceLocation.fromNamespaceAndPath(AtariMod.MOD_ID, "textures/entity/gecko/gecko_blue.png"));
                map.put(GeckoVariant.GREEN,
                        ResourceLocation.fromNamespaceAndPath(AtariMod.MOD_ID, "textures/entity/gecko/gecko_green.png"));
                map.put(GeckoVariant.PINK,
                        ResourceLocation.fromNamespaceAndPath(AtariMod.MOD_ID, "textures/entity/gecko/gecko_pink.png"));
                map.put(GeckoVariant.BROWN,
                        ResourceLocation.fromNamespaceAndPath(AtariMod.MOD_ID, "textures/entity/gecko/gecko_brown.png"));
            });

    public GeckoRenderer(EntityRendererProvider.Context context) {
        super(context, new GeckoModel<>(context.bakeLayer(GeckoModel.LAYER_LOCATION)), 0.25f);
    }

    @Override
    public ResourceLocation getTextureLocation(GeckoEntity entity) {
        return LOCATION_BY_VARIANT.get(entity.getVariant());
    }

    @Override
    public void render(GeckoEntity entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        if(entity.isBaby()){
            poseStack.scale(0.45f, 0.45f, 0.45f);
        } else{
            poseStack.scale(1f, 1f, 1f);
        }

        if(entity.isClimbing()){
            poseStack.mulPose(Axis.XP.rotationDegrees(-90f));
            poseStack.mulPose(Axis.ZP.rotationDegrees(entity.getYRot()));
            poseStack.mulPose(Axis.YP.rotationDegrees(entity.getYRot()));
        }

        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }
}
