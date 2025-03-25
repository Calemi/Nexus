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

        NexusLists.ALL_BLOCKS.forEach(block -> {

            if (NexusLists.NEXUS_PORTAL_BLOCKS.contains(block)) {
                add(block.get(), noDrop());
                return;
            }

            Block chronowarpedGrass = NexusBlocks.CHRONOWARPED_GRASS.get();
            if (block.get().equals(chronowarpedGrass)) {
                add(chronowarpedGrass, createSingleItemTableWithSilkTouch(chronowarpedGrass, NexusBlocks.CHRONOWARPED_DIRT.get()));
                return;
            }

            Block warpslate = NexusBlocks.WARPSLATE.get();
            if (block.get().equals(warpslate)) {
                add(warpslate, createSingleItemTableWithSilkTouch(warpslate, NexusBlocks.COBBLED_WARPSLATE.get()));
                return;
            }

            Block pottedWarpblossomSapling = NexusBlocks.POTTED_WARPBLOSSOM_SAPLING.get();
            if (block.get().equals(pottedWarpblossomSapling)) {
                add(pottedWarpblossomSapling, createPotFlowerItemTable(NexusBlocks.WARPBLOSSOM_SAPLING));
                return;
            }

            Block warpblossomLeaves = NexusBlocks.WARPBLOSSOM_LEAVES.get();
            if (block.get().equals(warpblossomLeaves)) {
                add(warpblossomLeaves, createLeavesDrops(warpblossomLeaves, NexusBlocks.WARPBLOSSOM_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));
                return;
            }

            Block warpblossomDoor = NexusBlocks.WARPBLOSSOM_DOOR.get();
            if (block.get().equals(warpblossomDoor)) {
                add(warpblossomDoor, createDoorTable(warpblossomDoor));
                return;
            }

            dropSelf(block.get());
        });
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return NexusBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
