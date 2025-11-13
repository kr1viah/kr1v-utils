package kr1v.kr1vUtils.client.utils.malilib;

import fi.dy.masa.malilib.config.options.ConfigFloat;
import kr1v.kr1vUtils.client.config.ConfigHandler;

public class ConfigFloatPlus extends ConfigFloat implements Plus {
    @Override
    public boolean shouldHandle() {
        if (ConfigHandler.dependantOns.containsKey(this)) {
            String[] dependencyNames = ConfigHandler.dependantOns.get(this);
            for (String dependencyName : dependencyNames) {
                ConfigBooleanPlus dependencyConfig = ConfigHandler.dependencies.get(dependencyName);
                if (!dependencyConfig.shouldHandle()) {
                    return false;
                }
            }
        }
        return true;
    }

    public ConfigFloatPlus(String name) {
        super(name, 0.0f);
    }

    public ConfigFloatPlus(String name, float defaultValue) {
        super(name, defaultValue);
    }

    public ConfigFloatPlus(String name, float defaultValue, String comment) {
        super(name, defaultValue, comment);
    }

    public ConfigFloatPlus(String name, float defaultValue, String comment, String prettyName) {
        super(name, defaultValue, comment, prettyName);
    }

    public ConfigFloatPlus(String name, float defaultValue, String comment, String prettyName, String translatedName) {
        super(name, defaultValue, comment, prettyName, translatedName);
    }

    public ConfigFloatPlus(String name, float defaultValue, float minValue, float maxValue) {
        super(name, defaultValue, minValue, maxValue);
    }

    public ConfigFloatPlus(String name, float defaultValue, float minValue, float maxValue, String comment) {
        super(name, defaultValue, minValue, maxValue, comment);
    }

    public ConfigFloatPlus(String name, float defaultValue, float minValue, float maxValue, String comment, String prettyName) {
        super(name, defaultValue, minValue, maxValue, comment, prettyName);
    }

    public ConfigFloatPlus(String name, float defaultValue, float minValue, float maxValue, boolean useSlider) {
        super(name, defaultValue, minValue, maxValue, useSlider);
    }

    public ConfigFloatPlus(String name, float defaultValue, float minValue, float maxValue, boolean useSlider, String comment) {
        super(name, defaultValue, minValue, maxValue, useSlider, comment);
    }

    public ConfigFloatPlus(String name, float defaultValue, float minValue, float maxValue, boolean useSlider, String comment, String prettyName) {
        super(name, defaultValue, minValue, maxValue, useSlider, comment, prettyName);
    }

    public ConfigFloatPlus(String name, float defaultValue, float minValue, float maxValue, boolean useSlider, String comment, String prettyName, String translatedName) {
        super(name, defaultValue, minValue, maxValue, useSlider, comment, prettyName, translatedName);
    }
}
