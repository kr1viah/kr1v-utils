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
import kr1v.kr1vUtils.client.utils.ClassUtils;
import kr1v.kr1vUtils.client.utils.annotation.fieldannotations.Label;
import kr1v.kr1vUtils.client.utils.annotation.classannotations.PopupConfig;
import kr1v.kr1vUtils.client.utils.annotation.fieldannotations.Marker;
import kr1v.kr1vUtils.client.utils.annotation.methodannotations.Extras;
import kr1v.kr1vUtils.client.utils.malilib.configbutton.ConfigButton;
import kr1v.kr1vUtils.client.utils.malilib.plus.ConfigBooleanPlus;
import net.minecraft.client.MinecraftClient;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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

        List<ClassUtils.Element> elements = ClassUtils.getDeclaredElements(clazz);

        try {
            for (ClassUtils.Element element : elements) {
                handleAnnotations(element, list, clazz);
                if (element.field != null) {
                    Field f = element.field;
                    if (IConfigBase.class.isAssignableFrom(f.getType())) {
                        f.setAccessible(true);
                        IConfigBase value = (IConfigBase) f.get(null);
                        if (value != null) {
                            list.add(value);
                        }
                    }
                }
            }
        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    private static void handleAnnotations(ClassUtils.Element element, List<IConfigBase> list, Class<?> declaringClass) throws InvocationTargetException, IllegalAccessException {
        for (Annotation annotation : element.annotations) {
            switch (annotation) {
                case PopupConfig popupConfig -> {
                    assert element.aClass != null;
                    Class<?> klass = element.aClass;
                    String name = popupConfig.name();
                    if (name.isEmpty()) {
                        name = klass.getSimpleName();
                    }
                    String buttonName = popupConfig.buttonName();
                    if (buttonName.isEmpty()) {
                        buttonName = "Edit " + name;
                    }

                    ConfigButton<Class<?>> configButton = new ConfigButton<>(name, buttonName, () ->
                            MinecraftClient.getInstance().setScreen(
                                    new ConfigPopupScreen(klass, GuiUtils.getCurrentScreen())
                            ),
                            klass);

                    boolean prev = ConfigBooleanPlus.defaultEnabled;
                    ConfigBooleanPlus.defaultEnabled = klass.getAnnotation(PopupConfig.class).defaultEnabled();
                    Annotations.CACHE.put(klass, generateOptions(klass));
                    ConfigBooleanPlus.defaultEnabled = prev;
                    list.add(configButton);
                }
                case Label label ->
                        list.add(new ConfigLabel(label.value()));
                case Extras extras -> {
                    if (extras.runAt().isEmpty()) {
                        element.method.invoke(null, list);
                    }
                }
                case Marker marker -> {
                    for (Method m : declaringClass.getDeclaredMethods()) {
                        if (m.isAnnotationPresent(Extras.class)) {
                            Extras extras = m.getAnnotation(Extras.class);
                            if (marker.value().equals(extras.runAt())) {
                                m.invoke(null, list);
                            }
                        }
                    }
                }
                default -> {}
            }
        }
    }

}
