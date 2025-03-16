package com.calemi.nexus.datagen;

import com.calemi.nexus.regsitry.NexusBlocks;
import com.calemi.nexus.regsitry.NexusLists;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;

import java.util.Set;

public class NexusBlockLootTableProvider extends BlockLootSubProvider {

    protected NexusBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {

        NexusLists.NEXUS_PORTAL_CORE_BLOCKS.forEach(deferredBlock -> dropSelf(deferredBlock.get()));
        NexusLists.NEXUS_PORTAL_BLOCKS.forEach(deferredBlock -> add(deferredBlock.get(), noDrop()));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return NexusBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
