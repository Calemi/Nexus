package com.calemi.nexus.client.partclie;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.CherryParticle;
import net.minecraft.client.particle.SpriteSet;

public class WarpblossomParticle extends CherryParticle {

    private final SpriteSet spriteSet;

    protected WarpblossomParticle(ClientLevel level, SpriteSet spriteSet, double x, double y, double z) {
        super(level, x, y, z, spriteSet);
        this.spriteSet = spriteSet;
    }
}
