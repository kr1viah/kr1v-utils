package kr1v.kr1vUtils.client.mixin.worldborder.fine;


import it.unimi.dsi.fastutil.longs.*;
import kr1v.kr1vUtils.client.mixinclasses.compatbreakers.ChunkPosUtils;
import net.minecraft.util.TypeFilter;
import net.minecraft.util.function.LazyIterationConsumer;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.ChunkSectionPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.entity.EntityLike;
import net.minecraft.world.entity.EntityTrackingSection;
import net.minecraft.world.entity.EntityTrackingStatus;
import net.minecraft.world.entity.SectionedEntityCache;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.*;

import java.util.*;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@SuppressWarnings("OverwriteAuthorRequired")
@Mixin(SectionedEntityCache.class)
public class SectionedEntityCacheMixin<T extends EntityLike> {
    @Unique
    private final Map<Vec3i, EntityTrackingSection<T>> trackingSections3d = new HashMap<>();

    @Unique
    private final SortedSet<Vec3i> trackedPositions3d = new TreeSet<>();

    @Shadow
    @Final
    private Long2ObjectFunction<EntityTrackingStatus> posToStatus;

    @Shadow
    @Final
    private Class<T> entityClass;

    @Overwrite
    public void forEachInBox(Box box, LazyIterationConsumer<EntityTrackingSection<T>> consumer) {
        int i = ChunkSectionPos.getSectionCoord(box.minX - 2.0);
        int j = ChunkSectionPos.getSectionCoord(box.minY - 4.0);
        int k = ChunkSectionPos.getSectionCoord(box.minZ - 2.0);
        int l = ChunkSectionPos.getSectionCoord(box.maxX + 2.0);
        int m = ChunkSectionPos.getSectionCoord(box.maxY + 0.0);
        int n = ChunkSectionPos.getSectionCoord(box.maxZ + 2.0);

        for (Map.Entry<Vec3i, EntityTrackingSection<T>> entry : this.trackingSections3d.entrySet()) {
            Vec3i pos = entry.getKey();
            int x = pos.getX();
            int y = pos.getY();
            int z = pos.getZ();

            if (x < i || x > l || y < j || y > m || z < k || z > n) continue;

            EntityTrackingSection<T> entityTrackingSection = entry.getValue();
            if (entityTrackingSection != null
                    && !entityTrackingSection.isEmpty()
                    && entityTrackingSection.getStatus().shouldTrack()) {
                if (consumer.accept(entityTrackingSection).shouldAbort()) {
                    return;
                }
            }
        }
    }

    @Unique
    public Stream<Vec3i> getSections(Vec3i chunkPos) {
        SortedSet<Vec3i> sortedSet = this.getSections(chunkPos.getX(), chunkPos.getZ());
        if (sortedSet.isEmpty()) {
            return Stream.empty();
        } else {
            Iterator<Vec3i> ofLong = sortedSet.iterator();
            return StreamSupport.stream(Spliterators.spliteratorUnknownSize(ofLong, Spliterator.ORDERED | Spliterator.DISTINCT | Spliterator.SORTED | Spliterator.NONNULL | Spliterator.IMMUTABLE), false);
        }
    }

    @Unique
    private SortedSet<Vec3i> getSections(int chunkX, int chunkZ) {
        return this.trackedPositions3d.subSet(new Vec3i(chunkX, 0, chunkZ), new Vec3i(chunkX, 0, chunkZ));
    }

    @Overwrite
    public Stream<EntityTrackingSection<T>> getTrackingSections(long chunkPos) {
        Vec3i vec3i = ChunkPosUtils.vec3i(chunkPos);
        return this.getSections(vec3i).map(this.trackingSections3d::get).filter(Objects::nonNull);
    }

    @Overwrite
    private static long chunkPosFromSectionPos(long sectionPos) {
        return ChunkPos.toLong(ChunkSectionPos.unpackX(sectionPos), ChunkSectionPos.unpackZ(sectionPos));
    }

    @Overwrite
    public EntityTrackingSection<T> getTrackingSection(long key) {
        Vec3i vec3i = ChunkPosUtils.vec3i(key);
        return this.trackingSections3d.computeIfAbsent(vec3i, this::addSection);
    }

    @Nullable
    @Overwrite
    public EntityTrackingSection<T> findTrackingSection(long key) {
        Vec3i vec3i = ChunkPosUtils.vec3i(key);
        return this.trackingSections3d.get(vec3i);
    }

    @Unique
    private EntityTrackingSection<T> addSection(Vec3i sectionPos) {
        long l = chunkPosFromSectionPos(ChunkPosUtils.getKeyForXYZ(sectionPos.getX(), sectionPos.getY(), sectionPos.getZ()));
        EntityTrackingStatus entityTrackingStatus = this.posToStatus.get(l);
        this.trackedPositions3d.add(sectionPos);
        return new EntityTrackingSection<>(this.entityClass, entityTrackingStatus);
    }

    @Overwrite
    public LongSet getChunkPositions() {
        LongSet longSet = new LongOpenHashSet();
        this.trackingSections3d.keySet().forEach(pos -> longSet.add(chunkPosFromSectionPos(ChunkPosUtils.getKeyForXYZ(pos.getX(), pos.getY(), pos.getZ()))));
        return longSet;
    }

    @Overwrite
    public void forEachIntersects(Box box, LazyIterationConsumer<T> consumer) {
        this.forEachInBox(box, section -> section.forEach(box, consumer));
    }

    @Overwrite
    public <U extends T> void forEachIntersects(TypeFilter<T, U> filter, Box box, LazyIterationConsumer<U> consumer) {
        this.forEachInBox(box, section -> section.forEach(filter, box, consumer));
    }

    @Overwrite
    public void removeSection(long key) {
        Vec3i vec3i = ChunkPosUtils.vec3i(key);
        this.trackingSections3d.remove(vec3i);
        this.trackedPositions3d.remove(vec3i);
    }
}