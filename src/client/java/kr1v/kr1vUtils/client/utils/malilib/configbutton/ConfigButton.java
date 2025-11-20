package kr1v.kr1vUtils.client.utils.malilib.configbutton;

import com.google.gson.JsonElement;
import fi.dy.masa.malilib.config.IConfigBoolean;
import fi.dy.masa.malilib.config.options.ConfigBase;

public class ConfigButton extends ConfigBase<ConfigButton> implements IConfigBoolean {
    public ConfigButton(String name, String comment, String prettyName, String translatedName, Runnable onPressed, String buttonDisplayName) {
        super(null, name, comment, prettyName, translatedName);
        this.displayName = buttonDisplayName;
        this.onPressed = onPressed;
    }

    private final Runnable onPressed;
    public final String displayName;

    @Override
    public void setValueFromJsonElement(JsonElement element) {}

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

    public void execute() {
        if (this.onPressed != null) {
            this.onPressed.run();
        }
    }









    @Override
    public boolean getBooleanValue() {
        return false;
    }

    @Override
    public boolean getDefaultBooleanValue() {
        return false;
    }

    @Override
    public void setBooleanValue(boolean value) {

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
