package kr1v.kr1vUtils.client.mixin.worldgen;


import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import kr1v.kr1vUtils.client.config.WorldGen;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.structure.StructureLiquidSettings;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePoolBasedGenerator;
import net.minecraft.structure.pool.alias.StructurePoolAliasLookup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.structure.DimensionPadding;
import net.minecraft.world.gen.structure.Structure;
import org.spongepowered.asm.mixin.Mixin;

import java.util.Optional;

@Mixin(StructurePoolBasedGenerator.class)
public class StructurePoolBasedGeneratorMixin {
    @WrapMethod(method = "generate(Lnet/minecraft/world/gen/structure/Structure$Context;Lnet/minecraft/registry/entry/RegistryEntry;Ljava/util/Optional;ILnet/minecraft/util/math/BlockPos;ZLjava/util/Optional;ILnet/minecraft/structure/pool/alias/StructurePoolAliasLookup;Lnet/minecraft/world/gen/structure/DimensionPadding;Lnet/minecraft/structure/StructureLiquidSettings;)Ljava/util/Optional;")
    private static Optional<Structure.StructurePosition> generate(Structure.Context context, RegistryEntry<StructurePool> structurePool, Optional<Identifier> id, int size, BlockPos pos, boolean useExpansionHack, Optional<Heightmap.Type> projectStartToHeightmap, int maxDistanceFromCenter, StructurePoolAliasLookup aliasLookup, DimensionPadding dimensionPadding, StructureLiquidSettings liquidSettings, Operation<Optional<Structure.StructurePosition>> original) {
        if (WorldGen.OVERRIDE_STRUCTURE_HEIGHT.getBooleanValue()) {
            pos = pos.withY(WorldGen.OVERRIDE_STRUCTURE_HEIGHT_VALUE.getIntegerValue());
        }
        return original.call(context, structurePool, id, size, pos, useExpansionHack, projectStartToHeightmap, maxDistanceFromCenter, aliasLookup, dimensionPadding, liquidSettings);
    }
}
