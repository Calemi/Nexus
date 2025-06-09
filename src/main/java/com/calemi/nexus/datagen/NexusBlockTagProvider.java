package com.calemi.nexus.datagen;

import com.calemi.ccore.api.block.family.CBlockFamily;
import com.calemi.nexus.block.NexusBlocks;
import com.calemi.nexus.block.family.NexusBlockFamilies;
import com.calemi.nexus.main.NexusRef;
import com.calemi.nexus.tag.NexusTags;
import com.calemi.nexus.util.NexusLists;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
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

        tag(NexusTags.Blocks.DIRTLIKE)
                .add(NexusBlocks.CHRONOWARPED_GRASS.get())
                .add(NexusBlocks.CHRONOWARPED_DIRT.get())
                .add(NexusBlocks.CHRONOWARPED_SAND.get());

        tag(NexusTags.Blocks.STONELIKE)
                .add(NexusBlocks.CHRONO_BLOCK.get())
                .add(NexusBlocks.BUDDING_CHRONO.get())
                .add(NexusBlocks.WARPSLATE.get())
                .add(NexusBlocks.WARPSLATE_ACCELERITE_ORE.get())
                .addOptionalTag(NexusTags.Blocks.NEXUS_PORTAL_CORES)
                .addOptionalTag(NexusTags.Blocks.ROADLIKE)
                .addAll(NexusLists.toBlockResourceKeyList((NexusLists.CHRONO_CLUSTER_BLOCKS)));

        tag(NexusTags.Blocks.WARPBLOSSOM_LOGS)
                .add(NexusBlocks.WARPBLOSSOM_LOG.get())
                .add(NexusBlocks.WARPBLOSSOM_WOOD.get())
                .add(NexusBlocks.STRIPPED_WARPBLOSSOM_LOG.get())
                .add(NexusBlocks.STRIPPED_WARPBLOSSOM_WOOD.get());

        tag(NexusTags.Blocks.WOODLIKE)
                .addOptionalTag(NexusTags.Blocks.WARPBLOSSOM_LOGS);

        tag(NexusTags.Blocks.ROADLIKE)
                .add(NexusBlocks.ROAD.get())
                .add(NexusBlocks.ROAD_SLAB.get())
                .add(NexusBlocks.JUMP_PAD.get());

        tag(NexusTags.Blocks.NEXUS_PORTAL_CORES)
                .addAll(NexusLists.toBlockResourceKeyList(NexusLists.NEXUS_PORTAL_CORE_BLOCKS));

        tag(NexusTags.Blocks.NEXUS_PORTALS)
                .addAll(NexusLists.toBlockResourceKeyList(NexusLists.NEXUS_PORTAL_BLOCKS));

        tag(NexusTags.Blocks.UPGRADES_ROAD_BLOCK)
                .add(NexusBlocks.CHARGED_ACCELERITE_BLOCK.get());

        tag(NexusTags.Blocks.NEEDS_ACCELERITE_TOOL)
                .addTag(BlockTags.NEEDS_IRON_TOOL);

        tag(NexusTags.Blocks.INCORRECT_FOR_ACCELERITE_TOOL)
                .addTag(BlockTags.INCORRECT_FOR_IRON_TOOL)
                .remove(NexusTags.Blocks.NEEDS_ACCELERITE_TOOL);

        /*
            BLOCK FAMILIES
         */

        NexusBlockFamilies.ALL.forEach(family -> {

            family.getMembers().forEach((type, member) -> {

                if (!member.genBlockTags()) return;

                Block block = member.getBlock();

                if (family.getFamilyType().isStone()) {
                    tag(NexusTags.Blocks.STONELIKE).add(block);
                }

                if (family.getFamilyType() == CBlockFamily.FamilyType.PLANKS) {
                    tag(NexusTags.Blocks.WOODLIKE).add(block);
                }

                if (type.equals(CBlockFamily.MemberType.BASE)) {

                    if (family.getFamilyType() == CBlockFamily.FamilyType.PLANKS) {
                        tag(BlockTags.PLANKS).add(block);
                    }
                }

                if (type.equals(CBlockFamily.MemberType.LOG)) {
                    tag(BlockTags.LOGS_THAT_BURN).add(block);
                }

                if (type.equals(CBlockFamily.MemberType.WOOD)) {
                    tag(BlockTags.LOGS_THAT_BURN).add(block);
                }

                if (type.equals(CBlockFamily.MemberType.STRIPPED_LOG)) {
                    tag(BlockTags.LOGS_THAT_BURN).add(block);
                    tag(Tags.Blocks.STRIPPED_LOGS).add(block);
                }

                if (type.equals(CBlockFamily.MemberType.STRIPPED_WOOD)) {
                    tag(BlockTags.LOGS_THAT_BURN).add(block);
                    tag(Tags.Blocks.STRIPPED_WOODS).add(block);
                }

                if (type.equals(CBlockFamily.MemberType.STAIRS)) {
                    tag(BlockTags.STAIRS).add(block);

                    if (family.getFamilyType() == CBlockFamily.FamilyType.PLANKS) {
                        tag(BlockTags.WOODEN_STAIRS).add(block);
                    }
                }

                if (type.equals(CBlockFamily.MemberType.SLAB)) {
                    tag(BlockTags.SLABS).add(block);

                    if (family.getFamilyType() == CBlockFamily.FamilyType.PLANKS) {
                        tag(BlockTags.WOODEN_SLABS).add(block);
                    }
                }

                if (type.equals(CBlockFamily.MemberType.WALL)) {
                    tag(BlockTags.WALLS).add(block);
                }

                if (type.equals(CBlockFamily.MemberType.FENCE)) {

                    tag(BlockTags.FENCES).add(block);
                    tag(Tags.Blocks.FENCES).add(block);

                    if (family.getFamilyType() == CBlockFamily.FamilyType.PLANKS) {
                        tag(BlockTags.WOODEN_FENCES).add(block);
                        tag(Tags.Blocks.FENCES_WOODEN).add(block);
                    }
                }

                if (type.equals(CBlockFamily.MemberType.FENCE_GATE)) {

                    tag(BlockTags.FENCE_GATES).add(block);
                    tag(Tags.Blocks.FENCE_GATES).add(block);

                    if (family.getFamilyType() == CBlockFamily.FamilyType.PLANKS) {
                        tag(Tags.Blocks.FENCE_GATES_WOODEN).add(block);
                    }
                }

                if (type.equals(CBlockFamily.MemberType.DOOR)) {
                    tag(BlockTags.DOORS).add(block);

                    if (family.getFamilyType() == CBlockFamily.FamilyType.PLANKS) {
                        tag(BlockTags.WOODEN_DOORS).add(block);
                    }
                }

                if (type.equals(CBlockFamily.MemberType.TRAPDOOR)) {
                    tag(BlockTags.TRAPDOORS).add(block);

                    if (family.getFamilyType() == CBlockFamily.FamilyType.PLANKS) {
                        tag(BlockTags.WOODEN_TRAPDOORS).add(block);
                    }
                }

                if (type.equals(CBlockFamily.MemberType.PRESSURE_PLATE)) {
                    tag(BlockTags.PRESSURE_PLATES).add(block);

                    if (family.getFamilyType() == CBlockFamily.FamilyType.PLANKS) {
                        tag(BlockTags.WOODEN_PRESSURE_PLATES).add(block);
                    }
                }

                if (type.equals(CBlockFamily.MemberType.BUTTON)) {
                    tag(BlockTags.BUTTONS).add(block);

                    if (family.getFamilyType() == CBlockFamily.FamilyType.PLANKS) {
                        tag(BlockTags.WOODEN_BUTTONS).add(block);
                    }
                }

                if (type.equals(CBlockFamily.MemberType.SIGN)) {
                    tag(BlockTags.STANDING_SIGNS).add(block);
                }

                if (type.equals(CBlockFamily.MemberType.WALL_SIGN)) {
                    tag(BlockTags.WALL_SIGNS).add(block);
                }

                if (type.equals(CBlockFamily.MemberType.HANGING_SIGN)) {
                    tag(BlockTags.CEILING_HANGING_SIGNS).add(block);
                }

                if (type.equals(CBlockFamily.MemberType.WALL_HANGING_SIGN)) {
                    tag(BlockTags.WALL_HANGING_SIGNS).add(block);
                }
            });
        });

        /*
            MINECRAFT TAGS
         */

        tag(BlockTags.MINEABLE_WITH_SHOVEL).addOptionalTag(NexusTags.Blocks.DIRTLIKE);

        tag(BlockTags.MINEABLE_WITH_PICKAXE).addOptionalTag(NexusTags.Blocks.STONELIKE);

        tag(BlockTags.MINEABLE_WITH_AXE).addOptionalTag(NexusTags.Blocks.WOODLIKE);

        tag(BlockTags.MINEABLE_WITH_HOE).add(NexusBlocks.WARPBLOSSOM_LEAVES.get());

        tag(BlockTags.SWORD_EFFICIENT).add(NexusBlocks.PURPLE_PETALS.get());

        tag(BlockTags.NEEDS_STONE_TOOL)
                .addOptionalTag(NexusTags.Blocks.NEXUS_PORTAL_CORES);

        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(
                        NexusBlocks.WARPSLATE_ACCELERITE_ORE.get(),
                        NexusBlocks.RAW_ACCELERITE_BLOCK.get(),
                        NexusBlocks.DORMANT_ACCELERITE_BLOCK.get(),
                        NexusBlocks.CHARGED_ACCELERITE_BLOCK.get()
                );

        tag(BlockTags.DIRT)
                .add(NexusBlocks.CHRONOWARPED_DIRT.get(), NexusBlocks.CHRONOWARPED_GRASS.get());

        tag(BlockTags.SAND)
                .add(NexusBlocks.CHRONOWARPED_SAND.get());

        tag(BlockTags.SMELTS_TO_GLASS)
                .add(NexusBlocks.CHRONOWARPED_SAND.get());

        tag(Tags.Blocks.SANDS)
                .add(NexusBlocks.CHRONOWARPED_SAND.get());

        tag(Tags.Blocks.ORES)
                .add(NexusBlocks.WARPSLATE_ACCELERITE_ORE.get());

        tag(Tags.Blocks.STORAGE_BLOCKS)
                .add(
                        NexusBlocks.RAW_ACCELERITE_BLOCK.get(),
                        NexusBlocks.DORMANT_ACCELERITE_BLOCK.get(),
                        NexusBlocks.CHARGED_ACCELERITE_BLOCK.get()
                );

        tag(BlockTags.SCULK_REPLACEABLE)
                .add(NexusBlocks.CHRONOWARPED_SAND.get());

        tag(BlockTags.SNIFFER_DIGGABLE_BLOCK)
                .add(NexusBlocks.CHRONOWARPED_DIRT.get(), NexusBlocks.CHRONOWARPED_GRASS.get());

        tag(BlockTags.ANIMALS_SPAWNABLE_ON)
                .add(NexusBlocks.CHRONOWARPED_GRASS.get());

        tag(Tags.Blocks.COBBLESTONES_DEEPSLATE)
                .add(NexusBlocks.COBBLED_WARPSLATE.get());

        tag(BlockTags.SNAPS_GOAT_HORN)
                .add(NexusBlocks.WARPBLOSSOM_LOG.get());

        tag(BlockTags.LEAVES)
                .add(NexusBlocks.WARPBLOSSOM_LEAVES.get());

        tag(BlockTags.FLOWERS)
                .add(NexusBlocks.PURPLE_PETALS.get());

        tag(BlockTags.SAPLINGS)
                .add(NexusBlocks.WARPBLOSSOM_SAPLING.get());

        tag(BlockTags.INSIDE_STEP_SOUND_BLOCKS)
                .add(NexusBlocks.PURPLE_PETALS.get(), NexusBlocks.SMALL_CHRONO_BUD.get());

        tag(BlockTags.MINEABLE_WITH_HOE)
                .add(NexusBlocks.PURPLE_PETALS.get());

        tag(BlockTags.CRYSTAL_SOUND_BLOCKS)
                .add(NexusBlocks.CHRONO_BLOCK.get(), NexusBlocks.BUDDING_CHRONO.get());

        tag(Tags.Blocks.BUDDING_BLOCKS)
                .add(NexusBlocks.BUDDING_CHRONO.get());

        tag(Tags.Blocks.BUDS)
                .add(NexusBlocks.SMALL_CHRONO_BUD.get(), NexusBlocks.MEDIUM_CHRONO_BUD.get(), NexusBlocks.LARGE_CHRONO_BUD.get());

        tag(Tags.Blocks.CLUSTERS)
                .add(NexusBlocks.CHRONO_CLUSTER.get());

        tag(BlockTags.VIBRATION_RESONATORS)
                .add(NexusBlocks.CHRONO_BLOCK.get());
    }
}
