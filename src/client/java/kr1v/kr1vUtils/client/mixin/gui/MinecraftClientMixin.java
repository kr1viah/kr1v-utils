package kr1v.kr1vUtils.client.mixin.gui;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {
    @Definition(id = "overlay", field = "Lnet/minecraft/client/MinecraftClient;overlay:Lnet/minecraft/client/gui/screen/Overlay;")
    @Expression("this.overlay == null")
    @ModifyExpressionValue(method = "tick", at = @At(value = "MIXINEXTRAS:EXPRESSION",  ordinal = 0))
    private boolean injected(boolean original) {

        return original;
    }
}
