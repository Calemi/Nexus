package com.calemi.nexus.util;

import com.calemi.nexus.world.dimension.NexusDimensionHelper;
import com.calemi.nexus.world.dimension.NexusDimensions;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;

public class HoleTeleportAction {

    @SubscribeEvent
    public void onPlayerTick(EntityTickEvent.Post event) {

        Entity entity = event.getEntity();
        Level level = entity.level();

        if (!level.isClientSide() && NexusDimensionHelper.isInNexus(entity)) {

            int nexusMinY = level.getServer().getLevel(NexusDimensions.NEXUS_LEVEL).getMinBuildHeight();
            int overworldHeight = level.getServer().getLevel(Level.OVERWORLD).getMaxBuildHeight();

            if (entity instanceof Player player) {
                if (player.isCreative() || player.isSpectator()) return;
            }

            if (entity.getBlockY() <= nexusMinY - 5) {

                TeleportHelper.teleportToWorld(entity, entity.blockPosition().offset(0, overworldHeight + 128, 0), Level.OVERWORLD);
            }
        }
    }
}
