package kr1v.kr1vUtils.client.config;

import fi.dy.masa.malilib.config.options.ConfigBooleanHotkeyed;
import kr1v.kr1vUtils.client.utils.annotation.Config;

@Config
@SuppressWarnings("unused")
public class Debug {
	public static final ConfigBooleanHotkeyed DISABLED_SERVER_SCREEN_CLOSING_PRINT = new ConfigBooleanHotkeyed("Print prevented screen closings", false, "", "");
	public static final ConfigBooleanHotkeyed PREVENT_OPENING_OF_SCREEN_PRINT = new ConfigBooleanHotkeyed("Print screen openings that aren't prevented", false, "", "");
	public static final ConfigBooleanHotkeyed PERCENTAGE_DROPPED_PACKETS_PRINT = new ConfigBooleanHotkeyed("Print prevented packets", false, "", "");
}
