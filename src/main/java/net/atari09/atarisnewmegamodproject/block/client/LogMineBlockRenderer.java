package net.atari09.atarisnewmegamodproject.block.client;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.atari09.atarisnewmegamodproject.AtariMod;
import net.atari09.atarisnewmegamodproject.block.custom.LogMineBlock;
import net.atari09.atarisnewmegamodproject.block.entity.LogMineBlockEntity;
import net.atari09.atarisnewmegamodproject.entity.GeckoVariant;
import net.minecraft.Util;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntityType;
import software.bernie.geckolib.cache.texture.AnimatableTexture;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoBlockRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;

import java.util.HashMap;
import java.util.Map;

public class LogMineBlockRenderer extends GeoBlockRenderer<LogMineBlockEntity> {
    private static final Map<Integer, ResourceLocation> LOCATION_BY_VARIANT = Map.of(0,AtariMod.res("textures/block/logmine.png"),1,AtariMod.res("textures/block/logmine_darkoak.png"),
            2,AtariMod.res("textures/block/logmine_birch.png"),3,AtariMod.res("textures/block/logmine_spruce.png"),4,AtariMod.res("textures/block/logmine_cherry.png"),5,
            AtariMod.res("textures/block/logminemangrove.png"),6,AtariMod.res("textures/block/logmine_jungle.png"),
            7,AtariMod.res("textures/block/logmine_acacia.png"));

    public LogMineBlockRenderer(BlockEntityRendererProvider.Context context) {
        super(new LogMineModel());
        addRenderLayer(new LogMineRenderLayer(this));
    }
}
