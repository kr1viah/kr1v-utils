package kr1v.kr1vUtils.client.mixin.worldborder;

import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.brigadier.arguments.DoubleArgumentType;
import net.minecraft.server.command.WorldBorderCommand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(WorldBorderCommand.class)
public class WorldBorderCommandMixin {
    @Expression("? > ?")
    @ModifyExpressionValue(method = "executeSet", at = @At(value = "MIXINEXTRAS:EXPRESSION", ordinal = 0))
    private static boolean injected(boolean original) {
        return false;
    }

    @Expression("? > ?")
    @ModifyExpressionValue(method = "executeCenter", at = @At(value = "MIXINEXTRAS:EXPRESSION"))
    private static boolean injected2(boolean original) {
        return false;
    }

    @WrapOperation(method = "register", at = @At(value = "INVOKE", target = "Lcom/mojang/brigadier/arguments/DoubleArgumentType;doubleArg(DD)Lcom/mojang/brigadier/arguments/DoubleArgumentType;"))
    private static DoubleArgumentType injected3(double min, double max, Operation<DoubleArgumentType> original) {
        return original.call(Double.MIN_VALUE, Double.MAX_VALUE);
    }
}
