package net.atari09.atarisnewmegamodproject.item.client;

import net.atari09.atarisnewmegamodproject.item.custom.JetPackChestPlateItem;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class JetPackChestPlateRenderer extends GeoArmorRenderer<JetPackChestPlateItem> {
    public <I extends JetPackChestPlateItem> JetPackChestPlateRenderer(I armorItem) {
        super(new JetPackChestPlateItemModel());
    }
}
