package net.atari09.atarisnewmegamodproject.entity.custom;

import net.atari09.atarisnewmegamodproject.entity.ModEntities;
import net.atari09.atarisnewmegamodproject.item.ModItems;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.FallingBlockRenderer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

public class BrokStone extends FallingBlockEntity {


    public BrokStone(EntityType<? extends FallingBlockEntity> entityType, Level level) {
        super(entityType, level);
    }


    @Override
    public BlockState getBlockState(){
        return Blocks.COBBLESTONE.defaultBlockState();
    }

    @Override
    public void tick() {
        this.move(MoverType.SELF, new  Vec3(0,-2,0));
        if(!this.level().isClientSide() && this.verticalCollision){
            this.level().explode(this, this.getX(),this.getY(),this.getZ(),5f, Level.ExplosionInteraction.TNT);
            this.kill();
        }


    }


}
