package kr1v.kr1vUtils.client.mixin.worldborder;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkSectionPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(World.class)
public abstract class WorldMixin implements WorldAccess {
    @WrapMethod(method = "getTopY")
    private int getTopY(Heightmap.Type heightmap, int x, int z, Operation<Integer> original) {
        if (this.isChunkLoaded(ChunkSectionPos.getSectionCoord(x), ChunkSectionPos.getSectionCoord(z))) {
            return this.getChunk(ChunkSectionPos.getSectionCoord(x), ChunkSectionPos.getSectionCoord(z)).sampleHeightmap(heightmap, x & 15, z & 15) + 1;
        } else {
            return this.getBottomY();
        }
    }

    @WrapMethod(method = "isValidHorizontally")
    private static boolean injected(BlockPos pos, Operation<Boolean> original) {
        return true;
    }
}
