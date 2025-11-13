package kr1v.kr1vUtils.client.mixin.worldgen.noise;

import net.minecraft.util.math.noise.DoublePerlinNoiseSampler;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static kr1v.kr1vUtils.client.config.WorldGen.*;

@Mixin(DoublePerlinNoiseSampler.class)
public class DoublePerlinNoiseSamplerMixin {
    @Mutable
    @Shadow
    @Final
    private double amplitude;

    @Mutable
    @Shadow
    @Final
    private double maxValue;

    @ModifyConstant(method = "sample", constant = @Constant(doubleValue = 1.0181268882175227))
    private double changeMagic(double constant) {
        if (OVERRIDE_DOUBLE_PERLIN_NOISE.shouldHandle()) return DPN_MAGIC_CONSTANT.getDoubleValue();
        return constant;
    }

    @Inject(method = "<init>", at = @At("RETURN"))
    private void changeMaxAndAmplitude(Random random, DoublePerlinNoiseSampler.NoiseParameters parameters, boolean modern, CallbackInfo ci) {
        if (OVERRIDE_DOUBLE_PERLIN_NOISE.shouldHandle()) {
            this.amplitude = DPN_AMPLITUDE.getDoubleValue();
            this.maxValue = DPN_MAX_VALUE.getDoubleValue();
        }
    }
}
