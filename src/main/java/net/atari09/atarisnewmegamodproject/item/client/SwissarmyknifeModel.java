package net.atari09.atarisnewmegamodproject.item.client;

import net.atari09.atarisnewmegamodproject.AtariMod;
import net.atari09.atarisnewmegamodproject.item.custom.SwissarmyknifeItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class SwissarmyknifeModel extends GeoModel<SwissarmyknifeItem> {
    @Override
    public ResourceLocation getModelResource(SwissarmyknifeItem animatable) {
        return AtariMod.res("geo/item/swissarmyknife/swissarmyknife.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(SwissarmyknifeItem animatable) {
        return AtariMod.res("textures/item/swissarmyknife.png");

    }

    @Override
    public ResourceLocation getAnimationResource(SwissarmyknifeItem animatable) {
        return AtariMod.res("animations/item/swissarmyknife/swissarmyknife.animation.json");

    }
}
