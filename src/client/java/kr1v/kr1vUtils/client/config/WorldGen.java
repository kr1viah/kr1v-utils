package kr1v.kr1vUtils.client.config;

import fi.dy.masa.malilib.config.options.ConfigBooleanHotkeyed;
import fi.dy.masa.malilib.config.options.ConfigDouble;
import fi.dy.masa.malilib.config.options.ConfigInteger;
import kr1v.kr1vUtils.client.utils.annotation.Config;
import kr1v.kr1vUtils.client.utils.annotation.Label;
import kr1v.kr1vUtils.client.utils.malilib.ConfigBooleanPlus;

@Config(name = "World generation")
@SuppressWarnings("unused")
public class WorldGen {
    public static final ConfigBooleanHotkeyed   GENERATE_STRUCTURES = new ConfigBooleanPlus("Generate structures");
    public static final ConfigBooleanHotkeyed   LOAD_STRUCTURES = new ConfigBooleanPlus("Load structures");
    public static final ConfigBooleanHotkeyed   GENERATE_STRUCTURE_REFERENCES = new ConfigBooleanPlus("Generate structure references");
    public static final ConfigBooleanHotkeyed   POPULATE_BIOMES = new ConfigBooleanPlus("Populate biomes");
    public static final ConfigBooleanHotkeyed   POPULATE_NOISE = new ConfigBooleanPlus("Populate noise");
    public static final ConfigBooleanHotkeyed   BUILD_SURFACE = new ConfigBooleanPlus("Build surface");
    public static final ConfigBooleanHotkeyed   CARVE = new ConfigBooleanPlus("Carve");
    public static final ConfigBooleanHotkeyed   GENERATE_FEATURES = new ConfigBooleanPlus("Generate features");
    public static final ConfigBooleanHotkeyed   INITIALIZE_LIGHT = new ConfigBooleanPlus("Initialize light");
    public static final ConfigBooleanHotkeyed   LIGHT = new ConfigBooleanPlus("Light");
    public static final ConfigBooleanHotkeyed   GENERATE_ENTITIES = new ConfigBooleanPlus("Generate entities");
    public static final ConfigBooleanHotkeyed   CONVERT_TO_FULL_CHUNK = new ConfigBooleanPlus("Convert to full chunk");

    @Label("Caves")
    public static final ConfigBooleanHotkeyed   CAVES = new ConfigBooleanPlus("Generate caves");
    public static final ConfigBooleanHotkeyed   CARVE_CAVE = new ConfigBooleanPlus("Carve caves");
    public static final ConfigBooleanHotkeyed   CARVE_TUNNELS = new ConfigBooleanPlus("Carve tunnels");
    public static final ConfigBooleanHotkeyed   NETHER_CAVES = new ConfigBooleanPlus("Generate nether caves");
    public static final ConfigBooleanHotkeyed   RAVINES = new ConfigBooleanPlus("Generate ravines");

    @Label
    public static final ConfigBooleanHotkeyed   OVERRIDE_STRUCTURE_HEIGHT = new ConfigBooleanPlus("Override structure height", false);
    public static final ConfigInteger           OVERRIDE_STRUCTURE_HEIGHT_VALUE = new ConfigInteger("Override structure height with", 64);

    @Label
    public static final ConfigBooleanHotkeyed   ICE_BERGS = new ConfigBooleanPlus("Ice bergs");
    public static final ConfigBooleanHotkeyed   BAD_LANDS_PILLARS = new ConfigBooleanPlus("Bad lands pillars");

    @Label("Double perlin noise overrides")
    public static final ConfigBooleanHotkeyed   OVERRIDE_DOUBLE_PERLIN_NOISE = new ConfigBooleanPlus("Override double perlin noise", false);
    public static final ConfigDouble            DPN_AMPLITUDE = new ConfigDouble("Amplitude", 1);
    public static final ConfigDouble            DPN_MAX_VALUE = new ConfigDouble("DPN Max value", 1);
    public static final ConfigDouble            DPN_MAGIC_CONSTANT = new ConfigDouble("Magic constant", 1.0181268882175227);

    @Label("Octave perlin noise overrides")
    public static final ConfigBooleanHotkeyed   OVERRIDE_OCTAVE_PERLIN_NOISE = new ConfigBooleanPlus("Override octave perlin noise", false);
    public static final ConfigInteger           OPN_FIRST_OCTAVE = new ConfigInteger("First octave", 2);
    public static final ConfigDouble            OPN_PERSISTENCE = new ConfigDouble("Persistence", 0.5714285714285714);
    public static final ConfigDouble            OPN_LACUNARITY = new ConfigDouble("Lacunarity", 8);
    public static final ConfigDouble            OPN_MAX_VALUE = new ConfigDouble("OPN Max value", 2.0);

    @Label("Simplex noise overrides")
    public static final ConfigBooleanHotkeyed   SN_OVERRIDE_SIMPLEX_NOISE = new ConfigBooleanPlus("Override simplex noise", false);
    public static final ConfigDouble            SN_SQRT_3 = new ConfigDouble("Square root of 3", 1.7320508075688772);
    public static final ConfigDouble            SN_SKEW_FACTOR_2d = new ConfigDouble("Skew factor 2d", 0.3660254037844386);
    public static final ConfigDouble            SN_UNSKEW_FACTOR_2d = new ConfigDouble("Unskew factor 2d", 0.2113248654051871);
    public static final ConfigDouble            SN_ORIGIN_X = new ConfigDouble("Origin x", 0.0);
    public static final ConfigDouble            SN_ORIGIN_Y = new ConfigDouble("Origin y", 0.0);
    public static final ConfigDouble            SN_ORIGIN_Z = new ConfigDouble("Origin z", 0.0);
}
