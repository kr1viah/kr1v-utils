package kr1v.kr1vUtils.client.mixin.worldborder.fine;

import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.server.command.ForceLoadCommand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ForceLoadCommand.class)
public class ForceLoadCommandMixin {
    @Expression("? >= ?")
    @ModifyExpressionValue(method = "executeChange", at = @At("MIXINEXTRAS:EXPRESSION"))
    private static boolean injected(boolean original) {
        return true;
    }
    @Expression("? < ?")
    @ModifyExpressionValue(method = "executeChange", at = @At("MIXINEXTRAS:EXPRESSION"))
    private static boolean injected2(boolean original) {
        return true;
    }
}
