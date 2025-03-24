package com.calemi.nexus.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.SimpleParticleType;

public class ColoredPortalParticleProvider implements ParticleProvider<SimpleParticleType> {

    private final SpriteSet spriteSet;
    private final float red;
    private final float green;
    private final float blue;

    public ColoredPortalParticleProvider(SpriteSet spriteSet, float red, float green, float blue) {
        this.spriteSet = spriteSet;
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    @Override
    public Particle createParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
        return new ColoredPortalParticle(level, spriteSet, red, green, blue, x, y, z, xSpeed, ySpeed, zSpeed);
    }
}