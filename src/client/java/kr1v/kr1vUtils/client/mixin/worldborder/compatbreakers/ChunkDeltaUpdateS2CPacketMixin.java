package kr1v.kr1vUtils.client.mixin.worldborder.compatbreakers;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.s2c.play.ChunkDeltaUpdateS2CPacket;
import net.minecraft.util.math.ChunkSectionPos;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@SuppressWarnings("OverwriteAuthorRequired")
@Mixin(ChunkDeltaUpdateS2CPacket.class)
public class ChunkDeltaUpdateS2CPacketMixin {
    @Shadow
    @Final
    private ChunkSectionPos sectionPos;

    @Shadow
    @Final
    private short[] positions;

    @Shadow
    @Final
    private BlockState[] blockStates;

    @Overwrite
    private void write(PacketByteBuf buf) {
        buf.writeInt(this.sectionPos.getX());
        buf.writeInt(this.sectionPos.getY());
        buf.writeInt(this.sectionPos.getZ());
        buf.writeVarInt(this.positions.length);

        for (int i = 0; i < this.positions.length; i++) {
            buf.writeVarLong((long) Block.getRawIdFromState(this.blockStates[i]) << 12 | this.positions[i]);
        }
    }

    @WrapOperation(method = "<init>(Lnet/minecraft/network/PacketByteBuf;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/network/PacketByteBuf;readLong()J"))
    private long preventLongRead(PacketByteBuf instance, Operation<Long> original) {
        return 0;
    }

    @WrapOperation(method = "<init>(Lnet/minecraft/network/PacketByteBuf;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/ChunkSectionPos;from(J)Lnet/minecraft/util/math/ChunkSectionPos;"))
    private ChunkSectionPos read3Ints(long packed, Operation<ChunkSectionPos> original, @Local PacketByteBuf buf) {
        int x = buf.readInt();
        int y = buf.readInt();
        int z = buf.readInt();
        return ChunkSectionPos.from(x, y, z);
    }
}
