package kr1v.kr1vUtils.client.config.configs;

import fi.dy.masa.malilib.config.IConfigBase;
import fi.dy.masa.malilib.config.options.ConfigDouble;
import fi.dy.masa.malilib.hotkeys.KeybindSettings;
import kr1v.kr1vUtils.client.utils.ClassUtils;
import kr1v.kr1vUtils.client.utils.MappingUtils;
import kr1v.kr1vUtils.client.utils.StringUtils;
import kr1v.kr1vUtils.client.utils.annotation.classannotations.Config;
import kr1v.kr1vUtils.client.utils.annotation.classannotations.PopupConfig;
import kr1v.kr1vUtils.client.utils.annotation.fieldannotations.Label;
import kr1v.kr1vUtils.client.utils.annotation.fieldannotations.Marker;
import kr1v.kr1vUtils.client.utils.annotation.methodannotations.Extras;
import kr1v.kr1vUtils.client.utils.malilib.KeybindSetting;
import kr1v.kr1vUtils.client.utils.malilib.plus.ConfigBooleanPlus;
import net.minecraft.client.render.RenderLayer;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

@Config
public class Render {
    public static final ConfigBooleanPlus       AFFECT_OFFSETTING = new ConfigBooleanPlus("Affect offsetting");
    public static final Class<?> CLASS_NAME = Offsetting.class;
    @PopupConfig
    public static class Offsetting {
        public static final ConfigDouble            OFFSET_X = new ConfigDouble("Offset x", 0, -100, 100, "");
        public static final ConfigDouble            OFFSET_Y = new ConfigDouble("Offset y", 0, -100, 100,"");
        public static final ConfigDouble            OFFSET_Z = new ConfigDouble("Offset z", 0, -100, 100,"");
        public static final ConfigBooleanPlus       RELATIVE_TO_PLAYER_ANGLE = new ConfigBooleanPlus("Offset relative to player angle");
    }

    @Label
    @Label("In game hud:")
    public static final ConfigBooleanPlus       MISC_OVERLAYS = new ConfigBooleanPlus("Misc overlays");
    public static final ConfigBooleanPlus       SLEEP_OVERLAY = new ConfigBooleanPlus("Sleep overlay");
    public static final ConfigBooleanPlus       OVERLAY_MESSAGE = new ConfigBooleanPlus("Overlay message");
    public static final ConfigBooleanPlus       TITLE_AND_SUBTITLE = new ConfigBooleanPlus("Title and subtitle");
    public static final ConfigBooleanPlus       CHAT = new ConfigBooleanPlus("Chat");
    public static final ConfigBooleanPlus       SCOREBOARD_SIDEBAR = new ConfigBooleanPlus("Scoreboard sidebar");
    public static final ConfigBooleanPlus       PLAYER_LIST = new ConfigBooleanPlus("Player list");
    public static final ConfigBooleanPlus       CROSSHAIR = new ConfigBooleanPlus("Crosshair (igh)");
    public static final ConfigBooleanPlus       STATUS_EFFECT_OVERLAY = new ConfigBooleanPlus("Status effect overlay");
    public static final ConfigBooleanPlus       MAIN_HUD = new ConfigBooleanPlus("Main HUD");
    public static final ConfigBooleanPlus       HOTBAR = new ConfigBooleanPlus("Hotbar");
    public static final ConfigBooleanPlus       MOUNT_JUMP_BAR = new ConfigBooleanPlus("Mount jump bar");
    public static final ConfigBooleanPlus       EXPERIENCE_BAR = new ConfigBooleanPlus("Experience bar");
    public static final ConfigBooleanPlus       EXPERIENCE_LEVEL = new ConfigBooleanPlus("Experience level");
    public static final ConfigBooleanPlus       HELD_ITEM_TOOLTIP = new ConfigBooleanPlus("Held item tooltip");
    public static final ConfigBooleanPlus       DEMO_TIMER = new ConfigBooleanPlus("Demo timer");
    public static final ConfigBooleanPlus       STATUS_BARS = new ConfigBooleanPlus("Status bars");
    public static final ConfigBooleanPlus       ARMOR = new ConfigBooleanPlus("Armor");
    public static final ConfigBooleanPlus       HEALTH_BAR = new ConfigBooleanPlus("Health bar");
    public static final ConfigBooleanPlus       FOOD = new ConfigBooleanPlus("Food");
    public static final ConfigBooleanPlus       MOUNT_HEALTH = new ConfigBooleanPlus("Mount health");
    public static final ConfigBooleanPlus       SPYGLASS_OVERLAY = new ConfigBooleanPlus("Spyglass overlay");
    public static final ConfigBooleanPlus       VIGNETTE_OVERLAY = new ConfigBooleanPlus("Vignette overlay");
    public static final ConfigBooleanPlus       PORTAL_OVERLAY = new ConfigBooleanPlus("Portal overlay");
    public static final ConfigBooleanPlus       NAUSEA_OVERLAY = new ConfigBooleanPlus("Nausea overlay");
    public static final ConfigBooleanPlus       HOTBAR_ITEM = new ConfigBooleanPlus("Hotbar item");
    public static final ConfigBooleanPlus       AUTOSAVE_INDICATOR = new ConfigBooleanPlus("Autosave indicator");

    @Extras(runAfterLabel = "Render layer:")
    public static void addRenderLayers(List<IConfigBase> currentList) {
        for (Field field : ClassUtils.getAllFields(RenderLayer.MultiPhase.class)) {
            if (Modifier.isStatic(field.getModifiers())) {
                if (RenderLayer.class.isAssignableFrom(field.getType()) ||
                        BiFunction.class.isAssignableFrom(field.getType()) ||
                        Function.class.isAssignableFrom(field.getType())) {

                    String name = MappingUtils.intermediaryToYarnSimple(field).toLowerCase(Locale.ROOT);

                    ConfigBooleanPlus hotkey = new ConfigBooleanPlus(StringUtils.convertCamelCase(name), true, "", (KeybindSettings) KeybindSetting.ofAny(), name, "", "");

                    currentList.add(hotkey);
                    Render.RENDER_HOTKEYS.put(name, hotkey);
                }
            }
        }
    }

    @Label
    @Label("Render layer:")
    @Marker("Test")
    @Label
    @Label("Game renderer:")
    public static final ConfigBooleanPlus       MAIN = new ConfigBooleanPlus("Main");
    public static final ConfigBooleanPlus       PARTICLES = new ConfigBooleanPlus("Particles");
    public static final ConfigBooleanPlus       CLOUDS = new ConfigBooleanPlus("Clouds");
    public static final ConfigBooleanPlus       WEATHER = new ConfigBooleanPlus("Weather");
    public static final ConfigBooleanPlus       LATE_DEBUG = new ConfigBooleanPlus("Late debug");
    public static final ConfigBooleanPlus       ENTITIES = new ConfigBooleanPlus("Entities");
    public static final ConfigBooleanPlus       BLOCK_ENTITIES = new ConfigBooleanPlus("Block entities");
    public static final ConfigBooleanPlus       BLOCK_DAMAGE = new ConfigBooleanPlus("Block damage");
    public static final ConfigBooleanPlus       TARGET_BLOCK_OUTLINE = new ConfigBooleanPlus("Target block outline");
    public static final ConfigBooleanPlus       SKY = new ConfigBooleanPlus("Sky");

    public static final Map<String, ConfigBooleanPlus> RENDER_HOTKEYS = new HashMap<>();
}
