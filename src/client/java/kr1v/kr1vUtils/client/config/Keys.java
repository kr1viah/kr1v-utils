package kr1v.kr1vUtils.client.config;

import fi.dy.masa.malilib.config.options.ConfigBooleanHotkeyed;
import fi.dy.masa.malilib.config.options.ConfigInteger;
import fi.dy.masa.malilib.hotkeys.KeybindMulti;
import kr1v.kr1vUtils.client.utils.annotation.Config;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;

@Config
@SuppressWarnings("unused")
public class Keys {
	public static final ConfigBooleanHotkeyed DISPLAY_CURRENTLY_PRESSED_KEYS = new ConfigBooleanHotkeyed("Display currently pressed keys", false, "", "");
	public static final ConfigBooleanHotkeyed DISPLAY_CURRENTLY_PRESSED_MOUSE_BUTTONS = new ConfigBooleanHotkeyed("Display currently pressed mouse buttons", false, "", "");
	public static final ConfigInteger PRESSED_KEYS_X = new ConfigInteger("Currently pressed keys X", 2, "");
	public static final ConfigInteger PRESSED_KEYS_Y = new ConfigInteger("Currently pressed keys Y", 0, "");
	public static final ConfigInteger PRESSED_MOUSE_X = new ConfigInteger("Currently pressed mouse X", 2, "");
	public static final ConfigInteger PRESSED_MOUSE_Y = new ConfigInteger("Currently pressed mouse Y", 11, "");

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
		var individualKeys = toDisplay.split("\\+");
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
		if (DISPLAY_CURRENTLY_PRESSED_KEYS.getBooleanValue()) {
			int x = PRESSED_KEYS_X.getIntegerValue();
			int y = PRESSED_KEYS_Y.getIntegerValue();
			context.drawTextWithShadow(MinecraftClient.getInstance().textRenderer, keyDisplay.toString(), x, y, 0xFFFFFFFF);
		}
		if (DISPLAY_CURRENTLY_PRESSED_MOUSE_BUTTONS.getBooleanValue()) {
			int x = PRESSED_MOUSE_X.getIntegerValue();
			int y = PRESSED_MOUSE_Y.getIntegerValue();
			context.drawTextWithShadow(MinecraftClient.getInstance().textRenderer, mouseDisplay.toString(), x, y, 0xFFFFFFFF);
		}
	}
}
