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

        add(NexusBlocks.CHRONOWARPED_GRASS.get(), createSingleItemTableWithSilkTouch(NexusBlocks.CHRONOWARPED_GRASS.get(), NexusBlocks.CHRONOWARPED_DIRT.get()));
        dropSelf(NexusBlocks.CHRONOWARPED_DIRT.get());
        dropSelf(NexusBlocks.CHRONOWARPED_SAND.get());

        add(NexusBlocks.WARPSLATE.get(), createSingleItemTableWithSilkTouch(NexusBlocks.WARPSLATE.get(), NexusBlocks.COBBLED_WARPSLATE.get()));

        NexusLists.ALL_BLOCKSETS.forEach(set -> set.getAll().forEach(block -> dropSelf(block.get())));
        dropSelf(NexusBlocks.CHISELED_WARPSLATE.get());

        dropSelf(NexusBlocks.WARPBLOSSOM_LOG.get());
        dropSelf(NexusBlocks.WARPBLOSSOM_WOOD.get());
        dropSelf(NexusBlocks.STRIPPED_WARPBLOSSOM_LOG.get());
        dropSelf(NexusBlocks.STRIPPED_WARPBLOSSOM_WOOD.get());

        add(NexusBlocks.WARPBLOSSOM_LEAVES.get(), createLeavesDrops(NexusBlocks.WARPBLOSSOM_LEAVES.get(), NexusBlocks.WARPBLOSSOM_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));
        dropSelf(NexusBlocks.WARPBLOSSOM_SAPLING.get());
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return NexusBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
