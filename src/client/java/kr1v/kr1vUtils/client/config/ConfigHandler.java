package kr1v.kr1vUtils.client.config;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import fi.dy.masa.malilib.config.ConfigUtils;
import fi.dy.masa.malilib.config.IConfigBase;
import fi.dy.masa.malilib.config.IConfigHandler;
import fi.dy.masa.malilib.util.GuiUtils;
import fi.dy.masa.malilib.util.JsonUtils;
import kr1v.kr1vUtils.client.Kr1vUtilsClient;
import kr1v.kr1vUtils.client.config.configs.Misc;
import kr1v.kr1vUtils.client.gui.screen.ConfigPopupScreen;
import kr1v.kr1vUtils.client.gui.screen.ConfigScreen;
import kr1v.kr1vUtils.client.malilib.ConfigLabel;
import kr1v.kr1vUtils.client.utils.Annotations;
import kr1v.kr1vUtils.client.utils.annotation.Label;
import kr1v.kr1vUtils.client.utils.annotation.PopupConfig;
import kr1v.kr1vUtils.client.utils.malilib.configbutton.ConfigButton;
import kr1v.kr1vUtils.client.utils.malilib.plus.ConfigBooleanPlus;
import net.minecraft.client.MinecraftClient;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ConfigHandler implements IConfigHandler {
    private static final String CONFIG_FILE_NAME = Kr1vUtilsClient.MOD_ID + ".json";

    @Override
    public void load() {
        File configFile = new File(MinecraftClient.getInstance().runDirectory, "config/" + CONFIG_FILE_NAME);

        if (configFile.exists() && configFile.isFile() && configFile.canRead()) {
            JsonElement element = JsonUtils.parseJsonFile(configFile);

            if (element != null && element.isJsonObject()) {
                JsonObject root = element.getAsJsonObject();

                if (ConfigScreen.tab == null && root.has("lastTab")) {
                    ConfigScreen.setTab(ConfigScreen.ConfigGuiTab.valueOf(root.get("lastTab").getAsString()));
                } else if (ConfigScreen.tab == null) {
                    ConfigScreen.setTab(ConfigScreen.ConfigGuiTab.values()[0]);
                    if (ConfigScreen.tab == null) {
                        throw new IllegalStateException("Something went wrong.");
                    }
                }

                if (root.has("scrollPositions") &&
                        root.get("scrollPositions").isJsonObject()) {
                    Map<String, JsonElement> scrollPositions = root.getAsJsonObject("scrollPositions").asMap();
                    for (Map.Entry<String, JsonElement> entry : scrollPositions.entrySet()) {
                        Misc.tabToScrollPosition.put(entry.getKey(), entry.getValue().getAsInt());
                    }
                }

                for (Class<?> configClass : Annotations.CACHE.keySet()) {
                    ConfigUtils.readConfigBase(root, configClass.getSimpleName(), Annotations.configsFor(configClass));
                }
            }
        }
    }

    @Override
    public void save() {
        File dir = new File(MinecraftClient.getInstance().runDirectory, "config");

        if ((dir.exists() && dir.isDirectory()) || dir.mkdirs()) {
            JsonObject root = new JsonObject();
            root.addProperty("lastTab", ConfigScreen.tab.toString());

            JsonObject scrollPositions = new JsonObject();
            for (Map.Entry<String, Integer> entry : Misc.tabToScrollPosition.entrySet()) {
                scrollPositions.add(entry.getKey(), new JsonPrimitive(entry.getValue()));
            }
            root.add("scrollPositions", scrollPositions);

            for (Class<?> configClass : Annotations.CACHE.keySet()) {
                ConfigUtils.writeConfigBase(root, configClass.getSimpleName(), Annotations.configsFor(configClass));
            }
            JsonUtils.writeJsonToFile(root, new File(dir, CONFIG_FILE_NAME));
        }
    }

    public static List<IConfigBase> generateOptions(Class<?> clazz) {
        List<IConfigBase> list = new ArrayList<>();
        try {
            for (Field f : clazz.getDeclaredFields()) {
                int mods = f.getModifiers();
                if (Modifier.isStatic(mods)) {
                    if (IConfigBase.class.isAssignableFrom(f.getType())) {
                        f.setAccessible(true);
                        handleFieldAnnotations(f, list);
                        IConfigBase value = (IConfigBase) f.get(null);
                        if (value != null) {
                            list.add(value);
                        }
                    } else if (Class.class.isAssignableFrom(f.getType())) {
                        f.setAccessible(true);
                        Class<?> klass = (Class<?>)f.get(null);

                        if (klass.isAnnotationPresent(PopupConfig.class)) {
                            boolean prev = ConfigBooleanPlus.defaultEnabled;
                            ConfigBooleanPlus.defaultEnabled = klass.getAnnotation(PopupConfig.class).defaultEnabled();
                            Annotations.CACHE.put(klass, generateOptions(klass));
                            ConfigBooleanPlus.defaultEnabled = prev;
                            handleClassAnnotations(klass, list);
                        }
                    }
                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    private static <T> void handleClassAnnotations(Class<T> value, List<IConfigBase> list) {
        PopupConfig annotation = value.getAnnotation(PopupConfig.class);

        String name = annotation.value();
        if (name.isEmpty()) {
            name = value.getSimpleName();
        }
        String buttonName = annotation.buttonName();
        if (buttonName.isEmpty()) {
            buttonName = "Edit " + name;
        }

        ConfigButton<Class<T>> configButton = new ConfigButton<>(name, buttonName, () ->
                MinecraftClient.getInstance().setScreen(
                        new ConfigPopupScreen(value, GuiUtils.getCurrentScreen())
                ),
                value);

        list.add(configButton);
    }

    private static void handleFieldAnnotations(Field f, List<IConfigBase> list) throws IllegalAccessException {
        for (Annotation annotation : f.getDeclaredAnnotations()) {
            handleFieldAnnotation(f, annotation, list);
        }
    }

    @SuppressWarnings("SwitchStatementWithTooFewBranches")
    private static void handleFieldAnnotation(Field f, Annotation annotation, List<IConfigBase> list) {
        switch (annotation) {
            case Label label -> list.add(new ConfigLabel(label.value()));
            default -> {}
        }
    }
}
