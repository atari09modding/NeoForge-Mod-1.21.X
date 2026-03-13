package net.atari09.atarisnewmegamodproject.entity.client;

import net.atari09.atarisnewmegamodproject.AtariMod;
import net.atari09.atarisnewmegamodproject.entity.custom.DragonflyEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class DragonflyModel extends GeoModel<DragonflyEntity> {
    @Override
    public ResourceLocation getModelResource(DragonflyEntity animatable) {
        return AtariMod.res("geo/entity/dragonfly/dragonfly.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(DragonflyEntity animatable) {
        return AtariMod.res("textures/entity/dragonfly/dragonfly.png");
    }

    @Override
    public ResourceLocation getAnimationResource(DragonflyEntity animatable) {
        return AtariMod.res("animations/entity/dragonfly/dragonfly.animation.json");
    }
}
