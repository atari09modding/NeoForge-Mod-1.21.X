package net.atari09.atarisnewmegamodproject.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class ExperimentalCustomRenderingBlockEntity extends BlockEntity {
    public LightningBolt bolt;
    public final RandomSource random;
    public long seed;

    public ExperimentalCustomRenderingBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.EXPERIMENTAL_CUSTOMRENDERING_BE.get(), pos, blockState);
        this.random = RandomSource.create();
        this.seed = random.nextLong();
        assert this.getLevel() != null;
        bolt = new LightningBolt(EntityType.LIGHTNING_BOLT,this.getLevel());

    }


    public void tick(Level level1, BlockPos pos, BlockState state1) {
        this.seed = random.nextLong();
    }
}
