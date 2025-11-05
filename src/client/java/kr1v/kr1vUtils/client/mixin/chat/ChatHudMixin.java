package kr1v.kr1vUtils.client.mixin.chat;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import kr1v.kr1vUtils.client.config.Chat;
import net.minecraft.client.gui.hud.ChatHud;
import net.minecraft.client.gui.hud.MessageIndicator;
import net.minecraft.network.message.MessageSignatureData;
import net.minecraft.text.HoverEvent;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;

import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Mixin(ChatHud.class)
public class ChatHudMixin {
	@WrapMethod(method = "addMessage(Lnet/minecraft/text/Text;Lnet/minecraft/network/message/MessageSignatureData;Lnet/minecraft/client/gui/hud/MessageIndicator;)V")
	private void addTimeStamp(Text message, MessageSignatureData signatureData, MessageIndicator indicator, Operation<Void> original) {
		MutableText mutableMessage = message.copy();
		if (Chat.ADD_HOVER_TIMESTAMP.getBooleanValue()) {
			Instant instant = Instant.now();
			LocalTime time = instant.atZone(ZoneId.systemDefault()).toLocalTime();
			String timeStr;
			try {
				timeStr = time.format(DateTimeFormatter.ofPattern(Chat.TIMESTAMP_FORMAT.getStringValue()));
			} catch (Exception e) {
				mutableMessage.append("\nTimestamp format is incorrect!");
				timeStr = time.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
			}
			mutableMessage.setStyle(mutableMessage.getStyle().withHoverEvent(new HoverEvent.ShowText(Text.literal("Timestamp: \n" + timeStr))));
		}

		original.call(mutableMessage, signatureData, indicator);
	}
}
