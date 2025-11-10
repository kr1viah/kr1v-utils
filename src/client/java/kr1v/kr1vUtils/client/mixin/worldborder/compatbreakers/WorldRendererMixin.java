package kr1v.kr1vUtils.client.mixin.worldborder.compatbreakers;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.world.chunk.light.LightingProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(WorldRenderer.class)
public class WorldRendererMixin {
    @WrapOperation(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/chunk/light/LightingProvider;doLightUpdates()I"))
    private int preventLightingUpdates(LightingProvider instance, Operation<Integer> original) {
        return 0;
    }
}
