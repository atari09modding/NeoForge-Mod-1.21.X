package net.atari09.atarisnewmegamodproject.block.client;

import net.atari09.atarisnewmegamodproject.AtariMod;
import net.atari09.atarisnewmegamodproject.block.custom.LogMineBlock;
import net.atari09.atarisnewmegamodproject.block.entity.LogMineBlockEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

import java.util.Map;

public class LogMineModel extends GeoModel<LogMineBlockEntity> {
    private static final Map<Integer, ResourceLocation> LOCATION_BY_VARIANT = Map.of(0,AtariMod.res("textures/block/logmine.png"),1,AtariMod.res("textures/block/logmine_darkoak.png"),
            2,AtariMod.res("textures/block/logmine_birch.png"),3,AtariMod.res("textures/block/logmine_spruce.png"),4,AtariMod.res("textures/block/logmine_cherry.png"),5,
            AtariMod.res("textures/block/logmine_mangrove.png"),6,AtariMod.res("textures/block/logmine_jungle.png"),
            7,AtariMod.res("textures/block/logmine_acacia.png"));
    private int ID;
    public LogMineModel(int id){
        this.ID = id;
    }

    @Override
    public ResourceLocation getModelResource(LogMineBlockEntity animatable) {
        return AtariMod.res("geo/block/logmine/logmine.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(LogMineBlockEntity animatable) {
        return LOCATION_BY_VARIANT.get(ID);
    }

    @Override
    public ResourceLocation getAnimationResource(LogMineBlockEntity animatable) {
        return AtariMod.res("animations/block/logmine/logmine.animation.json");
    }
}
