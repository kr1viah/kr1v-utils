package kr1v.kr1vUtils.client.mixin.speed;

import com.mojang.datafixers.DSL;
import com.mojang.datafixers.DataFixerBuilder;
import kr1v.kr1vUtils.client.config.Misc;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@Mixin(DataFixerBuilder.Result.class)
public class DataFixerBuilderMixin {
	@Inject(method = "optimize(Ljava/util/Set;Ljava/util/concurrent/Executor;)Ljava/util/concurrent/CompletableFuture;", at = @At("HEAD"), cancellable = true)
	private void injected(Set<DSL.TypeReference> requiredTypes, Executor executor, CallbackInfoReturnable<CompletableFuture<?>> cir) {
		if (Misc.FAST_MAIN_MENU.getBooleanValue())
			cir.setReturnValue(CompletableFuture.runAsync(() -> {}));
	}
}
