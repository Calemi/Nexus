package com.calemi.nexus.regsitry;

import com.calemi.nexus.main.Nexus;
import com.calemi.nexus.main.NexusRef;
import com.calemi.nexus.particle.ColoredPortalParticleProvider;
import com.calemi.nexus.particle.WarpblossomParticleProvider;
import net.minecraft.client.particle.CherryParticle;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class NexusParticles {

    private static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(Registries.PARTICLE_TYPE, NexusRef.ID);

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> WHITE_PORTAL_PARTICLES = PARTICLE_TYPES.register("white_portal", () -> new SimpleParticleType(false));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> ORANGE_PORTAL_PARTICLES = PARTICLE_TYPES.register("orange_portal", () -> new SimpleParticleType(false));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> YELLOW_PORTAL_PARTICLES = PARTICLE_TYPES.register("yellow_portal", () -> new SimpleParticleType(false));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> PURPLE_PORTAL_PARTICLES = PARTICLE_TYPES.register("purple_portal", () -> new SimpleParticleType(false));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> CYAN_PORTAL_PARTICLES = PARTICLE_TYPES.register("cyan_portal", () -> new SimpleParticleType(false));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> LIGHT_GRAY_PORTAL_PARTICLES = PARTICLE_TYPES.register("light_gray_portal", () -> new SimpleParticleType(false));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> GRAY_PORTAL_PARTICLES = PARTICLE_TYPES.register("gray_portal", () -> new SimpleParticleType(false));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> PINK_PORTAL_PARTICLES = PARTICLE_TYPES.register("pink_portal", () -> new SimpleParticleType(false));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> LIME_PORTAL_PARTICLES = PARTICLE_TYPES.register("lime_portal", () -> new SimpleParticleType(false));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> LIGHT_BLUE_PORTAL_PARTICLES = PARTICLE_TYPES.register("light_blue_portal", () -> new SimpleParticleType(false));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> MAGENTA_PORTAL_PARTICLES = PARTICLE_TYPES.register("magenta_portal", () -> new SimpleParticleType(false));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> GREEN_PORTAL_PARTICLES = PARTICLE_TYPES.register("green_portal", () -> new SimpleParticleType(false));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> BLUE_PORTAL_PARTICLES = PARTICLE_TYPES.register("blue_portal", () -> new SimpleParticleType(false));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> RED_PORTAL_PARTICLES = PARTICLE_TYPES.register("red_portal", () -> new SimpleParticleType(false));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> BROWN_PORTAL_PARTICLES = PARTICLE_TYPES.register("brown_portal", () -> new SimpleParticleType(false));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> BLACK_PORTAL_PARTICLES = PARTICLE_TYPES.register("black_portal", () -> new SimpleParticleType(false));

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> WARPBLOSSOM_PARTICLES = PARTICLE_TYPES.register("warpblossom", () -> new SimpleParticleType(false));

    @SubscribeEvent
    public static void registerParticleProviders(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(NexusParticles.WHITE_PORTAL_PARTICLES.get(),      sprites -> new ColoredPortalParticleProvider(sprites, 1.0F, 1.0F, 1.0F));
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

    public static void init() {
        Nexus.LOGGER.info("Registering: Particles Types - Start");
        PARTICLE_TYPES.register(Nexus.MOD_EVENT_BUS);
        Nexus.LOGGER.info("Registering: Particles Types - End");
    }
}
