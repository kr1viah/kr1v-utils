package kr1v.kr1vUtils.client.mixin.worldborder.compatbreakers;

import kr1v.kr1vUtils.client.mixinclasses.compatbreakers.ChunkPosUtils;
import net.minecraft.util.math.ChunkSectionPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@SuppressWarnings("OverwriteAuthorRequired")
@Mixin(ChunkSectionPos.class)
public class ChunkSectionPosMixin {
    @Overwrite
    public static long asLong(int x, int y, int z) {
        return ChunkPosUtils.getKeyForXYZ(x, y, z);
    }

    @Overwrite
    public static int unpackX(long packed) {
        return ChunkPosUtils.getX(packed);
    }

    @Overwrite
    public static int unpackY(long packed) {
        return ChunkPosUtils.getY(packed);
    }

    @Overwrite
    public static int unpackZ(long packed) {
        return ChunkPosUtils.getZ(packed);
    }

    @Overwrite
    public static long withZeroY(long pos) {
        return ChunkPosUtils.getKeyForXYZ(ChunkPosUtils.getX(pos), 0, ChunkPosUtils.getZ(pos));
    }
}
