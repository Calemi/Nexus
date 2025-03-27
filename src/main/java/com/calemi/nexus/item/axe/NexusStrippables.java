package com.calemi.nexus.item.axe;

import com.calemi.nexus.block.NexusBlocks;
import com.calemi.nexus.main.Nexus;
import com.google.common.collect.Maps;
import net.minecraft.world.item.AxeItem;

public class NexusStrippables {

    public static void init() {

        Nexus.LOGGER.info("Registering: Strippables - Start");

        AxeItem.STRIPPABLES = Maps.newHashMap(AxeItem.STRIPPABLES);
        AxeItem.STRIPPABLES.put(NexusBlocks.WARPBLOSSOM_LOG.get(), NexusBlocks.STRIPPED_WARPBLOSSOM_LOG.get());
        AxeItem.STRIPPABLES.put(NexusBlocks.WARPBLOSSOM_WOOD.get(), NexusBlocks.STRIPPED_WARPBLOSSOM_WOOD.get());

        Nexus.LOGGER.info("Registering: Strippables - End");
    }
}
