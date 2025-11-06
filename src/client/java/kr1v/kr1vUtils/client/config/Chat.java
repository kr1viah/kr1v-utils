package kr1v.kr1vUtils.client.config;

import com.google.common.collect.ImmutableList;
import fi.dy.masa.malilib.config.options.ConfigBooleanHotkeyed;
import fi.dy.masa.malilib.config.options.ConfigColor;
import fi.dy.masa.malilib.config.options.ConfigString;
import fi.dy.masa.malilib.config.options.ConfigStringList;
import fi.dy.masa.malilib.hotkeys.KeybindSettings;
import kr1v.kr1vUtils.client.gui.BetterChatHud;
import kr1v.kr1vUtils.client.utils.annotation.Config;
import net.minecraft.client.MinecraftClient;

@Config
@SuppressWarnings("unused")
public class Chat {
	public static final ConfigStringList CHAT_HIDE = new ConfigStringList("Prevent messages with these regex strings from getting added to the chat", ImmutableList.of(), "Happens after replacing");
	public static final ConfigBooleanHotkeyed REDIRECT_TO_SUBTITLES = new ConfigBooleanHotkeyed("Redirect matched messages to the subtitle hud element", true, "", "");
	public static final ConfigBooleanHotkeyed ALLOW_DUPLICATE_SUBTITLES = new ConfigBooleanHotkeyed("Duplicate subtitles", false, "", "Allow having multiple of the same message after each other in the subtitles");
	public static final ConfigBooleanHotkeyed CHAT_SELECTING = new ConfigBooleanHotkeyed("Chat selecting", true, "LEFT_CONTROL,C", KeybindSettings.GUI, "Be able to select and copy the chat");
	public static final ConfigColor CHAT_SELECTED_TEXT_BACKGROUND_COLOUR = new ConfigColor("Selected text background colour", "0xAA0033FF", "");
	public static final ConfigBooleanHotkeyed ADD_HOVER_TIMESTAMP = new ConfigBooleanHotkeyed("Add hover timestamp", true, "", "");
	public static final ConfigString TIMESTAMP_FORMAT = new ConfigString("Timestamp format", "[HH:mm:ss]",  "");

	static {
		CHAT_SELECTING.getKeybind().setCallback((button, keybind) -> {
			if (BetterChatHud.selectedText == null || BetterChatHud.selectedText.isEmpty() || !CHAT_SELECTING.getBooleanValue())
				return false;
			MinecraftClient.getInstance().keyboard.setClipboard(BetterChatHud.selectedText);
			return true;
		});
	}
}
