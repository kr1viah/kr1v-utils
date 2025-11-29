package kr1v.kr1vUtils.client.config;

import fi.dy.masa.malilib.config.options.ConfigInteger;
import fi.dy.masa.malilib.hotkeys.KeybindMulti;
import kr1v.kr1vUtils.client.Kr1vUtilsClient;
import kr1v.malilibApi.annotation.Config;
import kr1v.malilibApi.annotation.PopupConfig;
import kr1v.malilibApi.config.plus.ConfigBooleanPlus;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;

@SuppressWarnings("unused")
@Config(Kr1vUtilsClient.MOD_ID)
public class Keys {
    @PopupConfig(name = "Mouse", buttonName = "Edit")
    public static class KeysConfig {
        public static final ConfigBooleanPlus   DISPLAY_CURRENTLY_PRESSED_KEYS = new ConfigBooleanPlus("Display currently pressed keys");
        public static final ConfigInteger       PRESSED_KEYS_X = new ConfigInteger("Currently pressed keys X", 2, "");
        public static final ConfigInteger       PRESSED_KEYS_Y = new ConfigInteger("Currently pressed keys Y", 2, "");
    }

    @PopupConfig(name = "Keys", buttonName = "Edit")
    public static class MouseConfig {
        public static final ConfigBooleanPlus   DISPLAY_CURRENTLY_PRESSED_MOUSE_BUTTONS = new ConfigBooleanPlus("Display currently pressed mouse buttons");
        public static final ConfigInteger       PRESSED_MOUSE_X = new ConfigInteger("Currently pressed mouse X", 2, "");
        public static final ConfigInteger       PRESSED_MOUSE_Y = new ConfigInteger("Currently pressed mouse Y", 13, "");

    }

	static {
		//noinspection deprecation
		HudRenderCallback.EVENT.register((context, renderTickCounter) -> {
			//noinspection Convert2MethodRef
			renderKeysMouse(context, renderTickCounter);
		});
	}

	private static void renderKeysMouse(DrawContext context, RenderTickCounter renderTickCounter) {
		String toDisplay = KeybindMulti.getActiveKeysString();
		toDisplay = toDisplay.replaceAll("\\s*\\([^)]*\\)", "");
		if (toDisplay.equals("<none>")) return;
        String[] individualKeys = toDisplay.split("\\+");
		StringBuilder keyDisplay = new StringBuilder();
		StringBuilder mouseDisplay = new StringBuilder();
		boolean shouldNotAddPlusKey = true;
		boolean shouldNotAddPlusMouse = true;
		for (String s : individualKeys) {
			s = s.strip();
			if (s.startsWith("BUTTON_")) {
				if (!shouldNotAddPlusMouse) mouseDisplay.append(" + ");
				mouseDisplay.append(s);
				shouldNotAddPlusMouse = false;
			} else {
				if (!shouldNotAddPlusKey) keyDisplay.append(" + ");
				keyDisplay.append(s);
				shouldNotAddPlusKey = false;
			}
		}
		if (KeysConfig.DISPLAY_CURRENTLY_PRESSED_KEYS.getBooleanValue()) {
			int x = KeysConfig.PRESSED_KEYS_X.getIntegerValue();
			int y = KeysConfig.PRESSED_KEYS_Y.getIntegerValue();
			context.drawTextWithShadow(MinecraftClient.getInstance().textRenderer, keyDisplay.toString(), x, y, 0xFFFFFFFF);
		}
		if (MouseConfig.DISPLAY_CURRENTLY_PRESSED_MOUSE_BUTTONS.getBooleanValue()) {
			int x = MouseConfig.PRESSED_MOUSE_X.getIntegerValue();
			int y = MouseConfig.PRESSED_MOUSE_Y.getIntegerValue();
			context.drawTextWithShadow(MinecraftClient.getInstance().textRenderer, mouseDisplay.toString(), x, y, 0xFFFFFFFF);
		}
	}
}
