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
import java.util.List;
import java.util.Map;

public class LogMineBlockRenderer extends GeoBlockRenderer<LogMineBlockEntity> {
    public LogMineBlockRenderer(BlockEntityRendererProvider.Context context, int id) {
        super(new LogMineModel(id));
    }
}
