package kr1v.kr1vUtils.client.mixin.actionbar;

import kr1v.kr1vUtils.client.mixinclasses.ActionbarMessageManager;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.packet.s2c.play.OverlayMessageS2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayNetworkHandler.class)
public class ClientPlayNetworkHandlerMixin {
	@Inject(method = "onOverlayMessage", at = @At("HEAD"))
	private void addSource(OverlayMessageS2CPacket packet, CallbackInfo ci) {
		ActionbarMessageManager.serverMessages.add(packet.text());
	}

	@Inject(method = "onOverlayMessage", at = @At("TAIL"))
	private void removeSource(OverlayMessageS2CPacket packet, CallbackInfo ci) {
		ActionbarMessageManager.serverMessages.remove(packet.text());
	}
}
