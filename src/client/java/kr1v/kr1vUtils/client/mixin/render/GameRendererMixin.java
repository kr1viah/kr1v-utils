package kr1v.kr1vUtils.client.mixin.render;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import kr1v.kr1vUtils.client.config.Render;
import kr1v.kr1vUtils.client.mixinclasses.PositionOffsetter;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(GameRenderer.class)
public class GameRendererMixin {
	@Definition(id = "camera", local = @Local(type = Camera.class))
	@Definition(id = "getLastTickProgress", method = "Lnet/minecraft/client/render/Camera;getLastTickProgress()F")
	@Expression("camera.getLastTickProgress()")
	@ModifyExpressionValue(method = "renderWorld", at = @At("MIXINEXTRAS:EXPRESSION"))
	private float injected(float original, @Local Camera camera) {
//		ClientPlayerEntity player = MinecraftClient.getInstance().player;
//		if (player != null && Render.RELATIVE_TO_PLAYER_ANGLE.getBooleanValue()) {
//			double[] d = PositionOffsetter.worldOffsetFromPlayerAngles(player.getYaw(), player.getPitch());
//			camera.pos = camera.pos.add(d[0], d[1], d[2]);
//		} else {
//			camera.pos = camera.pos.add(Render.OFFSET_X.getDoubleValue(), Render.OFFSET_Y.getDoubleValue(), Render.OFFSET_Z.getDoubleValue());
//		}
		return original;
	}
}
