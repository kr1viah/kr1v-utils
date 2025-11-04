package kr1v.kr1vUtils.client.config;

import com.google.common.collect.ImmutableList;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import fi.dy.masa.malilib.config.ConfigUtils;
import fi.dy.masa.malilib.config.IConfigBase;
import fi.dy.masa.malilib.config.IConfigHandler;
import fi.dy.masa.malilib.config.options.ConfigBooleanHotkeyed;
import fi.dy.masa.malilib.hotkeys.KeybindMulti;
import fi.dy.masa.malilib.util.JsonUtils;
import kr1v.kr1vUtils.client.Kr1vUtilsClient;
import net.minecraft.client.MinecraftClient;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class ConfigHandler implements IConfigHandler {
	private static final String CONFIG_FILE_NAME = Kr1vUtilsClient.MOD_ID + ".json";

	@Override
	public void load() {
		File configFile = new File(MinecraftClient.getInstance().runDirectory, "config/" + CONFIG_FILE_NAME);

		if (configFile.exists() && configFile.isFile() && configFile.canRead()) {
			JsonElement element = JsonUtils.parseJsonFile(configFile);

			if (element != null && element.isJsonObject()) {
				JsonObject root = element.getAsJsonObject();

				ConfigUtils.readConfigBase(root, "Chat", Chat.OPTIONS);
				ConfigUtils.readConfigBase(root, "Debug", Debug.OPTIONS);
				ConfigUtils.readConfigBase(root, "Keys", Keys.OPTIONS);
				ConfigUtils.readConfigBase(root, "Misc", Misc.OPTIONS);
				ConfigUtils.readConfigBase(root, "Render", Render.OPTIONS);
				ConfigUtils.readConfigBase(root, "Screen", Screen.OPTIONS);
			}
		}
	}

	@Override
	public void save() {
		File dir = new File(MinecraftClient.getInstance().runDirectory, "config");

		if ((dir.exists() && dir.isDirectory()) || dir.mkdirs()) {
			JsonObject root = new JsonObject();

			ConfigUtils.writeConfigBase(root, "Chat", Chat.OPTIONS);
			ConfigUtils.writeConfigBase(root, "Debug", Debug.OPTIONS);
			ConfigUtils.writeConfigBase(root, "Keys", Keys.OPTIONS);
			ConfigUtils.writeConfigBase(root, "Misc", Misc.OPTIONS);
			ConfigUtils.writeConfigBase(root, "Render", Render.OPTIONS);
			ConfigUtils.writeConfigBase(root, "Screen", Screen.OPTIONS);

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

	public static ImmutableList<IConfigBase> generateOptions() {
		Class<?> clazz = StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE)
			.walk(frames -> frames
				.skip(1)
				.findFirst()
				.map(StackWalker.StackFrame::getDeclaringClass)
				.orElse(null));
		return generateOptions(clazz);
	}

	public static ImmutableList<IConfigBase> generateOptions(Class<?> clazz) {
		ImmutableList.Builder<IConfigBase> ilb = ImmutableList.builder();

		for (Field f : clazz.getDeclaredFields()) {
			int mods = f.getModifiers();
			if (Modifier.isStatic(mods) && IConfigBase.class.isAssignableFrom(f.getType())) {
				try {
					f.setAccessible(true);
					IConfigBase value = (IConfigBase) f.get(null);
					if (value != null) {
						ilb.add(value);

						if (value instanceof ConfigBooleanHotkeyed cbh) {
							addToggleHotkey(cbh);
						}
					}
				} catch (IllegalAccessException e) {
					throw new RuntimeException(e);
				}
			}
		}
		return ilb.build();
	}
}
