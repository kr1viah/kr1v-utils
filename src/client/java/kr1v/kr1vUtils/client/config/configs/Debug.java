package kr1v.kr1vUtils.client.config.configs;

import kr1v.kr1vUtils.client.utils.annotation.Config;
import kr1v.kr1vUtils.client.utils.annotation.DependantOn;
import kr1v.kr1vUtils.client.utils.malilib.ConfigBooleanPlus;

@Config(defaultEnabled = false)
@SuppressWarnings("unused")
public class Debug {
    @DependantOn("Affect anything")
	public static final ConfigBooleanPlus DISABLED_SERVER_SCREEN_CLOSING_PRINT = new ConfigBooleanPlus("Print prevented screen closings");
}
