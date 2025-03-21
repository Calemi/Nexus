package com.calemi.nexus.datagen;

import com.calemi.nexus.main.NexusRef;
import com.calemi.nexus.regsitry.NexusBlocks;
import com.calemi.nexus.regsitry.NexusLists;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.BlockTags;
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
                .add(NexusBlocks.COBBLED_WARPSLATE.asItem());

        tag(ItemTags.STONE_TOOL_MATERIALS)
                .add(NexusBlocks.COBBLED_WARPSLATE.asItem());

        copy(Tags.Blocks.SANDS, Tags.Items.SANDS);
        copy(BlockTags.DIRT, ItemTags.DIRT);
        copy(BlockTags.SAND, ItemTags.SAND);
        copy(BlockTags.SMELTS_TO_GLASS, ItemTags.SMELTS_TO_GLASS);
        copy(Tags.Blocks.COBBLESTONES_DEEPSLATE, Tags.Items.COBBLESTONES_DEEPSLATE);
        copy(BlockTags.STAIRS, ItemTags.STAIRS);
        copy(BlockTags.SLABS, ItemTags.SLABS);
        copy(BlockTags.WALLS, ItemTags.WALLS);
        copy(BlockTags.LOGS_THAT_BURN, ItemTags.LOGS_THAT_BURN);
        copy(BlockTags.LEAVES, ItemTags.LEAVES);
        copy(BlockTags.SAPLINGS, ItemTags.SAPLINGS);
    }
}
