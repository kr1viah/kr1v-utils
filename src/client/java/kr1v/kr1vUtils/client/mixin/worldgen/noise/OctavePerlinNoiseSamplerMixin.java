package kr1v.kr1vUtils.client.mixin.worldgen.noise;

import com.mojang.datafixers.util.Pair;
import it.unimi.dsi.fastutil.doubles.DoubleList;
import net.minecraft.util.math.noise.OctavePerlinNoiseSampler;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static kr1v.kr1vUtils.client.config.WorldGen.*;

@Mixin(OctavePerlinNoiseSampler.class)
public class OctavePerlinNoiseSamplerMixin {
    @Mutable
    @Shadow
    @Final
    private int firstOctave;

    @Mutable
    @Shadow
    @Final
    private double persistence;

    @Mutable
    @Shadow
    @Final
    private double lacunarity;

    @Mutable
    @Shadow
    @Final
    private double maxValue;

    @Inject(method = "<init>", at = @At("RETURN"))
    private void setConsts(Random random, Pair<Integer, DoubleList> firstOctaveAndAmplitudes, boolean xoroshiro, CallbackInfo ci) {
        if (OVERRIDE_OCTAVE_PERLIN_NOISE.getBooleanValue()) {
            this.firstOctave = OPN_FIRST_OCTAVE.getIntegerValue();
            this.persistence = OPN_PERSISTENCE.getDoubleValue();
            this.lacunarity = OPN_LACUNARITY.getDoubleValue();
            this.maxValue = OPN_MAX_VALUE.getDoubleValue();
        }
    }
}
