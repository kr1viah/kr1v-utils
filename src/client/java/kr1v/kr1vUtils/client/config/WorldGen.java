package kr1v.kr1vUtils.client.config;

import kr1v.kr1vUtils.client.utils.annotation.Config;
import kr1v.kr1vUtils.client.utils.malilib.ConfigBooleanPlus;

@Config(name = "World generation")
@SuppressWarnings("unused")
public class WorldGen {
    public static final ConfigBooleanPlus GENERATE_STRUCTURES = new ConfigBooleanPlus("Generate structures");
    public static final ConfigBooleanPlus LOAD_STRUCTURES = new ConfigBooleanPlus("Load structures");
    public static final ConfigBooleanPlus GENERATE_STRUCTURE_REFERENCES = new ConfigBooleanPlus("Generate structure references");
    public static final ConfigBooleanPlus POPULATE_BIOMES = new ConfigBooleanPlus("Populate biomes");
    public static final ConfigBooleanPlus POPULATE_NOISE = new ConfigBooleanPlus("Populate noise");
    public static final ConfigBooleanPlus BUILD_SURFACE = new ConfigBooleanPlus("Build surface");
    public static final ConfigBooleanPlus CARVE = new ConfigBooleanPlus("Carve");
    public static final ConfigBooleanPlus GENERATE_FEATURES = new ConfigBooleanPlus("Generate features");
    public static final ConfigBooleanPlus INITIALIZE_LIGHT = new ConfigBooleanPlus("Initialize light");
    public static final ConfigBooleanPlus LIGHT = new ConfigBooleanPlus("Light");
    public static final ConfigBooleanPlus GENERATE_ENTITIES = new ConfigBooleanPlus("Generate entities");
    public static final ConfigBooleanPlus CONVERT_TO_FULL_CHUNK = new ConfigBooleanPlus("Convert to full chunk");

}
