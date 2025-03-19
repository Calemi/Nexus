package com.calemi.nexus.event.listener;

import com.calemi.ccore.api.message.OverlayMessageHelper;
import com.calemi.nexus.block.NexusPortalCoreBlock;
import com.calemi.nexus.util.NexusDimensionHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

public class NexusPortalCoreHUDOverlayEventListener {

    @SubscribeEvent
    public void playerTickEvent(PlayerTickEvent.Post event) {

        Player player = event.getEntity();

        if (!player.level().isClientSide() && player instanceof ServerPlayer serverPlayer && player.level() instanceof ServerLevel serverLevel) {

            if (serverPlayer.getMainHandItem().getItem() instanceof BlockItem blockItem && blockItem.getBlock() instanceof NexusPortalCoreBlock originBlock) {

                BlockPos destinationPosition = NexusDimensionHelper.getDynamicBlockDestination(serverPlayer.level(), player.blockPosition(), originBlock.getCoordinateScale());

                OverlayMessageHelper.displayMsg(Component.literal("Calculated Destination: [x").append(destinationPosition.getX() + " z" + destinationPosition.getZ() + "]"),  serverPlayer);
            }
        }
    }
}
