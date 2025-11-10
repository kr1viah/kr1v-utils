package kr1v.kr1vUtils.client.utils.malilib;

import fi.dy.masa.malilib.config.options.ConfigBooleanHotkeyed;
import fi.dy.masa.malilib.hotkeys.KeybindSettings;

public class ConfigBooleanHotkeyedPlus extends ConfigBooleanHotkeyed {
    public ConfigBooleanHotkeyedPlus(String name, boolean defaultValue, String defaultHotkey, KeybindSettings settings, String comment, String prettyName, String translatedName) {
        super(name, defaultValue, defaultHotkey, settings, comment, prettyName, translatedName);
    }
}
