package kr1v.kr1vUtils.client.utils.malilib;

import fi.dy.masa.malilib.config.options.ConfigBoolean;
import fi.dy.masa.malilib.config.options.ConfigBooleanHotkeyed;
import fi.dy.masa.malilib.hotkeys.IHotkeyCallback;
import fi.dy.masa.malilib.hotkeys.KeybindSettings;
import fi.dy.masa.malilib.interfaces.IValueChangeCallback;

import static kr1v.kr1vUtils.client.config.ConfigHandler.dependantOns;
import static kr1v.kr1vUtils.client.config.ConfigHandler.dependencies;

public class ConfigBooleanPlus extends ConfigBooleanHotkeyed implements Plus {
    public static boolean defaultEnabled = true;

    @Override
    public boolean shouldHandle() {
        if (!getBooleanValue()) {
            return false;
        }
        if (dependantOns.containsKey(this)) {
            String[] dependencyNames = dependantOns.get(this);
            for (String dependencyName : dependencyNames) {
                ConfigBooleanPlus dependencyConfig = dependencies.get(dependencyName);
                if (!dependencyConfig.shouldHandle()) {
                    return false;
                }
            }
        }
        return true;
    }
    public boolean shouldHandleNoThis() {
        if (dependantOns.containsKey(this)) {
            String[] dependencyNames = dependantOns.get(this);
            for (String dependencyName : dependencyNames) {
                ConfigBooleanPlus dependencyConfig = dependencies.get(dependencyName);
                if (!dependencyConfig.shouldHandle()) {
                    return false;
                }
            }
        }
        return true;
    }

    {
        getKeybind().setCallback((keyAction, keybind) -> {
            if (!shouldHandleNoThis()) return false;
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
        super(name, defaultValue, defaultHotkey, comment.isEmpty() ? " " : comment);
    }

    public ConfigBooleanPlus(String name, IHotkeyCallback callback) {
        this(name, defaultEnabled, "", "");
        getKeybind().setCallback(callback);
    }

    public ConfigBooleanPlus(String name, boolean defaultValue, IHotkeyCallback callback) {
        this(name, defaultValue, "", "");
        getKeybind().setCallback(callback);
    }

    public ConfigBooleanPlus(String name, boolean defaultValue, String defaultHotkey, IHotkeyCallback callback) {
        this(name, defaultValue, defaultHotkey, "");
        getKeybind().setCallback(callback);
    }

    public ConfigBooleanPlus(String name, boolean defaultValue, String defaultHotkey, String comment, IHotkeyCallback callback) {
        super(name, defaultValue, defaultHotkey, comment.isEmpty() ? " " : comment);
        getKeybind().setCallback(callback);
    }

    public ConfigBooleanPlus(String name, boolean defaultValue, String defaultHotkey, KeybindSettings settings, String comment, String prettyName, String translatedName) {
        super(name, defaultValue, defaultHotkey, settings, comment.isEmpty() ? " " : comment, prettyName, translatedName);
    }

    public ConfigBooleanPlus(String name, IValueChangeCallback<ConfigBoolean> callback) {
        this(name, defaultEnabled, "", "");
        setValueChangeCallback(callback);
    }

    public ConfigBooleanPlus(String name, boolean defaultValue, IValueChangeCallback<ConfigBoolean> callback) {
        this(name, defaultValue, "", "");
        setValueChangeCallback(callback);
    }

    public ConfigBooleanPlus(String name, boolean defaultValue, String defaultHotkey, IValueChangeCallback<ConfigBoolean> callback) {
        this(name, defaultValue, defaultHotkey, "");
        setValueChangeCallback(callback);
    }

    public ConfigBooleanPlus(String name, boolean defaultValue, String defaultHotkey, String comment, IValueChangeCallback<ConfigBoolean> callback) {
        super(name, defaultValue, defaultHotkey, comment.isEmpty() ? " " : comment);
        setValueChangeCallback(callback);
    }
}
