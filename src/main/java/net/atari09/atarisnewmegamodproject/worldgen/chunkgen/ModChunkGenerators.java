package net.atari09.atarisnewmegamodproject.worldgen.chunkgen;

import com.mojang.serialization.MapCodec;
import net.atari09.atarisnewmegamodproject.AtariMod;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;


public class ModChunkGenerators {

    public static final DeferredRegister<MapCodec<? extends ChunkGenerator>> CHUNK_GENERATORS =
            DeferredRegister.create(BuiltInRegistries.CHUNK_GENERATOR, AtariMod.MOD_ID);

    public static final Supplier<MapCodec<AtariChunkGenerator>> ATARI =
            CHUNK_GENERATORS.register("atari", ()->AtariChunkGenerator.CODEC);

    public static final Supplier<MapCodec<CanyonChunkGenerator>> CANYONS =
            CHUNK_GENERATORS.register("canyons", ()->CanyonChunkGenerator.CODEC);











    public static void register(IEventBus eventBus){CHUNK_GENERATORS.register(eventBus);}


}
