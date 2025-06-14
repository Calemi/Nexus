package com.calemi.nexus.client.partclie;

import com.calemi.nexus.main.Nexus;
import com.calemi.nexus.main.NexusRef;
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

    public static void init() {
        Nexus.LOGGER.info("Registering: Particles Types - Start");
        PARTICLE_TYPES.register(Nexus.MOD_EVENT_BUS);
        Nexus.LOGGER.info("Registering: Particles Types - End");
    }
}
