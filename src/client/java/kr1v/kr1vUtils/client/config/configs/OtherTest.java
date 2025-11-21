package kr1v.kr1vUtils.client.config.configs;

import kr1v.kr1vUtils.client.utils.annotation.Config;
import kr1v.kr1vUtils.client.utils.annotation.PopupConfig;
import kr1v.kr1vUtils.client.utils.malilib.plus.ConfigBooleanPlus;
import kr1v.kr1vUtils.client.utils.malilib.plus.ConfigStringPlus;

@Config(name = "Custom name")
public class OtherTest {
    public static final ConfigBooleanPlus SOME_BOOLEAN = new ConfigBooleanPlus("Test boolean");
    public static final ConfigBooleanPlus ANOTHER_BOOLEAN = new ConfigBooleanPlus("Other test boolean");

    public static final Class<?> USELESS_NAME = Popup.class;
    @PopupConfig(height = 100)
    static class Popup {
        public static final ConfigStringPlus STRING_THING = new ConfigStringPlus("Strings!");
    }
}
