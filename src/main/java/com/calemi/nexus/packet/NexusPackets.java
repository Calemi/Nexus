package com.calemi.nexus.packet;

import com.calemi.nexus.main.Nexus;
import com.calemi.nexus.main.NexusRef;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

public class NexusPackets {

    @SubscribeEvent
    public void registerPackets(RegisterPayloadHandlersEvent event) {

        Nexus.LOGGER.info("Registering: Packets - Start");

        final PayloadRegistrar registrar = event.registrar(NexusRef.ID);

        registrar.playToClient(UnlockedDimensionsListSyncPayload.TYPE, UnlockedDimensionsListSyncPayload.CODEC, UnlockedDimensionsListSyncPayload::handle);
        registrar.playToServer(NexusPortalCoreDestinationDimensionSyncPayload.TYPE, NexusPortalCoreDestinationDimensionSyncPayload.CODEC, NexusPortalCoreDestinationDimensionSyncPayload::handle);
        registrar.playToServer(NexusPortalCoreTeleportPayload.TYPE, NexusPortalCoreTeleportPayload.CODEC, NexusPortalCoreTeleportPayload::handle);
        registrar.playToServer(NexusPortalCoreGenerateLinkPayload.TYPE, NexusPortalCoreGenerateLinkPayload.CODEC, NexusPortalCoreGenerateLinkPayload::handle);
        registrar.playToServer(NexusPortalCoreFindLinkPayload.TYPE, NexusPortalCoreFindLinkPayload.CODEC, NexusPortalCoreFindLinkPayload::handle);
        registrar.playToServer(NexusPortalCoreLightPortalPayload.TYPE, NexusPortalCoreLightPortalPayload.CODEC, NexusPortalCoreLightPortalPayload::handle);
        registrar.playToServer(NexusPortalCoreUnlinkPayload.TYPE, NexusPortalCoreUnlinkPayload.CODEC, NexusPortalCoreUnlinkPayload::handle);
        registrar.playBidirectional(NexusPortalCoreDestinationNameSyncPayload.TYPE, NexusPortalCoreDestinationNameSyncPayload.CODEC, NexusPortalCoreDestinationNameSyncPayload::handle);
        registrar.playToClient(TotemOfWarpingPayload.TYPE, TotemOfWarpingPayload.CODEC, TotemOfWarpingPayload::handle);
        registrar.playToServer(NexusPortalCoreHUDOverlayPayload.TYPE, NexusPortalCoreHUDOverlayPayload.CODEC, NexusPortalCoreHUDOverlayPayload::handle);

        Nexus.LOGGER.info("Registering: Packets - End");
    }
}
