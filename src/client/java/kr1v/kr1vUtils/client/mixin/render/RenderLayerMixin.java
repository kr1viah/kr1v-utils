package kr1v.kr1vUtils.client.mixin.render;

import kr1v.kr1vUtils.client.config.Render;
import kr1v.malilibApi.config.plus.ConfigBooleanPlus;
import net.minecraft.client.render.BuiltBuffer;
import net.minecraft.client.render.RenderLayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RenderLayer.MultiPhase.class)
public abstract class RenderLayerMixin extends RenderLayer {
	public RenderLayerMixin(String name, int size, boolean hasCrumbling, boolean translucent, Runnable begin, Runnable end) {
		super(name, size, hasCrumbling, translucent, begin, end);
	}

	@Inject(method = "draw", at = @At("HEAD"), cancellable = true)
	private void injected(BuiltBuffer buffer, CallbackInfo ci) {
		ConfigBooleanPlus correspondingHotkey = Render.RENDER_HOTKEYS.get(getName());
		if (correspondingHotkey == null) {
            return;
//            throw new IllegalStateException("Render layer " + getName() + " did not have a corresponding hotkey.");
		}
        if (!correspondingHotkey.getBooleanValue()) {
			ci.cancel();
			if (buffer != null) {
				buffer.close();
			}
		}
	}
}