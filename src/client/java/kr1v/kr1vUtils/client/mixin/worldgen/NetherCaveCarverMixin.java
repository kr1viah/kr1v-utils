package kr1v.kr1vUtils.client.mixin.worldgen;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import kr1v.kr1vUtils.client.config.WorldGen;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.carver.CarverContext;
import net.minecraft.world.gen.carver.CarvingMask;
import net.minecraft.world.gen.carver.CaveCarverConfig;
import net.minecraft.world.gen.carver.NetherCaveCarver;
import net.minecraft.world.gen.chunk.AquiferSampler;
import org.apache.commons.lang3.mutable.MutableBoolean;
import org.spongepowered.asm.mixin.Mixin;

import java.util.function.Function;

@Mixin(NetherCaveCarver.class)
public class NetherCaveCarverMixin {
    @WrapMethod(method = "carveAtPoint(Lnet/minecraft/world/gen/carver/CarverContext;Lnet/minecraft/world/gen/carver/CaveCarverConfig;Lnet/minecraft/world/chunk/Chunk;Ljava/util/function/Function;Lnet/minecraft/world/gen/carver/CarvingMask;Lnet/minecraft/util/math/BlockPos$Mutable;Lnet/minecraft/util/math/BlockPos$Mutable;Lnet/minecraft/world/gen/chunk/AquiferSampler;Lorg/apache/commons/lang3/mutable/MutableBoolean;)Z")
    private boolean carveAtPoint(CarverContext carverContext, CaveCarverConfig caveCarverConfig, Chunk chunk, Function<BlockPos, RegistryEntry<Biome>> function, CarvingMask carvingMask, BlockPos.Mutable mutable, BlockPos.Mutable mutable2, AquiferSampler aquiferSampler, MutableBoolean mutableBoolean, Operation<Boolean> original) {
        if (WorldGen.NETHER_CAVES.getBooleanValue()) return original.call(carverContext, caveCarverConfig, chunk, function, carvingMask, mutable, mutable2, aquiferSampler, mutableBoolean);
        return false;
    }
}
