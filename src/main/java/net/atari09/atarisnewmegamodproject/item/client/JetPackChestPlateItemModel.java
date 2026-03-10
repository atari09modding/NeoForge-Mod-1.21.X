package net.atari09.atarisnewmegamodproject.item.client;

import net.atari09.atarisnewmegamodproject.AtariMod;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.model.GeoModel;

public class JetPackChestPlateItemModel extends GeoModel {
    @Override
    public ResourceLocation getModelResource(GeoAnimatable animatable) {
        return AtariMod.res("animations/item/jetpackchestplate/jetpackchestplate.animation.json");
    }

    @Override
    public ResourceLocation getTextureResource(GeoAnimatable animatable) {
        return AtariMod.res("geo/item/jetpackchestplate/jetpackchestplate.geo.json");

    }

    @Override
    public ResourceLocation getAnimationResource(GeoAnimatable animatable) {
        return AtariMod.res("textures/item/texture_jetpackchestplate.png");
    }
}
