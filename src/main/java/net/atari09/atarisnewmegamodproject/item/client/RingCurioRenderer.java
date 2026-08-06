package net.atari09.atarisnewmegamodproject.item.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.client.CuriosRendererRegistry;
import top.theillusivec4.curios.api.client.ICurioRenderer;

public class RingCurioRenderer implements ICurioRenderer {
    @Override
    public <T extends LivingEntity, M extends EntityModel<T>> void render(
            ItemStack stack, SlotContext slotContext,
            PoseStack poseStack, RenderLayerParent<T, M> renderLayerParent,
            MultiBufferSource renderTypeBuffer, int light,
            float limbSwing, float limbSwingAmount,
            float partialTicks, float ageInTicks,
            float netHeadYaw, float headPitch) {



        LivingEntity livingEntity = slotContext.entity();

        M model = renderLayerParent.getModel();

        if(model instanceof HumanoidModel<?> humanoidModel){

            poseStack.pushPose();
            humanoidModel.rightArm.translateAndRotate(poseStack);
            poseStack.translate(0,0.5,-0.13);
            poseStack.mulPose(Axis.XP.rotationDegrees(180));
            poseStack.scale(0.5F,0.5F,0.5F);

            ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
            itemRenderer.renderStatic(livingEntity,stack,ItemDisplayContext.THIRD_PERSON_RIGHT_HAND,false,
                    poseStack,renderTypeBuffer,livingEntity.level(),light,OverlayTexture.NO_OVERLAY,1);
            poseStack.popPose();
        }





    }


}
