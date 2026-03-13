package net.atari09.atarisnewmegamodproject.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.atari09.atarisnewmegamodproject.entity.custom.DragonflyEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.EntityType;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class DragonflyRenderer extends GeoEntityRenderer<DragonflyEntity> {
    public DragonflyRenderer(EntityRendererProvider.Context context) {
        super(context, new DragonflyModel());
    }

    @Override
    public void render(DragonflyEntity entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        poseStack.scale(0.1f,0.1f,0.1f);
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
