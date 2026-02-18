package net.atari09.atarisnewmegamodproject.item.client;

import net.atari09.atarisnewmegamodproject.item.custom.LogMineBlockItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class LogMineBlockItemRenderer extends GeoItemRenderer<LogMineBlockItem> {
    public LogMineBlockItemRenderer() {
        super(new LogMineBlockItemModel());
    }
}
