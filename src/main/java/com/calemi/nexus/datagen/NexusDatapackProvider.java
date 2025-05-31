package com.calemi.nexus.datagen;

import com.calemi.nexus.item.enchantment.NexusEnchantments;
import com.calemi.nexus.main.NexusRef;
import com.calemi.nexus.world.biome.NexusBiomes;
import com.calemi.nexus.world.dimension.NexusDimensions;
import com.calemi.nexus.world.feature.configured.NexusConfiguredFeatures;
import com.calemi.nexus.world.feature.placed.NexusPlacedFeatures;
import com.calemi.nexus.world.noise.NexusNoiseSettings;
import com.calemi.nexus.world.structure.NexusStructureSets;
import com.calemi.nexus.world.structure.NexusTemplatePools;
import com.calemi.nexus.world.structure.NexusStructures;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class NexusDatapackProvider extends DatapackBuiltinEntriesProvider {

    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.ENCHANTMENT, NexusEnchantments::bootstrap)
            .add(Registries.CONFIGURED_FEATURE, NexusConfiguredFeatures::bootstrap)
            .add(Registries.PLACED_FEATURE, NexusPlacedFeatures::bootstrap)
            .add(Registries.STRUCTURE, NexusStructures::bootstrap)
            .add(Registries.STRUCTURE_SET, NexusStructureSets::bootstrap)
            .add(Registries.TEMPLATE_POOL, NexusTemplatePools::bootstrap)
            .add(Registries.BIOME, NexusBiomes::bootstrap)
            .add(Registries.DIMENSION_TYPE, NexusDimensions::bootstrapDimensionType)
            .add(Registries.LEVEL_STEM, NexusDimensions::bootstrapLevelStem)
            .add(Registries.NOISE_SETTINGS, NexusNoiseSettings::bootstrap);

    public NexusDatapackProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(NexusRef.ID));
    }
}
