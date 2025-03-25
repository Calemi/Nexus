package com.calemi.nexus.event.listener;

import com.calemi.nexus.regsitry.NexusBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.BlockEntityTypeAddBlocksEvent;

public class BlockEntityTypeAddBlocksEventListener {

    @SubscribeEvent
    public static void addBlockEntityTypes(BlockEntityTypeAddBlocksEvent event) {

        event.modify(BlockEntityType.SIGN,
                NexusBlocks.WARPBLOSSOM_SIGN.get(), NexusBlocks.WARPBLOSSOM_WALL_SIGN.get());

        event.modify(BlockEntityType.HANGING_SIGN,
                NexusBlocks.WARPBLOSSOM_HANGING_SIGN.get(), NexusBlocks.WARPBLOSSOM_WALL_HANGING_SIGN.get());
    }
}
