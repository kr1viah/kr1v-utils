package kr1v.kr1vUtils.client.mixin.speed;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import kr1v.kr1vUtils.client.config.Misc;
import net.minecraft.client.Mouse;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Mouse.class)
public class MouseMixin {
	@Definition(id = "client", field = "Lnet/minecraft/client/Mouse;client:Lnet/minecraft/client/MinecraftClient;")
	@Definition(id = "getOverlay", method = "Lnet/minecraft/client/MinecraftClient;getOverlay()Lnet/minecraft/client/gui/screen/Overlay;")
	@Expression("this.client.getOverlay() == null")
	@ModifyExpressionValue(method = "onMouseButton", at = @At("MIXINEXTRAS:EXPRESSION"))
	private boolean override(boolean original) {
		if (Misc.FAST_MAIN_MENU.getBooleanValue())
			return true;
		return original;
	}
	@Definition(id = "client", field = "Lnet/minecraft/client/Mouse;client:Lnet/minecraft/client/MinecraftClient;")
	@Definition(id = "getOverlay", method = "Lnet/minecraft/client/MinecraftClient;getOverlay()Lnet/minecraft/client/gui/screen/Overlay;")
	@Expression("this.client.getOverlay() == null")
	@ModifyExpressionValue(method = "onMouseScroll", at = @At("MIXINEXTRAS:EXPRESSION"))
	private boolean override2(boolean original) {
		if (Misc.FAST_MAIN_MENU.getBooleanValue())
			return true;
		return original;
	}
}
