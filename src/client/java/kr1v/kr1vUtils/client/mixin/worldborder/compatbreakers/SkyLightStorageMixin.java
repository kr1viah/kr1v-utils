package kr1v.kr1vUtils.client.mixin.worldborder.compatbreakers;

import kr1v.kr1vUtils.client.mixinclasses.compatbreakers.ChunkPosUtils;
import net.minecraft.util.math.ChunkSectionPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.LightType;
import net.minecraft.world.chunk.ChunkNibbleArray;
import net.minecraft.world.chunk.ChunkProvider;
import net.minecraft.world.chunk.light.LightStorage;
import net.minecraft.world.chunk.light.SkyLightStorage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("OverwriteAuthorRequired")
@Mixin(SkyLightStorage.class)
public abstract class SkyLightStorageMixin extends LightStorage<SkyLightStorage.Data> {

    @Shadow
    private static ChunkNibbleArray copy(ChunkNibbleArray source) {
        return null;
    }

    protected SkyLightStorageMixin(LightType lightType, ChunkProvider chunkProvider, SkyLightStorage.Data lightData) {
        super(lightType, chunkProvider, lightData);
    }

    @Overwrite
    public ChunkNibbleArray createSection(long sectionPos) {
        ChunkNibbleArray chunkNibbleArray = this.queuedSections.get(sectionPos);
        if (chunkNibbleArray != null) {
            return chunkNibbleArray;
        } else {
            int i = this.storage.columnToTopSection.get(ChunkSectionPos.withZeroY(sectionPos));
            if (i != this.storage.minSectionY && ChunkSectionPos.unpackY(sectionPos) < i) {
                long l = ChunkSectionPos.offset(sectionPos, Direction.UP);

                int y = ChunkPosUtils.getY(sectionPos);

                ChunkNibbleArray chunkNibbleArray2;
                while ((chunkNibbleArray2 = this.getLightSection(l, true)) == null) {
                    l = ChunkSectionPos.offset(l, Direction.UP);
                    if (y++ > 1000) {
                        return new ChunkNibbleArray();
                    }
                }

                return copy(chunkNibbleArray2);
            } else {
                return this.isSectionInEnabledColumn(sectionPos) ? new ChunkNibbleArray(15) : new ChunkNibbleArray();
            }
        }
    }
}
