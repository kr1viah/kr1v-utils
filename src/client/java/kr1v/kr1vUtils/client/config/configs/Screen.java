package kr1v.kr1vUtils.client.config.configs;

import com.google.common.collect.ImmutableList;
import kr1v.malilibApi.annotation.Config;
import kr1v.malilibApi.config.plus.ConfigBooleanPlus;
import kr1v.malilibApi.config.plus.ConfigStringListPlus;

@Config("kr1v-utils")
public class Screen {
    public static final ConfigBooleanPlus DISABLED_SERVER_SCREEN_CLOSING = new ConfigBooleanPlus("Prevent servers from closing the screen", false);
    public static final ConfigStringListPlus DISABLED_SCREEN_CLOSING_EXCEPTIONS = new ConfigStringListPlus("Only these", ImmutableList.of("ChatScreen"), "");
}
