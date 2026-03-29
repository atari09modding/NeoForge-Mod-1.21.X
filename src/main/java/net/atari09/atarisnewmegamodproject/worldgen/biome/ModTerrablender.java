package net.atari09.atarisnewmegamodproject.worldgen.biome;

import net.atari09.atarisnewmegamodproject.AtariMod;
import terrablender.api.Regions;

public class ModTerrablender {
    public static void registerBiomes(){
        Regions.register(new ModOverworldRegion(AtariMod.res("overworld"),5));
    }
}
