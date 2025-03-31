package com.calemi.nexus.world.structure;

import com.calemi.nexus.main.NexusRef;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.Pools;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;

import java.util.List;

public class NexusTemplatePools {

    public static final ResourceKey<StructureTemplatePool> RUINED_NEXUS_PORTAL = NexusRef.createKey("ruined_nexus_portal", Registries.TEMPLATE_POOL);

    public static void bootstrap(BootstrapContext<StructureTemplatePool> context) {

        HolderGetter<StructureTemplatePool> templatePoolHolder = context.lookup(Registries.TEMPLATE_POOL);
        Holder<StructureTemplatePool> emptyPoolHolder = templatePoolHolder.getOrThrow(Pools.EMPTY);

        HolderGetter<StructureProcessorList> processorHolder = context.lookup(Registries.PROCESSOR_LIST);

        context.register(RUINED_NEXUS_PORTAL, new StructureTemplatePool(
                emptyPoolHolder,
                List.of(
                        Pair.of(StructurePoolElement.single("nexus:ruined_nexus_portal/portal_1"), 1)
                ),
                StructureTemplatePool.Projection.RIGID
        ));
    }
}
