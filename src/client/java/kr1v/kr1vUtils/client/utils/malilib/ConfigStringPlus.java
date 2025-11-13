package kr1v.kr1vUtils.client.utils.malilib;

import fi.dy.masa.malilib.config.options.ConfigString;
import kr1v.kr1vUtils.client.config.ConfigHandler;

public class ConfigStringPlus extends ConfigString implements Plus {
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

    public ConfigStringPlus(String name) {
        super(name, "");
    }

    public ConfigStringPlus(String name, String defaultValue) {
        super(name, defaultValue);
    }

    public ConfigStringPlus(String name, String defaultValue, String comment) {
        super(name, defaultValue, comment);
    }

    public ConfigStringPlus(String name, String defaultValue, String comment, String prettyName) {
        super(name, defaultValue, comment, prettyName);
    }

    public ConfigStringPlus(String name, String defaultValue, String comment, String prettyName, String translatedName) {
        super(name, defaultValue, comment, prettyName, translatedName);
    }
}
