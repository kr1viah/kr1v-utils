package kr1v.kr1vUtils.client.mixin.worldborder.compatbreakers;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkSectionPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("OverwriteAuthorRequired")
@Mixin(PacketByteBuf.class)
public abstract class PacketByteBufMixin {
    @Shadow
    public abstract PacketByteBuf writeInt(int i);

    @Shadow
    public abstract int readInt();

    @Overwrite
    public ChunkSectionPos readChunkSectionPos() {
        return ChunkSectionPos.from(readInt(), readInt(), readInt());
    }
    @Overwrite
    public PacketByteBuf writeChunkSectionPos(ChunkSectionPos pos) {
        this.writeInt(pos.getX());
        this.writeInt(pos.getY());
        this.writeInt(pos.getZ());
        return ((PacketByteBuf)(Object)this);
    }

    @Overwrite
    public static BlockPos readBlockPos(ByteBuf buf) {
        return new BlockPos(buf.readInt(), buf.readInt(), buf.readInt());
    }

    @Overwrite
    public static void writeBlockPos(ByteBuf buf, BlockPos pos) {
        buf.writeInt(pos.getX());
        buf.writeInt(pos.getY());
        buf.writeInt(pos.getZ());
    }
}
