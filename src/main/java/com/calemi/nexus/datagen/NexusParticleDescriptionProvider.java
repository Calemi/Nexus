package com.calemi.nexus.datagen;

import com.calemi.nexus.regsitry.NexusLists;
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
}