package kr1v.kr1vUtils.client.config;

import kr1v.kr1vUtils.client.Kr1vUtilsClient;
import kr1v.malilibApi.annotation.Config;
import kr1v.malilibApi.config.plus.ConfigBooleanPlus;

@Config(value = Kr1vUtilsClient.MOD_ID, defaultEnabled = false)
public class Debug {
    public static final ConfigBooleanPlus DISABLED_SERVER_SCREEN_CLOSING_PRINT = new ConfigBooleanPlus("Print prevented screen closings");
}
