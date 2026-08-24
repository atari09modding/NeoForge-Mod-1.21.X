package net.atari09.atarisnewmegamodproject.block.custom;


import com.mojang.serialization.MapCodec;
import net.atari09.atarisnewmegamodproject.worldgen.dimension.ModDimensions;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Portal;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.EndPlatformFeature;
import net.minecraft.world.level.portal.DimensionTransition;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

public class CanyonsPortalBlock extends BaseEntityBlock implements Portal {
    public static final MapCodec<CanyonsPortalBlock> CODEC = simpleCodec(CanyonsPortalBlock::new);

    protected CanyonsPortalBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return aaa;
    }

    @Override
    public @Nullable DimensionTransition getPortalDestination(ServerLevel level, Entity entity, BlockPos blockPos) {
        ResourceKey<Level> levelKey = level.dimension() == ModDimensions.CANYONS_LEVEL_KEY ? Level.OVERWORLD : ModDimensions.CANYONS_LEVEL_KEY;
        ServerLevel serverlevel = level.getServer().getLevel(levelKey);

        return aaa;
    }
}
