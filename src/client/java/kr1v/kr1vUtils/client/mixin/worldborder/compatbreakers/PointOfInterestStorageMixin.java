package kr1v.kr1vUtils.client.mixin.worldborder.compatbreakers;

import com.mojang.serialization.Codec;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ChunkErrorHandler;
import net.minecraft.util.annotation.Debug;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.ChunkSectionPos;
import net.minecraft.world.HeightLimitView;
import net.minecraft.world.poi.PointOfInterest;
import net.minecraft.world.poi.PointOfInterestSet;
import net.minecraft.world.poi.PointOfInterestStorage;
import net.minecraft.world.poi.PointOfInterestType;
import net.minecraft.world.storage.ChunkPosKeyedStorage;
import net.minecraft.world.storage.SerializingRegionBasedStorage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@SuppressWarnings("OverwriteAuthorRequired")
@Mixin(PointOfInterestStorage.class)
public abstract class PointOfInterestStorageMixin extends SerializingRegionBasedStorage<PointOfInterestSet, PointOfInterestSet.Serialized> {
    public PointOfInterestStorageMixin(ChunkPosKeyedStorage storageAccess, Codec<PointOfInterestSet.Serialized> codec, Function<PointOfInterestSet, PointOfInterestSet.Serialized> serializer, BiFunction<PointOfInterestSet.Serialized, Runnable, PointOfInterestSet> deserializer, Function<Runnable, PointOfInterestSet> factory, DynamicRegistryManager registryManager, ChunkErrorHandler errorHandler, HeightLimitView world) {
        super(storageAccess, codec, serializer, deserializer, factory, registryManager, errorHandler, world);
    }

    @Debug
    @Overwrite
    public Stream<PointOfInterest> getInChunk(
            Predicate<RegistryEntry<PointOfInterestType>> typePredicate, ChunkPos chunkPos, PointOfInterestStorage.OccupationStatus occupationStatus
    ) {
        return IntStream.rangeClosed(this.world.getBottomSectionCoord(), this.world.getTopSectionCoord())
                .boxed()
                .map(coord -> this.get(ChunkSectionPos.from(chunkPos, coord).asLong()))
                .filter(Optional::isPresent)
                .flatMap(poiSet -> poiSet.get().get(typePredicate, occupationStatus));
    }
}
