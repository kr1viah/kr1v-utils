package kr1v.kr1vUtils.client.mixin.worldborder.compatbreakers;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.world.chunk.light.LightStorage;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(LightStorage.class)
public class LightStorageMixin {
    @WrapMethod(method = "set")
    private void preventSet(long blockPos, int value, Operation<Void> original) {
        try {
            original.call(blockPos, value);
        } catch (NullPointerException ignored) {}
    }

    @WrapMethod(method = "get")
    private int preventGet(long blockPos, Operation<Integer> original) {
        try {
            return original.call(blockPos);
        } catch (NullPointerException ignored){
            return 15;
        }
    }
}
