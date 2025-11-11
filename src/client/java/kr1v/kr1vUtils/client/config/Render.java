package kr1v.kr1vUtils.client.config;

import fi.dy.masa.malilib.config.options.ConfigBooleanHotkeyed;
import fi.dy.masa.malilib.config.options.ConfigDouble;
import kr1v.kr1vUtils.client.malilib.ConfigLabel;
import kr1v.kr1vUtils.client.utils.annotation.Config;
import kr1v.kr1vUtils.client.utils.malilib.ConfigBooleanPlus;

import java.util.HashMap;
import java.util.Map;

@Config
@SuppressWarnings("unused")
public class Render {
	public static final ConfigDouble          OFFSET_X = new ConfigDouble("Offset x", 0, -100, 100, "");
	public static final ConfigDouble          OFFSET_Y = new ConfigDouble("Offset y", 0, -100, 100,"");
	public static final ConfigDouble          OFFSET_Z = new ConfigDouble("Offset z", 0, -100, 100,"");
	public static final ConfigBooleanHotkeyed RELATIVE_TO_PLAYER_ANGLE = new ConfigBooleanPlus("Offset relative to player angle");

	public static final ConfigLabel SEPARATOR1 = new ConfigLabel("");
	public static final ConfigLabel SEPARATOR2 = new ConfigLabel("In game hud:");

	public static final ConfigBooleanHotkeyed MISC_OVERLAYS = new ConfigBooleanPlus("Misc overlays");
	public static final ConfigBooleanHotkeyed SLEEP_OVERLAY = new ConfigBooleanPlus("Sleep overlay");
	public static final ConfigBooleanHotkeyed OVERLAY_MESSAGE = new ConfigBooleanPlus("Overlay message");
	public static final ConfigBooleanHotkeyed TITLE_AND_SUBTITLE = new ConfigBooleanPlus("Title and subtitle");
	public static final ConfigBooleanHotkeyed CHAT = new ConfigBooleanPlus("Chat");
	public static final ConfigBooleanHotkeyed SCOREBOARD_SIDEBAR = new ConfigBooleanPlus("Scoreboard sidebar");
	public static final ConfigBooleanHotkeyed PLAYER_LIST = new ConfigBooleanPlus("Player list");
	public static final ConfigBooleanHotkeyed CROSSHAIR = new ConfigBooleanPlus("Crosshair (igh)");
	public static final ConfigBooleanHotkeyed STATUS_EFFECT_OVERLAY = new ConfigBooleanPlus("Status effect overlay");
	public static final ConfigBooleanHotkeyed MAIN_HUD = new ConfigBooleanPlus("Main HUD");
	public static final ConfigBooleanHotkeyed HOTBAR = new ConfigBooleanPlus("Hotbar");
	public static final ConfigBooleanHotkeyed MOUNT_JUMP_BAR = new ConfigBooleanPlus("Mount jump bar");
	public static final ConfigBooleanHotkeyed EXPERIENCE_BAR = new ConfigBooleanPlus("Experience bar");
	public static final ConfigBooleanHotkeyed EXPERIENCE_LEVEL = new ConfigBooleanPlus("Experience level");
	public static final ConfigBooleanHotkeyed HELD_ITEM_TOOLTIP = new ConfigBooleanPlus("Held item tooltip");
	public static final ConfigBooleanHotkeyed DEMO_TIMER = new ConfigBooleanPlus("Demo timer");
	public static final ConfigBooleanHotkeyed STATUS_BARS = new ConfigBooleanPlus("Status bars");
	public static final ConfigBooleanHotkeyed ARMOR = new ConfigBooleanPlus("Armor");
	public static final ConfigBooleanHotkeyed HEALTH_BAR = new ConfigBooleanPlus("Health bar");
	public static final ConfigBooleanHotkeyed FOOD = new ConfigBooleanPlus("Food");
	public static final ConfigBooleanHotkeyed MOUNT_HEALTH = new ConfigBooleanPlus("Mount health");
	public static final ConfigBooleanHotkeyed SPYGLASS_OVERLAY = new ConfigBooleanPlus("Spyglass overlay");
	public static final ConfigBooleanHotkeyed VIGNETTE_OVERLAY = new ConfigBooleanPlus("Vignette overlay");
	public static final ConfigBooleanHotkeyed PORTAL_OVERLAY = new ConfigBooleanPlus("Portal overlay");
	public static final ConfigBooleanHotkeyed NAUSEA_OVERLAY = new ConfigBooleanPlus("Nausea overlay");
	public static final ConfigBooleanHotkeyed HOTBAR_ITEM = new ConfigBooleanPlus("Hotbar item");
	public static final ConfigBooleanHotkeyed AUTOSAVE_INDICATOR = new ConfigBooleanPlus("Autosave indicator");

	public static final ConfigLabel SEPARATOR3 = new ConfigLabel("");
	public static final ConfigLabel SEPARATOR4 = new ConfigLabel("Game renderer:");

	public static final ConfigBooleanHotkeyed MAIN = new ConfigBooleanPlus("Main");
	public static final ConfigBooleanHotkeyed PARTICLES = new ConfigBooleanPlus("Particles");
	public static final ConfigBooleanHotkeyed CLOUDS = new ConfigBooleanPlus("Clouds");
	public static final ConfigBooleanHotkeyed WEATHER = new ConfigBooleanPlus("Weather");
	public static final ConfigBooleanHotkeyed LATE_DEBUG = new ConfigBooleanPlus("Late debug");
	public static final ConfigBooleanHotkeyed ENTITIES = new ConfigBooleanPlus("Entities");
	public static final ConfigBooleanHotkeyed BLOCK_ENTITIES = new ConfigBooleanPlus("Block entities");
	public static final ConfigBooleanHotkeyed BLOCK_DAMAGE = new ConfigBooleanPlus("Block damage");
	public static final ConfigBooleanHotkeyed TARGET_BLOCK_OUTLINE = new ConfigBooleanPlus("Target block outline");
	public static final ConfigBooleanHotkeyed SKY = new ConfigBooleanPlus("Sky");

	public static final ConfigLabel SEPARATOR5 = new ConfigLabel("");
	public static final ConfigLabel SEPARATOR6 = new ConfigLabel("Render layer:");

	public static final Map<String, ConfigBooleanHotkeyed> RENDER_HOTKEYS = new HashMap<>();
}
