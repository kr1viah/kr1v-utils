package kr1v.kr1vUtils.client.config.configs;

import fi.dy.masa.malilib.config.options.ConfigDouble;
import fi.dy.masa.malilib.config.options.ConfigInteger;
import kr1v.kr1vUtils.client.utils.annotation.Config;
import kr1v.kr1vUtils.client.utils.annotation.DependantOn;
import kr1v.kr1vUtils.client.utils.annotation.Dependency;
import kr1v.kr1vUtils.client.utils.annotation.Label;
import kr1v.kr1vUtils.client.utils.malilib.ConfigBooleanPlus;
import kr1v.kr1vUtils.client.utils.malilib.ConfigIntegerPlus;

@Config(name = "World generation")
@SuppressWarnings("unused")
public class WorldGen {
    @Dependency("Affect generation in general")
    @DependantOn("Affect anything")
    public static final ConfigBooleanPlus   AFFECT_GENERATION = new ConfigBooleanPlus("Affect generation in general");

    @Label("Generation steps")
    @Dependency("Affect generation steps")
    @DependantOn("Affect generation in general")
    public static final ConfigBooleanPlus   GENERATION_STEPS = new ConfigBooleanPlus("Affect generation steps");
    @DependantOn("Affect generation steps")
    public static final ConfigBooleanPlus   GENERATE_STRUCTURES = new ConfigBooleanPlus("Generate structures");
    @DependantOn("Affect generation steps")
    public static final ConfigBooleanPlus   LOAD_STRUCTURES = new ConfigBooleanPlus("Load structures");
    @DependantOn("Affect generation steps")
    public static final ConfigBooleanPlus   GENERATE_STRUCTURE_REFERENCES = new ConfigBooleanPlus("Generate structure references");
    @DependantOn("Affect generation steps")
    public static final ConfigBooleanPlus   POPULATE_BIOMES = new ConfigBooleanPlus("Populate biomes");
    @DependantOn("Affect generation steps")
    public static final ConfigBooleanPlus   POPULATE_NOISE = new ConfigBooleanPlus("Populate noise");
    @DependantOn("Affect generation steps")
    public static final ConfigBooleanPlus   BUILD_SURFACE = new ConfigBooleanPlus("Build surface");
    @DependantOn("Affect generation steps")
    public static final ConfigBooleanPlus   CARVE = new ConfigBooleanPlus("Carve");
    @DependantOn("Affect generation steps")
    public static final ConfigBooleanPlus   GENERATE_FEATURES = new ConfigBooleanPlus("Generate features");
    @DependantOn("Affect generation steps")
    public static final ConfigBooleanPlus   INITIALIZE_LIGHT = new ConfigBooleanPlus("Initialize light");
    @DependantOn("Affect generation steps")
    public static final ConfigBooleanPlus   LIGHT = new ConfigBooleanPlus("Light");
    @DependantOn("Affect generation steps")
    public static final ConfigBooleanPlus   GENERATE_ENTITIES = new ConfigBooleanPlus("Generate entities");
    @DependantOn("Affect generation steps")
    public static final ConfigBooleanPlus   CONVERT_TO_FULL_CHUNK = new ConfigBooleanPlus("Convert to full chunk");

    @Label("Caves")
    @Dependency("Generate caves")
    @DependantOn("Affect generation in general")
    public static final ConfigBooleanPlus   CAVES = new ConfigBooleanPlus("Generate caves");
    @DependantOn("Generate caves")
    public static final ConfigBooleanPlus   CARVE_CAVE = new ConfigBooleanPlus("Carve caves");
    @DependantOn("Generate caves")
    public static final ConfigBooleanPlus   CARVE_TUNNELS = new ConfigBooleanPlus("Carve tunnels");
    @DependantOn("Generate caves")
    public static final ConfigBooleanPlus   NETHER_CAVES = new ConfigBooleanPlus("Generate nether caves");
    @DependantOn("Generate caves")
    public static final ConfigBooleanPlus   RAVINES = new ConfigBooleanPlus("Generate ravines");

    @Label
    @DependantOn("Affect generation in general")
    public static final ConfigBooleanPlus   OVERRIDE_STRUCTURE_HEIGHT = new ConfigBooleanPlus("Override structure height", false);
    @DependantOn("Affect generation in general")
    public static final ConfigInteger           OVERRIDE_STRUCTURE_HEIGHT_VALUE = new ConfigIntegerPlus("Override structure height with", 64);

    @Label
    @DependantOn("Affect generation in general")
    public static final ConfigBooleanPlus   ICE_BERGS = new ConfigBooleanPlus("Ice bergs");
    @DependantOn("Affect generation in general")
    public static final ConfigBooleanPlus   BAD_LANDS_PILLARS = new ConfigBooleanPlus("Bad lands pillars");

    @Label("Double perlin noise overrides")
    @Dependency("Override double perlin noise")
    public static final ConfigBooleanPlus   OVERRIDE_DOUBLE_PERLIN_NOISE = new ConfigBooleanPlus("Override double perlin noise", false);
    @DependantOn("Override double perlin noise")
    public static final ConfigDouble            DPN_AMPLITUDE = new ConfigDouble("Amplitude", 1);
    @DependantOn("Override double perlin noise")
    public static final ConfigDouble            DPN_MAX_VALUE = new ConfigDouble("DPN Max value", 1);
    @DependantOn("Override double perlin noise")
    public static final ConfigDouble            DPN_MAGIC_CONSTANT = new ConfigDouble("Magic constant", 1.0181268882175227);

    @Label("Octave perlin noise overrides")
    @Dependency("Override octave perlin noise")
    public static final ConfigBooleanPlus   OVERRIDE_OCTAVE_PERLIN_NOISE = new ConfigBooleanPlus("Override octave perlin noise", false);
    @DependantOn("Override double perlin noise")
    public static final ConfigInteger           OPN_FIRST_OCTAVE = new ConfigInteger("First octave", 2);
    @DependantOn("Override double perlin noise")
    public static final ConfigDouble            OPN_PERSISTENCE = new ConfigDouble("Persistence", 0.5714285714285714);
    @DependantOn("Override double perlin noise")
    public static final ConfigDouble            OPN_LACUNARITY = new ConfigDouble("Lacunarity", 8);
    @DependantOn("Override double perlin noise")
    public static final ConfigDouble            OPN_MAX_VALUE = new ConfigDouble("OPN Max value", 2.0);

    @Label("Simplex noise overrides")
    @Dependency("Override simplex noise")
    public static final ConfigBooleanPlus   SN_OVERRIDE_SIMPLEX_NOISE = new ConfigBooleanPlus("Override simplex noise", false);
    @DependantOn("Override simplex noise")
    public static final ConfigDouble            SN_SQRT_3 = new ConfigDouble("Square root of 3", 1.7320508075688772);
    @DependantOn("Override simplex noise")
    public static final ConfigDouble            SN_SKEW_FACTOR_2d = new ConfigDouble("Skew factor 2d", 0.3660254037844386);
    @DependantOn("Override simplex noise")
    public static final ConfigDouble            SN_UNSKEW_FACTOR_2d = new ConfigDouble("Unskew factor 2d", 0.2113248654051871);
    @DependantOn("Override simplex noise")
    public static final ConfigDouble            SN_ORIGIN_X = new ConfigDouble("Origin x", 0.0);
    @DependantOn("Override simplex noise")
    public static final ConfigDouble            SN_ORIGIN_Y = new ConfigDouble("Origin y", 0.0);
    @DependantOn("Override simplex noise")
    public static final ConfigDouble            SN_ORIGIN_Z = new ConfigDouble("Origin z", 0.0);
}
