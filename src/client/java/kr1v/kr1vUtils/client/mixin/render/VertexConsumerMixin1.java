package kr1v.kr1vUtils.client.mixin.render;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import kr1v.kr1vUtils.client.config.Render;
import kr1v.kr1vUtils.client.mixinclasses.PositionOffsetter;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.*;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({
	BufferBuilder.class,
	VertexConsumers.Dual.class,
	OutlineVertexConsumerProvider.OutlineVertexConsumer.class,
	OverlayVertexConsumer.class,
	SpriteTexturedVertexConsumer.class,
	VertexConsumers.Union.class
})
public class VertexConsumerMixin1 {
	@WrapMethod(method = "vertex(FFF)Lnet/minecraft/client/render/VertexConsumer;")
	private VertexConsumer wrap(float x, float y, float z, Operation<VertexConsumer> original) {
        if (!Render.AFFECT_OFFSETTING.shouldHandle()) return original.call(x, y, z);
		ClientPlayerEntity player = MinecraftClient.getInstance().player;
		if (player != null && Render.RELATIVE_TO_PLAYER_ANGLE.getBooleanValue()) {
			double[] d = PositionOffsetter.worldOffsetFromPlayerAngles(player.getYaw(), player.getPitch());
			x += (float) d[0]; y += (float) d[1]; z += (float) d[2];
		} else {
			x += (float) Render.OFFSET_X.getDoubleValue();
			y += (float) Render.OFFSET_Y.getDoubleValue();
			z += (float) Render.OFFSET_Z.getDoubleValue();
		}
		return original.call(x, y, z);
	}
}