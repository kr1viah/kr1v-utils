package kr1v.kr1vUtils.client.config.configs;

import fi.dy.masa.malilib.gui.GuiBase;
import kr1v.kr1vUtils.client.gui.screen.ConfigScreen;
import kr1v.kr1vUtils.client.gui.screen.DummyScreen;
import kr1v.kr1vUtils.client.utils.annotation.Config;
import kr1v.kr1vUtils.client.utils.malilib.plus.ConfigBooleanPlus;
import kr1v.kr1vUtils.client.utils.malilib.plus.ConfigHotkeyPlus;
import kr1v.kr1vUtils.client.utils.malilib.plus.ConfigStringListPlus;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.util.math.Vec3d;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unused")
@Config
public class Misc {
	public static boolean preventClosingOnce = false;

	public static final ConfigHotkeyPlus OPEN_GUI = new ConfigHotkeyPlus("Open config gui", "G,C", (keyAction, keybind) -> {
        GuiBase.openGui(new ConfigScreen());
        return true;
    });
    public static final ConfigHotkeyPlus SHOW_CURSOR = new ConfigHotkeyPlus("Show cursor", (button, keybind) -> {
        MinecraftClient.getInstance().setScreen(new DummyScreen());
        return true;
    });
	public static final ConfigBooleanPlus ALWAYS_CLOSE_BUTTON = new ConfigBooleanPlus("Always close screens upon pressing escape", false, (keyAction, keybind) -> {
        preventClosingOnce = true;
        if (MinecraftClient.getInstance().currentScreen != null)
            MinecraftClient.getInstance().currentScreen.close();
        preventClosingOnce = false;
        return true;
    });
    public static final ConfigBooleanPlus PREVENT_FLIGHT_STATE_CHANGE = new ConfigBooleanPlus("Prevent creative flight state change", false);
    public static final ConfigHotkeyPlus FORCE_TOGGLE_FLIGHT = new ConfigHotkeyPlus("Force toggle creative flight", (button, keybind) -> {
        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        if (player != null && player.getAbilities().allowFlying) {
            player.getAbilities().flying = !player.getAbilities().flying;
            if (!PREVENT_FLIGHT_STATE_CHANGE.getBooleanValue() && player.isOnGround())
                player.addVelocity(new Vec3d(0, 0.08, 0));
        }
        return true;
    });
    public static final ConfigStringListPlus QUICKPLAY_SERVERS = new ConfigStringListPlus("Servers to put onto the main menu", "Separate name with ip with a #");
    public static final ConfigBooleanPlus FAST_MAIN_MENU = new ConfigBooleanPlus("Fast main menu", true);
    public static final ConfigBooleanPlus PRINT_SUBOPTIMAL_JUMPS = new ConfigBooleanPlus("Print suboptimal jumps", false, "", "Prints a message to the chat when a jump isn't made on the last tick possible");
    public static final ConfigBooleanPlus PRINT_OPTIMAL_JUMPS = new ConfigBooleanPlus("Print optimal jumps", false, "", "Prints a message to the chat when a jump is made on the last tick possible");
    public static final ConfigBooleanPlus PRINT_MISSED_JUMPS = new ConfigBooleanPlus("Print missed jumps", false, "", "Prints a message to the chat when a jump is missed");
    public static final ConfigBooleanPlus SEPARATE_SERVER_CLIENT_ACTION_BAR = new ConfigBooleanPlus("Separate the server and client action bar", true);
    public static final ConfigBooleanPlus CLIENT_ON_TOP = new ConfigBooleanPlus("Put client action bar on top", true);

	public static final Map<String, Integer> tabToScrollPosition = new HashMap<>();
}
