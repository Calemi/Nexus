package com.calemi.nexus.main;

import com.calemi.ccore.api.init.CEntities;
import com.calemi.nexus.event.listener.*;
import com.calemi.nexus.packet.*;
import com.calemi.nexus.regsitry.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(NexusRef.ID)
public class Nexus {

    public static final IEventBus FORGE_EVENT_BUS = NeoForge.EVENT_BUS;
    public static IEventBus MOD_EVENT_BUS;
    public static ModContainer MOD_CONTAINER;

    public static final Logger LOGGER = LogManager.getLogger(NexusRef.NAME);

    public Nexus(IEventBus modEventBus, ModContainer modContainer) {

        LOGGER.info("Registering: Main - Start");

        MOD_EVENT_BUS = modEventBus;
        MOD_CONTAINER = modContainer;

        NexusItems.init();
        NexusBlocks.init();
        NexusBlockEntities.init();
        NexusCreativeModeTabs.init();
        NexusLootModifiers.init();
        NexusLootItemConditions.init();
        NexusAttachments.init();
        NexusParticles.init();
        NexusFoliagePlacers.init();

        //TODO: MOVE TO CCORE
        CEntities.init();

        MOD_EVENT_BUS.addListener(this::commonSetup);
        MOD_EVENT_BUS.addListener(this::clientSetup);
        MOD_EVENT_BUS.addListener(this::registerPackets);

        MOD_EVENT_BUS.addListener(NexusParticles::registerParticleProviders);
        MOD_EVENT_BUS.addListener(BlockEntityTypeAddBlocksEventListener::addBlockEntityTypes);

        LOGGER.info("Registering: Main - End");
    }

    public void commonSetup(final FMLCommonSetupEvent event) {

        LOGGER.info("Registering: Common - Start");

        NexusStrippables.init();

        MOD_EVENT_BUS.register(new CreativeTabContentsEventListener());
        FORGE_EVENT_BUS.register(new NexusPortalCoreHUDOverlayEventListener());
        FORGE_EVENT_BUS.register(new DyeNexusPortalBlockEventListener());

        LOGGER.info("Registering: Common - End");
    }

    public void clientSetup(final FMLClientSetupEvent event) {

        LOGGER.info("Registering: Client - Start");

        FORGE_EVENT_BUS.register(new NexusPortalCoreWorldOverlayEventListener());

        LOGGER.info("Registering: Client - End");
    }

    public void registerPackets(RegisterPayloadHandlersEvent event) {

        LOGGER.info("Registering: Packets - Start");

        final PayloadRegistrar registrar = event.registrar(NexusRef.ID);

        registrar.playToClient(UnlockedDimensionsListSyncPayload.TYPE, UnlockedDimensionsListSyncPayload.CODEC, UnlockedDimensionsListSyncPayload::handle);
        registrar.playToServer(NexusPortalCoreDestinationDimensionSyncPayload.TYPE, NexusPortalCoreDestinationDimensionSyncPayload.CODEC, NexusPortalCoreDestinationDimensionSyncPayload::handle);
        registrar.playToServer(NexusPortalCoreTeleportPayload.TYPE, NexusPortalCoreTeleportPayload.CODEC, NexusPortalCoreTeleportPayload::handle);
        registrar.playToServer(NexusPortalCoreGenerateLinkPayload.TYPE, NexusPortalCoreGenerateLinkPayload.CODEC, NexusPortalCoreGenerateLinkPayload::handle);
        registrar.playToServer(NexusPortalCoreFindLinkPayload.TYPE, NexusPortalCoreFindLinkPayload.CODEC, NexusPortalCoreFindLinkPayload::handle);
        registrar.playToServer(NexusPortalCoreLightPortalPayload.TYPE, NexusPortalCoreLightPortalPayload.CODEC, NexusPortalCoreLightPortalPayload::handle);
        registrar.playToServer(NexusPortalCoreUnlinkPayload.TYPE, NexusPortalCoreUnlinkPayload.CODEC, NexusPortalCoreUnlinkPayload::handle);
        registrar.playBidirectional(NexusPortalCoreDestinationNameSyncPayload.TYPE, NexusPortalCoreDestinationNameSyncPayload.CODEC, NexusPortalCoreDestinationNameSyncPayload::handle);

        LOGGER.info("Registering: Packets - End");
    }
}