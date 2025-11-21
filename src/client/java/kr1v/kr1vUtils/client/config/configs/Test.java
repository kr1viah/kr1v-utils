package kr1v.kr1vUtils.client.config.configs;

import fi.dy.masa.malilib.config.options.ConfigDouble;
import fi.dy.masa.malilib.config.options.ConfigInteger;
import fi.dy.masa.malilib.config.options.ConfigString;
import kr1v.kr1vUtils.client.utils.annotation.Config;
import kr1v.kr1vUtils.client.utils.annotation.PopupConfig;
import kr1v.kr1vUtils.client.utils.malilib.plus.*;

@Config
public class Test {
    public static final ConfigStringPlus STRING = new ConfigStringPlus("String config");
    public static final ConfigHotkeyPlus HOTKEY = new ConfigHotkeyPlus("Hotkey config", "", (action, key) -> {
        System.out.println(STRING.getName() + "'s value is: " + STRING.getStringValue());

        return true;
    });

    public static final Class<?> USELESS_NAME = PopupData.class;
    @PopupConfig(distanceFromSides = 50, distanceFromTops = 50)
    static class PopupData {
        public static final ConfigInteger TEST = new ConfigIntegerPlus("Integer", 0, -20, 20);

        public static final Class<?> LAYERED_CONFIGS = LayeredConfigs.class;
        @PopupConfig(buttonName = "Custom button name", name = "Custom name for popup")
        static class LayeredConfigs {
            public static final ConfigDouble DOUBLE = new ConfigDoublePlus("Double layered configs!", 0, -100, 100, "Haha, get it? because this is a double config?");
            public static final ConfigString STRING = new ConfigStringPlus("String!!");
        }
    }

    public static final ConfigBooleanPlus BOOLEAN = new ConfigBooleanPlus("Boolean config", false, "G,R", "By default, when the hotkey is\npressed it'll toggle this");
}
