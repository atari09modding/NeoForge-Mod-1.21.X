package net.atari09.atarisnewmegamodproject.worldgen.noise;

import net.atari09.atarisnewmegamodproject.AtariMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.SurfaceRuleData;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.*;

import java.util.List;

public record ModNoiseGeneratorSettings(NoiseSettings noiseSettings, BlockState defaultBlock, BlockState defaultFluid,
                                        NoiseRouter noiseRouter, SurfaceRules.RuleSource surfaceRule, List<Climate.ParameterPoint> spawnTarget,
                                        int seaLevel, boolean disableMobGeneration, boolean aquifersEnabled, boolean oreVeinsEnabled, boolean useLegacyRandomSource) {
    public static final ResourceKey<NoiseGeneratorSettings> ATARIDIM = ResourceKey.create(Registries.NOISE_SETTINGS, AtariMod.res("ataridim"));


    public static void bootstrap(BootstrapContext<NoiseGeneratorSettings> context){
        context.register(ATARIDIM,ataridim(context));
    }


    public static NoiseGeneratorSettings ataridim(BootstrapContext<?> context){
        return new NoiseGeneratorSettings(NoiseSettings.create(0, 128, 2, 1), Blocks.STONE.defaultBlockState(), Blocks.AIR.defaultBlockState(),
                ModNoiseRouterData.ataridim(context.lookup(Registries.DENSITY_FUNCTION), context.lookup(Registries.NOISE)), SurfaceRuleData.air(), List.of(), 63, true, false, false, false);
    }
}
