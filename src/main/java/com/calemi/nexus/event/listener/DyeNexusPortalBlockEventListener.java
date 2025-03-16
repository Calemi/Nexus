package com.calemi.nexus.event.listener;

import com.calemi.ccore.api.location.Location;
import com.calemi.ccore.api.scanner2.VeinBlockScanner2;
import com.calemi.nexus.block.NexusPortalBlock;
import net.minecraft.core.BlockPos;
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
            BlockPos pos = event.getPos();
            BlockState state = level.getBlockState(pos);

            if (!(state.getBlock() instanceof NexusPortalBlock)) {
                return;
            }

            Player player = event.getPlayer();

            if (player == null) return;

            ItemStack stack = event.getItemStack();

            if (!(stack.getItem() instanceof DyeItem dyeItem)) return;

            Block dyedPortalBlock = NexusPortalBlock.fromDye(dyeItem.getDyeColor());

            if (dyedPortalBlock != null) {

                if (player.isCrouching()) {
                    dyePortalBlock(dyedPortalBlock, pos, state, level);
                }

                else {
                    VeinBlockScanner2 scanner = new VeinBlockScanner2(new Location(level, pos), 1000);
                    scanner.start();
                    scanner.collectedLocations.forEach(location -> dyePortalBlock(dyedPortalBlock, location.getBlockPos(), state, level));
                }

                if (!player.isCreative()) stack.shrink(1);

                event.cancelWithResult(ItemInteractionResult.SUCCESS);
            }
        }
    }

    private void dyePortalBlock(Block dyedPortalBlock, BlockPos pos, BlockState originalState, Level level) {
        level.setBlock(pos, dyedPortalBlock.defaultBlockState().setValue(NexusPortalBlock.AXIS, originalState.getValue(NexusPortalBlock.AXIS)), 1 | 2);
    }
}
