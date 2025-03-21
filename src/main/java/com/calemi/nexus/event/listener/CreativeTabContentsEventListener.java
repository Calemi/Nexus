package com.calemi.nexus.event.listener;

import com.calemi.nexus.regsitry.NexusBlocks;
import com.calemi.nexus.regsitry.NexusItems;
import com.calemi.nexus.regsitry.NexusLists;
import net.minecraft.world.item.*;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

import java.util.ArrayList;
import java.util.List;

public class CreativeTabContentsEventListener {

    @SubscribeEvent
    public void onBuildCreativeModeTabContentsEvent(final BuildCreativeModeTabContentsEvent event) {

        CreativeModeTab tab = event.getTab();

        if (event.getTabKey() == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
            add(event, Items.END_PORTAL_FRAME, NexusLists.toItemArray(NexusLists.NEXUS_PORTAL_CORE_BLOCKS));
        }

        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            add(event, Items.AMETHYST_SHARD,
                    NexusItems.CHRONO_SHARD.get());
            add(event, Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE,
                    NexusItems.CHRONO_UPGRADE_SMITHING_TEMPLATE.get());
        }

        if (event.getTabKey() == CreativeModeTabs.NATURAL_BLOCKS) {
            add(event, Items.MYCELIUM,
                    NexusBlocks.CHRONOWARPED_GRASS.get().asItem());
            add(event, Items.MUD,
                    NexusBlocks.CHRONOWARPED_DIRT.get().asItem());
            add(event, Items.RED_SAND,
                    NexusBlocks.CHRONOWARPED_SAND.get().asItem());
            add(event, Items.DEEPSLATE,
                    NexusBlocks.WARPSLATE.get().asItem());
            add(event, Items.WARPED_STEM,
                    NexusBlocks.WARPBLOSSOM_LOG.get().asItem());
            add(event, Items.CHERRY_LEAVES,
                    NexusBlocks.WARPBLOSSOM_LEAVES.get().asItem());
            add(event, Items.CHERRY_SAPLING,
                    NexusBlocks.WARPBLOSSOM_SAPLING.get().asItem());
        }

        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {

            List<Item> entries = new ArrayList<>();

            entries.addAll(NexusLists.toItemListFromDefBlock(NexusLists.COBBLED_WARPSLATE_BLOCKSET.getAll()));
            entries.add(NexusBlocks.CHISELED_WARPSLATE.asItem());
            entries.addAll(NexusLists.toItemListFromDefBlock(NexusLists.POLISHED_WARPSLATE_BLOCKSET.getAll()));
            entries.addAll(NexusLists.toItemListFromDefBlock(NexusLists.WARPSLATE_TILE_BLOCKSET.getAll()));

            add(event, Items.REINFORCED_DEEPSLATE, entries);

            add(event, Items.WARPED_BUTTON, NexusLists.toItemListFromDefBlock(NexusLists.CHRONOWARPED_LOGS));
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