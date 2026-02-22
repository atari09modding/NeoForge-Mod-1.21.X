package net.atari09.atarisnewmegamodproject.block.custom;

import com.mojang.serialization.MapCodec;
import net.atari09.atarisnewmegamodproject.block.entity.LogMineBlockEntity;
import net.atari09.atarisnewmegamodproject.block.entity.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;

public class LogMineBlock extends BaseEntityBlock {
    public static final MapCodec<LogMineBlock> CODEC = simpleCodec(LogMineBlock::new);
    public static final BooleanProperty ACTIVATED = BooleanProperty.create("activated");
    public static final IntegerProperty VARIANT = IntegerProperty.create("variant",0,7);


    public LogMineBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(ACTIVATED, false));
        this.registerDefaultState(this.defaultBlockState().setValue(VARIANT, 0));
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new LogMineBlockEntity(pos,state);
    }

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
        if(level.isClientSide()){
            return null;
        }

        return createTickerHelper(blockEntityType, ModBlockEntities.LOGMINE_BE.get(),
                (level1, blockPos, blockState, blockEntity)->blockEntity.tick(level1, blockPos, blockState));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(ACTIVATED);
        builder.add(VARIANT);
    }


    @Override
    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block neighborBlock, BlockPos neighborPos, boolean movedByPiston) {
        super.neighborChanged(state, level, pos, neighborBlock, neighborPos, movedByPiston);
        if (level.getBlockState(pos.below()).is(BlockTags.LOGS) && !level.isClientSide){
            System.out.println("x");
            Map<Block, Integer> logTypeIdMap = Map.of(Blocks.OAK_LOG,0,Blocks.DARK_OAK_LOG,1,Blocks.BIRCH_LOG,2,Blocks.SPRUCE_LOG,3,
                    Blocks.CHERRY_LOG, 4, Blocks.MANGROVE_LOG,5,Blocks.JUNGLE_LOG,6,Blocks.ACACIA_LOG,7);
            if(logTypeIdMap.containsKey(level.getBlockState(pos.below()).getBlock())){
                state.setValue(VARIANT,logTypeIdMap.get(level.getBlockState(pos.below()).getBlock()));
                level.sendBlockUpdated(pos, state, state, 3);
            }
        }
    }
}