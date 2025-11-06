package kr1v.kr1vUtils.client;

import com.google.common.collect.ImmutableList;
import fi.dy.masa.malilib.config.ConfigManager;
import fi.dy.masa.malilib.config.IConfigBase;
import fi.dy.masa.malilib.config.options.ConfigBooleanHotkeyed;
import fi.dy.masa.malilib.event.InitializationHandler;
import fi.dy.masa.malilib.event.InputEventHandler;
import fi.dy.masa.malilib.hotkeys.KeybindSettings;
import fi.dy.masa.malilib.registry.Registry;
import fi.dy.masa.malilib.util.data.ModInfo;
import kr1v.kr1vUtils.client.config.ConfigHandler;
import kr1v.kr1vUtils.client.config.Render;
import kr1v.kr1vUtils.client.gui.screen.ConfigScreen;
import kr1v.kr1vUtils.client.malilib.event.InputHandler;
import kr1v.kr1vUtils.client.utils.Annotations;
import kr1v.kr1vUtils.client.utils.ClassUtils;
import kr1v.kr1vUtils.client.utils.MappingUtils;
import kr1v.kr1vUtils.client.utils.StringUtils;
import kr1v.kr1vUtils.client.utils.malilib.KeybindSetting;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.minecraft.client.render.RenderLayer;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Locale;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Kr1vUtilsClient implements ClientModInitializer {

	public static final String MOD_ID = "kr1v-utils";

	@Override
	public void onInitializeClient() {
		ClassUtils.touch(Annotations.class, MappingUtils.class);

		var ilb = new ImmutableList.Builder<IConfigBase>();

		ilb.addAll(ConfigHandler.generateOptions(Render.class));

		for (Field field : ClassUtils.getAllFields(RenderLayer.MultiPhase.class)) {
			if (Modifier.isStatic(field.getModifiers())) {
				if (RenderLayer.class.isAssignableFrom(field.getType()) ||
					BiFunction.class.isAssignableFrom(field.getType()) ||
					Function.class.isAssignableFrom(field.getType())) {

					String name = MappingUtils.intermediaryToYarnSimple(field).toLowerCase(Locale.ROOT);

					ConfigBooleanHotkeyed hotkey = new ConfigBooleanHotkeyed(StringUtils.convertCamelCase(name), true, "", (KeybindSettings) KeybindSetting.ofAny(), name);
					ConfigHandler.addToggleHotkey(hotkey);

					ilb.add(hotkey);
					Render.RENDER_HOTKEYS.put(name, hotkey);
				}
			}
		}

		Annotations.configsFor(Render.class).clear();
		Annotations.configsFor(Render.class).addAll(ilb.build());

		ConfigHandler configHandler = new ConfigHandler();

		InitializationHandler.getInstance().registerInitializationHandler(() -> {
			ConfigManager.getInstance().registerConfigHandler(Kr1vUtilsClient.MOD_ID, configHandler);

			Registry.CONFIG_SCREEN.registerConfigScreenFactory(
				new ModInfo(Kr1vUtilsClient.MOD_ID, Kr1vUtilsClient.MOD_ID, ConfigScreen::new)
			);
			InputEventHandler.getKeybindManager().registerKeybindProvider(InputHandler.getInstance());
			InputEventHandler.getInputManager().registerKeyboardInputHandler(InputHandler.getInstance());
			InputEventHandler.getInputManager().registerMouseInputHandler(InputHandler.getInstance());
		});

		ClientLifecycleEvents.CLIENT_STOPPING.register(client -> configHandler.save());

	}
}
