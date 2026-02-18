package net.atari09.atarisnewmegamodproject.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.atari09.atarisnewmegamodproject.AtariMod;
import net.atari09.atarisnewmegamodproject.entity.custom.PiranhaEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class PiranhaRenderer extends GeoEntityRenderer<PiranhaEntity> {
    public PiranhaRenderer(EntityRendererProvider.Context context) {
        super(context, new PiranhaModel());
    }

    @Override
    public ResourceLocation getTextureLocation(PiranhaEntity animatable) {
        return AtariMod.res("textures/entity/piranha/piranha_texture.png");
    }
}
