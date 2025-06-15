package com.calemi.nexus.datagen;

import com.calemi.ccore.api.list.ListHelper;
import com.calemi.nexus.main.NexusRef;
import com.calemi.nexus.block.NexusBlocks;
import com.calemi.nexus.item.NexusItems;
import com.calemi.nexus.tag.NexusTags;
import com.calemi.nexus.util.NexusLists;
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

        tag(NexusTags.Items.ACCELERITE_INGOTS)
                .add(NexusItems.DORMANT_ACCELERITE_INGOT.get(), NexusItems.CHARGED_ACCELERITE_INGOT.get());

        tag(ItemTags.STONE_CRAFTING_MATERIALS)
                .add(NexusBlocks.COBBLED_WARPSLATE.asItem());

        tag(ItemTags.STONE_TOOL_MATERIALS)
                .add(NexusBlocks.COBBLED_WARPSLATE.asItem());

        copy(NexusTags.Blocks.WARPBLOSSOM_LOGS, NexusTags.Items.WARPBLOSSOM_LOGS);

        tag(NexusTags.Items.ACCELERITE_ARMOR).addAll(ListHelper.toItemResourceKeyList(NexusLists.ACCELERITE_ARMOR));

        tag(NexusTags.Items.ACCELERITE_DIGGERS).addAll(ListHelper.toItemResourceKeyList(NexusLists.ACCELERITE_DIGGERS));

        tag(NexusTags.Items.ACCELERITE_TOOLS).add(NexusItems.ACCELERITE_SWORD.get());
        tag(NexusTags.Items.ACCELERITE_TOOLS).addAll(ListHelper.toItemResourceKeyList(NexusLists.ACCELERITE_DIGGERS));

        tag(NexusTags.Items.ACCELERITE_EQUIPMENT).addAll(ListHelper.toItemResourceKeyList(NexusLists.ACCELERITE_EQUIPMENT));

        tag(NexusTags.Items.ACCELERATION_ENCHANTABLE).addTag(ItemTags.ARMOR_ENCHANTABLE);
        tag(NexusTags.Items.ACCELERATION_ENCHANTABLE).addTag(ItemTags.WEAPON_ENCHANTABLE);
        tag(NexusTags.Items.ACCELERATION_ENCHANTABLE).addTag(ItemTags.MINING_ENCHANTABLE);
        tag(NexusTags.Items.ACCELERATION_ENCHANTABLE).remove(NexusTags.Items.ACCELERITE_EQUIPMENT);

        tag(NexusTags.Items.SPEED_MENDING_ENCHANTABLE).addTag(ItemTags.DURABILITY_ENCHANTABLE);
        tag(NexusTags.Items.SPEED_MENDING_ENCHANTABLE).remove(NexusTags.Items.ACCELERITE_EQUIPMENT);

        tag(Tags.Items.RAW_MATERIALS).add(NexusItems.RAW_ACCELERITE.get());
        tag(Tags.Items.INGOTS).add(NexusItems.DORMANT_ACCELERITE_INGOT.get(), NexusItems.CHARGED_ACCELERITE_INGOT.get());
        tag(Tags.Items.GEMS).add(NexusItems.CHRONO_SHARD.get());

        tag(ItemTags.HEAD_ARMOR).add(NexusItems.ACCELERITE_HELMET.get());
        tag(ItemTags.CHEST_ARMOR).add(NexusItems.ACCELERITE_CHESTPLATE.get());
        tag(ItemTags.LEG_ARMOR).add(NexusItems.ACCELERITE_LEGGINGS.get());
        tag(ItemTags.FOOT_ARMOR).add(NexusItems.ACCELERITE_BOOTS.get());
        tag(ItemTags.FOOT_ARMOR).add(NexusItems.FALLBREAKERS.get());

        tag(Tags.Items.ARMORS).add(
                NexusItems.FALLBREAKERS.get(),
                NexusItems.ACCELERITE_HELMET.get(),
                NexusItems.ACCELERITE_CHESTPLATE.get(),
                NexusItems.ACCELERITE_LEGGINGS.get(),
                NexusItems.ACCELERITE_BOOTS.get()
        );

        tag(Tags.Items.ENCHANTABLES).add(
                NexusItems.FALLBREAKERS.get(),
                NexusItems.ACCELERITE_HELMET.get(),
                NexusItems.ACCELERITE_CHESTPLATE.get(),
                NexusItems.ACCELERITE_LEGGINGS.get(),
                NexusItems.ACCELERITE_BOOTS.get()
        );

        tag(Tags.Items.RODS_WOODEN).add(NexusItems.WARPBLOSSOM_STICK.get());

        tag(ItemTags.SWORDS).add(NexusItems.ACCELERITE_SWORD.get());
        tag(Tags.Items.MELEE_WEAPON_TOOLS).add(NexusItems.ACCELERITE_SWORD.get());

        tag(ItemTags.PICKAXES).add(NexusItems.ACCELERITE_PICKAXE.get());
        tag(ItemTags.CLUSTER_MAX_HARVESTABLES).add(NexusItems.ACCELERITE_PICKAXE.get());
        tag(Tags.Items.MINING_TOOL_TOOLS).add(NexusItems.ACCELERITE_PICKAXE.get());

        tag(ItemTags.SHOVELS).add(NexusItems.ACCELERITE_SHOVEL.get());

        tag(ItemTags.AXES).add(NexusItems.ACCELERITE_AXE.get());

        tag(ItemTags.HOES).add(NexusItems.ACCELERITE_HOE.get());

        tag(ItemTags.BOATS).add(NexusItems.WARPBLOSSOM_BOAT.get());
        tag(ItemTags.CHEST_BOATS).add(NexusItems.WARPBLOSSOM_CHEST_BOAT.get());

        copy(Tags.Blocks.SANDS, Tags.Items.SANDS);
        copy(Tags.Blocks.STORAGE_BLOCKS, Tags.Items.STORAGE_BLOCKS);
        copy(Tags.Blocks.ORES, Tags.Items.ORES);
        copy(BlockTags.DIRT, ItemTags.DIRT);
        copy(BlockTags.SAND, ItemTags.SAND);
        copy(BlockTags.SMELTS_TO_GLASS, ItemTags.SMELTS_TO_GLASS);
        copy(Tags.Blocks.COBBLESTONES_DEEPSLATE, Tags.Items.COBBLESTONES_DEEPSLATE);
        copy(BlockTags.STAIRS, ItemTags.STAIRS);
        copy(BlockTags.WOODEN_STAIRS, ItemTags.WOODEN_STAIRS);
        copy(BlockTags.SLABS, ItemTags.SLABS);
        copy(BlockTags.WOODEN_SLABS, ItemTags.WOODEN_SLABS);
        copy(BlockTags.WALLS, ItemTags.WALLS);
        copy(BlockTags.LOGS_THAT_BURN, ItemTags.LOGS_THAT_BURN);
        copy(Tags.Blocks.STRIPPED_LOGS, Tags.Items.STRIPPED_LOGS);
        copy(Tags.Blocks.STRIPPED_WOODS, Tags.Items.STRIPPED_WOODS);
        copy(BlockTags.LEAVES, ItemTags.LEAVES);
        copy(BlockTags.SAPLINGS, ItemTags.SAPLINGS);
        copy(BlockTags.PLANKS, ItemTags.PLANKS);
        copy(BlockTags.FENCES, ItemTags.FENCES);
        copy(BlockTags.WOODEN_FENCES, ItemTags.WOODEN_FENCES);
        copy(BlockTags.FENCE_GATES, ItemTags.FENCE_GATES);
        copy(Tags.Blocks.FENCES, Tags.Items.FENCES);
        copy(Tags.Blocks.FENCES_WOODEN, Tags.Items.FENCES_WOODEN);
        copy(Tags.Blocks.FENCE_GATES, Tags.Items.FENCE_GATES);
        copy(Tags.Blocks.FENCE_GATES_WOODEN, Tags.Items.FENCE_GATES_WOODEN);
        copy(BlockTags.WOODEN_DOORS, ItemTags.WOODEN_DOORS);
        copy(BlockTags.WOODEN_TRAPDOORS, ItemTags.WOODEN_TRAPDOORS);
        copy(BlockTags.WOODEN_PRESSURE_PLATES, ItemTags.WOODEN_PRESSURE_PLATES);
        copy(BlockTags.WOODEN_BUTTONS, ItemTags.WOODEN_BUTTONS);
        copy(BlockTags.STANDING_SIGNS, ItemTags.SIGNS);
        copy(BlockTags.CEILING_HANGING_SIGNS, ItemTags.HANGING_SIGNS);
        copy(BlockTags.FLOWERS, ItemTags.FLOWERS);
        copy(Tags.Blocks.CLUSTERS, Tags.Items.CLUSTERS);
        copy(Tags.Blocks.BUDDING_BLOCKS, Tags.Items.BUDDING_BLOCKS);
        copy(Tags.Blocks.BUDS, Tags.Items.BUDS);
    }
}
