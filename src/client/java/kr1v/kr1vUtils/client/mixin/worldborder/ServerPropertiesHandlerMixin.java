package kr1v.kr1vUtils.client.mixin.worldborder;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.server.dedicated.ServerPropertiesHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ServerPropertiesHandler.class)
public class ServerPropertiesHandlerMixin {
    @WrapOperation(method = "method_16715", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/MathHelper;clamp(III)I"))
    private static int injected(int value, int min, int max, Operation<Integer> original) {
        return original.call(value, 1, Integer.MAX_VALUE);
    }
}
