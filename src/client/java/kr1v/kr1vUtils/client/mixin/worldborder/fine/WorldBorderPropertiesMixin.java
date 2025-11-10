package kr1v.kr1vUtils.client.mixin.worldborder.fine;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.world.border.WorldBorder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(WorldBorder.Properties.class)
public class WorldBorderPropertiesMixin {
    @WrapOperation(method = "fromDynamic", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/MathHelper;clamp(DDD)D"))
    private static double injected(double value, double min, double max, Operation<Double> original) {
        return value;
    }
}
