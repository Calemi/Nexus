package com.calemi.nexus.client.render;

import com.calemi.nexus.config.NexusConfig;
import com.calemi.nexus.packet.NexusPortalCoreHUDOverlayPayload;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.neoforge.network.PacketDistributor;

public class RenderNexusPortalCoreHUDOverlay {

    @SubscribeEvent
    public void playerTickEvent(PlayerTickEvent.Post event) {

        Player player = event.getEntity();

        if (!player.level().isClientSide()) return;

        if (player.level().getGameTime() % 10 != 0) return;

        if (!NexusConfig.client.portalCoreHUDOverlay.get()) return;

        PacketDistributor.sendToServer(new NexusPortalCoreHUDOverlayPayload());
    }
}
