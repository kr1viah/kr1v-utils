package kr1v.kr1vUtils.client.mixin.accessor;

import org.joml.Matrix4fStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Matrix4fStack.class)
public interface Matrix4fStackAccessor {
	@Accessor int getCurr();
}
