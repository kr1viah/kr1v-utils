package kr1v.kr1vUtils.client.config.configs;

import fi.dy.masa.malilib.config.options.ConfigDouble;
import kr1v.kr1vUtils.client.utils.annotation.Config;
import kr1v.kr1vUtils.client.utils.annotation.DependantOn;
import kr1v.kr1vUtils.client.utils.annotation.Dependency;
import kr1v.kr1vUtils.client.utils.annotation.Label;
import kr1v.kr1vUtils.client.utils.malilib.ConfigBooleanPlus;

import java.util.HashMap;
import java.util.Map;

@Config
@SuppressWarnings("unused")
public class Render {
    @Dependency("Affect rendering")
    @DependantOn("Affect anything")
    public static final ConfigBooleanPlus       AFFECT_RENDERING = new ConfigBooleanPlus("Affect rendering");

    @Label
    @Dependency("Affect offsetting")
    @DependantOn("Affect rendering")
    public static final ConfigBooleanPlus       AFFECT_OFFSETTING = new ConfigBooleanPlus("Affect offsetting");

    @DependantOn("Affect offsetting")
	public static final ConfigDouble            OFFSET_X = new ConfigDouble("Offset x", 0, -100, 100, "");
    @DependantOn("Affect offsetting")
	public static final ConfigDouble            OFFSET_Y = new ConfigDouble("Offset y", 0, -100, 100,"");
    @DependantOn("Affect offsetting")
	public static final ConfigDouble            OFFSET_Z = new ConfigDouble("Offset z", 0, -100, 100,"");
    @DependantOn("Affect offsetting")
	public static final ConfigBooleanPlus       RELATIVE_TO_PLAYER_ANGLE = new ConfigBooleanPlus("Offset relative to player angle");

    @Label
    @Label("In game hud:")
    @Dependency("Affect in game hud")
    @DependantOn("Affect rendering")
    public static final ConfigBooleanPlus       AFFECT_IN_GAME_HUD = new ConfigBooleanPlus("Affect in game hud");

    @DependantOn("Affect in game hud")
	public static final ConfigBooleanPlus       MISC_OVERLAYS = new ConfigBooleanPlus("Misc overlays");
    @DependantOn("Affect in game hud")
	public static final ConfigBooleanPlus       SLEEP_OVERLAY = new ConfigBooleanPlus("Sleep overlay");
    @DependantOn("Affect in game hud")
	public static final ConfigBooleanPlus       OVERLAY_MESSAGE = new ConfigBooleanPlus("Overlay message");
    @DependantOn("Affect in game hud")
	public static final ConfigBooleanPlus       TITLE_AND_SUBTITLE = new ConfigBooleanPlus("Title and subtitle");
    @DependantOn("Affect in game hud")
	public static final ConfigBooleanPlus       CHAT = new ConfigBooleanPlus("Chat");
    @DependantOn("Affect in game hud")
	public static final ConfigBooleanPlus       SCOREBOARD_SIDEBAR = new ConfigBooleanPlus("Scoreboard sidebar");
    @DependantOn("Affect in game hud")
	public static final ConfigBooleanPlus       PLAYER_LIST = new ConfigBooleanPlus("Player list");
    @DependantOn("Affect in game hud")
	public static final ConfigBooleanPlus       CROSSHAIR = new ConfigBooleanPlus("Crosshair (igh)");
    @DependantOn("Affect in game hud")
	public static final ConfigBooleanPlus       STATUS_EFFECT_OVERLAY = new ConfigBooleanPlus("Status effect overlay");
    @DependantOn("Affect in game hud")
	public static final ConfigBooleanPlus       MAIN_HUD = new ConfigBooleanPlus("Main HUD");
    @DependantOn("Affect in game hud")
	public static final ConfigBooleanPlus       HOTBAR = new ConfigBooleanPlus("Hotbar");
    @DependantOn("Affect in game hud")
	public static final ConfigBooleanPlus       MOUNT_JUMP_BAR = new ConfigBooleanPlus("Mount jump bar");
    @DependantOn("Affect in game hud")
	public static final ConfigBooleanPlus       EXPERIENCE_BAR = new ConfigBooleanPlus("Experience bar");
    @DependantOn("Affect in game hud")
	public static final ConfigBooleanPlus       EXPERIENCE_LEVEL = new ConfigBooleanPlus("Experience level");
    @DependantOn("Affect in game hud")
	public static final ConfigBooleanPlus       HELD_ITEM_TOOLTIP = new ConfigBooleanPlus("Held item tooltip");
    @DependantOn("Affect in game hud")
	public static final ConfigBooleanPlus       DEMO_TIMER = new ConfigBooleanPlus("Demo timer");
    @DependantOn("Affect in game hud")
	public static final ConfigBooleanPlus       STATUS_BARS = new ConfigBooleanPlus("Status bars");
    @DependantOn("Affect in game hud")
	public static final ConfigBooleanPlus       ARMOR = new ConfigBooleanPlus("Armor");
    @DependantOn("Affect in game hud")
	public static final ConfigBooleanPlus       HEALTH_BAR = new ConfigBooleanPlus("Health bar");
    @DependantOn("Affect in game hud")
	public static final ConfigBooleanPlus       FOOD = new ConfigBooleanPlus("Food");
    @DependantOn("Affect in game hud")
	public static final ConfigBooleanPlus       MOUNT_HEALTH = new ConfigBooleanPlus("Mount health");
    @DependantOn("Affect in game hud")
	public static final ConfigBooleanPlus       SPYGLASS_OVERLAY = new ConfigBooleanPlus("Spyglass overlay");
    @DependantOn("Affect in game hud")
	public static final ConfigBooleanPlus       VIGNETTE_OVERLAY = new ConfigBooleanPlus("Vignette overlay");
    @DependantOn("Affect in game hud")
	public static final ConfigBooleanPlus       PORTAL_OVERLAY = new ConfigBooleanPlus("Portal overlay");
    @DependantOn("Affect in game hud")
	public static final ConfigBooleanPlus       NAUSEA_OVERLAY = new ConfigBooleanPlus("Nausea overlay");
    @DependantOn("Affect in game hud")
	public static final ConfigBooleanPlus       HOTBAR_ITEM = new ConfigBooleanPlus("Hotbar item");
    @DependantOn("Affect in game hud")
	public static final ConfigBooleanPlus       AUTOSAVE_INDICATOR = new ConfigBooleanPlus("Autosave indicator");

    @Label
    @Label("Game renderer:")
    @Dependency("Affect game renderer")
    @DependantOn("Affect rendering")
	public static final ConfigBooleanPlus       AFFECT_GAME_RENDERER = new ConfigBooleanPlus("Affect game renderer");

    @DependantOn("Affect game renderer")
	public static final ConfigBooleanPlus       MAIN = new ConfigBooleanPlus("Main");
    @DependantOn("Affect game renderer")
	public static final ConfigBooleanPlus       PARTICLES = new ConfigBooleanPlus("Particles");
    @DependantOn("Affect game renderer")
	public static final ConfigBooleanPlus       CLOUDS = new ConfigBooleanPlus("Clouds");
    @DependantOn("Affect game renderer")
	public static final ConfigBooleanPlus       WEATHER = new ConfigBooleanPlus("Weather");
    @DependantOn("Affect game renderer")
	public static final ConfigBooleanPlus       LATE_DEBUG = new ConfigBooleanPlus("Late debug");
    @DependantOn("Affect game renderer")
	public static final ConfigBooleanPlus       ENTITIES = new ConfigBooleanPlus("Entities");
    @DependantOn("Affect game renderer")
	public static final ConfigBooleanPlus       BLOCK_ENTITIES = new ConfigBooleanPlus("Block entities");
    @DependantOn("Affect game renderer")
	public static final ConfigBooleanPlus       BLOCK_DAMAGE = new ConfigBooleanPlus("Block damage");
    @DependantOn("Affect game renderer")
	public static final ConfigBooleanPlus       TARGET_BLOCK_OUTLINE = new ConfigBooleanPlus("Target block outline");
    @DependantOn("Affect game renderer")
	public static final ConfigBooleanPlus       SKY = new ConfigBooleanPlus("Sky");

    @Label
    @Label("Render layer:")
    @Dependency("Affect render layers")
    @DependantOn("Affect rendering")
    public static final ConfigBooleanPlus       AFFECT_RENDER_LAYERS = new ConfigBooleanPlus("Affect render layers");

	public static final Map<String, ConfigBooleanPlus> RENDER_HOTKEYS = new HashMap<>();
}
