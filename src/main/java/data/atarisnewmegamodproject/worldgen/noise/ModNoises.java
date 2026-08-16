package data.atarisnewmegamodproject.worldgen.noise;

import net.atari09.atarisnewmegamodproject.AtariMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.synth.NormalNoise;

public class ModNoises {

    public static final ResourceKey<NormalNoise.NoiseParameters> SPIKY = createKey("spiky");
    public static final ResourceKey<NormalNoise.NoiseParameters> CANYON_VALLEY = createKey("canyon_valley");

    private static ResourceKey<NormalNoise.NoiseParameters> createKey(String key) {
        return ResourceKey.create(Registries.NOISE, AtariMod.res(key));
    }

}
