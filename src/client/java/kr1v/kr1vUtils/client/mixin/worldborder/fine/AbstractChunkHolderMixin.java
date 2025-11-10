package kr1v.kr1vUtils.client.mixin.worldborder.fine;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.chunk.AbstractChunkHolder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(AbstractChunkHolder.class)
public class AbstractChunkHolderMixin {
    @Definition(id = "pos", local = @Local(type = ChunkPos.class, argsOnly = true))
    @Definition(id = "getChebyshevDistance", method = "Lnet/minecraft/util/math/ChunkPos;getChebyshevDistance(Lnet/minecraft/util/math/ChunkPos;)I")
    @Definition(id = "ORIGIN", field = "Lnet/minecraft/util/math/ChunkPos;ORIGIN:Lnet/minecraft/util/math/ChunkPos;")
    @Expression("pos.getChebyshevDistance(ORIGIN) > ?")
    @ModifyExpressionValue(method = "<init>", at = @At("MIXINEXTRAS:EXPRESSION"))
    private boolean injected(boolean original) {
        return false;
    }
}
