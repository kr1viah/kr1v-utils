package kr1v.kr1vUtils.client.config;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import fi.dy.masa.malilib.config.ConfigUtils;
import fi.dy.masa.malilib.config.IConfigBase;
import fi.dy.masa.malilib.config.IConfigHandler;
import fi.dy.masa.malilib.util.GuiUtils;
import fi.dy.masa.malilib.util.JsonUtils;
import kr1v.kr1vUtils.client.Kr1vUtilsClient;
import kr1v.kr1vUtils.client.gui.screen.ConfigPopupScreen;
import kr1v.kr1vUtils.client.malilib.ConfigLabel;
import kr1v.kr1vUtils.client.utils.Annotations;
import kr1v.kr1vUtils.client.utils.annotation.fieldannotations.Label;
import kr1v.kr1vUtils.client.utils.annotation.classannotations.PopupConfig;
import kr1v.kr1vUtils.client.utils.annotation.fieldannotations.Labels;
import kr1v.kr1vUtils.client.utils.annotation.methodannotations.Extras;
import kr1v.kr1vUtils.client.utils.malilib.configbutton.ConfigButton;
import kr1v.kr1vUtils.client.utils.malilib.plus.ConfigBooleanPlus;
import net.minecraft.client.MinecraftClient;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class ConfigHandler implements IConfigHandler {
    private static final String CONFIG_FILE_NAME = Kr1vUtilsClient.MOD_ID + ".json";

    @Override
    public void load() {
        File configFile = new File(MinecraftClient.getInstance().runDirectory, "config/" + CONFIG_FILE_NAME);

        if (configFile.exists() && configFile.isFile() && configFile.canRead()) {
            JsonElement element = JsonUtils.parseJsonFile(configFile);

            if (element != null && element.isJsonObject()) {
                JsonObject root = element.getAsJsonObject();


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
                    handleFieldAnnotations(f, list);
                    if (IConfigBase.class.isAssignableFrom(f.getType())) {
                        f.setAccessible(true);
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

            for (Method m : clazz.getDeclaredMethods()) {
                if (m.isAnnotationPresent(Extras.class)) {
                    m.invoke(null, list);
                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException("Method descriptor probably doesn't match.", e);
        }
        return list;
    }

    private static <T> void handleClassAnnotations(Class<T> value, List<IConfigBase> list) {
        PopupConfig annotation = value.getAnnotation(PopupConfig.class);

        String name = annotation.name();
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

    private static void handleFieldAnnotation(Field f, Annotation annotation, List<IConfigBase> list) {
        switch (annotation) {
            case Label label -> list.add(new ConfigLabel(label.value()));
            case Labels labels -> {
                for (Label label : labels.value()) {
                    list.add(new ConfigLabel(label.value()));
                }
            }
            default -> {}
        }
    }
}
