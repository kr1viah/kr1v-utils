package kr1v.kr1vUtils.client.mixin.actionbar;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import kr1v.kr1vUtils.client.config.configs.Misc;
import kr1v.kr1vUtils.client.mixinclasses.ActionbarMessageManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.text.Text;
import net.minecraft.util.Colors;
import net.minecraft.util.math.ColorHelper;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.profiler.Profilers;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class InGameHudMixin {
	@Unique
	Text clientOverlayMessage;
	@Unique
	int clientOverlayRemaining;
	@Unique
	boolean overlayTinted;

	@Inject(method = "tick()V", at = @At("HEAD"))
	private void decrementRemaining(CallbackInfo ci) {
		if (clientOverlayRemaining > 0) {
			clientOverlayRemaining--;
		}
	}

	@WrapMethod(method = "setOverlayMessage")
	private void redirectOverlayMessage(Text text, boolean tinted, Operation<Void> original) {
		if (!Misc.SEPARATE_SERVER_CLIENT_ACTION_BAR.getBooleanValue()) {
			original.call(text, tinted);
			return;
		}
		if (ActionbarMessageManager.displayOnTop(text)) {
			original.call(text, tinted);
		}  else {
			clientOverlayMessage = text;
			clientOverlayRemaining = 60;
			overlayTinted = tinted;
		}
	}

	@Inject(method = "renderOverlayMessage", at = @At("HEAD"))
	private void renderClientOverlay(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
		TextRenderer textRenderer = MinecraftClient.getInstance().textRenderer;
		if (this.clientOverlayMessage != null && this.clientOverlayRemaining > 0) {
			Profilers.get().push("clientOverlayMessage");
			float f = this.clientOverlayRemaining - tickCounter.getTickProgress(false);
			int i = (int)(f * 255.0F / 20.0F);
			if (i > 255) {
				i = 255;
			}

			if (i > 8) {
				context.getMatrices().push();
				context.getMatrices().translate((float)(context.getScaledWindowWidth() / 2), (float)(context.getScaledWindowHeight() - 68), 0.0F);
				int j;
				if (this.overlayTinted) {
					j = MathHelper.hsvToArgb(f / 50.0F, 0.7F, 0.6F, i);
				} else {
					j = ColorHelper.withAlpha(i, Colors.WHITE);
				}

				int k = textRenderer.getWidth(this.clientOverlayMessage);
				context.drawTextWithBackground(textRenderer, this.clientOverlayMessage, -k / 2, 12, k, j);
				context.getMatrices().pop();
			}

			Profilers.get().pop();
		}
	}
}
