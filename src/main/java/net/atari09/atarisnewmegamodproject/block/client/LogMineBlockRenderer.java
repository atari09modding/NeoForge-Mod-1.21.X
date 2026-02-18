package net.atari09.atarisnewmegamodproject.block.client;

import net.atari09.atarisnewmegamodproject.block.entity.LogMineBlockEntity;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.level.block.entity.BlockEntityType;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class LogMineBlockRenderer extends GeoBlockRenderer<LogMineBlockEntity> {
    public LogMineBlockRenderer(BlockEntityRendererProvider.Context context) {
        super(new LogMineModel());
    }
}
