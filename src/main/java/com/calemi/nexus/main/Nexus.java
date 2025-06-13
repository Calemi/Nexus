package com.calemi.nexus.main;

import com.calemi.nexus.block.DyeNexusPortalBlockAction;
import com.calemi.nexus.block.NexusBlocks;
import com.calemi.nexus.block.entity.BlockEntityTypeInjector;
import com.calemi.nexus.block.entity.NexusBlockEntities;
import com.calemi.nexus.capability.NexusAttachments;
import com.calemi.nexus.client.partclie.NexusParticles;
import com.calemi.nexus.client.render.RenderNexusPortalCoreHUDOverlay;
import com.calemi.nexus.client.render.RenderNexusPortalCoreWorldOverlay;
import com.calemi.nexus.compat.NexusCreateCompatibility;
import com.calemi.nexus.config.NexusConfig;
import com.calemi.nexus.effect.NexusMobEffects;
import com.calemi.nexus.entity.NexusSheepSpawnColor;
import com.calemi.nexus.item.*;
import com.calemi.nexus.item.axe.NexusStrippables;
import com.calemi.nexus.item.property.NexusItemProperties;
import com.calemi.nexus.packet.NexusPackets;
import com.calemi.nexus.tab.CreativeTabInjector;
import com.calemi.nexus.tab.NexusCreativeModeTabs;
import com.calemi.nexus.entity.HoleTeleportAction;
import com.calemi.nexus.world.feature.NexusFeatures;
import com.calemi.nexus.world.feature.tree.NexusFoliagePlacers;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModList;
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
        NexusArmorMaterials.init();
        NexusBlocks.init();
        NexusBlockEntities.init();
        NexusCreativeModeTabs.init();
        NexusMobEffects.init();
        NexusAttachments.init();
        NexusParticles.init();
        NexusFeatures.init();
        NexusFoliagePlacers.init();

        MOD_EVENT_BUS.addListener(this::commonSetup);
        MOD_EVENT_BUS.addListener(this::clientSetup);

        MOD_EVENT_BUS.addListener(NexusParticles::registerParticleProviders);
        MOD_EVENT_BUS.addListener(BlockEntityTypeInjector::addBlockEntityTypes);

        LOGGER.info("Registering: Main - End");
    }

    public void commonSetup(final FMLCommonSetupEvent event) {

        LOGGER.info("Registering: Common - Start");

        NexusItemProperties.init();
        NexusStrippables.init();

        MOD_EVENT_BUS.register(new NexusPackets());
        MOD_EVENT_BUS.register(new CreativeTabInjector());
        FORGE_EVENT_BUS.register(new RenderNexusPortalCoreHUDOverlay());
        FORGE_EVENT_BUS.register(new DyeNexusPortalBlockAction());
        FORGE_EVENT_BUS.register(new HoleTeleportAction());
        FORGE_EVENT_BUS.register(new ItemSpeedChargeAction());
        FORGE_EVENT_BUS.register(new ItemAccelerationEffectAction());
        FORGE_EVENT_BUS.register(new TotemOfWarpingImmunityAction());
        FORGE_EVENT_BUS.register(new FallbreakersImmunityAction());
        FORGE_EVENT_BUS.register(new NexusSheepSpawnColor());

        if (ModList.get().isLoaded("create")) {
            NexusCreateCompatibility.init();
        }

        LOGGER.info("Registering: Common - End");
    }

    public void clientSetup(final FMLClientSetupEvent event) {

        LOGGER.info("Registering: Client - Start");

        FORGE_EVENT_BUS.register(new RenderNexusPortalCoreWorldOverlay());
        FORGE_EVENT_BUS.register(new FallbreakersTooltip());

        LOGGER.info("Registering: Client - End");
    }
}