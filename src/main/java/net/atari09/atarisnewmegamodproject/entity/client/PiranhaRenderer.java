package net.atari09.atarisnewmegamodproject.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.atari09.atarisnewmegamodproject.AtariMod;
import net.atari09.atarisnewmegamodproject.entity.custom.PiranhaEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.layer.AutoGlowingGeoLayer;

public class PiranhaRenderer extends GeoEntityRenderer<PiranhaEntity> {
    public PiranhaRenderer(EntityRendererProvider.Context context) {
        super(context, new PiranhaModel());
        addRenderLayer(new AutoGlowingGeoLayer<>(this){
            @Override
            protected @Nullable RenderType getRenderType(PiranhaEntity animatable, @Nullable MultiBufferSource bufferSource) {
                return RenderType.eyes(AtariMod.res("textures/entity/piranha/piranha_texture_glowmask.png"));
            }
        });
    }

    @Override
    public ResourceLocation getTextureLocation(PiranhaEntity animatable) {
        return AtariMod.res("textures/entity/piranha/piranha_texture.png");
    }
}
