package net.atari09.atarisnewmegamodproject.block.entity;

import net.atari09.atarisnewmegamodproject.block.ModBlocks;
import net.atari09.atarisnewmegamodproject.block.custom.LogMineBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.registries.DeferredBlock;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.Map;
import java.util.function.Supplier;


public class LogMineBlockEntity extends BlockEntity implements GeoBlockEntity {
    private final AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);
    private int explosionCountdown = 20;
    private static final Map<Integer, Supplier<BlockEntityType<LogMineBlockEntity>>> TYPE_BY_ID = Map.of(
            0, ModBlockEntities.LOGMINE_BE,
            1,ModBlockEntities.LOGMINE_BE_DARKOAK,
            2,ModBlockEntities.LOGMINE_BE_BIRCH,
            3,ModBlockEntities.LOGMINE_BE_SPRUCE,
            4,ModBlockEntities.LOGMINE_BE_CHERRY,
            5, ModBlockEntities.LOGMINE_BE_MANGROVE,
            6,ModBlockEntities.LOGMINE_BE_JUNGLE,
            7,ModBlockEntities.LOGMINE_BE_ACACIA);

    public LogMineBlockEntity(BlockPos pos, BlockState blockState,int id) {
        super(TYPE_BY_ID.get(id).get(), pos, blockState);
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



    public void tick(Level level1, BlockPos pos, BlockState blockState) {
        if(blockState.getValue(LogMineBlock.ACTIVATED).equals(true)){
            explosionCountdown--;
            if(explosionCountdown <= 0){
                if(!level1.isClientSide){
                    PrimedTnt tnt = new PrimedTnt(EntityType.TNT, level1);
                    tnt.setPos(pos.getX(), pos.getY(), pos.getZ());
                    tnt.setFuse(0);
                    level1.addFreshEntity(tnt);
                    level1.addFreshEntity(tnt);
                    level1.addFreshEntity(tnt);
                    level1.addFreshEntity(tnt);
                    level1.removeBlockEntity(pos);
                    level1.removeBlock(pos, false);
                }
            }
        } else if (level.getBlockState(pos.below()).is(BlockTags.LOGS) && !level.isClientSide){
            Map<Block, DeferredBlock<Block>> logTypeMap = Map.of(
                    Blocks.OAK_LOG, ModBlocks.LOGMINE,
                    Blocks.DARK_OAK_LOG,ModBlocks.LOGMINE_DARKOAK,
                    Blocks.BIRCH_LOG,ModBlocks.LOGMINE_BIRCH,
                    Blocks.SPRUCE_LOG,ModBlocks.LOGMINE_SPRUCE,
                    Blocks.CHERRY_LOG, ModBlocks.LOGMINE_CHERRY,
                    Blocks.MANGROVE_LOG,ModBlocks.LOGMINE_MANGROVE,
                    Blocks.JUNGLE_LOG,ModBlocks.LOGMINE_JUNGLE,
                    Blocks.ACACIA_LOG,ModBlocks.LOGMINE_ACACIA);
            if(!logTypeMap.containsKey(level.getBlockState(pos.below()).getBlock())) return;
            level.removeBlock(pos,false);
            level.removeBlockEntity(pos);
            level.setBlock(pos, logTypeMap.get(level.getBlockState(pos.below()).getBlock()).get().defaultBlockState(),0);
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
