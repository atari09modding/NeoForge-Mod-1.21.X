package net.atari09.atarisnewmegamodproject.worldgen.chunkgen;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.atari09.atarisnewmegamodproject.worldgen.noise.ModNoises;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.server.level.WorldGenRegion;
import net.minecraft.util.KeyDispatchDataCodec;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.NoiseColumn;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.biome.BiomeManager;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.*;
import net.minecraft.world.level.levelgen.blending.Blender;
import net.minecraft.world.level.levelgen.synth.NormalNoise;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static net.minecraft.world.level.dimension.DimensionType.MIN_Y;

public class CanyonChunkGenerator extends ChunkGenerator {

    public static final MapCodec<CanyonChunkGenerator> CODEC = RecordCodecBuilder.mapCodec((p_255585_) ->
            p_255585_.group(BiomeSource.CODEC.fieldOf("biome_source")
                    .forGetter((p_255584_) -> p_255584_.biomeSource), NoiseGeneratorSettings.CODEC.fieldOf("settings")
                    .forGetter((p_224278_) -> p_224278_.settings)).apply(p_255585_, p_255585_.stable(CanyonChunkGenerator::new)));


    private final Holder<NoiseGeneratorSettings> settings;

    private final int BASE_HEIGHT = 90;

    public CanyonChunkGenerator(BiomeSource biomeSource, Holder<NoiseGeneratorSettings> settings) {
        super(biomeSource);
        this.settings = settings;

    }

    @Override
    protected MapCodec<? extends ChunkGenerator> codec() {
        return CODEC;
    }

    /**
     * main method called to get height
     * called in fillfromnoise, getbaseheight, and getbasecolumn
     */
    private int sampleHeight(int x, int z, RandomState randomState) {


        NormalNoise continentalnessNoise = randomState.getOrCreateNoise(Noises.CONTINENTALNESS);
        NormalNoise jaggednessNoise = randomState.getOrCreateNoise(Noises.JAGGED);
        NormalNoise canyonvalleyNoise = randomState.getOrCreateNoise(ModNoises.CANYON_VALLEY);

        double noiseContinentalnessValue = continentalnessNoise.getValue(x ,0, z );
        double noiseJaggedValue = jaggednessNoise.getValue(x ,0, z );
        double noisecanyonvalleyValue = Math.abs(canyonvalleyNoise.getValue((double) x/2,0, (double) z/2)*15);


        if(noisecanyonvalleyValue > 2){
            noisecanyonvalleyValue = 0;
        } else {
            noisecanyonvalleyValue = (Math.atan(noisecanyonvalleyValue-1)*-2+1.6)*10;
        }


        int continentalness = (int)(Math.round(noiseContinentalnessValue *10));
        int jagged = (int)(Math.round(noiseJaggedValue * 8));
        int canyonvalley = (int)(Math.round(noisecanyonvalleyValue));







        return BASE_HEIGHT + continentalness + jagged - canyonvalley;
    }


    //cuts holes like caves
    @Override
    public void applyCarvers(WorldGenRegion worldGenRegion, long l, RandomState randomState, BiomeManager biomeManager, StructureManager structureManager, ChunkAccess chunkAccess, GenerationStep.Carving carving) {

    }


    // runs AFTER fillFromNoise and replaces surface with certain blocks
    @Override
    public void buildSurface(WorldGenRegion level, StructureManager structureManager,
                             RandomState randomState, ChunkAccess chunk) {
        int chunkX = chunk.getPos().getMinBlockX();
        int chunkZ = chunk.getPos().getMinBlockZ();

        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                int worldX = chunkX + x;
                int worldZ = chunkZ + z;
                int surfaceY = sampleHeight(worldX, worldZ,randomState);

                if (surfaceY - 1 >= MIN_Y) {
                    if (surfaceY-1 > getSeaLevel()){
                        chunk.setBlockState(new BlockPos(x, surfaceY - 1, z),
                                Blocks.TERRACOTTA.defaultBlockState(), false);
                    } else {
                        chunk.setBlockState(new BlockPos(x, surfaceY - 1, z),
                                Blocks.DIRT.defaultBlockState(), false);
                    }

                }
                for (int y = surfaceY - 4; y < surfaceY - 1; y++) {
                    if (y >= MIN_Y) {
                        if (surfaceY-1 > getSeaLevel()){
                            chunk.setBlockState(new BlockPos(x, y, z),
                                    Blocks.TERRACOTTA.defaultBlockState(), false);
                        } else {
                            chunk.setBlockState(new BlockPos(x, y, z),
                                    Blocks.DIRT.defaultBlockState(), false);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void spawnOriginalMobs(WorldGenRegion worldGenRegion) {

    }


    //core of the whole thing
    @Override
    public CompletableFuture<ChunkAccess> fillFromNoise(Blender blender, RandomState randomState, StructureManager structureManager, ChunkAccess chunkAccess) {
        return CompletableFuture.supplyAsync(()->{
            int chunkX = chunkAccess.getPos().getMinBlockX();
            int chunkZ = chunkAccess.getPos().getMinBlockZ();

            for (int x = 0; x < 16; x++) {
                for (int z = 0; z < 16; z++) {
                    int worldX = chunkX + x;
                    int worldZ = chunkZ + z;
                    int surfaceY = sampleHeight(worldX, worldZ,randomState);

                    for (int y = getMinY(); y < surfaceY; y++) {
                        chunkAccess.setBlockState(new BlockPos(x, y, z),
                                Blocks.TERRACOTTA.defaultBlockState(), false);
                    }
                    // fill with Water if sealevel lower
                    for (int y = surfaceY; y < getSeaLevel(); y++) {
                        chunkAccess.setBlockState(new BlockPos(x, y, z),
                                Blocks.WATER.defaultBlockState(), false);
                    }
                }
            }
            return chunkAccess;
        });


    }

    @Override
    public int getGenDepth() {
        return ((NoiseGeneratorSettings)this.settings.value()).noiseSettings().height();
    }

    @Override
    public int getSeaLevel() {
        return ((NoiseGeneratorSettings)this.settings.value()).seaLevel();
    }

    public int getMinY() {
        return ((NoiseGeneratorSettings)this.settings.value()).noiseSettings().minY();
    }
    @Override
    public int getBaseHeight(int x, int z, Heightmap.Types types, LevelHeightAccessor levelHeightAccessor, RandomState randomState) {
        return sampleHeight(x,z,randomState);
    }


    // similar to getBase height but returns whole noisecolumn, used for visibility calculations
    @Override
    public NoiseColumn getBaseColumn(int x, int z, LevelHeightAccessor levelHeightAccessor, RandomState randomState) {
        int surfaceY = sampleHeight(x, z,randomState);
        var states = new net.minecraft.world.level.block.state.BlockState[levelHeightAccessor.getHeight()];
        for (int i = 0; i < states.length; i++) {
            int y = levelHeightAccessor.getMinBuildHeight() + i;
            if (y < surfaceY) {
                states[i] = Blocks.STONE.defaultBlockState();
            } else if (y < getSeaLevel()) {
                states[i] = Blocks.WATER.defaultBlockState();
            } else {
                states[i] = Blocks.AIR.defaultBlockState();
            }
        }
        return new NoiseColumn(levelHeightAccessor.getMinBuildHeight(), states);
    }


    //optional F3 stuff
    @Override
    public void addDebugScreenInfo(List<String> list, RandomState randomState, BlockPos blockPos) {
        NormalNoise temperature = randomState.getOrCreateNoise(Noises.TEMPERATURE);
        NormalNoise vegetation = randomState.getOrCreateNoise(Noises.VEGETATION);
        NormalNoise continentalness = randomState.getOrCreateNoise(Noises.CONTINENTALNESS);
        NormalNoise erosion = randomState.getOrCreateNoise(Noises.EROSION);
        NormalNoise ridges = randomState.getOrCreateNoise(Noises.RIDGE);
        NormalNoise canyonvalley = randomState.getOrCreateNoise(ModNoises.CANYON_VALLEY);
        list.add("AtariChunkGenerator:");
        list.add("Temperature:" + temperature.getValue(blockPos.getX(),0,blockPos.getZ()));
        list.add("vegetation:" + vegetation.getValue(blockPos.getX(),0,blockPos.getZ()));
        list.add("continentalness:" + continentalness.getValue(blockPos.getX(),0,blockPos.getZ()));
        list.add("erosion:" + erosion.getValue(blockPos.getX(),0,blockPos.getZ()));
        list.add("ridges:" + ridges.getValue(blockPos.getX(),0,blockPos.getZ()));
        list.add("canyonvalley:" + canyonvalley.getValue(blockPos.getX(),0,blockPos.getZ()));
    }

    private DensityFunction wrapNoise(NormalNoise noise) {
        return new DensityFunction() {
            @Override
            public double compute(FunctionContext context) {
                return noise.getValue(context.blockX() * 0.1, 0, context.blockZ() * 0.1);
            }

            @Override
            public void fillArray(double[] values, ContextProvider contextProvider) {
                for (int i = 0; i < values.length; i++) {
                    values[i] = compute(contextProvider.forIndex(i));
                }
            }

            @Override
            public DensityFunction mapAll(Visitor visitor) {
                return visitor.apply(this);
            }

            @Override
            public double minValue() {
                return -2.0;
            }

            @Override
            public double maxValue() {
                return 2.0;
            }

            @Override
            public KeyDispatchDataCodec<? extends DensityFunction> codec() {
                return KeyDispatchDataCodec.of(MapCodec.unit(this));
            }
        };
    }


    @Override
    public CompletableFuture<ChunkAccess> createBiomes(RandomState randomState, Blender blender,
                                                       StructureManager structureManager, ChunkAccess chunk) {

        NormalNoise temperature = randomState.getOrCreateNoise(Noises.TEMPERATURE);
        NormalNoise vegetation = randomState.getOrCreateNoise(Noises.VEGETATION);
        NormalNoise continentalness = randomState.getOrCreateNoise(Noises.CONTINENTALNESS);
        NormalNoise erosion = randomState.getOrCreateNoise(Noises.EROSION);
        NormalNoise ridges = randomState.getOrCreateNoise(Noises.RIDGE);

        DensityFunction depth = new DensityFunction() {
            @Override
            public double compute(FunctionContext context) {
                return (64 - context.blockY()) / 128.0;
            }
            @Override
            public void fillArray(double[] values, ContextProvider contextProvider) {
                for (int i = 0; i < values.length; i++) {
                    values[i] = compute(contextProvider.forIndex(i));
                }
            }
            @Override
            public DensityFunction mapAll(Visitor visitor) {
                return visitor.apply(this);
            }
            @Override
            public double minValue() { return -2.0; }
            @Override
            public double maxValue() { return 2.0; }

            @Override
            public KeyDispatchDataCodec<? extends DensityFunction> codec() {
                return KeyDispatchDataCodec.of(MapCodec.unit(this));
            }
        };

        Climate.Sampler sampler = new Climate.Sampler(
                wrapNoise(temperature),
                wrapNoise(vegetation),
                wrapNoise(continentalness),
                wrapNoise(erosion),
                depth,
                wrapNoise(ridges),
                List.of() // spawnTarget - leer, falls dir Kompass/Spawn-Logik egal ist
        );

        return CompletableFuture.supplyAsync(() -> {
            chunk.fillBiomesFromNoise(this.biomeSource, sampler);
            return chunk;
        });
    }
}
