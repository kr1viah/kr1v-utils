package kr1v.kr1vUtils.client.mixin.worldgen.noise;

import net.minecraft.util.math.noise.SimplexNoiseSampler;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static kr1v.kr1vUtils.client.config.WorldGen.*;

@Mixin(SimplexNoiseSampler.class)
public class SimplexNoiseSamplerMixin {
    @Mutable
    @Shadow
    @Final
    private static double SQRT_3;

    @Mutable
    @Shadow
    @Final
    private static double SKEW_FACTOR_2D;

    @Mutable
    @Shadow
    @Final
    private static double UNSKEW_FACTOR_2D;

    @Mutable
    @Shadow
    @Final
    public double originX;

    @Mutable
    @Shadow
    @Final
    public double originY;

    @Mutable
    @Shadow
    @Final
    public double originZ;

    @Inject(method = "<init>", at = @At("RETURN"))
    private void setConsts(Random random, CallbackInfo ci) {
        if (SN_OVERRIDE_SIMPLEX_NOISE.shouldHandle()) {
            SQRT_3 = SN_SQRT_3.getDoubleValue();
            SKEW_FACTOR_2D = SN_SKEW_FACTOR_2d.getDoubleValue();
            UNSKEW_FACTOR_2D = SN_UNSKEW_FACTOR_2d.getDoubleValue();
            this.originX = SN_ORIGIN_X.getDoubleValue();
            this.originY = SN_ORIGIN_Y.getDoubleValue();
            this.originZ = SN_ORIGIN_Z.getDoubleValue();
        }
    }
}
