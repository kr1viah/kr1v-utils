package kr1v.kr1vUtils.client.mixin.render;

import kr1v.kr1vUtils.client.config.Render;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.GameRenderer;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public class GameRendererMixin {
    @Inject(method = "renderHand", at = @At("HEAD"), cancellable = true)
    private void noRenderHand(Camera camera, float tickProgress, Matrix4f positionMatrix, CallbackInfo ci) {
//        if (!Render.DONT_RENDER_HAND.getBooleanValue()) {
//            ci.cancel();
//        }
    }
}
