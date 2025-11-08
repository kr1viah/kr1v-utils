package kr1v.kr1vUtils.client.mixin.worldborder;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockRenderView;
import net.minecraft.world.WorldView;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(WorldView.class)
public interface WorldViewMixin extends BlockRenderView {
    @WrapMethod(method = "getLightLevel(Lnet/minecraft/util/math/BlockPos;I)I")
    private int wrap(BlockPos pos, int ambientDarkness, Operation<Integer> original) {
        return this.getBaseLightLevel(pos, ambientDarkness);
    }
}
