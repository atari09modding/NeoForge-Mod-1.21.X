package net.atari09.atarisnewmegamodproject.block.entity;

import net.atari09.atarisnewmegamodproject.block.custom.LogMineBlock;
import net.atari09.atarisnewmegamodproject.entity.custom.BrokEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.animation.*;

public class LogMineBlockEntity extends BlockEntity implements GeoBlockEntity {
    private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);
    private int explosionCountdown = 20;


    public LogMineBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.LOGMINE_BE.get(), pos, blockState);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this,"controller", this::predicate).triggerableAnim("EXPLODE",RawAnimation.begin().then("EXPLODE", Animation.LoopType.PLAY_ONCE)));
    }

    private PlayState predicate(AnimationState<LogMineBlockEntity> logMineBlockEntityAnimationState) {
        return logMineBlockEntityAnimationState.setAndContinue(
                RawAnimation.begin().thenLoop("NONE")
        );
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }



    public void tick(Level level1, BlockPos blockPos, BlockState blockState) {
        if (level != null && !level.isClientSide) {
            if (blockState.getValue(LogMineBlock.ACTIVATED).equals(true)) {
                triggerAnim("controller","EXPLODE");
                setChanged();

            }
        }

        if(blockState.getValue(LogMineBlock.ACTIVATED).equals(true)){
            explosionCountdown--;
            if(explosionCountdown <= 0){
                if(!level.isClientSide){
                    level.explode(null, blockPos.getX()+0.5,blockPos.getY()+0.5,blockPos.getZ()+0.5,5f, Level.ExplosionInteraction.TNT);
                    level.removeBlock(blockPos, false);
                    level.removeBlockEntity(blockPos);
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
}
