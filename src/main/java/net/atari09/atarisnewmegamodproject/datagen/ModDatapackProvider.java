package net.atari09.atarisnewmegamodproject.datagen;

import net.atari09.atarisnewmegamodproject.AtariMod;
import net.atari09.atarisnewmegamodproject.enchantment.ModEnchantments;
import net.atari09.atarisnewmegamodproject.trim.ModTrimMaterials;
import net.atari09.atarisnewmegamodproject.trim.ModTrimPatterns;
import net.atari09.atarisnewmegamodproject.worldgen.ModBiomeModifiers;
import net.atari09.atarisnewmegamodproject.worldgen.ModConfiguredFeatures;
import net.atari09.atarisnewmegamodproject.worldgen.ModPlacedFeatures;
import net.atari09.atarisnewmegamodproject.worldgen.biome.ModBiomes;
import net.atari09.atarisnewmegamodproject.worldgen.dimension.ModDimensions;
import net.atari09.atarisnewmegamodproject.worldgen.noise.ModNoiseGeneratorSettings;
import net.atari09.atarisnewmegamodproject.worldgen.noise.ModNoiseRouterData;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class ModDatapackProvider extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.DIMENSION_TYPE, ModDimensions::bootstrapType)//this line HAS to be at the top
            .add(Registries.TRIM_MATERIAL, ModTrimMaterials::bootstrap)
            .add(Registries.TRIM_PATTERN, ModTrimPatterns::bootstrap)
            .add(Registries.ENCHANTMENT, ModEnchantments::bootstrap)
            .add(Registries.CONFIGURED_FEATURE, ModConfiguredFeatures::bootstrap)
            .add(Registries.PLACED_FEATURE, ModPlacedFeatures::bootstrap)
            .add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ModBiomeModifiers::bootstrap)
            .add(Registries.BIOME, ModBiomes::bootstrap)
            //.add(Registries.NOISE_SETTINGS, ModNoiseGeneratorSettings::bootstrap)
            //.add(Registries.DENSITY_FUNCTION, ModNoiseRouterData::bootstrap)
            .add(Registries.LEVEL_STEM, ModDimensions::bootstrapStem);


    public ModDatapackProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER,Set.of(AtariMod.MOD_ID));
    }
}
