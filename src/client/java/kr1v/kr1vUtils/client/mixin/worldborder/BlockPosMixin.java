package kr1v.kr1vUtils.client.mixin.worldborder;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(BlockPos.class)
public class BlockPosMixin {
    @WrapOperation(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/MathHelper;smallestEncompassingPowerOfTwo(I)I"))
    private static int big(int value, Operation<Integer> original) {
//        return original.call(Integer.MAX_VALUE);
        return original.call(value);
    }
}
