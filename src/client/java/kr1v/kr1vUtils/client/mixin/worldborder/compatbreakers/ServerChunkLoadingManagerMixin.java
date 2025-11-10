package kr1v.kr1vUtils.client.mixin.worldborder.compatbreakers;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.server.world.ServerChunkLoadingManager;
import net.minecraft.util.math.ChunkSectionPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ServerChunkLoadingManager.class)
public class ServerChunkLoadingManagerMixin {
    @Definition(id = "chunkSectionPos", local = @Local(type = ChunkSectionPos.class, ordinal = 0))
    @Definition(id = "asLong", method = "Lnet/minecraft/util/math/ChunkSectionPos;asLong()J")
    @Definition(id = "chunkSectionPos2", local = @Local(type = ChunkSectionPos.class, ordinal = 1))
    @Expression("chunkSectionPos.asLong() != chunkSectionPos2.asLong()")
    @ModifyExpressionValue(method = "updatePosition", at = @At("MIXINEXTRAS:EXPRESSION"))
    private boolean useRawPos(boolean original, @Local(ordinal = 0) ChunkSectionPos chunkSectionPos, @Local(ordinal = 1) ChunkSectionPos chunkSectionPos2) {
        return !(chunkSectionPos.getX() == chunkSectionPos2.getX() ||
                chunkSectionPos.getY() == chunkSectionPos2.getY() ||
                chunkSectionPos.getZ() == chunkSectionPos2.getZ());
    }
}
