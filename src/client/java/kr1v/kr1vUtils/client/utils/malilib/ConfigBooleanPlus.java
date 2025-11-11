package kr1v.kr1vUtils.client.utils.malilib;

import fi.dy.masa.malilib.config.options.ConfigBooleanHotkeyed;
import fi.dy.masa.malilib.hotkeys.KeybindSettings;

public class ConfigBooleanPlus extends ConfigBooleanHotkeyed {
    public static boolean defaultEnabled = true;
    {
        getKeybind().setCallback((keyAction, keybind) -> {
            setBooleanValue(!getBooleanValue());
            return true;
        });
    }

    public ConfigBooleanPlus(String name) {
        this(name, defaultEnabled, "", "");
    }

    public ConfigBooleanPlus(String name, boolean defaultValue) {
        this(name, defaultValue, "", "");
    }

    public ConfigBooleanPlus(String name, boolean defaultValue, String defaultHotkey) {
        this(name, defaultValue, defaultHotkey, "");
    }

    public ConfigBooleanPlus(String name, boolean defaultValue, String defaultHotkey, String comment) {
        super(name, defaultValue, defaultHotkey, comment.isEmpty() ? comment + " " : comment);
    }

    public ConfigBooleanPlus(String name, boolean defaultValue, String defaultHotkey, String comment, String prettyName) {
        super(name, defaultValue, defaultHotkey, comment.isEmpty() ? comment + " " : comment, prettyName);
    }

    public ConfigBooleanPlus(String name, boolean defaultValue, String defaultHotkey, String comment, String prettyName, String translatedName) {
        super(name, defaultValue, defaultHotkey, comment.isEmpty() ? comment + " " : comment, prettyName, translatedName);
    }

    public ConfigBooleanPlus(String name, boolean defaultValue, String defaultHotkey, KeybindSettings settings) {
        super(name, defaultValue, defaultHotkey, settings);
    }

    public ConfigBooleanPlus(String name, boolean defaultValue, String defaultHotkey, KeybindSettings settings, String comment) {
        super(name, defaultValue, defaultHotkey, settings, comment.isEmpty() ? comment + " " : comment);
    }

    public ConfigBooleanPlus(String name, boolean defaultValue, String defaultHotkey, KeybindSettings settings, String comment, String prettyName) {
        super(name, defaultValue, defaultHotkey, settings, comment.isEmpty() ? comment + " " : comment, prettyName);
    }

    public ConfigBooleanPlus(String name, boolean defaultValue, String defaultHotkey, KeybindSettings settings, String comment, String prettyName, String translatedName) {
        super(name, defaultValue, defaultHotkey, settings, comment.isEmpty() ? comment + " " : comment, prettyName, translatedName);
    }
}
