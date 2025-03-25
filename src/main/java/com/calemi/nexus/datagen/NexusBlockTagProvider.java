package com.calemi.nexus.datagen;

import com.calemi.nexus.main.NexusRef;
import com.calemi.nexus.regsitry.NexusBlockFamilies;
import com.calemi.nexus.regsitry.NexusBlocks;
import com.calemi.nexus.regsitry.NexusLists;
import com.calemi.nexus.regsitry.NexusTags;
import com.calemi.ccore.api.family.CBlockFamily;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
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

        tag(NexusTags.Blocks.DIRTLIKE)
                .add(NexusBlocks.CHRONOWARPED_GRASS.get())
                .add(NexusBlocks.CHRONOWARPED_DIRT.get())
                .add(NexusBlocks.CHRONOWARPED_SAND.get());

        tag(NexusTags.Blocks.STONELIKE)
                .add(NexusBlocks.WARPSLATE.get())
                .add(NexusBlocks.CHISELED_WARPSLATE.get())
                .addOptionalTag(NexusTags.Blocks.NEXUS_PORTAL_CORES)
                .addAll(NexusLists.toResourceKeyList((NexusLists.toDefBlockListFromBlockSet(NexusBlockFamilies.getFamiliesOfType(CBlockFamily.Type.COBBLESTONE)))))
                .addAll(NexusLists.toResourceKeyList((NexusLists.toDefBlockListFromBlockSet(NexusBlockFamilies.getFamiliesOfType(CBlockFamily.Type.STONE)))));

        tag(NexusTags.Blocks.WARPBLOSSOM_LOGS)
                .add(NexusBlocks.WARPBLOSSOM_LOG.get())
                .add(NexusBlocks.WARPBLOSSOM_WOOD.get())
                .add(NexusBlocks.STRIPPED_WARPBLOSSOM_LOG.get())
                .add(NexusBlocks.STRIPPED_WARPBLOSSOM_WOOD.get());

        tag(NexusTags.Blocks.WOODLIKE)
                .addOptionalTag(NexusTags.Blocks.WARPBLOSSOM_LOGS)
                .addAll(NexusLists.toResourceKeyList((NexusLists.toDefBlockListFromBlockSet(NexusBlockFamilies.getFamiliesOfType(CBlockFamily.Type.PLANKS)))));

        tag(NexusTags.Blocks.NEXUS_PORTAL_CORES)
                .addAll(NexusLists.toResourceKeyList(NexusLists.NEXUS_PORTAL_CORE_BLOCKS));

        tag(NexusTags.Blocks.NEXUS_PORTALS)
                .addAll(NexusLists.toResourceKeyList(NexusLists.NEXUS_PORTAL_BLOCKS));

        /*
            BLOCK FAMILIES
         */

        for (CBlockFamily family : NexusBlockFamilies.ALL) {

            DeferredBlock<Block> baseBlock = family.get(CBlockFamily.Variant.BASE);
            DeferredBlock<Block> log = family.get(CBlockFamily.Variant.LOG);
            DeferredBlock<Block> wood = family.get(CBlockFamily.Variant.WOOD);
            DeferredBlock<Block> strippedLog = family.get(CBlockFamily.Variant.STRIPPED_LOG);
            DeferredBlock<Block> strippedWood = family.get(CBlockFamily.Variant.STRIPPED_WOOD);
            DeferredBlock<Block> crackedBlock = family.get(CBlockFamily.Variant.CRACKED);
            DeferredBlock<Block> stairs = family.get(CBlockFamily.Variant.STAIRS);
            DeferredBlock<Block> slab = family.get(CBlockFamily.Variant.SLAB);
            DeferredBlock<Block> wall = family.get(CBlockFamily.Variant.WALL);
            DeferredBlock<Block> fence = family.get(CBlockFamily.Variant.FENCE);
            DeferredBlock<Block> fenceGate = family.get(CBlockFamily.Variant.FENCE_GATE);
            DeferredBlock<Block> door = family.get(CBlockFamily.Variant.DOOR);
            DeferredBlock<Block> trapDoor = family.get(CBlockFamily.Variant.TRAPDOOR);
            DeferredBlock<Block> pressurePlate = family.get(CBlockFamily.Variant.PRESSURE_PLATE);
            DeferredBlock<Block> button = family.get(CBlockFamily.Variant.BUTTON);
            DeferredBlock<Block> sign = family.get(CBlockFamily.Variant.SIGN);
            DeferredBlock<Block> wallSign = family.get(CBlockFamily.Variant.WALL_SIGN);
            DeferredBlock<Block> hangingSign = family.get(CBlockFamily.Variant.HANGING_SIGN);
            DeferredBlock<Block> wallHangingSign = family.get(CBlockFamily.Variant.WALL_HANGING_SIGN);

            if (baseBlock != null) {

                if (family.getType() == CBlockFamily.Type.PLANKS) {
                    tag(BlockTags.PLANKS).add(baseBlock.get());
                }
            }

            if (log != null) {
                tag(BlockTags.LOGS_THAT_BURN).add(log.get());

            }

            if (wood != null) {
                tag(BlockTags.LOGS_THAT_BURN).add(wood.get());
            }

            if (strippedLog != null) {
                tag(BlockTags.LOGS_THAT_BURN).add(strippedLog.get());
                tag(Tags.Blocks.STRIPPED_LOGS).add(strippedLog.get());
            }

            if (strippedWood != null) {
                tag(BlockTags.LOGS_THAT_BURN).add(strippedWood.get());
                tag(Tags.Blocks.STRIPPED_WOODS).add(strippedWood.get());
            }

            if (stairs != null) {
                tag(BlockTags.STAIRS).add(stairs.get());

                if (family.getType() == CBlockFamily.Type.PLANKS) {
                    tag(BlockTags.WOODEN_STAIRS).add(stairs.get());
                }
            }

            if (slab != null) {
                tag(BlockTags.SLABS).add(slab.get());

                if (family.getType() == CBlockFamily.Type.PLANKS) {
                    tag(BlockTags.WOODEN_SLABS).add(slab.get());
                }
            }

            if (wall != null) {
                tag(BlockTags.WALLS).add(wall.get());
            }

            if (fence != null) {

                tag(BlockTags.FENCES).add(fence.get());
                tag(Tags.Blocks.FENCES).add(fence.get());

                if (family.getType() == CBlockFamily.Type.PLANKS) {
                    tag(BlockTags.WOODEN_FENCES).add(fence.get());
                    tag(Tags.Blocks.FENCES_WOODEN).add(fence.get());
                }
            }

            if (fenceGate != null) {

                tag(BlockTags.FENCE_GATES).add(fenceGate.get());
                tag(Tags.Blocks.FENCE_GATES).add(fenceGate.get());

                if (family.getType() == CBlockFamily.Type.PLANKS) {
                    tag(Tags.Blocks.FENCE_GATES_WOODEN).add(fenceGate.get());
                }
            }

            if (door != null) {
                tag(BlockTags.DOORS).add(door.get());

                if (family.getType() == CBlockFamily.Type.PLANKS) {
                    tag(BlockTags.WOODEN_DOORS).add(door.get());
                }
            }

            if (trapDoor != null) {
                tag(BlockTags.TRAPDOORS).add(trapDoor.get());

                if (family.getType() == CBlockFamily.Type.PLANKS) {
                    tag(BlockTags.WOODEN_TRAPDOORS).add(trapDoor.get());
                }
            }

            if (pressurePlate != null) {
                tag(BlockTags.PRESSURE_PLATES).add(pressurePlate.get());

                if (family.getType() == CBlockFamily.Type.PLANKS) {
                    tag(BlockTags.WOODEN_PRESSURE_PLATES).add(pressurePlate.get());
                }
            }

            if (button != null) {
                tag(BlockTags.BUTTONS).add(button.get());

                if (family.getType() == CBlockFamily.Type.PLANKS) {
                    tag(BlockTags.WOODEN_BUTTONS).add(button.get());
                }
            }

            if (sign != null) {
                tag(BlockTags.STANDING_SIGNS).add(sign.get());
            }

            if (wallSign != null) {
                tag(BlockTags.WALL_SIGNS).add(wallSign.get());
            }

            if (hangingSign != null) {
                tag(BlockTags.CEILING_HANGING_SIGNS).add(hangingSign.get());
            }

            if (wallHangingSign != null) {
                tag(BlockTags.WALL_HANGING_SIGNS).add(wallHangingSign.get());
            }
        }

        /*
            MINECRAFT TAGS
         */

        tag(BlockTags.MINEABLE_WITH_SHOVEL).addOptionalTag(NexusTags.Blocks.DIRTLIKE);

        tag(BlockTags.MINEABLE_WITH_PICKAXE).addOptionalTag(NexusTags.Blocks.STONELIKE);

        tag(BlockTags.MINEABLE_WITH_AXE).addOptionalTag(NexusTags.Blocks.WOODLIKE);

        tag(BlockTags.MINEABLE_WITH_HOE).add(NexusBlocks.WARPBLOSSOM_LEAVES.get());

        tag(BlockTags.NEEDS_STONE_TOOL)
                .addOptionalTag(NexusTags.Blocks.NEXUS_PORTAL_CORES);

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

        tag(BlockTags.SNAPS_GOAT_HORN)
                .add(NexusBlocks.WARPBLOSSOM_LOG.get());

        tag(BlockTags.LEAVES)
                .add(NexusBlocks.WARPBLOSSOM_LEAVES.get());

        tag(BlockTags.SAPLINGS)
                .add(NexusBlocks.WARPBLOSSOM_SAPLING.get());
    }
}
