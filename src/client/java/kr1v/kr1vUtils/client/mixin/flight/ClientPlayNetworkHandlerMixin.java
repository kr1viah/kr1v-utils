package kr1v.kr1vUtils.client.mixin.flight;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import kr1v.kr1vUtils.client.config.Misc;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ClientPlayNetworkHandler.class)
public class ClientPlayNetworkHandlerMixin {
    @ModifyExpressionValue(method = "onPlayerAbilities", at = @At(value = "INVOKE", target = "Lnet/minecraft/network/packet/s2c/play/PlayerAbilitiesS2CPacket;isFlying()Z"))
    private boolean injected(boolean original, @Local PlayerEntity player) {
        if (Misc.PREVENT_FLIGHT_STATE_CHANGE.getBooleanValue()) {
            return player.getAbilities().flying;
        } else {
            return original;
        }
    }
}
