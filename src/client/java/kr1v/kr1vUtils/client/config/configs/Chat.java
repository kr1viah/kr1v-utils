package kr1v.kr1vUtils.client.config.configs;

import com.google.common.collect.ImmutableList;
import fi.dy.masa.malilib.config.options.ConfigColor;
import fi.dy.masa.malilib.config.options.ConfigStringList;
import fi.dy.masa.malilib.hotkeys.KeybindSettings;
import kr1v.kr1vUtils.client.gui.BetterChatHud;
import kr1v.kr1vUtils.client.utils.annotation.Config;
import kr1v.kr1vUtils.client.utils.annotation.DependantOn;
import kr1v.kr1vUtils.client.utils.annotation.Dependency;
import kr1v.kr1vUtils.client.utils.malilib.ConfigBooleanPlus;
import kr1v.kr1vUtils.client.utils.malilib.ConfigStringPlus;
import net.minecraft.client.MinecraftClient;

@Config
@SuppressWarnings("unused")
public class Chat {
    @DependantOn("Affect anything")
    @Dependency("Affect chat")
    public static final ConfigBooleanPlus       AFFECT_CHAT = new ConfigBooleanPlus("Affect chat");
    @DependantOn("Affect chat")
	public static final ConfigStringList        CHAT_HIDE = new ConfigStringList("Prevent messages with these regex strings from getting added to the chat", ImmutableList.of(), "Happens after replacing");
    @DependantOn("Affect chat")
	public static final ConfigBooleanPlus       REDIRECT_TO_SUBTITLES = new ConfigBooleanPlus("Redirect matched messages to the subtitle hud element", true, "", "");
    @DependantOn("Affect chat")
	public static final ConfigBooleanPlus       ALLOW_DUPLICATE_SUBTITLES = new ConfigBooleanPlus("Duplicate subtitles", false, "", "Allow having multiple of the same message after each other in the subtitles");
    @DependantOn("Affect chat")
	public static final ConfigBooleanPlus       CHAT_SELECTING = new ConfigBooleanPlus("Chat selecting", true, "LEFT_CONTROL,C", KeybindSettings.GUI, "Be able to select and copy the chat", "", "");
	public static final ConfigColor             CHAT_SELECTED_TEXT_BACKGROUND_COLOUR = new ConfigColor("Selected text background colour", "0xAA0033FF", "");
    @DependantOn("Affect chat")
	public static final ConfigBooleanPlus       ADD_HOVER_TIMESTAMP = new ConfigBooleanPlus("Add hover timestamp", true);
	public static final ConfigStringPlus        TIMESTAMP_FORMAT = new ConfigStringPlus("Timestamp format", "[HH:mm:ss]");

	static {
		CHAT_SELECTING.getKeybind().setCallback((button, keybind) -> {
			if (BetterChatHud.selectedText == null || BetterChatHud.selectedText.isEmpty() || !CHAT_SELECTING.shouldHandle())
				return false;
			MinecraftClient.getInstance().keyboard.setClipboard(BetterChatHud.selectedText);
			return true;
		});
	}
}
