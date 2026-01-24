package net.atari09.atarisnewmegamodproject.entity.client;

import net.atari09.atarisnewmegamodproject.entity.custom.ChairEntity;
import net.atari09.atarisnewmegamodproject.entity.custom.MagicProjectileEntity;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class MagicProjectileRenderer extends EntityRenderer<MagicProjectileEntity> {
    public MagicProjectileRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public ResourceLocation getTextureLocation(MagicProjectileEntity entity) {
        return null;
    }

    @Override
    public boolean shouldRender(MagicProjectileEntity livingEntity, Frustum camera, double camX, double camY, double camZ) {
        return true;
    }
}
