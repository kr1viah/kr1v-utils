package kr1v.kr1vUtils.client.config.configs;

import com.google.common.collect.ImmutableList;
import kr1v.kr1vUtils.client.utils.annotation.Config;
import kr1v.kr1vUtils.client.utils.annotation.DependantOn;
import kr1v.kr1vUtils.client.utils.malilib.ConfigBooleanPlus;
import kr1v.kr1vUtils.client.utils.malilib.ConfigStringListPlus;

@Config
@SuppressWarnings("unused")
public class Screen {
    @DependantOn("Affect anything")
	public static final ConfigBooleanPlus DISABLED_SERVER_SCREEN_CLOSING = new ConfigBooleanPlus("Prevent servers from closing the screen", false);
    @DependantOn("Affect anything")
	public static final ConfigStringListPlus DISABLED_SCREEN_CLOSING_EXCEPTIONS = new ConfigStringListPlus("Only these", ImmutableList.of("ChatScreen"), "");
    @DependantOn("Affect anything")
	public static final ConfigStringListPlus PREVENT_OPENING_OF_SCREEN = new ConfigStringListPlus("Prevent these screens from opening");
}
