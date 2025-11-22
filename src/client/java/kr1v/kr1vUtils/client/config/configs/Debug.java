package kr1v.kr1vUtils.client.config.configs;

import kr1v.kr1vUtils.client.utils.annotation.Config;
import kr1v.kr1vUtils.client.utils.malilib.plus.ConfigBooleanPlus;

@Config(defaultEnabled = false)
public class Debug {
    public static final ConfigBooleanPlus DISABLED_SERVER_SCREEN_CLOSING_PRINT = new ConfigBooleanPlus("Print prevented screen closings");
}
