package com.calemi.nexus.client;

import com.calemi.ccore.api.list.ListHelper;
import com.calemi.ccore.api.string.StringHelper;
import com.calemi.nexus.client.partclie.ColoredPortalParticleProvider;
import com.calemi.nexus.client.partclie.NexusParticles;
import com.calemi.nexus.client.partclie.WarpblossomParticleProvider;
import com.calemi.nexus.main.Nexus;
import com.calemi.nexus.main.NexusRef;
import com.calemi.nexus.block.NexusBlocks;
import com.calemi.nexus.util.NexusLists;
import com.calemi.nexus.client.color.CamoBlockColor;
import com.calemi.nexus.client.model.NexusPortalCoreBakedModel;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.world.level.GrassColor;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ModelEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;

@EventBusSubscriber(modid = NexusRef.ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class NexusClient {

    @SubscribeEvent
    public static void onBlockColorHandlersRegistry(RegisterColorHandlersEvent.Block event) {

        Nexus.LOGGER.info("Registering: Block Colors - Start");

        event.register(new CamoBlockColor(), ListHelper.toBlockArray(NexusLists.NEXUS_PORTAL_CORE_BLOCKS));

        event.register((state, blockAndTintGetter, pos, tintIndex) -> {
            if (tintIndex != 0) return blockAndTintGetter != null && pos != null ? BiomeColors.getAverageGrassColor(blockAndTintGetter, pos) : GrassColor.getDefaultColor();
            else return -1;
        }, NexusBlocks.PURPLE_PETALS.get());

        Nexus.LOGGER.info("Registering: Block Colors - End");
    }

    @SubscribeEvent
    public static void onModelBake(ModelEvent.ModifyBakingResult event) {

        Nexus.LOGGER.info("Registering: Block Models - Start");

        event.getModels().entrySet().stream()
                .filter(entry -> entry.getKey().id().getNamespace().equals(NexusRef.ID) && entry.getKey().id().getPath().contains("nexus_portal_core"))
                .forEach(entry -> event.getModels().put(entry.getKey(), new NexusPortalCoreBakedModel(entry.getValue())));

        Nexus.LOGGER.info("Registering: Block Models - End");
    }

    @SubscribeEvent
    public static void registerParticleProviders(RegisterParticleProvidersEvent event) {

        event.registerSpriteSet(NexusParticles.WHITE_PORTAL_PARTICLES.get(), sprites -> new ColoredPortalParticleProvider(sprites, 1.0F, 1.0F, 1.0F));
        event.registerSpriteSet(NexusParticles.ORANGE_PORTAL_PARTICLES.get(),     sprites -> new ColoredPortalParticleProvider(sprites, 1.0F, 0.5F, 0.0F));
        event.registerSpriteSet(NexusParticles.YELLOW_PORTAL_PARTICLES.get(),     sprites -> new ColoredPortalParticleProvider(sprites, 1.0F, 1.0F, 0.0F));
        event.registerSpriteSet(NexusParticles.PURPLE_PORTAL_PARTICLES.get(),     sprites -> new ColoredPortalParticleProvider(sprites, 0.5F, 0.0F, 1.0F));
        event.registerSpriteSet(NexusParticles.CYAN_PORTAL_PARTICLES.get(),       sprites -> new ColoredPortalParticleProvider(sprites, 0.0F, 1.0F, 1.0F));
        event.registerSpriteSet(NexusParticles.LIGHT_GRAY_PORTAL_PARTICLES.get(), sprites -> new ColoredPortalParticleProvider(sprites, 0.75F, 0.75F, 0.75F));
        event.registerSpriteSet(NexusParticles.GRAY_PORTAL_PARTICLES.get(),       sprites -> new ColoredPortalParticleProvider(sprites, 0.5F, 0.5F, 0.5F));
        event.registerSpriteSet(NexusParticles.PINK_PORTAL_PARTICLES.get(),       sprites -> new ColoredPortalParticleProvider(sprites, 1.0F, 0.5F, 1.0F));
        event.registerSpriteSet(NexusParticles.LIME_PORTAL_PARTICLES.get(),       sprites -> new ColoredPortalParticleProvider(sprites, 0.5F, 1.0F, 0.5F));
        event.registerSpriteSet(NexusParticles.LIGHT_BLUE_PORTAL_PARTICLES.get(), sprites -> new ColoredPortalParticleProvider(sprites, 0.4F, 0.4F, 0.9F));
        event.registerSpriteSet(NexusParticles.MAGENTA_PORTAL_PARTICLES.get(),    sprites -> new ColoredPortalParticleProvider(sprites, 0.75F, 0.5F, 1.0F));
        event.registerSpriteSet(NexusParticles.GREEN_PORTAL_PARTICLES.get(),      sprites -> new ColoredPortalParticleProvider(sprites, 0.0F, 0.5F, 0.0F));
        event.registerSpriteSet(NexusParticles.BLUE_PORTAL_PARTICLES.get(),       sprites -> new ColoredPortalParticleProvider(sprites, 0.25F, 0.25F, 1.0F));
        event.registerSpriteSet(NexusParticles.RED_PORTAL_PARTICLES.get(),        sprites -> new ColoredPortalParticleProvider(sprites, 1.0F, 0.0F, 0.0F));
        event.registerSpriteSet(NexusParticles.BROWN_PORTAL_PARTICLES.get(),      sprites -> new ColoredPortalParticleProvider(sprites, 0.5F, 0.25F, 0.0F));
        event.registerSpriteSet(NexusParticles.BLACK_PORTAL_PARTICLES.get(),      sprites -> new ColoredPortalParticleProvider(sprites, 0.0F, 0.0F, 0.0F));

        event.registerSpriteSet(NexusParticles.WARPBLOSSOM_PARTICLES.get(), WarpblossomParticleProvider::new);
    }
}