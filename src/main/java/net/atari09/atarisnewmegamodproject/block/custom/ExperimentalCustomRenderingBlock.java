package net.atari09.atarisnewmegamodproject.block.custom;

import com.mojang.serialization.MapCodec;
import net.atari09.atarisnewmegamodproject.block.entity.ExperimentalCustomRenderingBlockEntity;
import net.atari09.atarisnewmegamodproject.block.entity.ModBlockEntities;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LightningBoltRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class ExperimentalCustomRenderingBlock extends BaseEntityBlock {
    public static final MapCodec<ExperimentalCustomRenderingBlock> CODEC = simpleCodec(ExperimentalCustomRenderingBlock::new);


    public ExperimentalCustomRenderingBlock(Properties properties) {
        super(properties);

    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new ExperimentalCustomRenderingBlockEntity(pos,state);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
        //if(level.isClientSide()) return null;

        return createTickerHelper(blockEntityType,ModBlockEntities.EXPERIMENTAL_CUSTOMRENDERING_BE.get(),
                ((level1, pos, state1, blockEntity) -> blockEntity.tick(level1,pos,state1)));
    }
}
