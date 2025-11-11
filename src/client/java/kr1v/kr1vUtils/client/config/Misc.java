package kr1v.kr1vUtils.client.config;

import com.google.common.collect.ImmutableList;
import fi.dy.masa.malilib.config.options.ConfigBooleanHotkeyed;
import fi.dy.masa.malilib.config.options.ConfigHotkey;
import fi.dy.masa.malilib.config.options.ConfigStringList;
import fi.dy.masa.malilib.gui.GuiBase;
import kr1v.kr1vUtils.client.gui.screen.ConfigScreen;
import kr1v.kr1vUtils.client.gui.screen.DummyScreen;
import kr1v.kr1vUtils.client.utils.annotation.Config;
import kr1v.kr1vUtils.client.utils.malilib.ConfigBooleanPlus;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.util.math.Vec3d;

import java.util.HashMap;
import java.util.Map;

@Config
@SuppressWarnings("unused")
public class Misc {
	public static boolean preventClosingOnce = false;

	public static final ConfigHotkey OPEN_GUI = new ConfigHotkey("Open config gui", "G,C", "") {{
        this.getKeybind().setCallback((keyAction, keybind) -> {
            GuiBase.openGui(new ConfigScreen());
            return true;
        });
    }};
	public static final ConfigHotkey SHOW_CURSOR = new ConfigHotkey("Show cursor", "", "") {{
        this.getKeybind().setCallback((button, keybind) -> {
            MinecraftClient.getInstance().setScreen(new DummyScreen());
            return true;
        });
    }};
	public static final ConfigBooleanHotkeyed ALWAYS_CLOSE_BUTTON = new ConfigBooleanPlus("Always close screens upon pressing escape", false, (keyAction, keybind) -> {
        preventClosingOnce = true;
        if (MinecraftClient.getInstance().currentScreen != null)
            MinecraftClient.getInstance().currentScreen.close();
        preventClosingOnce = false;
        return true;
    });
	public static final ConfigHotkey FORCE_TOGGLE_FLIGHT = new ConfigHotkey("Force toggle creative flight", "", "") {{
        this.getKeybind().setCallback((button, keybind) -> {
            ClientPlayerEntity player = MinecraftClient.getInstance().player;
            if (player != null && player.getAbilities().allowFlying) {
                player.getAbilities().flying = !player.getAbilities().flying;
                if (!PREVENT_FLIGHT_STATE_CHANGE.getBooleanValue() && player.isOnGround())
                    player.addVelocity(new Vec3d(0, 0.08, 0));
            }
            return true;
        });
    }};
	public static final ConfigBooleanHotkeyed PREVENT_FLIGHT_STATE_CHANGE = new ConfigBooleanPlus("Prevent creative flight state change", false);
	public static final ConfigStringList QUICKPLAY_SERVERS = new ConfigStringList("Servers to put onto the main menu", ImmutableList.of(), "Separate name with ip with a #");
	public static final ConfigBooleanHotkeyed FAST_MAIN_MENU = new ConfigBooleanPlus("Fast main menu", true);
	public static final ConfigBooleanHotkeyed PRINT_SUBOPTIMAL_JUMPS = new ConfigBooleanPlus("Print suboptimal jumps", false, "", "Prints a message to the chat when a jump isn't made on the last tick possible");
	public static final ConfigBooleanHotkeyed PRINT_OPTIMAL_JUMPS = new ConfigBooleanPlus("Print optimal jumps", false, "", "Prints a message to the chat when a jump is made on the last tick possible");
	public static final ConfigBooleanHotkeyed PRINT_MISSED_JUMPS = new ConfigBooleanPlus("Print missed jumps", false, "", "Prints a message to the chat when a jump is missed");
	public static final ConfigBooleanHotkeyed SEPARATE_SERVER_CLIENT_ACTION_BAR = new ConfigBooleanPlus("Separate the server and client action bar", true);
	public static final ConfigBooleanHotkeyed CLIENT_ON_TOP = new ConfigBooleanPlus("Put client action bar on top", true);
	public static final ConfigBooleanHotkeyed SAVE_LAST_POSITION = new ConfigBooleanPlus("Remember where you were exactly in configs", true);
    private static double prevGamma = MinecraftClient.getInstance().options.getGamma().getValue();
    public static final ConfigBooleanPlus FULLBRIGHT = new ConfigBooleanPlus("Fullbright", config -> {
        if (config.getBooleanValue()) {
            prevGamma = MinecraftClient.getInstance().options.getGamma().getValue();
            MinecraftClient.getInstance().options.getGamma().setValue(1600.0);
        } else {
            MinecraftClient.getInstance().options.getGamma().setValue(prevGamma);
        }
    });
    public static final ConfigBooleanPlus FOG = new ConfigBooleanPlus("Fog");

	public static final Map<String, Integer> tabToScrollPosition = new HashMap<>();
}
