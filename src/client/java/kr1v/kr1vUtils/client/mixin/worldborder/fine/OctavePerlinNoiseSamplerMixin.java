package kr1v.kr1vUtils.client.mixin.worldborder.fine;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.util.math.noise.OctavePerlinNoiseSampler;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(OctavePerlinNoiseSampler.class)
public class OctavePerlinNoiseSamplerMixin {
    @Shadow
    @Final
    @Mutable
    private static int field_31704 = Integer.MAX_VALUE;

    @WrapMethod(method = "maintainPrecision")
    private static double wrap(double value, Operation<Double> original) {
        return value;
    }
}
