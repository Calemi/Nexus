package com.calemi.nexus.world.structure;

import com.calemi.nexus.main.NexusRef;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadStructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadType;

public class NexusStructureSets {

    public static final ResourceKey<StructureSet> RUINED_NEXUS_PORTAL = NexusRef.createKey("ruined_nexus_portal", Registries.STRUCTURE_SET);

    public static void bootstrap(BootstrapContext<StructureSet> context) {

        HolderGetter<Structure> structures = context.lookup(Registries.STRUCTURE);

        context.register(RUINED_NEXUS_PORTAL, new StructureSet(structures.getOrThrow(NexusStructures.RUINED_NEXUS_PORTAL),
                new RandomSpreadStructurePlacement(32, 16, RandomSpreadType.LINEAR, 627627627)));
    }
}
