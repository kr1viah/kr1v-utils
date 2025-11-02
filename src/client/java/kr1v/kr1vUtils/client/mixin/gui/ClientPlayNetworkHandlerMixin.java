package kr1v.kr1vUtils.client.mixin.gui;

import kr1v.kr1vUtils.client.config.Debug;
import kr1v.kr1vUtils.client.config.Screen;
import kr1v.kr1vUtils.client.utils.ChatUtils;
import kr1v.kr1vUtils.client.utils.MappingUtils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.packet.s2c.play.CloseScreenS2CPacket;
import net.minecraft.text.ClickEvent;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayNetworkHandler.class)
public class ClientPlayNetworkHandlerMixin {
    @Inject(method = "onCloseScreen", at = @At("HEAD"), cancellable = true)
    public void preventScreenClosing(CloseScreenS2CPacket packet, CallbackInfo ci) {
        if (Screen.DISABLED_SERVER_SCREEN_CLOSING.getBooleanValue()) {
            if (MinecraftClient.getInstance().currentScreen != null) {
                String currentScreenClass = MappingUtils.intermediaryToYarnSimple(MinecraftClient.getInstance().currentScreen.getClass());
                boolean shouldPrevent = false;
                for (String s : Screen.DISABLED_SCREEN_CLOSING_EXCEPTIONS.getStrings()) {
                    if (s.equals(currentScreenClass)) {
                        shouldPrevent = true;
                        break;
                    }
                }
                if (shouldPrevent) {
                    ci.cancel();
                } else if (Debug.DISABLED_SERVER_SCREEN_CLOSING_PRINT.getBooleanValue()) {
                    ChatUtils.sendMessage(Text.literal("Allowed closing of screen class: " + currentScreenClass + " (Click to copy)").setStyle(Style.EMPTY.withClickEvent(new ClickEvent.CopyToClipboard(currentScreenClass))));
                }
            }
        }
    }
}
