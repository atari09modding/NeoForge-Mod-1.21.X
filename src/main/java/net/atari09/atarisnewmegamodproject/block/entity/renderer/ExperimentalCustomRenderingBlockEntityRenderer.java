package net.atari09.atarisnewmegamodproject.block.entity.renderer;


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.atari09.atarisnewmegamodproject.block.entity.ExperimentalCustomRenderingBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.LightningBoltRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix4f;

import java.util.ArrayList;
import java.util.List;

public class ExperimentalCustomRenderingBlockEntityRenderer implements BlockEntityRenderer<ExperimentalCustomRenderingBlockEntity> {
    public ExperimentalCustomRenderingBlockEntityRenderer(BlockEntityRendererProvider.Context context){
    }

    @Override
    public boolean shouldRender(ExperimentalCustomRenderingBlockEntity blockEntity, Vec3 cameraPos) {
        return true;
    }

    @Override
    public boolean shouldRenderOffScreen(ExperimentalCustomRenderingBlockEntity blockEntity) {
        return true;
    }

    @Override
    public AABB getRenderBoundingBox(ExperimentalCustomRenderingBlockEntity blockEntity) {
        return AABB.INFINITE;
    }

    @Override
    public void render(ExperimentalCustomRenderingBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        EntityRenderDispatcher entityRenderDispatcher = Minecraft.getInstance().getEntityRenderDispatcher();
        RandomSource random = RandomSource.create(blockEntity.seed);

        Vec3 pos = blockEntity.getBlockPos().getCenter().add(0,1f,0);
        int light = getLightLevel(blockEntity.getLevel(),blockEntity.getBlockPos());
        poseStack.translate(0f,1f,0f);

        poseStack.pushPose();
        poseStack.scale(0.05f,0.05f,0.05f);
        //entityRenderDispatcher.render(blockEntity.bolt,0,0,0,0f,partialTick,poseStack,bufferSource,light);


        poseStack.popPose();

        renderVisualLightning(new Vec3(0,0,0),new Vec3(0,10,0),5,0.5f,poseStack,bufferSource,0x55FF0000, random);
    }

    private int getLightLevel(Level level, BlockPos pos) {
        int bLight = level.getBrightness(LightLayer.BLOCK, pos.above());
        int sLight = level.getBrightness(LightLayer.SKY, pos.above());
        return LightTexture.pack(bLight, sLight);
    }

    private static void  renderVisualLightning(Vec3 pos1, Vec3 pos2,int segments,float width, PoseStack poseStack, MultiBufferSource buffer, RandomSource random){
        renderVisualLightning(pos1,pos2,segments,width,poseStack,buffer,0x55FFFFFF, random);
    }

    private static void  renderVisualLightning(Vec3 pos1, Vec3 pos2,int segments,float width, PoseStack poseStack, MultiBufferSource buffer,int outerColor, RandomSource random){
        VertexConsumer consumer = buffer.getBuffer(RenderType.lightning());
        PoseStack.Pose pose = poseStack.last();
        int white = 0x55FFFFFF;
        Vec3 direction = pos2.add(pos1.reverse());
        Vec3 rx = new Vec3(1,0,0);
        Vec3 rz = new Vec3(0,0,1);
        if(direction.y!=0 || direction.z==0){
            rx = direction.cross(rz).normalize().scale(width);
            rz = direction.cross(rx).normalize().scale(-width);
        } else {
            rz = direction.cross(rx).normalize().scale(-width);
            rx = direction.cross(rz).normalize().scale(width);
        }

        Vec3 segment_distance = direction.scale(1f/((float) segments));

        ArrayList<Vec2> randomize = new ArrayList<>();
        randomize.add(new Vec2(0,0));
        for(int i = 0; i<segments-1;i++){
            randomize.add(new Vec2(random.nextFloat()*2f-1f,random.nextFloat()*2f-1f));
        }
        randomize.add(new Vec2(0,0));

        for(int segment_index = 0; segment_index < segments; segment_index++){
            ArrayList<Vec3> rim = new ArrayList<Vec3>();
            //rim1
            Vec3 v = rx.scale(randomize.get(segment_index).x).add(rz.scale(randomize.get(segment_index).y));
            rim.add(pos1.add(segment_distance.scale(segment_index)).add(v));
            rim.add(pos1.add(segment_distance.scale(segment_index)).add(rx).add(v));
            rim.add(pos1.add(segment_distance.scale(segment_index)).add(rx).add(rz).add(v));
            rim.add(pos1.add(segment_distance.scale(segment_index)).add(rz).add(v));

            //rim2
            v = rx.scale(randomize.get(segment_index+1).x).add(rz.scale(randomize.get(segment_index+1).y));
            rim.add(pos1.add(segment_distance.scale(segment_index+1)).add(v));
            rim.add(pos1.add(segment_distance.scale(segment_index+1)).add(rx).add(v));
            rim.add(pos1.add(segment_distance.scale(segment_index+1)).add(rx).add(rz).add(v));
            rim.add(pos1.add(segment_distance.scale(segment_index+1)).add(rz).add(v));

            for(int layer = 0;layer<4;layer++){
                int color = layer ==0?outerColor:white;
                quad(pose,consumer,
                        rim.get(0).add(rz.scale(0.1*layer)).add(rx.scale(0.1*layer)),
                        rim.get(4).add(rz.scale(0.1*layer)).add(rx.scale(0.1*layer)),
                        rim.get(5).add(rz.scale(0.1*layer)).add(rx.scale(-0.1*layer)),
                        rim.get(1).add(rz.scale(0.1*layer)).add(rx.scale(-0.1*layer)),
                        color);

                quad(pose,consumer,
                        rim.get(2).add(rz.scale(-0.1*layer)).add(rx.scale(-0.1*layer)),
                        rim.get(6).add(rz.scale(-0.1*layer)).add(rx.scale(-0.1*layer)),
                        rim.get(7).add(rz.scale(-0.1*layer)).add(rx.scale(0.1*layer)),
                        rim.get(3).add(rz.scale(-0.1*layer)).add(rx.scale(0.1*layer)),
                        color);

                quad(pose,consumer,
                        rim.get(1).add(rx.scale(-0.1*layer)).add(rz.scale(0.1*layer)),
                        rim.get(5).add(rx.scale(-0.1*layer)).add(rz.scale(0.1*layer)),
                        rim.get(6).add(rx.scale(-0.1*layer)).add(rz.scale(-0.1*layer)),
                        rim.get(2).add(rx.scale(-0.1*layer)).add(rz.scale(-0.1*layer)),
                        color);

                quad(pose,consumer,
                        rim.get(3).add(rx.scale(0.1*layer)).add(rz.scale(-0.1*layer)),
                        rim.get(7).add(rx.scale(0.1*layer)).add(rz.scale(-0.1*layer)),
                        rim.get(4).add(rx.scale(0.1*layer)).add(rz.scale(0.1*layer)),
                        rim.get(0).add(rx.scale(0.1*layer)).add(rz.scale(0.1*layer)),
                        color);

            }
        }

        /*
        ArrayList<Vec3> rim = new ArrayList<Vec3>();
        //rim1
        rim.add(pos1);
        rim.add(pos1.add(rx));
        rim.add(pos1.add(rx).add(rz));
        rim.add(pos1.add(rz));
        //rim2
        rim.add(pos2);
        rim.add(pos2.add(rx));
        rim.add(pos2.add(rx).add(rz));
        rim.add(pos2.add(rz));

        for(int layer = 0;layer<4;layer++){
            int color = layer ==0?outerColor:white;
            quad(pose,consumer,
                    rim.get(0).add(rz.scale(0.1*layer)).add(rx.scale(0.1*layer)),
                    rim.get(4).add(rz.scale(0.1*layer)).add(rx.scale(0.1*layer)),
                    rim.get(5).add(rz.scale(0.1*layer)).add(rx.scale(-0.1*layer)),
                    rim.get(1).add(rz.scale(0.1*layer)).add(rx.scale(-0.1*layer)),
                    color);

            quad(pose,consumer,
                    rim.get(2).add(rz.scale(-0.1*layer)).add(rx.scale(-0.1*layer)),
                    rim.get(6).add(rz.scale(-0.1*layer)).add(rx.scale(-0.1*layer)),
                    rim.get(7).add(rz.scale(-0.1*layer)).add(rx.scale(0.1*layer)),
                    rim.get(3).add(rz.scale(-0.1*layer)).add(rx.scale(0.1*layer)),
                    color);

            quad(pose,consumer,
                    rim.get(1).add(rx.scale(-0.1*layer)).add(rz.scale(0.1*layer)),
                    rim.get(5).add(rx.scale(-0.1*layer)).add(rz.scale(0.1*layer)),
                    rim.get(6).add(rx.scale(-0.1*layer)).add(rz.scale(-0.1*layer)),
                    rim.get(2).add(rx.scale(-0.1*layer)).add(rz.scale(-0.1*layer)),
                    color);

            quad(pose,consumer,
                    rim.get(3).add(rx.scale(0.1*layer)).add(rz.scale(-0.1*layer)),
                    rim.get(7).add(rx.scale(0.1*layer)).add(rz.scale(-0.1*layer)),
                    rim.get(4).add(rx.scale(0.1*layer)).add(rz.scale(0.1*layer)),
                    rim.get(0).add(rx.scale(0.1*layer)).add(rz.scale(0.1*layer)),
                    color);

        }*/
        //quad(matrix4f,consumer, (float) pos1.x, (float) pos1.y, (float) pos1.z, (float) pos2.x, (float) pos2.y, (float) pos2.z,color);

    }

    private static void quad(PoseStack.Pose pose, VertexConsumer consumer,Vec3 point, Vec3 point1, Vec3 point2, Vec3 point3,int color){
        consumer.addVertex(pose,point.toVector3f()).setColor(color);
        consumer.addVertex(pose,point1.toVector3f()).setColor(color);
        consumer.addVertex(pose,point2.toVector3f()).setColor(color);
        consumer.addVertex(pose,point3.toVector3f()).setColor(color);
    }
}
