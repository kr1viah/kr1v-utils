package kr1v.kr1vUtils.client.mixin.worldgen;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import kr1v.kr1vUtils.client.config.configs.WorldGen;
import net.minecraft.world.HeightLimitView;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.chunk.NoiseChunkGenerator;
import net.minecraft.world.gen.noise.NoiseConfig;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(NoiseChunkGenerator.class)
public class NoiseChunkGeneratorMixin {
    @WrapMethod(method = "getHeight")
    private int getHeight(int x, int z, Heightmap.Type heightmap, HeightLimitView world, NoiseConfig noiseConfig, Operation<Integer> original) {
        if (WorldGen.OVERRIDE_STRUCTURE_HEIGHT.shouldHandle()) {
            return WorldGen.OVERRIDE_STRUCTURE_HEIGHT_VALUE.getIntegerValue();
        }
        return original.call(x, z, heightmap, world, noiseConfig);
    }
}
