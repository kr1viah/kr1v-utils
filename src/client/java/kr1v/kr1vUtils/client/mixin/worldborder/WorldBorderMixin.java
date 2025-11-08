package kr1v.kr1vUtils.client.mixin.worldborder;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.world.border.WorldBorder;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.lang.reflect.Constructor;

@Mixin(WorldBorder.class)
public class WorldBorderMixin {
    @Shadow
    @Mutable
    @Final
    public static WorldBorder.Properties DEFAULT_BORDER;

    @Shadow
    int maxRadius;

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void onClinit(CallbackInfo ci) {
        try {
            Constructor<?> ctor =
                    WorldBorder.Properties.class.getDeclaredConstructor(
                            double.class,
                            double.class,
                            double.class,
                            double.class,
                            int.class,
                            int.class,
                            double.class,
                            long.class,
                            double.class
                    );
            ctor.setAccessible(true);
            DEFAULT_BORDER = (WorldBorder.Properties) ctor.newInstance(
                    0.0, 0.0, 0.2, 5.0, 5, 15, Double.MAX_VALUE, 0L, 0.0
            );
        } catch (Exception ignored) {
//            throw new RuntimeException(e);
        }
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    private void setMaxRadiusInit(CallbackInfo ci) {
        this.maxRadius = Integer.MAX_VALUE;
    }

    @WrapMethod(method = "setMaxRadius")
    private void setMaxRadius(int maxRadius, Operation<Void> original) {
        original.call(Integer.MAX_VALUE);
    }
}
