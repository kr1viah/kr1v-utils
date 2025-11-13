package kr1v.kr1vUtils.client.mixin.worldgen;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import kr1v.kr1vUtils.client.config.WorldGen;
import net.minecraft.util.collection.BoundedRegionArray;
import net.minecraft.world.chunk.*;
import org.spongepowered.asm.mixin.Mixin;

import java.util.concurrent.CompletableFuture;

@Mixin(ChunkGenerating.class)
public class ChunkGeneratingMixin {
    @WrapMethod(method = "generateStructures")
    private static CompletableFuture<Chunk> generateStructures(ChunkGenerationContext context, ChunkGenerationStep step, BoundedRegionArray<AbstractChunkHolder> chunks, Chunk chunk, Operation<CompletableFuture<Chunk>> original) {
        if (WorldGen.GENERATE_STRUCTURES.shouldHandleNoThis() && !WorldGen.GENERATE_STRUCTURES.getBooleanValue()) return CompletableFuture.completedFuture(chunk);
        return original.call(context, step, chunks, chunk);
    }

    @WrapMethod(method = "loadStructures")
    private static CompletableFuture<Chunk> loadStructures(ChunkGenerationContext context, ChunkGenerationStep step, BoundedRegionArray<AbstractChunkHolder> chunks, Chunk chunk, Operation<CompletableFuture<Chunk>> original) {
        if (WorldGen.LOAD_STRUCTURES.shouldHandleNoThis() && !WorldGen.LOAD_STRUCTURES.getBooleanValue()) return CompletableFuture.completedFuture(chunk);
        return original.call(context, step, chunks, chunk);
    }

    @WrapMethod(method = "generateStructureReferences")
    private static CompletableFuture<Chunk> generateStructureReferences(ChunkGenerationContext context, ChunkGenerationStep step, BoundedRegionArray<AbstractChunkHolder> chunks, Chunk chunk, Operation<CompletableFuture<Chunk>> original) {
        if (WorldGen.GENERATE_STRUCTURE_REFERENCES.shouldHandleNoThis() && !WorldGen.GENERATE_STRUCTURE_REFERENCES.getBooleanValue()) return CompletableFuture.completedFuture(chunk);
        return original.call(context, step, chunks, chunk);
    }

    @WrapMethod(method = "populateBiomes")
    private static CompletableFuture<Chunk> populateBiomes(ChunkGenerationContext context, ChunkGenerationStep step, BoundedRegionArray<AbstractChunkHolder> chunks, Chunk chunk, Operation<CompletableFuture<Chunk>> original) {
        if (WorldGen.POPULATE_BIOMES.shouldHandleNoThis() && !WorldGen.POPULATE_BIOMES.getBooleanValue()) return CompletableFuture.completedFuture(chunk);
        return original.call(context, step, chunks, chunk);
    }

    @WrapMethod(method = "populateNoise")
    private static CompletableFuture<Chunk> populateNoise(ChunkGenerationContext context, ChunkGenerationStep step, BoundedRegionArray<AbstractChunkHolder> chunks, Chunk chunk, Operation<CompletableFuture<Chunk>> original) {
        if (WorldGen.POPULATE_NOISE.shouldHandleNoThis() && !WorldGen.POPULATE_NOISE.getBooleanValue()) return CompletableFuture.completedFuture(chunk);
        return original.call(context, step, chunks, chunk);
    }

    @WrapMethod(method = "buildSurface")
    private static CompletableFuture<Chunk> buildSurface(ChunkGenerationContext context, ChunkGenerationStep step, BoundedRegionArray<AbstractChunkHolder> chunks, Chunk chunk, Operation<CompletableFuture<Chunk>> original) {
        if (WorldGen.BUILD_SURFACE.shouldHandleNoThis() && !WorldGen.BUILD_SURFACE.getBooleanValue()) return CompletableFuture.completedFuture(chunk);
        return original.call(context, step, chunks, chunk);
    }

    @WrapMethod(method = "carve")
    private static CompletableFuture<Chunk> carve(ChunkGenerationContext context, ChunkGenerationStep step, BoundedRegionArray<AbstractChunkHolder> chunks, Chunk chunk, Operation<CompletableFuture<Chunk>> original) {
        if (WorldGen.CARVE.shouldHandleNoThis() && !WorldGen.CARVE.getBooleanValue()) return CompletableFuture.completedFuture(chunk);
        return original.call(context, step, chunks, chunk);
    }

    @WrapMethod(method = "generateFeatures")
    private static CompletableFuture<Chunk> generateFeatures(ChunkGenerationContext context, ChunkGenerationStep step, BoundedRegionArray<AbstractChunkHolder> chunks, Chunk chunk, Operation<CompletableFuture<Chunk>> original) {
        if (WorldGen.GENERATE_FEATURES.shouldHandleNoThis() && !WorldGen.GENERATE_FEATURES.getBooleanValue()) return CompletableFuture.completedFuture(chunk);
        return original.call(context, step, chunks, chunk);
    }

    @WrapMethod(method = "initializeLight")
    private static CompletableFuture<Chunk> initializeLight(ChunkGenerationContext context, ChunkGenerationStep step, BoundedRegionArray<AbstractChunkHolder> chunks, Chunk chunk, Operation<CompletableFuture<Chunk>> original) {
        if (WorldGen.INITIALIZE_LIGHT.shouldHandleNoThis() && !WorldGen.INITIALIZE_LIGHT.getBooleanValue()) return CompletableFuture.completedFuture(chunk);
        return original.call(context, step, chunks, chunk);
    }

    @WrapMethod(method = "light")
    private static CompletableFuture<Chunk> light(ChunkGenerationContext context, ChunkGenerationStep step, BoundedRegionArray<AbstractChunkHolder> chunks, Chunk chunk, Operation<CompletableFuture<Chunk>> original) {
        if (WorldGen.LIGHT.shouldHandleNoThis() && !WorldGen.LIGHT.getBooleanValue()) return CompletableFuture.completedFuture(chunk);
        return original.call(context, step, chunks, chunk);
    }

    @WrapMethod(method = "generateEntities")
    private static CompletableFuture<Chunk> generateEntities(ChunkGenerationContext context, ChunkGenerationStep step, BoundedRegionArray<AbstractChunkHolder> chunks, Chunk chunk, Operation<CompletableFuture<Chunk>> original) {
        if (WorldGen.GENERATE_ENTITIES.shouldHandleNoThis() && !WorldGen.GENERATE_ENTITIES.getBooleanValue()) return CompletableFuture.completedFuture(chunk);
        return original.call(context, step, chunks, chunk);
    }

    @WrapMethod(method = "convertToFullChunk")
    private static CompletableFuture<Chunk> convertToFullChunk(ChunkGenerationContext context, ChunkGenerationStep step, BoundedRegionArray<AbstractChunkHolder> chunks, Chunk chunk, Operation<CompletableFuture<Chunk>> original) {
        if (WorldGen.CONVERT_TO_FULL_CHUNK.shouldHandleNoThis() && !WorldGen.CONVERT_TO_FULL_CHUNK.getBooleanValue()) return CompletableFuture.completedFuture(chunk);
        return original.call(context, step, chunks, chunk);
    }

}
