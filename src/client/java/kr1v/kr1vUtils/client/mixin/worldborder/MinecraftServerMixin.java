package kr1v.kr1vUtils.client.mixin.worldborder;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(MinecraftServer.class)
public class MinecraftServerMixin {
    @WrapMethod(method = "getMaxWorldBorderRadius")
    private int injected(Operation<Integer> original) {
        return Integer.MAX_VALUE;
    }
}
