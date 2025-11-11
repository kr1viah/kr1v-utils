package kr1v.kr1vUtils.client.mixin.worldgen;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import kr1v.kr1vUtils.client.config.WorldGen;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.HeightLimitView;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.chunk.BlockColumn;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(SurfaceBuilder.class)
public class SurfaceBuilderMixin {
    @WrapMethod(method = "placeIceberg")
    private void placeIceberg(int minY, Biome biome, BlockColumn column, BlockPos.Mutable mutablePos, int x, int z, int surfaceY, Operation<Void> original) {
        if (WorldGen.ICE_BERGS.getBooleanValue()) original.call(minY, biome, column, mutablePos, x, z, surfaceY);
    }

    @WrapMethod(method = "placeBadlandsPillar")
    private void placeBadlandsPillar(BlockColumn column, int x, int z, int surfaceY, HeightLimitView chunk, Operation<Void> original) {
        if (WorldGen.BAD_LANDS_PILLARS.getBooleanValue()) original.call(column, x, z, surfaceY, chunk);
    }
}
