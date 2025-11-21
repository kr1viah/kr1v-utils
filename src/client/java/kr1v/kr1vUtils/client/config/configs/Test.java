package kr1v.kr1vUtils.client.config.configs;

import fi.dy.masa.malilib.config.options.ConfigInteger;
import kr1v.kr1vUtils.client.utils.annotation.Config;
import kr1v.kr1vUtils.client.utils.annotation.PopupConfig;
import kr1v.kr1vUtils.client.utils.malilib.configbutton.ConfigButton;
import kr1v.kr1vUtils.client.utils.malilib.plus.ConfigDoublePlus;
import kr1v.kr1vUtils.client.utils.malilib.plus.ConfigIntegerPlus;

@SuppressWarnings("rawtypes")
@Config
public class Test {
    public static final ConfigButton CONFIG_BUTTON = new ConfigButton("Test button", "Press me", () -> System.out.println("Test button pressed!"));

    public static final Class<?> POPUP_DATA = PopupData.class;
    @PopupConfig(distanceFromSides = 50, distanceFromTops = 50)
    static class PopupData {
        public static final ConfigInteger TEST = new ConfigIntegerPlus("Test", 0, -20, 20);

        public static final Class<?> LAYERED_CONFIGS = LayeredConfigs.class;
        @PopupConfig(buttonName = "Custom button name")
        static class LayeredConfigs {
            public static final ConfigDoublePlus DOUBLE = new ConfigDoublePlus("Double layered configs!", 0, -100, 100, "Haha, get it? because this is a double config?");
        }
    }

    public static final ConfigButton CONFIG_BUTTON2 = new ConfigButton("Other test button", "Press me too!", () -> System.out.println("Test button 2 pressed!"));
}
