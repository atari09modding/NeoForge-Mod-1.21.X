package net.atari09.atarisnewmegamodproject.item.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.atari09.atarisnewmegamodproject.AtariMod;
import net.atari09.atarisnewmegamodproject.item.custom.JetPackChestPlateItem;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoArmorRenderer;
import software.bernie.geckolib.renderer.layer.AutoGlowingGeoLayer;

public class JetPackChestPlateRenderer extends GeoArmorRenderer<JetPackChestPlateItem> {
    public <I extends JetPackChestPlateItem> JetPackChestPlateRenderer(I armorItem) {
        super(new JetPackChestPlateItemModel());
        addRenderLayer(new AutoGlowingGeoLayer<>(this){
            @Override
            protected @Nullable RenderType getRenderType(JetPackChestPlateItem animatable, @Nullable MultiBufferSource bufferSource) {
                return RenderType.eyes(AtariMod.res("textures/item/jetpackchestplate_glowmask.png"));
            }
        });
    }
}
