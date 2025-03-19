package com.calemi.nexus.event.listener;

import com.calemi.ccore.api.location.Location;
import com.calemi.nexus.block.NexusPortalBlock;
import com.calemi.nexus.blockentity.NexusPortalBlockEntity;
import com.calemi.nexus.blockentity.NexusPortalCoreBlockEntity;
import com.calemi.nexus.main.Nexus;
import com.calemi.nexus.scanner.PortalScanner;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.UseItemOnBlockEvent;

public class DyeNexusPortalBlockEventListener {

    @SubscribeEvent
    public void useItemOnBlockEvent(UseItemOnBlockEvent event) {

        if (event.getUsePhase() == UseItemOnBlockEvent.UsePhase.ITEM_BEFORE_BLOCK) {

            Level level = event.getLevel();

            if (event.getLevel().isClientSide()) return;

            BlockPos pos = event.getPos();
            BlockState state = level.getBlockState(pos);

            if (!(state.getBlock() instanceof NexusPortalBlock)) return;

            Player player = event.getPlayer();

            if (player == null) return;

            ItemStack stack = event.getItemStack();

            if (!(stack.getItem() instanceof DyeItem dyeItem)) return;

            Block dyedPortalBlock = NexusPortalBlock.fromDye(dyeItem.getDyeColor());

            if (dyedPortalBlock != null && !state.getBlock().equals(dyedPortalBlock)) {

                if (player.isCrouching()) {
                    dyePortalBlock(dyedPortalBlock, pos, state, level);
                }

                else {
                    PortalScanner scanner = new PortalScanner(new Location(level, pos), state.getValue(NexusPortalBlock.AXIS), false,1000);
                    scanner.start();
                    scanner.getCollectedPositions().forEach(blockPos -> dyePortalBlock(dyedPortalBlock, blockPos, state, level));
                }

                if (!player.isCreative()) stack.shrink(1);

                event.cancelWithResult(ItemInteractionResult.SUCCESS);
            }

            if (!(level.getBlockEntity(pos) instanceof NexusPortalBlockEntity portalBlockEntity)) return;

            if (!(level.getBlockEntity(portalBlockEntity.getCorePosition()) instanceof NexusPortalCoreBlockEntity portalCoreBlockEntity)) return;

            portalCoreBlockEntity.savePortalColorPattern(portalCoreBlockEntity.getPortalPositions());

            Nexus.LOGGER.debug("SAVING PATTERN");
        }
    }

    private void dyePortalBlock(Block dyedPortalBlock, BlockPos pos, BlockState originalState, Level level) {
        CompoundTag tag = level.getBlockEntity(pos).saveWithoutMetadata(level.registryAccess());
        level.setBlock(pos, dyedPortalBlock.defaultBlockState().setValue(NexusPortalBlock.AXIS, originalState.getValue(NexusPortalBlock.AXIS)), 1 | 2);
        level.getBlockEntity(pos).loadCustomOnly(tag, level.registryAccess());
    }
}
