package net.atari09.atarisnewmegamodproject.block.entity;

import net.atari09.atarisnewmegamodproject.AtariMod;
import net.atari09.atarisnewmegamodproject.block.ModBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, AtariMod.MOD_ID);

    public static final Supplier<BlockEntityType<PedestalBlockEntity>> PEDESTAL_BE =
        BLOCK_ENTITIES.register("pedestal_be", ()->BlockEntityType.Builder.of(
                PedestalBlockEntity::new, ModBlocks.PEDESTAL.get()).build(null));

    public static final Supplier<BlockEntityType<GrowthChamberBlockEntity>> GROWTH_CHAMBER_BE =
            BLOCK_ENTITIES.register("growth_chamber_be",()->BlockEntityType.Builder.of(
                    GrowthChamberBlockEntity::new, ModBlocks.GROWTH_CHAMBER.get()).build(null));

    public static final Supplier<BlockEntityType<LogMineBlockEntity>> LOGMINE_BE =
            BLOCK_ENTITIES.register("logmine_be",()->BlockEntityType.Builder.of(
                    (pos,state) -> new LogMineBlockEntity(pos,state,0), ModBlocks.LOGMINE.get()).build(null));
    public static final Supplier<BlockEntityType<LogMineBlockEntity>> LOGMINE_BE_ACACIA =
            BLOCK_ENTITIES.register("logmine_be_acacia",()->BlockEntityType.Builder.of(
                    (pos,state) -> new LogMineBlockEntity(pos,state,7), ModBlocks.LOGMINE_ACACIA.get()).build(null));
    public static final Supplier<BlockEntityType<LogMineBlockEntity>> LOGMINE_BE_SPRUCE =
            BLOCK_ENTITIES.register("logmine_be_spruce",()->BlockEntityType.Builder.of(
                    (pos,state) -> new LogMineBlockEntity(pos,state,3), ModBlocks.LOGMINE_SPRUCE.get()).build(null));
    public static final Supplier<BlockEntityType<LogMineBlockEntity>> LOGMINE_BE_JUNGLE =
            BLOCK_ENTITIES.register("logmine_be_jungle",()->BlockEntityType.Builder.of(
                    (pos,state) -> new LogMineBlockEntity(pos,state,6), ModBlocks.LOGMINE_JUNGLE.get()).build(null));
    public static final Supplier<BlockEntityType<LogMineBlockEntity>> LOGMINE_BE_BIRCH =
            BLOCK_ENTITIES.register("logmine_be_birch",()->BlockEntityType.Builder.of(
                    (pos,state) -> new LogMineBlockEntity(pos,state,2), ModBlocks.LOGMINE_BIRCH.get()).build(null));
    public static final Supplier<BlockEntityType<LogMineBlockEntity>> LOGMINE_BE_CHERRY =
            BLOCK_ENTITIES.register("logmine_be_cherry",()->BlockEntityType.Builder.of(
                    (pos,state) -> new LogMineBlockEntity(pos,state,4), ModBlocks.LOGMINE_CHERRY.get()).build(null));
    public static final Supplier<BlockEntityType<LogMineBlockEntity>> LOGMINE_BE_MANGROVE =
            BLOCK_ENTITIES.register("logmine_be_mangrove",()->BlockEntityType.Builder.of(
                    (pos,state) -> new LogMineBlockEntity(pos,state,5), ModBlocks.LOGMINE_MANGROVE.get()).build(null));
    public static final Supplier<BlockEntityType<LogMineBlockEntity>> LOGMINE_BE_DARKOAK =
            BLOCK_ENTITIES.register("logmine_be_darkoak",()->BlockEntityType.Builder.of(
                    (pos,state) -> new LogMineBlockEntity(pos,state,1), ModBlocks.LOGMINE_DARKOAK.get()).build(null));


    public static void register(IEventBus eventBus){
        BLOCK_ENTITIES.register(eventBus);
    }
}
