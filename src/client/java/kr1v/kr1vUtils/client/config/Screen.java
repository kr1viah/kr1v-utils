package kr1v.kr1vUtils.client.config;

import com.google.common.collect.ImmutableList;
import fi.dy.masa.malilib.config.options.ConfigBooleanHotkeyed;
import fi.dy.masa.malilib.config.options.ConfigStringList;
import kr1v.kr1vUtils.client.utils.annotation.Config;
import kr1v.kr1vUtils.client.utils.malilib.ConfigBooleanPlus;

@Config
@SuppressWarnings("unused")
public class Screen {
	public static final ConfigBooleanHotkeyed DISABLED_SERVER_SCREEN_CLOSING = new ConfigBooleanPlus("Prevent servers from closing the screen", false);
	public static final ConfigStringList DISABLED_SCREEN_CLOSING_EXCEPTIONS = new ConfigStringList("Only these", ImmutableList.of("ChatScreen"), "");
	public static final ConfigStringList PREVENT_OPENING_OF_SCREEN = new ConfigStringList("Prevent these screens from opening", ImmutableList.of(), "", "", "");
}
