package kr1v.kr1vUtils.client.config;

import fi.dy.masa.malilib.config.IConfigBase;
import fi.dy.masa.malilib.config.options.ConfigBooleanHotkeyed;
import kr1v.kr1vUtils.client.malilib.ConfigLabel;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.WorldRenderer;

import java.util.List;

@SuppressWarnings("unused")
public class Render {
    public static final ConfigLabel             SEPARATOR1 = new ConfigLabel("");
    public static final ConfigLabel             SEPARATOR2 = new ConfigLabel("In game hud:");

    public static final ConfigBooleanHotkeyed	MISC_OVERLAYS = new ConfigBooleanHotkeyed("Misc overlays", true, "", "renderMiscOverlays");
    public static final ConfigBooleanHotkeyed	SLEEP_OVERLAY = new ConfigBooleanHotkeyed("Sleep overlay", true, "", "renderSleepOverlay");
    public static final ConfigBooleanHotkeyed	OVERLAY_MESSAGE = new ConfigBooleanHotkeyed("Overlay message", true, "", "renderOverlayMessage");
    public static final ConfigBooleanHotkeyed	TITLE_AND_SUBTITLE = new ConfigBooleanHotkeyed("Title and subtitle", true, "", "renderTitleAndSubtitle");
    public static final ConfigBooleanHotkeyed	CHAT = new ConfigBooleanHotkeyed("Chat", true, "", "renderChat");
    public static final ConfigBooleanHotkeyed	SCOREBOARD_SIDEBAR = new ConfigBooleanHotkeyed("Scoreboard sidebar", true, "", "renderScoreboardSidebar");
    public static final ConfigBooleanHotkeyed	PLAYER_LIST = new ConfigBooleanHotkeyed("Player list", true, "", "renderPlayerList");
    public static final ConfigBooleanHotkeyed	CROSSHAIR = new ConfigBooleanHotkeyed("Crosshair", true, "", "renderCrosshair");
    public static final ConfigBooleanHotkeyed	STATUS_EFFECT_OVERLAY = new ConfigBooleanHotkeyed("Status effect overlay", true, "", "renderStatusEffectOverlay");
    public static final ConfigBooleanHotkeyed	MAIN_HUD = new ConfigBooleanHotkeyed("Main HUD", true, "", "renderMainHud");
    public static final ConfigBooleanHotkeyed	HOTBAR = new ConfigBooleanHotkeyed("Hotbar", true, "", "renderHotbar");
    public static final ConfigBooleanHotkeyed	MOUNT_JUMP_BAR = new ConfigBooleanHotkeyed("Mount jump bar", true, "", "renderMountJumpBar");
    public static final ConfigBooleanHotkeyed	EXPERIENCE_BAR = new ConfigBooleanHotkeyed("Experience bar", true, "", "renderExperienceBar");
    public static final ConfigBooleanHotkeyed	EXPERIENCE_LEVEL = new ConfigBooleanHotkeyed("Experience level", true, "", "renderExperienceLevel");
    public static final ConfigBooleanHotkeyed	HELD_ITEM_TOOLTIP = new ConfigBooleanHotkeyed("Held item tooltip", true, "", "renderHeldItemTooltip");
    public static final ConfigBooleanHotkeyed	DEMO_TIMER = new ConfigBooleanHotkeyed("Demo timer", true, "", "renderDemoTimer");
    public static final ConfigBooleanHotkeyed	STATUS_BARS = new ConfigBooleanHotkeyed("Status bars", true, "", "renderStatusBars");
    public static final ConfigBooleanHotkeyed	ARMOR = new ConfigBooleanHotkeyed("Armor", true, "", "renderArmor");
    public static final ConfigBooleanHotkeyed	HEALTH_BAR = new ConfigBooleanHotkeyed("Health bar", true, "", "renderHealthBar");
    public static final ConfigBooleanHotkeyed	FOOD = new ConfigBooleanHotkeyed("Food", true, "", "renderFood");
    public static final ConfigBooleanHotkeyed	MOUNT_HEALTH = new ConfigBooleanHotkeyed("Mount health", true, "", "renderMountHealth");
    public static final ConfigBooleanHotkeyed	SPYGLASS_OVERLAY = new ConfigBooleanHotkeyed("Spyglass overlay", true, "", "renderSpyglassOverlay");
    public static final ConfigBooleanHotkeyed	VIGNETTE_OVERLAY = new ConfigBooleanHotkeyed("Vignette overlay", true, "", "renderVignetteOverlay");
    public static final ConfigBooleanHotkeyed	PORTAL_OVERLAY = new ConfigBooleanHotkeyed("Portal overlay", true, "", "renderPortalOverlay");
    public static final ConfigBooleanHotkeyed	NAUSEA_OVERLAY = new ConfigBooleanHotkeyed("Nausea overlay", true, "", "renderNauseaOverlay");
    public static final ConfigBooleanHotkeyed	HOTBAR_ITEM = new ConfigBooleanHotkeyed("Hotbar item", true, "", "renderHotbarItem");
    public static final ConfigBooleanHotkeyed	AUTOSAVE_INDICATOR = new ConfigBooleanHotkeyed("Autosave indicator", true, "", "renderAutosaveIndicator");

    public static final ConfigLabel             SEPARATOR3 = new ConfigLabel("");
    public static final ConfigLabel             SEPARATOR4 = new ConfigLabel("Game renderer:");

    public static final ConfigBooleanHotkeyed	MAIN = new ConfigBooleanHotkeyed("Main", true, "", "renderMain");
    public static final ConfigBooleanHotkeyed	PARTICLES = new ConfigBooleanHotkeyed("Particles", true, "", "renderParticles");
    public static final ConfigBooleanHotkeyed	CLOUDS = new ConfigBooleanHotkeyed("Clouds", true, "", "renderClouds");
    public static final ConfigBooleanHotkeyed	WEATHER = new ConfigBooleanHotkeyed("Weather", true, "", "renderWeather");
    public static final ConfigBooleanHotkeyed	LATE_DEBUG = new ConfigBooleanHotkeyed("Late debug", true, "", "renderLateDebug");
    public static final ConfigBooleanHotkeyed	ENTITIES = new ConfigBooleanHotkeyed("Entities", true, "", "renderEntities");
    public static final ConfigBooleanHotkeyed	BLOCK_ENTITIES = new ConfigBooleanHotkeyed("Block entities", true, "", "renderBlockEntities");
    public static final ConfigBooleanHotkeyed	BLOCK_DAMAGE = new ConfigBooleanHotkeyed("Block damage", true, "", "renderBlockDamage");
    public static final ConfigBooleanHotkeyed	TARGET_BLOCK_OUTLINE = new ConfigBooleanHotkeyed("Target block outline", true, "", "renderTargetBlockOutline");
    public static final ConfigBooleanHotkeyed	SKY = new ConfigBooleanHotkeyed("Sky", true, "", "renderSky");

    public static final ConfigLabel             SEPARATOR5 = new ConfigLabel("");
    public static final ConfigLabel             SEPARATOR6 = new ConfigLabel("Render layer:");

    public static final ConfigBooleanHotkeyed   SOLID = new ConfigBooleanHotkeyed("Solid", true, "", "solid");
    public static final ConfigBooleanHotkeyed   CUTOUT_MIPPED = new ConfigBooleanHotkeyed("Cutout mipped", true, "", "cutout_mipped");
    public static final ConfigBooleanHotkeyed   CUTOUT = new ConfigBooleanHotkeyed("Cutout", true, "", "cutout");
    public static final ConfigBooleanHotkeyed   TRANSLUCENT = new ConfigBooleanHotkeyed("Translucent", true, "", "translucent");
    public static final ConfigBooleanHotkeyed   TRANSLUCENT_MOVING_BLOCK = new ConfigBooleanHotkeyed("Translucent moving block", true, "", "translucent_moving_block");
    public static final ConfigBooleanHotkeyed   ARMOR_CUTOUT_NO_CULL = new ConfigBooleanHotkeyed("Armor cutout no cull", true, "", "armor_cutout_no_cull");
    public static final ConfigBooleanHotkeyed   ARMOR_TRANSLUCENT = new ConfigBooleanHotkeyed("Armor translucent", true, "", "armor_translucent");
    public static final ConfigBooleanHotkeyed   ENTITY_SOLID = new ConfigBooleanHotkeyed("Entity solid", true, "", "entity_solid");
    public static final ConfigBooleanHotkeyed   ENTITY_SOLID_Z_OFFSET_FORWARD = new ConfigBooleanHotkeyed("Entity solid z offset forward", true, "", "entity_solid_z_offset_forward");
    public static final ConfigBooleanHotkeyed   ENTITY_CUTOUT = new ConfigBooleanHotkeyed("Entity cutout", true, "", "entity_cutout");
    public static final ConfigBooleanHotkeyed   ENTITY_CUTOUT_NO_CULL = new ConfigBooleanHotkeyed("Entity cutout no cull", true, "", "entity_cutout_no_cull");
    public static final ConfigBooleanHotkeyed   ENTITY_CUTOUT_NO_CULL_Z_OFFSET = new ConfigBooleanHotkeyed("Entity cutout no cull z offset", true, "", "entity_cutout_no_cull_z_offset");
    public static final ConfigBooleanHotkeyed   ITEM_ENTITY_TRANSLUCENT_CULL = new ConfigBooleanHotkeyed("Item entity translucent cull", true, "", "item_entity_translucent_cull");
    public static final ConfigBooleanHotkeyed   ENTITY_TRANSLUCENT = new ConfigBooleanHotkeyed("Entity translucent", true, "", "entity_translucent");
    public static final ConfigBooleanHotkeyed   ENTITY_TRANSLUCENT_EMISSIVE = new ConfigBooleanHotkeyed("Entity translucent emissive", true, "", "entity_translucent_emissive");
    public static final ConfigBooleanHotkeyed   ENTITY_SMOOTH_CUTOUT = new ConfigBooleanHotkeyed("Entity smooth cutout", true, "", "entity_smooth_cutout");
    public static final ConfigBooleanHotkeyed   BEACON_BEAM = new ConfigBooleanHotkeyed("Beacon beam", true, "", "beacon_beam");
    public static final ConfigBooleanHotkeyed   ENTITY_DECAL = new ConfigBooleanHotkeyed("Entity decal", true, "", "entity_decal");
    public static final ConfigBooleanHotkeyed   ENTITY_NO_OUTLINE = new ConfigBooleanHotkeyed("Entity no outline", true, "", "entity_no_outline");
    public static final ConfigBooleanHotkeyed   ENTITY_SHADOW = new ConfigBooleanHotkeyed("Entity shadow", true, "", "entity_shadow");
    public static final ConfigBooleanHotkeyed   ENTITY_ALPHA = new ConfigBooleanHotkeyed("Entity alpha", true, "", "entity_alpha");
    public static final ConfigBooleanHotkeyed   EYES = new ConfigBooleanHotkeyed("Eyes", true, "", "eyes");
    public static final ConfigBooleanHotkeyed   LEASH = new ConfigBooleanHotkeyed("Leash", true, "", "leash");
    public static final ConfigBooleanHotkeyed   WATER_MASK = new ConfigBooleanHotkeyed("Water mask", true, "", "water_mask");
    public static final ConfigBooleanHotkeyed   ARMOR_ENTITY_GLINT = new ConfigBooleanHotkeyed("Armor entity glint", true, "", "armor_entity_glint");
    public static final ConfigBooleanHotkeyed   GLINT_TRANSLUCENT = new ConfigBooleanHotkeyed("Glint translucent", true, "", "glint_translucent");
    public static final ConfigBooleanHotkeyed   GLINT = new ConfigBooleanHotkeyed("Glint", true, "", "glint");
    public static final ConfigBooleanHotkeyed   ENTITY_GLINT = new ConfigBooleanHotkeyed("Entity glint", true, "", "entity_glint");
    public static final ConfigBooleanHotkeyed   CRUMBLING = new ConfigBooleanHotkeyed("Crumbling", true, "", "crumbling");
    public static final ConfigBooleanHotkeyed   TEXT = new ConfigBooleanHotkeyed("Text", true, "", "text");
    public static final ConfigBooleanHotkeyed   TEXT_BACKGROUND = new ConfigBooleanHotkeyed("Text background", true, "", "text_background");
    public static final ConfigBooleanHotkeyed   TEXT_INTENSITY = new ConfigBooleanHotkeyed("Text intensity", true, "", "text_intensity");
    public static final ConfigBooleanHotkeyed   TEXT_POLYGON_OFFSET = new ConfigBooleanHotkeyed("Text polygon offset", true, "", "text_polygon_offset");
    public static final ConfigBooleanHotkeyed   TEXT_INTENSITY_POLYGON_OFFSET = new ConfigBooleanHotkeyed("Text intensity polygon offset", true, "", "text_intensity_polygon_offset");
    public static final ConfigBooleanHotkeyed   TEXT_SEE_THROUGH = new ConfigBooleanHotkeyed("Text see through", true, "", "text_see_through");
    public static final ConfigBooleanHotkeyed   TEXT_BACKGROUND_SEE_THROUGH = new ConfigBooleanHotkeyed("Text background see through", true, "", "text_background_see_through");
    public static final ConfigBooleanHotkeyed   TEXT_INTENSITY_SEE_THROUGH = new ConfigBooleanHotkeyed("Text intensity see through", true, "", "text_intensity_see_through");
    public static final ConfigBooleanHotkeyed   LIGHTNING = new ConfigBooleanHotkeyed("Lightning", true, "", "lightning");
    public static final ConfigBooleanHotkeyed   DRAGON_RAYS = new ConfigBooleanHotkeyed("Dragon rays", true, "", "dragon_rays");
    public static final ConfigBooleanHotkeyed   DRAGON_RAYS_DEPTH = new ConfigBooleanHotkeyed("Dragon rays depth", true, "", "dragon_rays_depth");
    public static final ConfigBooleanHotkeyed   TRIPWIRE = new ConfigBooleanHotkeyed("Tripwire", true, "", "tripwire");
    public static final ConfigBooleanHotkeyed   END_PORTAL = new ConfigBooleanHotkeyed("End portal", true, "", "end_portal");
    public static final ConfigBooleanHotkeyed   END_GATEWAY = new ConfigBooleanHotkeyed("End gateway", true, "", "end_gateway");
    public static final ConfigBooleanHotkeyed   LINES = new ConfigBooleanHotkeyed("Lines", true, "", "lines");
    public static final ConfigBooleanHotkeyed   SECONDARY_BLOCK_OUTLINE = new ConfigBooleanHotkeyed("Secondary block outline", true, "", "secondary_block_outline");
    public static final ConfigBooleanHotkeyed   LINE_STRIP = new ConfigBooleanHotkeyed("Line strip", true, "", "line_strip");
    public static final ConfigBooleanHotkeyed   DEBUG_LINE_STRIP = new ConfigBooleanHotkeyed("Debug line strip", true, "", "debug_line_strip");
    public static final ConfigBooleanHotkeyed   DEBUG_LINE = new ConfigBooleanHotkeyed("Debug line", true, "", "debug_line");
    public static final ConfigBooleanHotkeyed   DEBUG_FILLED_BOX = new ConfigBooleanHotkeyed("Debug filled box", true, "", "debug_filled_box");
    public static final ConfigBooleanHotkeyed   DEBUG_QUADS = new ConfigBooleanHotkeyed("Debug quads", true, "", "debug_quads");
    public static final ConfigBooleanHotkeyed   DEBUG_TRIANGLE_FAN = new ConfigBooleanHotkeyed("Debug triangle fan", true, "", "debug_triangle_fan");
    public static final ConfigBooleanHotkeyed   DEBUG_STRUCTURE_QUADS = new ConfigBooleanHotkeyed("Debug structure quads", true, "", "debug_structure_quads");
    public static final ConfigBooleanHotkeyed   DEBUG_SECTION_QUADS = new ConfigBooleanHotkeyed("Debug section quads", true, "", "debug_section_quads");
    public static final ConfigBooleanHotkeyed   OPAQUE_PARTICLE = new ConfigBooleanHotkeyed("Opaque particle", true, "", "opaque_particle");
    public static final ConfigBooleanHotkeyed   TRANSLUCENT_PARTICLE = new ConfigBooleanHotkeyed("Translucent particle", true, "", "translucent_particle");
    public static final ConfigBooleanHotkeyed   WEATHER_ALL_MASK = new ConfigBooleanHotkeyed("Weather all mask", true, "", "weather_all_mask");
    public static final ConfigBooleanHotkeyed   WEATHER_COLOR_MASK = new ConfigBooleanHotkeyed("Weather color mask", true, "", "weather_color_mask");
    public static final ConfigBooleanHotkeyed   SUNRISE_SUNSET = new ConfigBooleanHotkeyed("Sunrise sunset", true, "", "sunrise_sunset");
    public static final ConfigBooleanHotkeyed   CELESTIAL = new ConfigBooleanHotkeyed("Celestial", true, "", "celestial");
    public static final ConfigBooleanHotkeyed   BLOCK_SCREEN_EFFECT = new ConfigBooleanHotkeyed("Block screen effect", true, "", "block_screen_effect");
    public static final ConfigBooleanHotkeyed   FIRE_SCREEN_EFFECT = new ConfigBooleanHotkeyed("Fire screen effect", true, "", "fire_screen_effect");
    public static final ConfigBooleanHotkeyed   GUI = new ConfigBooleanHotkeyed("GUI", true, "", "gui");
    public static final ConfigBooleanHotkeyed   GUI_OVERLAY = new ConfigBooleanHotkeyed("GUI overlay", true, "", "gui_overlay");
    public static final ConfigBooleanHotkeyed   GUI_TEXTURED_OVERLAY = new ConfigBooleanHotkeyed("GUI textured overlay", true, "", "gui_textured_overlay");
    public static final ConfigBooleanHotkeyed   GUI_OPAQUE_TEXTURED_BACKGROUND = new ConfigBooleanHotkeyed("GUI opaque textured background", true, "", "gui_opaque_textured_background");
    public static final ConfigBooleanHotkeyed   GUI_NAUSEA_OVERLAY = new ConfigBooleanHotkeyed("GUI nausea overlay", true, "", "gui_nausea_overlay");
    public static final ConfigBooleanHotkeyed   GUI_TEXT_HIGHLIGHT = new ConfigBooleanHotkeyed("GUI text highlight", true, "", "gui_text_highlight");
    public static final ConfigBooleanHotkeyed   GUI_GHOST_RECIPE_OVERLAY = new ConfigBooleanHotkeyed("GUI ghost recipe overlay", true, "", "gui_ghost_recipe_overlay");
    public static final ConfigBooleanHotkeyed   GUI_TEXTURED = new ConfigBooleanHotkeyed("GUI textured", true, "", "gui_textured");
    public static final ConfigBooleanHotkeyed   VIGNETTE = new ConfigBooleanHotkeyed("Vignette", true, "", "vignette");
    public static final ConfigBooleanHotkeyed   CROSSHAIR_TEXTURED = new ConfigBooleanHotkeyed("Crosshair (textured)", true, "", "crosshair");
    public static final ConfigBooleanHotkeyed   MOJANG_LOGO = new ConfigBooleanHotkeyed("Mojang logo", true, "", "mojang_logo");


    public static final List<? extends IConfigBase> OPTIONS = ConfigHandler.generateOptions();

//    {
            GameRenderer gr = new GameRenderer(null, null, null, null);
            WorldRenderer wr = new WorldRenderer(null, null, null, null);
            InGameHud igh = new InGameHud(null);

//            igh.renderMiscOverlays();
//            igh.renderSleepOverlay();
//            igh.renderOverlayMessage();
//            igh.renderTitleAndSubtitle();
//            igh.renderChat();
//            igh.renderScoreboardSidebar(null, (ScoreboardObjective) null);
//            igh.renderPlayerList();
//            igh.renderCrosshair();
//            igh.renderStatusEffectOverlay();
//            igh.renderMainHud();
//            igh.renderHotbar();
//            igh.renderMountJumpBar();
//            igh.renderExperienceBar();
//            igh.renderExperienceLevel();
//            igh.renderHeldItemTooltip();
//            igh.renderDemoTimer();
//            igh.renderStatusBars();
//            igh.renderArmor();
//            igh.renderHealthBar();
//            igh.renderFood();
//            igh.renderMountHealth();
//            igh.renderSpyglassOverlay();
//            igh.renderVignetteOverlay();
//            igh.renderPortalOverlay();
//            igh.renderNauseaOverlay();
//            igh.renderHotbarItem();
//            igh.renderAutosaveIndicator();
//
//            gr.renderWorld(null);
//            gr.renderFloatingItem();
//            gr.renderBlur();
//            gr.renderHand();

            // gr.renderOverlay
            // gr.renderScreen

//            wr.renderMain();
//            wr.renderParticles();
//            wr.renderClouds();
//            wr.renderWeather();
//            wr.renderLateDebug();
//            wr.renderEntities();
//            wr.renderBlockEntities();
//            wr.renderBlockDamage();
//            wr.renderTargetBlockOutline();
//            wr.renderSky();
//            wr.renderLayer(RenderLayer.SOLID);
//            wr.renderLayer(RenderLayer.CUTOUT_MIPPED);
//            wr.renderLayer(RenderLayer.CUTOUT);
//            wr.renderLayer(RenderLayer.TRANSLUCENT);
//            wr.renderLayer(RenderLayer.TRANSLUCENT_MOVING_BLOCK);
//            wr.renderLayer(RenderLayer.LEASH);
//            wr.renderLayer(RenderLayer.WATER_MASK);
//            wr.renderLayer(RenderLayer.ARMOR_ENTITY_GLINT);
//            wr.renderLayer(RenderLayer.GLINT_TRANSLUCENT);
//            wr.renderLayer(RenderLayer.GLINT);
//            wr.renderLayer(RenderLayer.ENTITY_GLINT);
//            wr.renderLayer(RenderLayer.TEXT_BACKGROUND);
//            wr.renderLayer(RenderLayer.TEXT_BACKGROUND_SEE_THROUGH);
//            wr.renderLayer(RenderLayer.LIGHTNING);
//            wr.renderLayer(RenderLayer.DRAGON_RAYS);
//            wr.renderLayer(RenderLayer.DRAGON_RAYS_DEPTH);
//            wr.renderLayer(RenderLayer.TRIPWIRE);
//            wr.renderLayer(RenderLayer.END_PORTAL);
//            wr.renderLayer(RenderLayer.END_GATEWAY);
//			  wr.renderLayer(RenderLayer.DEBUG_FILLED_BOX);
//			  wr.renderLayer(RenderLayer.DEBUG_QUADS);
//			  wr.renderLayer(RenderLayer.DEBUG_TRIANGLE_FAN);
//		  	  wr.renderLayer(RenderLayer.DEBUG_STRUCTURE_QUADS);
//			  wr.renderLayer(RenderLayer.DEBUG_SECTION_QUADS);
//            wr.renderLayer(RenderLayer.SUNRISE_SUNSET);
//            wr.renderLayer(RenderLayer.GUI);
//            wr.renderLayer(RenderLayer.GUI_OVERLAY);
//            wr.renderLayer(RenderLayer.GUI_NAUSEA_OVERLAY);
//            wr.renderLayer(RenderLayer.GUI_TEXT_HIGHLIGHT);
//            wr.renderLayer(RenderLayer.GUI_GHOST_RECIPE_OVERLAY);
//            wr.renderLayer(RenderLayer.MOJANG_LOGO);
//    }
}
