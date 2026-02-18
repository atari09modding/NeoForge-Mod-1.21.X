package net.atari09.atarisnewmegamodproject.block.client;

import net.atari09.atarisnewmegamodproject.AtariMod;
import net.atari09.atarisnewmegamodproject.block.entity.LogMineBlockEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.model.GeoModel;

public class LogMineModel extends GeoModel<LogMineBlockEntity> {
    @Override
    public ResourceLocation getModelResource(LogMineBlockEntity animatable) {
        return AtariMod.res("geo/block/logmine/logmine.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(LogMineBlockEntity animatable) {
        return AtariMod.res("textures/block/logmine.png");
    }

    @Override
    public ResourceLocation getAnimationResource(LogMineBlockEntity animatable) {
        return AtariMod.res("animations/block/logmine/logmine.animation.json");
    }
}
