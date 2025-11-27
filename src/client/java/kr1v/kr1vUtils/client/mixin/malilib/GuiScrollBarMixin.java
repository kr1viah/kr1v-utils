package kr1v.kr1vUtils.client.mixin.malilib;

import fi.dy.masa.malilib.gui.GuiScrollBar;
import kr1v.kr1vUtils.client.config.configs.Misc;
import kr1v.malilibApi.screen.ConfigScreen;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = GuiScrollBar.class, remap = false)
public class GuiScrollBarMixin {
	@Inject(method = "setValue", at = @At("HEAD"))
	private void setScrollBar(int value, CallbackInfo ci) {
//		if (MinecraftClient.getInstance().currentScreen instanceof ConfigScreen) {
//			Misc.tabToScrollPosition.put(ConfigScreen..toString(), value);
//		}
	}
}
