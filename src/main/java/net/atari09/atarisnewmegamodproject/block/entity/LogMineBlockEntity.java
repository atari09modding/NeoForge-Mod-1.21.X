package net.atari09.atarisnewmegamodproject.block.entity;

import net.atari09.atarisnewmegamodproject.block.client.LogMineVariant;
import net.atari09.atarisnewmegamodproject.block.custom.LogMineBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.animation.*;


public class LogMineBlockEntity extends BlockEntity implements GeoBlockEntity {
    private final AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);
    private int explosionCountdown = 20;


    public LogMineBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.LOGMINE_BE.get(), pos, blockState);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this,"controller", this::predicate));
    }

    private PlayState predicate(AnimationState<LogMineBlockEntity> logMineBlockEntityAnimationState) {
        return logMineBlockEntityAnimationState.setAndContinue(this.getBlockState().getValue(LogMineBlock.ACTIVATED).equals(true)?
                RawAnimation.begin().thenLoop("NONE") : RawAnimation.begin().then("EXPLODE", Animation.LoopType.PLAY_ONCE));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }



    public void tick(Level level1, BlockPos blockPos, BlockState blockState) {
        if(blockState.getValue(LogMineBlock.ACTIVATED).equals(true)){
            explosionCountdown--;
            if(explosionCountdown <= 0){
                if(!level1.isClientSide){
                    PrimedTnt tnt = new PrimedTnt(EntityType.TNT, level1);
                    tnt.setPos(blockPos.getX(), blockPos.getY(), blockPos.getZ());
                    tnt.setFuse(0);
                    level1.addFreshEntity(tnt);
                    level1.removeBlockEntity(blockPos);
                    level1.removeBlock(blockPos, false);
                }
            }
        }
    }



    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        explosionCountdown = tag.getInt("countdown");
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.putInt("countdown", explosionCountdown);
    }

    public LogMineVariant getVariant() {
        return LogMineVariant.byId(this.getLevel().getBlockState(this.getBlockPos()).getValue(LogMineBlock.VARIANT));
    }
}
