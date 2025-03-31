package com.calemi.nexus.world.structure;

import com.calemi.nexus.main.NexusRef;
import com.calemi.nexus.tag.NexusTags;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.heightproviders.ConstantHeight;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.TerrainAdjustment;
import net.minecraft.world.level.levelgen.structure.pools.DimensionPadding;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.structures.JigsawStructure;
import net.minecraft.world.level.levelgen.structure.templatesystem.LiquidSettings;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class NexusStructures {

    public static final ResourceKey<Structure> RUINED_NEXUS_PORTAL = NexusRef.createKey("ruined_nexus_portal", Registries.STRUCTURE);

    public static void bootstrap(BootstrapContext<Structure> context) {

        HolderGetter<Biome> biomeHolder = context.lookup(Registries.BIOME);
        HolderGetter<StructureTemplatePool> templatePoolHolder = context.lookup(Registries.TEMPLATE_POOL);

        context.register(RUINED_NEXUS_PORTAL, new JigsawStructure(
                new Structure.StructureSettings.Builder(biomeHolder.getOrThrow(NexusTags.Biomes.VALID_RUINED_NEXUS_PORTAL_BIOMES))
                        .generationStep(GenerationStep.Decoration.SURFACE_STRUCTURES)
                        .terrainAdapation(TerrainAdjustment.BEARD_THIN)
                        .spawnOverrides(Collections.emptyMap())
                        .build(),
                templatePoolHolder.getOrThrow(NexusTemplatePools.RUINED_NEXUS_PORTAL),
                Optional.empty(),
                1,
                ConstantHeight.of(VerticalAnchor.absolute(0)),
                false,
                Optional.of(Heightmap.Types.WORLD_SURFACE_WG),
                80,
                List.of(),
                new DimensionPadding(10),
                LiquidSettings.APPLY_WATERLOGGING
        ));
    }
}
