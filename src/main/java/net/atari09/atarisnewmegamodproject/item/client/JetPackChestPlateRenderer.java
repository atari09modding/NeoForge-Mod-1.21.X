package net.atari09.atarisnewmegamodproject.item.client;

import net.atari09.atarisnewmegamodproject.AtariMod;
import net.atari09.atarisnewmegamodproject.item.custom.JetPackChestPlateItem;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.renderer.GeoArmorRenderer;
import software.bernie.geckolib.renderer.layer.AutoGlowingGeoLayer;

public class JetPackChestPlateRenderer extends GeoArmorRenderer<JetPackChestPlateItem> {
    public <I extends JetPackChestPlateItem> JetPackChestPlateRenderer(I armorItem) {
        super(new JetPackChestPlateItemModel());

        addRenderLayer(new AutoGlowingGeoLayer<>(this));
    }
}
