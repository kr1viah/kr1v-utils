package kr1v.kr1vUtils.client.config.configs;

import kr1v.kr1vUtils.client.utils.annotation.Config;
import kr1v.kr1vUtils.client.utils.malilib.configbutton.ConfigButton;

@Config
public class Test {
    public static final ConfigButton CONFIG_BUTTON = new ConfigButton(
            "test_button",
            "This is a test button",
            "Test Button",
            "config.kr1vUtils.test_button",
            () -> System.out.println("Test button pressed!"),
            "Press Me"
    );
}
