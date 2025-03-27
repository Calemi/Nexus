package com.calemi.nexus.datagen;

import com.calemi.nexus.main.NexusRef;
import com.calemi.nexus.util.NexusLists;
import com.calemi.nexus.client.partclie.NexusParticles;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.common.data.ParticleDescriptionProvider;

public class NexusParticleDescriptionProvider extends ParticleDescriptionProvider {

    public NexusParticleDescriptionProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, existingFileHelper);
    }

    @Override
    protected void addDescriptions() {
        NexusLists.NEXUS_PORTAL_PARTICLES.forEach(particle -> portal(particle.get()));
        warpblossom(NexusParticles.WARPBLOSSOM_PARTICLES.get());
    }

    private void portal(ParticleType<?> particle) {
        spriteSet(particle,
                ResourceLocation.withDefaultNamespace("generic_0"),
                ResourceLocation.withDefaultNamespace("generic_1"),
                ResourceLocation.withDefaultNamespace("generic_2"),
                ResourceLocation.withDefaultNamespace("generic_3"),
                ResourceLocation.withDefaultNamespace("generic_4"),
                ResourceLocation.withDefaultNamespace("generic_5"),
                ResourceLocation.withDefaultNamespace("generic_6"),
                ResourceLocation.withDefaultNamespace("generic_7")
        );
    }

    private void warpblossom(ParticleType<?> particle) {
        spriteSet(particle,
                NexusRef.rl("warpblossom_0"),
                NexusRef.rl("warpblossom_1"),
                NexusRef.rl("warpblossom_2"),
                NexusRef.rl("warpblossom_3"),
                NexusRef.rl("warpblossom_4"),
                NexusRef.rl("warpblossom_5"),
                NexusRef.rl("warpblossom_6"),
                NexusRef.rl("warpblossom_7"),
                NexusRef.rl("warpblossom_8"),
                NexusRef.rl("warpblossom_9"),
                NexusRef.rl("warpblossom_10"),
                NexusRef.rl("warpblossom_11")
        );
    }
}