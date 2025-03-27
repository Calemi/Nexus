package com.calemi.nexus.world.biome;

import com.calemi.nexus.main.NexusRef;
import com.calemi.nexus.world.feature.placed.NexusPlacedFeatures;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class NexusBiomes {

    public static final ResourceKey<Biome> CHRONOWARPED_FIELDS = NexusRef.createKey("chronowarped_fields", Registries.BIOME);

    public static void init(BootstrapContext<Biome> context) {

        HolderGetter<PlacedFeature> featureGetter = context.lookup(Registries.PLACED_FEATURE);
        HolderGetter<ConfiguredWorldCarver<?>> carverGetter = context.lookup(Registries.CONFIGURED_CARVER);

        context.register(CHRONOWARPED_FIELDS, chronowarpedFields(featureGetter, carverGetter));
    }

    public static Biome chronowarpedFields(HolderGetter<PlacedFeature> featureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        return new Biome.BiomeBuilder()
                .temperature(0.8F)
                .downfall(0.4F)
                .hasPrecipitation(true)
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .skyColor(7372031)
                        .fogColor(5491855)
                        .waterColor(7372031)
                        .waterFogColor(7372031)
                        .grassColorOverride(5491855)
                        .foliageColorOverride(10068991)
                        .build())
                .generationSettings(new BiomeGenerationSettings.Builder(featureGetter, carverGetter)
                        .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_GRASS_PLAIN)
                        .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, NexusPlacedFeatures.FLOWER_PURPLE_PETAL_PLACED_KEY)
                        .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, NexusPlacedFeatures.WARPBLOSSOM_PLACED_KEY)
                        .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, NexusPlacedFeatures.AMETHYST_CLUSTER_PLACED_KEY)
                        .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, NexusPlacedFeatures.AMETHYST_CLUSTER_SUBMERGED_PLACED_KEY)
                        .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, NexusPlacedFeatures.CHRONO_CLUSTER_PLACED_KEY)
                        .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, NexusPlacedFeatures.CHRONO_CLUSTER_SUBMERGED_PLACED_KEY)
                        .build())
                .mobSpawnSettings(new MobSpawnSettings.Builder().build())
                .build();
    }
}
