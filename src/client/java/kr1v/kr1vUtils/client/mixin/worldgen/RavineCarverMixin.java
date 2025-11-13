package kr1v.kr1vUtils.client.mixin.worldgen;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import kr1v.kr1vUtils.client.config.WorldGen;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.carver.RavineCarver;
import net.minecraft.world.gen.carver.RavineCarverConfig;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(RavineCarver.class)
public class RavineCarverMixin {
    @WrapMethod(method = "shouldCarve(Lnet/minecraft/world/gen/carver/RavineCarverConfig;Lnet/minecraft/util/math/random/Random;)Z")
    private boolean shouldCarve(RavineCarverConfig ravineCarverConfig, Random random, Operation<Boolean> original) {
        if (!WorldGen.RAVINES.shouldHandleNoThis()) return original.call(ravineCarverConfig, random);
        if (WorldGen.RAVINES.getBooleanValue()) return original.call(ravineCarverConfig, random);
        return false;
    }
}
