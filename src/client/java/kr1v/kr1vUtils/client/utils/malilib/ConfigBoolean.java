package kr1v.kr1vUtils.client.utils.malilib;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import fi.dy.masa.malilib.MaLiLib;
import fi.dy.masa.malilib.config.IConfigBoolean;
import fi.dy.masa.malilib.config.options.ConfigBase;
import fi.dy.masa.malilib.config.options.ConfigHotkey;
import fi.dy.masa.malilib.hotkeys.IKeybind;
import fi.dy.masa.malilib.hotkeys.KeybindMulti;

public class ConfigBoolean extends ConfigBase<ConfigBoolean> implements IConfigBoolean {
    public ConfigBoolean(String name, boolean defaultValue, String comment, String prettyName, String translatedName) {
        super(null, name, comment, prettyName, translatedName);
        this.defaultValue = defaultValue;
        this.value = defaultValue;
    }

    private final boolean defaultValue;
    private boolean value;

    private IKeybind toggleKeybind;

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
            this.toggleKeybind.setValueFromJsonElement(jsonObject.get("toggleKeybind"));
        } catch (Exception e) {
            MaLiLib.LOGGER.warn("Failed to set config value for '{}' from the JSON element '{}'", this.getName(), element);
        }
    }

    @Override
    public JsonElement getAsJsonElement() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("value", this.value);
        jsonObject.add("toggleKeybind", this.toggleKeybind.getAsJsonElement());
        return jsonObject;
    }

    @Override
    public boolean isModified() {
        return this.value != this.defaultValue;
    }

    @Override
    public void resetToDefault() {
        this.value = this.defaultValue;
    }

    @Override
    public String getDefaultStringValue() {
        return Boolean.toString(this.defaultValue);
    }

    @Override
    public void setValueFromString(String value) {
        this.value = Boolean.parseBoolean(value);
    }

    @Override
    public boolean isModified(String newValue) {
        return !Boolean.toString(this.value).equals(newValue);
    }

    @Override
    public String getStringValue() {
        return Boolean.toString(this.value);
    }
}
