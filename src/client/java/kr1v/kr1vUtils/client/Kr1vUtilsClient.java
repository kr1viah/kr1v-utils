package kr1v.kr1vUtils.client;

import fi.dy.masa.malilib.config.ConfigManager;
import fi.dy.masa.malilib.event.InitializationHandler;
import fi.dy.masa.malilib.event.InputEventHandler;
import fi.dy.masa.malilib.registry.Registry;
import fi.dy.masa.malilib.util.data.ModInfo;
import kr1v.kr1vUtils.client.config.ConfigHandler;
import kr1v.kr1vUtils.client.gui.screen.ConfigScreen;
import kr1v.kr1vUtils.client.malilib.event.InputHandler;
import kr1v.kr1vUtils.client.utils.Annotations;
import kr1v.kr1vUtils.client.utils.ClassUtils;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;

public class Kr1vUtilsClient implements ClientModInitializer {
	public static final String MOD_ID = "kr1v-utils";

	@Override
	public void onInitializeClient() {
		ClassUtils.touch(Annotations.class);

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

        if (ConfigScreen.tab == null) {
            ConfigScreen.setTab(ConfigScreen.ConfigGuiTab.values()[0]);
            if (ConfigScreen.tab == null) {
                throw new IllegalStateException("Something went wrong.");
            }
        }
	}
}
