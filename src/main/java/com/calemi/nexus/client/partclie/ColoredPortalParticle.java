package com.calemi.nexus.client.partclie;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.PortalParticle;
import net.minecraft.client.particle.SpriteSet;

public class ColoredPortalParticle extends PortalParticle {

    private final SpriteSet spriteSet;

    protected ColoredPortalParticle(ClientLevel level, SpriteSet spriteSet, float red, float green, float blue, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
        super(level, x, y, z, xSpeed, ySpeed, zSpeed);
        this.spriteSet = spriteSet;
        setColor(red, green, blue);
        setSpriteFromAge(spriteSet);
    }

    @Override
    public void tick() {
        this.setSpriteFromAge(spriteSet);
        super.tick();
    }
}
