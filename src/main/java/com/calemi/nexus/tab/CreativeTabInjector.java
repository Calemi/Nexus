package com.calemi.nexus.tab;

import com.calemi.nexus.block.family.NexusBlockFamilies;
import com.calemi.nexus.block.NexusBlocks;
import com.calemi.nexus.item.NexusItems;
import com.calemi.nexus.util.NexusLists;
import net.minecraft.world.item.*;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

import java.util.ArrayList;
import java.util.List;

public class CreativeTabInjector {

    @SubscribeEvent
    public void onBuildCreativeModeTabContentsEvent(final BuildCreativeModeTabContentsEvent event) {

        CreativeModeTab tab = event.getTab();

        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {

            List<Item> entries = new ArrayList<>();

            entries.addAll(NexusBlockFamilies.COBBLED_WARPSLATE.getAllTabItems());
            entries.addAll(NexusBlockFamilies.POLISHED_WARPSLATE.getAllTabItems());
            entries.addAll(NexusBlockFamilies.WARPSLATE_TILE.getAllTabItems());
            entries.addAll(NexusBlockFamilies.POLISHED_CALCITE.getAllTabItems());
            entries.addAll(NexusBlockFamilies.CALCITE_BRICKS.getAllTabItems());
            entries.addAll(NexusBlockFamilies.CALCITE_TILES.getAllTabItems());

            add(event, Items.REINFORCED_DEEPSLATE, entries);

            add(event, Items.WARPED_BUTTON, NexusLists.toItemListFromBlock(NexusLists.CHRONOWARPED_LOGS));
            add(event, NexusBlocks.STRIPPED_WARPBLOSSOM_WOOD.asItem(),
                    NexusBlocks.WARPBLOSSOM_PLANKS.asItem(),
                    NexusBlocks.WARPBLOSSOM_STAIRS.asItem(),
                    NexusBlocks.WARPBLOSSOM_SLAB.asItem(),
                    NexusBlocks.WARPBLOSSOM_FENCE.asItem(),
                    NexusBlocks.WARPBLOSSOM_FENCE_GATE.asItem(),
                    NexusBlocks.WARPBLOSSOM_DOOR.asItem(),
                    NexusBlocks.WARPBLOSSOM_TRAPDOOR.asItem(),
                    NexusBlocks.WARPBLOSSOM_PRESSURE_PLATE.asItem(),
                    NexusBlocks.WARPBLOSSOM_BUTTON.asItem()
            );

            add(event, Items.NETHERITE_BLOCK, NexusBlocks.DORMANT_ACCELERITE_BLOCK.asItem(), NexusBlocks.CHARGED_ACCELERITE_BLOCK.asItem());
            add(event, Items.AMETHYST_BLOCK, NexusBlocks.CHRONO_BLOCK.asItem());
        }

        if (event.getTabKey() == CreativeModeTabs.NATURAL_BLOCKS) {
            add(event, Items.MYCELIUM, NexusBlocks.CHRONOWARPED_GRASS.asItem());
            add(event, Items.MUD, NexusBlocks.CHRONOWARPED_DIRT.asItem());
            add(event, Items.RED_SAND, NexusBlocks.CHRONOWARPED_SAND.asItem());
            add(event, Items.DEEPSLATE, NexusBlocks.WARPSLATE.asItem());
            add(event, Items.WARPED_STEM, NexusBlocks.WARPBLOSSOM_LOG.asItem());
            add(event, Items.CHERRY_LEAVES, NexusBlocks.WARPBLOSSOM_LEAVES.asItem());
            add(event, Items.CHERRY_SAPLING, NexusBlocks.WARPBLOSSOM_SAPLING.asItem());
            add(event, Items.PINK_PETALS, NexusBlocks.PURPLE_PETALS.asItem());

            add(event, Items.RAW_GOLD_BLOCK, NexusBlocks.RAW_ACCELERITE_BLOCK.asItem());

            add(event, Items.AMETHYST_CLUSTER,
                    NexusBlocks.CHRONO_BLOCK.asItem(),
                    NexusBlocks.BUDDING_CHRONO.asItem(),
                    NexusBlocks.SMALL_CHRONO_BUD.asItem(),
                    NexusBlocks.MEDIUM_CHRONO_BUD.asItem(),
                    NexusBlocks.LARGE_CHRONO_BUD.asItem(),
                    NexusBlocks.CHRONO_CLUSTER.asItem()
            );
        }

        if (event.getTabKey() == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
            add(event, Items.END_PORTAL_FRAME, NexusLists.toItemArray(NexusLists.NEXUS_PORTAL_CORE_BLOCKS));

            add(event, Items.WARPED_HANGING_SIGN.asItem(),
                    NexusBlocks.WARPBLOSSOM_SIGN.asItem(),
                    NexusBlocks.WARPBLOSSOM_HANGING_SIGN.asItem()
            );
        }

        if (event.getTabKey() == CreativeModeTabs.REDSTONE_BLOCKS) {
            add(event, Items.CAULDRON, NexusLists.toItemArray(NexusLists.NEXUS_PORTAL_CORE_BLOCKS));
        }

        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            add(event, Items.CHERRY_CHEST_BOAT, NexusItems.WARPBLOSSOM_BOAT.get(), NexusItems.WARPBLOSSOM_CHEST_BOAT.get());
            add(event, Items.CLOCK, NexusItems.SPEEDOMETER.get());
        }

        if (event.getTabKey() == CreativeModeTabs.COMBAT) {
            add(event, Items.TURTLE_HELMET, NexusItems.FALLBREAKERS.get());
        }

        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            add(event, Items.AMETHYST_SHARD, NexusItems.CHRONO_SHARD.get());
            add(event, Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, NexusItems.CHRONO_UPGRADE_SMITHING_TEMPLATE.get());

            add(event, Items.RAW_GOLD, NexusItems.RAW_ACCELERITE.get());
            add(event, Items.NETHERITE_INGOT, NexusItems.DORMANT_ACCELERITE_INGOT.get(), NexusItems.CHARGED_ACCELERITE_INGOT.get());
        }
    }

    private void add(BuildCreativeModeTabContentsEvent event, Item item, Item... itemsToAdd) {

        for (int i = 0; i < itemsToAdd.length; i++) {
            Item itemToAdd = itemsToAdd[i];
            event.insertAfter(new ItemStack(i == 0 ? item : itemsToAdd[i - 1]), new ItemStack(itemToAdd), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
    }

    private void add(BuildCreativeModeTabContentsEvent event, Item item, List<Item> itemsToAdd) {
        add(event, item, itemsToAdd.toArray(Item[]::new));
    }
}