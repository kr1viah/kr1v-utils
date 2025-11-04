package kr1v.kr1vUtils.client.utils;

import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

public class ChatUtils {
	public static void sendMessage(String s) {
		sendMessage(Text.of(s));
	}

	public static void sendMessage(Text text) {
		MinecraftClient.getInstance().inGameHud.getChatHud().addMessage(text);
	}
}
