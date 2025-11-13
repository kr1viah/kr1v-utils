package kr1v.kr1vUtils.client.utils.malilib;

import com.google.common.collect.ImmutableList;
import fi.dy.masa.malilib.config.options.ConfigStringList;
import kr1v.kr1vUtils.client.config.ConfigHandler;

import java.util.ArrayList;
import java.util.List;

public class ConfigStringListPlus extends ConfigStringList implements Plus {
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

    @Override
    public List<String> getStrings() {
        if (shouldHandle())
            return super.getStrings();
        else
            return new ArrayList<>();
    }

    public ConfigStringListPlus(String name) {
        super(name, ImmutableList.of());
    }

    public ConfigStringListPlus(String name, String comment) {
        super(name, ImmutableList.of(), comment);
    }

    public ConfigStringListPlus(String name, ImmutableList<String> defaultValue) {
        super(name, defaultValue);
    }

    public ConfigStringListPlus(String name, ImmutableList<String> defaultValue, String comment) {
        super(name, defaultValue, comment);
    }

    public ConfigStringListPlus(String name, ImmutableList<String> defaultValue, String comment, String prettyName) {
        super(name, defaultValue, comment, prettyName);
    }

    public ConfigStringListPlus(String name, ImmutableList<String> defaultValue, String comment, String prettyName, String translatedName) {
        super(name, defaultValue, comment, prettyName, translatedName);
    }
}
