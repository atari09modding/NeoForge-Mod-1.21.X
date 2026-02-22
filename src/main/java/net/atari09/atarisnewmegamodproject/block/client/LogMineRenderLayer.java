package net.atari09.atarisnewmegamodproject.block.client;

import net.atari09.atarisnewmegamodproject.AtariMod;
import net.atari09.atarisnewmegamodproject.block.custom.LogMineBlock;
import net.atari09.atarisnewmegamodproject.block.entity.LogMineBlockEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;

import java.util.Map;

public class LogMineRenderLayer extends GeoRenderLayer {
    private static final Map<Integer, ResourceLocation> LOCATION_BY_VARIANT = Map.of(0, AtariMod.res("textures/block/logmine.png"),1,AtariMod.res("textures/block/logmine_darkoak.png"),
            2,AtariMod.res("textures/block/logmine_birch.png"),3,AtariMod.res("textures/block/logmine_spruce.png"),4,AtariMod.res("textures/block/logmine_cherry.png"),5,
            AtariMod.res("textures/block/logminemangrove.png"),6,AtariMod.res("textures/block/logmine_jungle.png"),
            7,AtariMod.res("textures/block/logmine_acacia.png"));

    public LogMineRenderLayer(GeoRenderer entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    protected ResourceLocation getTextureResource(GeoAnimatable animatable) {
        return LOCATION_BY_VARIANT.get(((LogMineBlockEntity) animatable).getLevel().getBlockState(((LogMineBlockEntity) animatable).getBlockPos()).getValue(LogMineBlock.VARIANT));
    }
}
