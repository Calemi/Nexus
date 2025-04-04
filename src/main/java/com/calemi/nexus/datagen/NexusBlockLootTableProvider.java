package com.calemi.nexus.datagen;

import com.calemi.ccore.api.block.family.CBlockFamily;
import com.calemi.nexus.block.NexusBlocks;
import com.calemi.nexus.block.family.NexusBlockFamilies;
import com.calemi.nexus.item.NexusItems;
import com.calemi.nexus.main.Nexus;
import com.calemi.nexus.util.NexusLists;
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

        Nexus.LOGGER.debug("Generating: Block Loot Tables - Start");

        NexusBlockFamilies.ALL.forEach(family -> {

            Nexus.LOGGER.debug("Generating: Block Family Loot Tables - Start");

            family.getMembers().forEach((type, member) -> {

                Nexus.LOGGER.debug("Generating block loot table for {}", member.getBlock());

                if (!member.genLootTable()) {
                    Nexus.LOGGER.debug(" - skip");
                    return;
                }

                if (type.equals(CBlockFamily.MemberType.DOOR)) {
                    add(member.getBlock(), createDoorTable(member.getBlock()));
                    Nexus.LOGGER.debug(" - door drop");
                    return;
                }

                Nexus.LOGGER.debug(" - self drop");

                dropSelf(member.getBlock());
            });

            Nexus.LOGGER.debug("Generating: Block Family Loot Tables - End");
        });

        NexusLists.ALL_BLOCKS_BUT_FAMILIES.forEach(block -> {

            if (NexusLists.NEXUS_PORTAL_BLOCKS.contains(block)) {
                add(block, noDrop());
                return;
            }

            Block chronowarpedGrass = NexusBlocks.CHRONOWARPED_GRASS.get();
            if (block.equals(chronowarpedGrass)) {
                add(chronowarpedGrass, createSingleItemTableWithSilkTouch(chronowarpedGrass, NexusBlocks.CHRONOWARPED_DIRT.get()));
                return;
            }

            Block buddingChrono = NexusBlocks.BUDDING_CHRONO.get();
            if (block.equals(buddingChrono)) {
                add(buddingChrono, noDrop());
                return;
            }

            if (NexusLists.CHRONO_CLUSTER_BLOCKS.contains(block)) {

                Block chronoCluster = NexusBlocks.CHRONO_CLUSTER.get();
                if (block.equals(chronoCluster)) {
                    add(chronoCluster, createSingleItemTableWithSilkTouch(chronoCluster, NexusItems.CHRONO_SHARD));
                    return;
                }

                add(block, createSilkTouchOnlyTable(block));
                return;
            }

            Block warpslate = NexusBlocks.WARPSLATE.get();
            if (block.equals(warpslate)) {
                add(warpslate, createSingleItemTableWithSilkTouch(warpslate, NexusBlocks.COBBLED_WARPSLATE.get()));
                return;
            }

            Block warpslateAcceleriteOre = NexusBlocks.WARPSLATE_ACCELERITE_ORE.get();
            if (block.equals(warpslateAcceleriteOre)) {
                add(warpslateAcceleriteOre, createOreDrop(warpslateAcceleriteOre, NexusItems.RAW_ACCELERITE.get()));
                return;
            }

            Block pottedWarpblossomSapling = NexusBlocks.POTTED_WARPBLOSSOM_SAPLING.get();
            if (block.equals(pottedWarpblossomSapling)) {
                add(pottedWarpblossomSapling, createPotFlowerItemTable(NexusBlocks.WARPBLOSSOM_SAPLING));
                return;
            }

            Block warpblossomLeaves = NexusBlocks.WARPBLOSSOM_LEAVES.get();
            if (block.equals(warpblossomLeaves)) {
                add(warpblossomLeaves, createLeavesDrops(warpblossomLeaves, NexusBlocks.WARPBLOSSOM_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));
                return;
            }

            Block purplePetals = NexusBlocks.PURPLE_PETALS.get();
            if (block.equals(purplePetals)) {
                add(purplePetals, createPetalsDrops(purplePetals));
                return;
            }

            dropSelf(block);
        });

        Nexus.LOGGER.debug("Generating: Block Loot Tables - End");
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return NexusBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
