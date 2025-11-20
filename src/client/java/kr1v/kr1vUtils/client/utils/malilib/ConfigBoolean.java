package kr1v.kr1vUtils.client.utils.malilib;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import fi.dy.masa.malilib.MaLiLib;
import fi.dy.masa.malilib.config.IConfigBoolean;
import fi.dy.masa.malilib.config.IHotkeyTogglable;
import fi.dy.masa.malilib.config.options.ConfigBase;
import fi.dy.masa.malilib.hotkeys.IKeybind;

public class ConfigBoolean extends ConfigBase<ConfigBoolean> implements IConfigBoolean {
    public ConfigBoolean(String name, boolean defaultValue, String comment, String prettyName, String translatedName) {
        super(null, name, comment, prettyName, translatedName);
        this.defaultValue = defaultValue;
        this.value = defaultValue;
    }

    private final boolean defaultValue;
    private boolean value;

    @Override
    public boolean getBooleanValue() {
        return value;
    }

    @Override
    public boolean getDefaultBooleanValue() {
        return defaultValue;
    }

    @Override
    public void setBooleanValue(boolean value) {
        this.value = value;
    }

    /**
     * Toggles the boolean value.
     * @return The new boolean value after toggling.
     */
    public boolean toggle() {
        this.value = !this.value;
        return this.value;
    }

    @Override
    public void setValueFromJsonElement(JsonElement element) {
        try {
            JsonObject jsonObject = element.getAsJsonObject();
            this.value = jsonObject.get("value").getAsBoolean();
        } catch (Exception e) {
            MaLiLib.LOGGER.warn("Failed to set config value for '{}' from the JSON element '{}'", this.getName(), element);
        }
    }

    @Override
    public JsonElement getAsJsonElement() {
        return null;
    }

    @Override
    public boolean isModified() {
        return false;
    }

    @Override
    public void resetToDefault() {

    }

    @Override
    public String getDefaultStringValue() {
        return "";
    }

    @Override
    public void setValueFromString(String value) {

    }

    @Override
    public boolean isModified(String newValue) {
        return false;
    }

    @Override
    public String getStringValue() {
        return "";
    }
}
