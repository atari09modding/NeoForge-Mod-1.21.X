package net.atari09.atarisnewmegamodproject.item.client;

import net.atari09.atarisnewmegamodproject.item.custom.LogMineBlockItem;
import net.atari09.atarisnewmegamodproject.item.custom.SwissarmyknifeItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class SwissArmyKnifeRenderer extends GeoItemRenderer<SwissarmyknifeItem> {
    public SwissArmyKnifeRenderer() {
        super(new SwissarmyknifeModel());
    }
}
