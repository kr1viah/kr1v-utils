package kr1v.kr1vUtils.client.mixin.worldgen;


import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import kr1v.kr1vUtils.client.config.WorldGen;
import net.minecraft.structure.pool.StructurePoolBasedGenerator;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(StructurePoolBasedGenerator.StructurePoolGenerator.class)
public class StructurePoolGeneratorMixin  {
    @WrapOperation(method = "generatePiece", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/BlockPos;getY()I"))
    private int generatePiece(BlockPos instance, Operation<Integer> original) {
        if (WorldGen.OVERRIDE_STRUCTURE_HEIGHT.shouldHandle()) {
            return original.call(instance.withY(WorldGen.OVERRIDE_STRUCTURE_HEIGHT_VALUE.getIntegerValue()));
        }
        return original.call(instance);
    }
}
