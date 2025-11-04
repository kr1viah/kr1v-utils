package kr1v.kr1vUtils.client.mixin.render;

import kr1v.kr1vUtils.client.config.Render;
import net.minecraft.client.render.WorldRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldRenderer.class)
public class WorldRendererMixin {
	@Inject(method = "renderMain", at = @At("HEAD"), cancellable = true)
	private void preventMain(CallbackInfo ci) {
		if (!Render.MAIN.getBooleanValue()) ci.cancel();
	}

	@Inject(method = "renderParticles", at = @At("HEAD"), cancellable = true)
	private void preventParticles(CallbackInfo ci) {
		if (!Render.PARTICLES.getBooleanValue()) ci.cancel();
	}

	@Inject(method = "renderClouds", at = @At("HEAD"), cancellable = true)
	private void preventClouds(CallbackInfo ci) {
		if (!Render.CLOUDS.getBooleanValue()) ci.cancel();
	}

	@Inject(method = "renderWeather", at = @At("HEAD"), cancellable = true)
	private void preventWeather(CallbackInfo ci) {
		if (!Render.WEATHER.getBooleanValue()) ci.cancel();
	}

	@Inject(method = "renderLateDebug", at = @At("HEAD"), cancellable = true)
	private void preventLateDebug(CallbackInfo ci) {
		if (!Render.LATE_DEBUG.getBooleanValue()) ci.cancel();
	}

	@Inject(method = "renderEntities", at = @At("HEAD"), cancellable = true)
	private void preventEntities(CallbackInfo ci) {
		if (!Render.ENTITIES.getBooleanValue()) ci.cancel();
	}

	@Inject(method = "renderBlockEntities", at = @At("HEAD"), cancellable = true)
	private void preventBlockEntities(CallbackInfo ci) {
		if (!Render.BLOCK_ENTITIES.getBooleanValue()) ci.cancel();
	}

	@Inject(method = "renderBlockDamage", at = @At("HEAD"), cancellable = true)
	private void preventBlockDamage(CallbackInfo ci) {
		if (!Render.BLOCK_DAMAGE.getBooleanValue()) ci.cancel();
	}

	@Inject(method = "renderTargetBlockOutline", at = @At("HEAD"), cancellable = true)
	private void preventTargetBlockOutline(CallbackInfo ci) {
		if (!Render.TARGET_BLOCK_OUTLINE.getBooleanValue()) ci.cancel();
	}

	@Inject(method = "renderSky", at = @At("HEAD"), cancellable = true)
	private void preventSky(CallbackInfo ci) {
		if (!Render.SKY.getBooleanValue()) ci.cancel();
	}

}
