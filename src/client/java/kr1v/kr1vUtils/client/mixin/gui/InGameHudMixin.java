package kr1v.kr1vUtils.client.mixin.gui;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import kr1v.kr1vUtils.client.gui.BetterChatHud;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.ChatHud;
import net.minecraft.client.gui.hud.InGameHud;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(InGameHud.class)
public class InGameHudMixin {
	@Shadow
	@Final
	private MinecraftClient client;

	@Definition(id = "ChatHud", type = ChatHud.class)
	@Definition(id = "client", local = @Local(type = MinecraftClient.class, argsOnly = true))
	@Expression("new ChatHud(client)")
	@ModifyExpressionValue(method = "<init>", at = @At("MIXINEXTRAS:EXPRESSION"))
	private ChatHud injected(ChatHud original) {
		return new BetterChatHud(client);
	}
}
