package com.calemi.nexus.datagen;

import com.calemi.nexus.main.NexusRef;
import com.calemi.nexus.world.biome.NexusBiomes;
import com.calemi.nexus.world.dimension.NexusDimensions;
import com.calemi.nexus.world.feature.configured.NexusConfiguredFeatures;
import com.calemi.nexus.world.feature.placed.NexusPlacedFeatures;
import com.calemi.nexus.world.noise.NexusNoiseSettings;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class NexusDatapackProvider extends DatapackBuiltinEntriesProvider {

    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, NexusConfiguredFeatures::bootstrap)
            .add(Registries.PLACED_FEATURE, NexusPlacedFeatures::bootstrap)
            .add(Registries.BIOME, NexusBiomes::init)
            .add(Registries.DIMENSION_TYPE, NexusDimensions::initDimensionType)
            .add(Registries.LEVEL_STEM, NexusDimensions::initLevelStem)
            .add(Registries.NOISE_SETTINGS, NexusNoiseSettings::init);

    public NexusDatapackProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(NexusRef.ID));
    }
}
