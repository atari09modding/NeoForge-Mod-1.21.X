package net.atari09.atarisnewmegamodproject.entity.client.layers;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.atari09.atarisnewmegamodproject.AtariMod;
import net.atari09.atarisnewmegamodproject.entity.GeckoVariant;
import net.atari09.atarisnewmegamodproject.entity.client.GeckoModel;
import net.atari09.atarisnewmegamodproject.entity.custom.GeckoEntity;
import net.minecraft.Util;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

import java.util.Map;

public class GeckoEyesLayer extends EyesLayer<GeckoEntity, GeckoModel<GeckoEntity>> {
    private static final Map<GeckoVariant, ResourceLocation> LOCATION_BY_VARIANT_E =
            Util.make(Maps.newEnumMap(GeckoVariant.class), map -> {
                map.put(GeckoVariant.BLUE,
                        ResourceLocation.fromNamespaceAndPath(AtariMod.MOD_ID, "textures/entity/gecko/gecko_blue_eyes.png"));
                map.put(GeckoVariant.GREEN,
                        ResourceLocation.fromNamespaceAndPath(AtariMod.MOD_ID, "textures/entity/gecko/gecko_green_eyes.png"));
                map.put(GeckoVariant.PINK,
                        ResourceLocation.fromNamespaceAndPath(AtariMod.MOD_ID, "textures/entity/gecko/gecko_pink_eyes.png"));
                map.put(GeckoVariant.BROWN,
                        ResourceLocation.fromNamespaceAndPath(AtariMod.MOD_ID, "textures/entity/gecko/gecko_brown_eyes.png"));
            });


    public GeckoEyesLayer(RenderLayerParent<GeckoEntity, GeckoModel<GeckoEntity>> renderer) {
        super(renderer);
    }


    @Override
    public RenderType renderType() {
        return null;
    }

    @Override
    public void render(
            PoseStack poseStack,
            MultiBufferSource buffer,
            int packedLight,
            GeckoEntity entity,
            float limbSwing,
            float limbSwingAmount,
            float partialTicks,
            float ageInTicks,
            float netHeadYaw,
            float headPitch
    ) {
        VertexConsumer vertexconsumer = buffer.getBuffer(RenderType.eyes(LOCATION_BY_VARIANT_E.get(entity.getVariant())));
        this.getParentModel().renderToBuffer(poseStack, vertexconsumer, 15728640, OverlayTexture.NO_OVERLAY);
    }
}
