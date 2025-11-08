package kr1v.kr1vUtils.client.config;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import fi.dy.masa.malilib.config.ConfigUtils;
import fi.dy.masa.malilib.config.IConfigBase;
import fi.dy.masa.malilib.config.IConfigHandler;
import fi.dy.masa.malilib.config.options.ConfigBooleanHotkeyed;
import fi.dy.masa.malilib.hotkeys.KeybindMulti;
import fi.dy.masa.malilib.util.JsonUtils;
import kr1v.kr1vUtils.client.Kr1vUtilsClient;
import kr1v.kr1vUtils.client.gui.screen.ConfigScreen;
import kr1v.kr1vUtils.client.utils.Annotations;
import net.minecraft.client.MinecraftClient;

import java.io.File;
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
					ConfigScreen.tab = ConfigScreen.ConfigGuiTab.valueOf(root.get("lastTab").getAsString());
				} else if (ConfigScreen.tab == null) {
					ConfigScreen.tab = ConfigScreen.ConfigGuiTab.MISC;
				}

				if (root.has("scrollPositions") &&
					root.get("scrollPositions").isJsonObject()) {
					Map<String, JsonElement> scrollPositions = root.getAsJsonObject("scrollPositions").asMap();
					for (Map.Entry<String, JsonElement> entry : scrollPositions.entrySet()) {
						Misc.tabToScrollPosition.put(entry.getKey(), entry.getValue().getAsInt());
					}
				}

				ConfigUtils.readConfigBase(root, "Chat", Annotations.configsFor(Chat.class));
				ConfigUtils.readConfigBase(root, "Debug", Annotations.configsFor(Debug.class));
				ConfigUtils.readConfigBase(root, "Keys", Annotations.configsFor(Keys.class));
				ConfigUtils.readConfigBase(root, "Misc", Annotations.configsFor(Misc.class));
				ConfigUtils.readConfigBase(root, "Render", Annotations.configsFor(Render.class));
				ConfigUtils.readConfigBase(root, "Screen", Annotations.configsFor(Screen.class));
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

			ConfigUtils.writeConfigBase(root, "Chat", Annotations.configsFor(Chat.class));
			ConfigUtils.writeConfigBase(root, "Debug", Annotations.configsFor(Debug.class));
			ConfigUtils.writeConfigBase(root, "Keys", Annotations.configsFor(Keys.class));
			ConfigUtils.writeConfigBase(root, "Misc", Annotations.configsFor(Misc.class));
			ConfigUtils.writeConfigBase(root, "Render", Annotations.configsFor(Render.class));
			ConfigUtils.writeConfigBase(root, "Screen", Annotations.configsFor(Screen.class));

			JsonUtils.writeJsonToFile(root, new File(dir, CONFIG_FILE_NAME));
		}
	}

	public static void addToggleHotkey(ConfigBooleanHotkeyed cbh) {
		if (((KeybindMulti) cbh.getKeybind()).getCallback() == null) {
			cbh.getKeybind().setCallback((keyAction, keybind) -> {
				cbh.setBooleanValue(!cbh.getBooleanValue());
				return true;
			});
		}
	}

	public static List<IConfigBase> generateOptions(Class<?> clazz) {
		List<IConfigBase> list = new ArrayList<>();
		for (Field f : clazz.getDeclaredFields()) {
			int mods = f.getModifiers();
			if (Modifier.isStatic(mods) && IConfigBase.class.isAssignableFrom(f.getType())) {
				try {
					f.setAccessible(true);
					IConfigBase value = (IConfigBase) f.get(null);
					if (value != null) {
                        list.add(value);

						if (value instanceof ConfigBooleanHotkeyed cbh) {
							addToggleHotkey(cbh);
						}
					}
				} catch (IllegalAccessException e) {
					throw new RuntimeException(e);
				}
			}
		}
		return list;
	}
}
