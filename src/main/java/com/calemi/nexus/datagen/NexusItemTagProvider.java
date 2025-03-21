package com.calemi.nexus.datagen;

import com.calemi.nexus.main.NexusRef;
import com.calemi.nexus.regsitry.NexusBlocks;
import com.calemi.nexus.regsitry.NexusLists;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class NexusItemTagProvider extends ItemTagsProvider {

    public NexusItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> itemProvider, CompletableFuture<TagsProvider.TagLookup<Block>> blockProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, itemProvider, blockProvider, NexusRef.ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

        tag(ItemTags.STONE_CRAFTING_MATERIALS)
                .add(NexusBlocks.COBBLED_WARPSLATE.get().asItem());

        tag(ItemTags.STONE_TOOL_MATERIALS)
                .add(NexusBlocks.COBBLED_WARPSLATE.get().asItem());

        tag(Tags.Items.COBBLESTONES_DEEPSLATE)
                .add(NexusBlocks.COBBLED_WARPSLATE.get().asItem());

        tag(ItemTags.DIRT)
                .add(NexusBlocks.CHRONOWARPED_DIRT.get().asItem(), NexusBlocks.CHRONOWARPED_GRASS.get().asItem());

        for (NexusLists.BlockSet blockSet : NexusLists.ALL_BLOCKSETS) {

            if (blockSet.getStairs() != null) {
                tag(ItemTags.STAIRS).add(blockSet.getStairs().asItem());
            }

            if (blockSet.getSlab() != null) {
                tag(ItemTags.SLABS).add(blockSet.getSlab().asItem());
            }

            if (blockSet.getWall() != null) {
                tag(ItemTags.WALLS).add(blockSet.getWall().asItem());
            }
        }
    }
}
