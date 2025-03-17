package com.calemi.nexus.event.listener;

import com.calemi.nexus.regsitry.NexusBlocks;
import com.calemi.nexus.regsitry.NexusItems;
import net.minecraft.world.item.*;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

public class BuildCreativeModeTabContentsEventListener {

    @SubscribeEvent
    public void onBuildCreativeModeTabContentsEvent(final BuildCreativeModeTabContentsEvent event) {

        CreativeModeTab tab = event.getTab();

        if (event.getTabKey() == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
            add(event, Items.END_PORTAL_FRAME,
                    NexusBlocks.NEXUS_PORTAL_CORE.get().asItem(),
                    NexusBlocks.IRON_NEXUS_PORTAL_CORE.get().asItem(),
                    NexusBlocks.GOLD_NEXUS_PORTAL_CORE.get().asItem(),
                    NexusBlocks.DIAMOND_NEXUS_PORTAL_CORE.get().asItem(),
                    NexusBlocks.NETHERITE_NEXUS_PORTAL_CORE.get().asItem(),
                    NexusBlocks.STARLIGHT_NEXUS_PORTAL_CORE.get().asItem()
            );
        }

        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            add(event, Items.AMETHYST_SHARD,
                    NexusItems.CHRONO_SHARD.get()
            );
            add(event, Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE,
                    NexusItems.CHRONO_UPGRADE_SMITHING_TEMPLATE.get());
        }
    }

    private void add(BuildCreativeModeTabContentsEvent event, Item item, Item... itemsToAdd) {

        for (int i = 0; i < itemsToAdd.length; i++) {
            Item itemToAdd = itemsToAdd[i];
            event.insertAfter(new ItemStack(i == 0 ? item : itemsToAdd[i - 1]), new ItemStack(itemToAdd), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
    }
}