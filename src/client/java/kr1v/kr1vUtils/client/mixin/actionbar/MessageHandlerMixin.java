package kr1v.kr1vUtils.client.mixin.actionbar;

import kr1v.kr1vUtils.client.mixinclasses.ActionbarMessageManager;
import net.minecraft.client.network.message.MessageHandler;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MessageHandler.class)
public class MessageHandlerMixin {
	@Inject(method = "onGameMessage", at = @At("HEAD"))
	private void addSource(Text message, boolean overlay, CallbackInfo ci) {
		if (overlay)
			ActionbarMessageManager.serverMessages.add(message);
	}

	@Inject(method = "onGameMessage", at = @At("TAIL"))
	private void removeSource(Text message, boolean overlay, CallbackInfo ci) {
		ActionbarMessageManager.serverMessages.remove(message);
	}
}
