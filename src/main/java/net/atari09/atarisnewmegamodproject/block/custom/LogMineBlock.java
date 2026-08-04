package net.atari09.atarisnewmegamodproject.block.custom;

import com.mojang.serialization.MapCodec;
import net.atari09.atarisnewmegamodproject.AtariMod;
import net.atari09.atarisnewmegamodproject.block.ModBlocks;
import net.atari09.atarisnewmegamodproject.block.entity.LogMineBlockEntity;
import net.atari09.atarisnewmegamodproject.block.entity.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
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
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.registries.DeferredBlock;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class LogMineBlock extends BaseEntityBlock {
    public static final MapCodec<LogMineBlock> CODEC = simpleCodec(props -> new LogMineBlock(props, 0));
    public static final BooleanProperty ACTIVATED = BooleanProperty.create("activated");
    private int ID;
    private static final Map<Integer, Supplier<BlockEntityType<LogMineBlockEntity>>> TYPE_BY_ID = Map.of(
            0, ModBlockEntities.LOGMINE_BE,
            1,ModBlockEntities.LOGMINE_BE_DARKOAK,
            2,ModBlockEntities.LOGMINE_BE_BIRCH,
            3,ModBlockEntities.LOGMINE_BE_SPRUCE,
            4,ModBlockEntities.LOGMINE_BE_CHERRY,
            5, ModBlockEntities.LOGMINE_BE_MANGROVE,
            6,ModBlockEntities.LOGMINE_BE_JUNGLE,
            7,ModBlockEntities.LOGMINE_BE_ACACIA);

    public LogMineBlock(Properties properties,int id) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(ACTIVATED, false));
        this.ID = id;
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new LogMineBlockEntity(pos,state,ID);
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

        return createTickerHelper(blockEntityType, TYPE_BY_ID.get(ID).get(),
                (level1, blockPos, blockState, blockEntity)->blockEntity.tick(level1, blockPos, blockState));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(ACTIVATED);
    }


    @Override
    protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        super.tick(state, level, pos, random);
    }
}