package kr1v.kr1vUtils.client.utils.malilib.plus;

import fi.dy.masa.malilib.config.options.ConfigInteger;
import kr1v.kr1vUtils.client.config.ConfigHandler;

public class ConfigIntegerPlus extends ConfigInteger implements Plus {
    public ConfigIntegerPlus(String name) {
        super(name, 0);
    }

    public ConfigIntegerPlus(String name, int defaultValue) {
        super(name, defaultValue);
    }

    public ConfigIntegerPlus(String name, int defaultValue, String comment) {
        super(name, defaultValue, comment);
    }

    public ConfigIntegerPlus(String name, int defaultValue, String comment, String prettyName) {
        super(name, defaultValue, comment, prettyName);
    }

    public ConfigIntegerPlus(String name, int defaultValue, String comment, String prettyName, String translatedName) {
        super(name, defaultValue, comment, prettyName, translatedName);
    }

    public ConfigIntegerPlus(String name, int defaultValue, int minValue, int maxValue) {
        super(name, defaultValue, minValue, maxValue);
    }

    public ConfigIntegerPlus(String name, int defaultValue, int minValue, int maxValue, String comment) {
        super(name, defaultValue, minValue, maxValue, comment);
    }

    public ConfigIntegerPlus(String name, int defaultValue, int minValue, int maxValue, String comment, String prettyName) {
        super(name, defaultValue, minValue, maxValue, comment, prettyName);
    }

    public ConfigIntegerPlus(String name, int defaultValue, int minValue, int maxValue, String comment, String prettyName, String translatedName) {
        super(name, defaultValue, minValue, maxValue, comment, prettyName, translatedName);
    }

    public ConfigIntegerPlus(String name, int defaultValue, int minValue, int maxValue, boolean useSlider) {
        super(name, defaultValue, minValue, maxValue, useSlider);
    }

    public ConfigIntegerPlus(String name, int defaultValue, int minValue, int maxValue, boolean useSlider, String comment) {
        super(name, defaultValue, minValue, maxValue, useSlider, comment);
    }

    public ConfigIntegerPlus(String name, int defaultValue, int minValue, int maxValue, boolean useSlider, String comment, String prettyName) {
        super(name, defaultValue, minValue, maxValue, useSlider, comment, prettyName);
    }

    public ConfigIntegerPlus(String name, int defaultValue, int minValue, int maxValue, boolean useSlider, String comment, String prettyName, String translatedName) {
        super(name, defaultValue, minValue, maxValue, useSlider, comment, prettyName, translatedName);
    }
}
