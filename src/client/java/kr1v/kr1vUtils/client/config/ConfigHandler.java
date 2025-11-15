package kr1v.kr1vUtils.client.config;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import fi.dy.masa.malilib.config.ConfigUtils;
import fi.dy.masa.malilib.config.IConfigBase;
import fi.dy.masa.malilib.config.IConfigHandler;
import fi.dy.masa.malilib.util.JsonUtils;
import kr1v.kr1vUtils.client.Kr1vUtilsClient;
import kr1v.kr1vUtils.client.config.configs.Misc;
import kr1v.kr1vUtils.client.gui.screen.ConfigScreen;
import kr1v.kr1vUtils.client.malilib.ConfigLabel;
import kr1v.kr1vUtils.client.utils.Annotations;
import kr1v.kr1vUtils.client.utils.annotation.DependantOn;
import kr1v.kr1vUtils.client.utils.annotation.Dependency;
import kr1v.kr1vUtils.client.utils.annotation.Label;
import kr1v.kr1vUtils.client.utils.malilib.ConfigBooleanPlus;
import net.minecraft.client.MinecraftClient;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
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
		for (Field f : clazz.getDeclaredFields()) {
			int mods = f.getModifiers();
			if (Modifier.isStatic(mods) && IConfigBase.class.isAssignableFrom(f.getType())) {
				try {
                    f.setAccessible(true);
                    handleConfigAnnotations(f, list);
					IConfigBase value = (IConfigBase) f.get(null);
					if (value != null) {
                        list.add(value);
					}
				} catch (IllegalAccessException e) {
					throw new RuntimeException(e);
				}
			}
		}
		return list;
	}

    private static void handleConfigAnnotations(Field f, List<IConfigBase> list) throws IllegalAccessException {
        System.out.println("Handling field: " + f.getName());
        for (Annotation annotation : f.getDeclaredAnnotations()) {
            handleConfigAnnotation(f, annotation, list);
        }
    }

    private static void handleConfigAnnotation(Field f, Annotation annotation, List<IConfigBase> list) throws IllegalAccessException {
        System.out.println("   Handling annotation " + annotation.toString());
        switch (annotation) {
            case Label label -> list.add(new ConfigLabel(label.value()));
            case DependantOn dependantOn -> dependantOns.put((IConfigBase) f.get(null), dependantOn.value());
            case Dependency dependency -> dependencies.put(dependency.value(), (ConfigBooleanPlus) f.get(null));
            default -> {}
        }
    }

    public static void addDependantOn(IConfigBase config, String... dependencies) {
        dependantOns.put(config, dependencies);
    }

    public static final Map<IConfigBase, String[]> dependantOns = new HashMap<>();
    public static final Map<String, ConfigBooleanPlus> dependencies = new HashMap<>();
}
