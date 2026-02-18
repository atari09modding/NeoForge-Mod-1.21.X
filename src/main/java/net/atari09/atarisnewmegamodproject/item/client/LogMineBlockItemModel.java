package net.atari09.atarisnewmegamodproject.item.client;

import net.atari09.atarisnewmegamodproject.AtariMod;
import net.atari09.atarisnewmegamodproject.block.entity.LogMineBlockEntity;
import net.atari09.atarisnewmegamodproject.item.custom.LogMineBlockItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class LogMineBlockItemModel extends GeoModel<LogMineBlockItem> {
    @Override
    public ResourceLocation getModelResource(LogMineBlockItem animatable) {
        return AtariMod.res("geo/block/logmine/logmine.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(LogMineBlockItem animatable) {
        return AtariMod.res("textures/block/logmine.png");
    }

    @Override
    public ResourceLocation getAnimationResource(LogMineBlockItem animatable) {
        return AtariMod.res("animations/block/logmine/logmine.animation.json");
    }
}
