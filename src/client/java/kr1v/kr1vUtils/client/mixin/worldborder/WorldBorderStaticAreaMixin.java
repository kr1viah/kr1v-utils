package kr1v.kr1vUtils.client.mixin.worldborder;

import net.minecraft.world.border.WorldBorder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(WorldBorder.StaticArea.class)
public class WorldBorderStaticAreaMixin {
    @ModifyVariable(method = "<init>", at = @At("HEAD"), ordinal = 0, argsOnly = true)
    private static double modifyCtorSize(double value) {
        return Double.MAX_VALUE;
    }
}
