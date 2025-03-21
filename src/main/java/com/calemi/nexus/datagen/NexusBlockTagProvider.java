package com.calemi.nexus.datagen;

import com.calemi.nexus.main.NexusRef;
import com.calemi.nexus.regsitry.NexusBlocks;
import com.calemi.nexus.regsitry.NexusLists;
import com.calemi.nexus.regsitry.NexusTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class NexusBlockTagProvider extends BlockTagsProvider {

    public NexusBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, NexusRef.ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

        /*
            NEXUS TAGS
         */

        tag(NexusTags.DIRTLIKE)
                .add(NexusBlocks.CHRONOWARPED_GRASS.get())
                .add(NexusBlocks.CHRONOWARPED_DIRT.get())
                .add(NexusBlocks.CHRONOWARPED_SAND.get());

        tag(NexusTags.STONELIKE)
                .add(NexusBlocks.WARPSLATE.get())
                .add(NexusBlocks.CHISELED_WARPSLATE.get())
                .addOptionalTag(NexusTags.NEXUS_PORTAL_CORES)
                .addAll(NexusLists.toResourceKeyList((NexusLists.toDefBlockListFromBlockSet(NexusLists.ALL_STONE_BLOCKSETS))));

        tag(NexusTags.WARPBLOSSOM_LOGS)
                .add(NexusBlocks.WARPBLOSSOM_LOG.get())
                .add(NexusBlocks.WARPBLOSSOM_WOOD.get())
                .add(NexusBlocks.STRIPPED_WARPBLOSSOM_LOG.get())
                .add(NexusBlocks.STRIPPED_WARPBLOSSOM_WOOD.get());

        tag(NexusTags.WOODLIKE)
                .addOptionalTag(NexusTags.WARPBLOSSOM_LOGS);

        tag(NexusTags.NEXUS_PORTAL_CORES)
                .addAll(NexusLists.toResourceKeyList(NexusLists.NEXUS_PORTAL_CORE_BLOCKS));

        tag(NexusTags.NEXUS_PORTALS)
                .addAll(NexusLists.toResourceKeyList(NexusLists.NEXUS_PORTAL_BLOCKS));

        /*
            MINECRAFT TAGS
         */

        tag(BlockTags.MINEABLE_WITH_SHOVEL).addOptionalTag(NexusTags.DIRTLIKE);

        tag(BlockTags.MINEABLE_WITH_PICKAXE).addOptionalTag(NexusTags.STONELIKE);

        tag(BlockTags.MINEABLE_WITH_AXE).addOptionalTag(NexusTags.WOODLIKE);

        tag(BlockTags.MINEABLE_WITH_HOE).add(NexusBlocks.WARPBLOSSOM_LEAVES.get());

        tag(BlockTags.NEEDS_STONE_TOOL)
                .addOptionalTag(NexusTags.NEXUS_PORTAL_CORES);

        tag(BlockTags.DIRT)
                .add(NexusBlocks.CHRONOWARPED_DIRT.get(), NexusBlocks.CHRONOWARPED_GRASS.get());

        tag(BlockTags.SAND)
                .add(NexusBlocks.CHRONOWARPED_SAND.get());

        tag(BlockTags.SMELTS_TO_GLASS)
                .add(NexusBlocks.CHRONOWARPED_SAND.get());

        tag(Tags.Blocks.SANDS)
                .add(NexusBlocks.CHRONOWARPED_SAND.get());

        tag(BlockTags.SCULK_REPLACEABLE)
                .add(NexusBlocks.CHRONOWARPED_SAND.get());

        tag(BlockTags.SNIFFER_DIGGABLE_BLOCK)
                .add(NexusBlocks.CHRONOWARPED_DIRT.get(), NexusBlocks.CHRONOWARPED_GRASS.get());

        tag(Tags.Blocks.COBBLESTONES_DEEPSLATE)
                .add(NexusBlocks.COBBLED_WARPSLATE.get());

        for (NexusLists.BlockSet blockSet : NexusLists.ALL_BLOCKSETS) {

            if (blockSet.getStairs() != null) {
                tag(BlockTags.STAIRS).add(blockSet.getStairs().get());
            }

            if (blockSet.getSlab() != null) {
                tag(BlockTags.SLABS).add(blockSet.getSlab().get());
            }

            if (blockSet.getWall() != null) {
                tag(BlockTags.WALLS).add(blockSet.getWall().get());
            }
        }

        tag(BlockTags.LOGS_THAT_BURN)
                .add(NexusBlocks.WARPBLOSSOM_LOG.get());

        tag(Tags.Blocks.STRIPPED_LOGS)
                .add(NexusBlocks.STRIPPED_WARPBLOSSOM_LOG.get());

        tag(Tags.Blocks.STRIPPED_WOODS)
                .add(NexusBlocks.STRIPPED_WARPBLOSSOM_WOOD.get());

        tag(BlockTags.LEAVES)
                .add(NexusBlocks.WARPBLOSSOM_LEAVES.get());

        tag(BlockTags.SAPLINGS)
                .add(NexusBlocks.WARPBLOSSOM_SAPLING.get());
    }
}
