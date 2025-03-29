package com.calemi.nexus.main;

import com.calemi.ccore.api.init.CEntities;
import com.calemi.nexus.block.DyeNexusPortalBlockAction;
import com.calemi.nexus.block.NexusBlocks;
import com.calemi.nexus.blockentity.BlockEntityTypeInjector;
import com.calemi.nexus.blockentity.NexusBlockEntities;
import com.calemi.nexus.capability.NexusAttachments;
import com.calemi.nexus.client.partclie.NexusParticles;
import com.calemi.nexus.client.render.RenderNexusPortalCoreHUDOverlay;
import com.calemi.nexus.client.render.RenderNexusPortalCoreWorldOverlay;
import com.calemi.nexus.config.NexusConfig;
import com.calemi.nexus.item.NexusItems;
import com.calemi.nexus.item.axe.NexusStrippables;
import com.calemi.nexus.loot.condition.NexusLootItemConditions;
import com.calemi.nexus.loot.modifier.NexusLootModifiers;
import com.calemi.nexus.packet.NexusPackets;
import com.calemi.nexus.tab.CreativeTabInjector;
import com.calemi.nexus.tab.NexusCreativeModeTabs;
import com.calemi.nexus.util.HoleTeleportAction;
import com.calemi.nexus.world.feature.NexusFeatures;
import com.calemi.nexus.world.feature.tree.NexusFoliagePlacers;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.common.NeoForge;
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

        NexusConfig.init();
        modContainer.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);

        NexusItems.init();
        NexusBlocks.init();
        NexusBlockEntities.init();
        NexusCreativeModeTabs.init();
        NexusLootModifiers.init();
        NexusLootItemConditions.init();
        NexusAttachments.init();
        NexusParticles.init();
        NexusFeatures.init();
        NexusFoliagePlacers.init();

        //TODO: MOVE TO CCORE
        CEntities.init();

        MOD_EVENT_BUS.addListener(this::commonSetup);
        MOD_EVENT_BUS.addListener(this::clientSetup);

        MOD_EVENT_BUS.addListener(NexusParticles::registerParticleProviders);
        MOD_EVENT_BUS.addListener(BlockEntityTypeInjector::addBlockEntityTypes);

        LOGGER.info("Registering: Main - End");
    }

    public void commonSetup(final FMLCommonSetupEvent event) {

        LOGGER.info("Registering: Common - Start");

        NexusStrippables.init();

        MOD_EVENT_BUS.register(new NexusPackets());
        MOD_EVENT_BUS.register(new CreativeTabInjector());
        FORGE_EVENT_BUS.register(new RenderNexusPortalCoreHUDOverlay());
        FORGE_EVENT_BUS.register(new DyeNexusPortalBlockAction());
        FORGE_EVENT_BUS.register(new HoleTeleportAction());

        LOGGER.info("Registering: Common - End");
    }

    public void clientSetup(final FMLClientSetupEvent event) {

        LOGGER.info("Registering: Client - Start");

        FORGE_EVENT_BUS.register(new RenderNexusPortalCoreWorldOverlay());

        LOGGER.info("Registering: Client - End");
    }
}