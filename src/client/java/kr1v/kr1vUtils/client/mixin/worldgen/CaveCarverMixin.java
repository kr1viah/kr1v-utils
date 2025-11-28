package kr1v.kr1vUtils.client.mixin.worldgen;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import kr1v.kr1vUtils.client.config.WorldGen;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.carver.*;
import net.minecraft.world.gen.chunk.AquiferSampler;
import org.spongepowered.asm.mixin.Mixin;

import java.util.function.Function;

@Mixin(CaveCarver.class)
public class CaveCarverMixin {
    @WrapMethod(method = "shouldCarve(Lnet/minecraft/world/gen/carver/CaveCarverConfig;Lnet/minecraft/util/math/random/Random;)Z")
    private boolean shouldCarve(CaveCarverConfig caveCarverConfig, Random random, Operation<Boolean> original)  {
        if (WorldGen.CAVES.getBooleanValue()) return original.call(caveCarverConfig, random);
        return false;
    }

    @WrapMethod(method = "carveCave")
    private void carveCave(CarverContext context, CaveCarverConfig config, Chunk chunk, Function<BlockPos, RegistryEntry<Biome>> posToBiome, AquiferSampler aquiferSampler, double d, double e, double f, float g, double h, CarvingMask mask, Carver.SkipPredicate skipPredicate, Operation<Void> original) {
        if (WorldGen.CARVE_CAVE.getBooleanValue()) original.call(context, config, chunk, posToBiome, aquiferSampler, d, e, f, g, h, mask, skipPredicate);
    }

    @WrapMethod(method = "carveTunnels")
    private void carveCave(CarverContext context, CaveCarverConfig config, Chunk chunk, Function<BlockPos, RegistryEntry<Biome>> posToBiome, long seed, AquiferSampler aquiferSampler, double x, double y, double z, double horizontalScale, double verticalScale, float width, float yaw, float pitch, int branchStartIndex, int branchCount, double yawPitchRatio, CarvingMask mask, Carver.SkipPredicate skipPredicate, Operation<Void> original) {
        if (WorldGen.CARVE_TUNNELS.getBooleanValue()) original.call(context, config, chunk, posToBiome, seed, aquiferSampler, x, y, z, horizontalScale, verticalScale, width, yaw, pitch, branchStartIndex, branchCount, yawPitchRatio, mask, skipPredicate);
    }
}
