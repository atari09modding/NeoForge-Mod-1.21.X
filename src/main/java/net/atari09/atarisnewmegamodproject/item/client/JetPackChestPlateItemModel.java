package net.atari09.atarisnewmegamodproject.item.client;

import net.atari09.atarisnewmegamodproject.AtariMod;
import net.atari09.atarisnewmegamodproject.item.custom.JetPackChestPlateItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.model.GeoModel;

public class JetPackChestPlateItemModel extends GeoModel<JetPackChestPlateItem> {

    @Override
    public ResourceLocation getModelResource(JetPackChestPlateItem animatable) {
        return AtariMod.res("geo/item/jetpackchestplate/jetpackchestplate.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(JetPackChestPlateItem animatable) {
        return AtariMod.res("textures/item/texture_jetpackchestplate.png");
    }

    @Override
    public ResourceLocation getAnimationResource(JetPackChestPlateItem animatable) {
        return AtariMod.res("animations/item/jetpackchestplate/jetpackchestplate.animation.json");
    }
}
