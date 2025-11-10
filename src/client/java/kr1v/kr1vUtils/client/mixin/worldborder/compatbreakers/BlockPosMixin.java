package kr1v.kr1vUtils.client.mixin.worldborder.compatbreakers;

import kr1v.kr1vUtils.client.mixinclasses.compatbreakers.ChunkPosUtils;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@SuppressWarnings("OverwriteAuthorRequired")
@Mixin(BlockPos.class)
public class BlockPosMixin {
    @Overwrite
    public static int unpackLongX(long packedPos) {
        return ChunkPosUtils.getX(packedPos);
    }

    @Overwrite
    public static int unpackLongY(long packedPos) {
        return ChunkPosUtils.getY(packedPos);
    }

    @Overwrite
    public static int unpackLongZ(long packedPos) {
        return ChunkPosUtils.getZ(packedPos);
    }

    @Overwrite
    public static long asLong(int x, int y, int z) {
        return ChunkPosUtils.getKeyForXYZ(x, y, z);
    }
}
