package com.calemi.nexus.blockentity;

import com.calemi.nexus.block.NexusBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.BlockEntityTypeAddBlocksEvent;

public class BlockEntityTypeInjector {

    @SubscribeEvent
    public static void addBlockEntityTypes(BlockEntityTypeAddBlocksEvent event) {

        event.modify(BlockEntityType.SIGN,
                NexusBlocks.WARPBLOSSOM_SIGN.get(), NexusBlocks.WARPBLOSSOM_WALL_SIGN.get());

        event.modify(BlockEntityType.HANGING_SIGN,
                NexusBlocks.WARPBLOSSOM_HANGING_SIGN.get(), NexusBlocks.WARPBLOSSOM_WALL_HANGING_SIGN.get());
    }
}
