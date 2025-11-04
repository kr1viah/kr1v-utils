package kr1v.kr1vUtils.client.config;

import fi.dy.masa.malilib.config.IConfigBase;
import fi.dy.masa.malilib.config.options.ConfigBooleanHotkeyed;
import kr1v.kr1vUtils.client.malilib.ConfigLabel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unused")
public class Render {
//    public static final ConfigBooleanHotkeyed

	public static final ConfigLabel SEPARATOR1 = new ConfigLabel("");
	public static final ConfigLabel SEPARATOR2 = new ConfigLabel("In game hud:");

	public static final ConfigBooleanHotkeyed MISC_OVERLAYS = new ConfigBooleanHotkeyed("Misc overlays", true, "", "renderMiscOverlays");
	public static final ConfigBooleanHotkeyed SLEEP_OVERLAY = new ConfigBooleanHotkeyed("Sleep overlay", true, "", "renderSleepOverlay");
	public static final ConfigBooleanHotkeyed OVERLAY_MESSAGE = new ConfigBooleanHotkeyed("Overlay message", true, "", "renderOverlayMessage");
	public static final ConfigBooleanHotkeyed TITLE_AND_SUBTITLE = new ConfigBooleanHotkeyed("Title and subtitle", true, "", "renderTitleAndSubtitle");
	public static final ConfigBooleanHotkeyed CHAT = new ConfigBooleanHotkeyed("Chat", true, "", "renderChat");
	public static final ConfigBooleanHotkeyed SCOREBOARD_SIDEBAR = new ConfigBooleanHotkeyed("Scoreboard sidebar", true, "", "renderScoreboardSidebar");
	public static final ConfigBooleanHotkeyed PLAYER_LIST = new ConfigBooleanHotkeyed("Player list", true, "", "renderPlayerList");
	public static final ConfigBooleanHotkeyed CROSSHAIR = new ConfigBooleanHotkeyed("Crosshair", true, "", "renderCrosshair");
	public static final ConfigBooleanHotkeyed STATUS_EFFECT_OVERLAY = new ConfigBooleanHotkeyed("Status effect overlay", true, "", "renderStatusEffectOverlay");
	public static final ConfigBooleanHotkeyed MAIN_HUD = new ConfigBooleanHotkeyed("Main HUD", true, "", "renderMainHud");
	public static final ConfigBooleanHotkeyed HOTBAR = new ConfigBooleanHotkeyed("Hotbar", true, "", "renderHotbar");
	public static final ConfigBooleanHotkeyed MOUNT_JUMP_BAR = new ConfigBooleanHotkeyed("Mount jump bar", true, "", "renderMountJumpBar");
	public static final ConfigBooleanHotkeyed EXPERIENCE_BAR = new ConfigBooleanHotkeyed("Experience bar", true, "", "renderExperienceBar");
	public static final ConfigBooleanHotkeyed EXPERIENCE_LEVEL = new ConfigBooleanHotkeyed("Experience level", true, "", "renderExperienceLevel");
	public static final ConfigBooleanHotkeyed HELD_ITEM_TOOLTIP = new ConfigBooleanHotkeyed("Held item tooltip", true, "", "renderHeldItemTooltip");
	public static final ConfigBooleanHotkeyed DEMO_TIMER = new ConfigBooleanHotkeyed("Demo timer", true, "", "renderDemoTimer");
	public static final ConfigBooleanHotkeyed STATUS_BARS = new ConfigBooleanHotkeyed("Status bars", true, "", "renderStatusBars");
	public static final ConfigBooleanHotkeyed ARMOR = new ConfigBooleanHotkeyed("Armor", true, "", "renderArmor");
	public static final ConfigBooleanHotkeyed HEALTH_BAR = new ConfigBooleanHotkeyed("Health bar", true, "", "renderHealthBar");
	public static final ConfigBooleanHotkeyed FOOD = new ConfigBooleanHotkeyed("Food", true, "", "renderFood");
	public static final ConfigBooleanHotkeyed MOUNT_HEALTH = new ConfigBooleanHotkeyed("Mount health", true, "", "renderMountHealth");
	public static final ConfigBooleanHotkeyed SPYGLASS_OVERLAY = new ConfigBooleanHotkeyed("Spyglass overlay", true, "", "renderSpyglassOverlay");
	public static final ConfigBooleanHotkeyed VIGNETTE_OVERLAY = new ConfigBooleanHotkeyed("Vignette overlay", true, "", "renderVignetteOverlay");
	public static final ConfigBooleanHotkeyed PORTAL_OVERLAY = new ConfigBooleanHotkeyed("Portal overlay", true, "", "renderPortalOverlay");
	public static final ConfigBooleanHotkeyed NAUSEA_OVERLAY = new ConfigBooleanHotkeyed("Nausea overlay", true, "", "renderNauseaOverlay");
	public static final ConfigBooleanHotkeyed HOTBAR_ITEM = new ConfigBooleanHotkeyed("Hotbar item", true, "", "renderHotbarItem");
	public static final ConfigBooleanHotkeyed AUTOSAVE_INDICATOR = new ConfigBooleanHotkeyed("Autosave indicator", true, "", "renderAutosaveIndicator");

	public static final ConfigLabel SEPARATOR3 = new ConfigLabel("");
	public static final ConfigLabel SEPARATOR4 = new ConfigLabel("Game renderer:");

	public static final ConfigBooleanHotkeyed MAIN = new ConfigBooleanHotkeyed("Main", true, "", "renderMain");
	public static final ConfigBooleanHotkeyed PARTICLES = new ConfigBooleanHotkeyed("Particles", true, "", "renderParticles");
	public static final ConfigBooleanHotkeyed CLOUDS = new ConfigBooleanHotkeyed("Clouds", true, "", "renderClouds");
	public static final ConfigBooleanHotkeyed WEATHER = new ConfigBooleanHotkeyed("Weather", true, "", "renderWeather");
	public static final ConfigBooleanHotkeyed LATE_DEBUG = new ConfigBooleanHotkeyed("Late debug", true, "", "renderLateDebug");
	public static final ConfigBooleanHotkeyed ENTITIES = new ConfigBooleanHotkeyed("Entities", true, "", "renderEntities");
	public static final ConfigBooleanHotkeyed BLOCK_ENTITIES = new ConfigBooleanHotkeyed("Block entities", true, "", "renderBlockEntities");
	public static final ConfigBooleanHotkeyed BLOCK_DAMAGE = new ConfigBooleanHotkeyed("Block damage", true, "", "renderBlockDamage");
	public static final ConfigBooleanHotkeyed TARGET_BLOCK_OUTLINE = new ConfigBooleanHotkeyed("Target block outline", true, "", "renderTargetBlockOutline");
	public static final ConfigBooleanHotkeyed SKY = new ConfigBooleanHotkeyed("Sky", true, "", "renderSky");

	public static final ConfigLabel SEPARATOR5 = new ConfigLabel("");
	public static final ConfigLabel SEPARATOR6 = new ConfigLabel("Render layer:");

	public static final Map<String, ConfigBooleanHotkeyed> RENDER_HOTKEYS = new HashMap<>();

	public static List<? extends IConfigBase> OPTIONS;
}
