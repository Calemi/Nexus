package com.calemi.nexus.world.feature.placed;

import com.calemi.nexus.block.NexusBlocks;
import com.calemi.nexus.main.NexusRef;
import com.calemi.nexus.world.feature.configured.NexusConfiguredFeatures;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.Vec3i;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraft.world.level.material.Fluids;

import java.util.ArrayList;
import java.util.List;

public class NexusPlacedFeatures {

    public static final ResourceKey<PlacedFeature> WARPBLOSSOM_PLACED_KEY = registerKey("warpblossom");
    public static final ResourceKey<PlacedFeature> FLOWER_PURPLE_PETAL_PLACED_KEY = registerKey("flower_purple_petal");
    public static final ResourceKey<PlacedFeature> AMETHYST_CLUSTER_PLACED_KEY = registerKey("amethyst_cluster");
    public static final ResourceKey<PlacedFeature> AMETHYST_CLUSTER_SUBMERGED_PLACED_KEY = registerKey("amethyst_cluster_submerged");
    public static final ResourceKey<PlacedFeature> CHRONO_CLUSTER_PLACED_KEY = registerKey("chrono_cluster");
    public static final ResourceKey<PlacedFeature> CHRONO_CLUSTER_SUBMERGED_PLACED_KEY = registerKey("chrono_cluster_submerged");
    public static final ResourceKey<PlacedFeature> CHASM = registerKey("chasm");

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {

        var configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        /*
            WARPBLOSSOM TREES
         */

        register(context, WARPBLOSSOM_PLACED_KEY, configuredFeatures.getOrThrow(NexusConfiguredFeatures.WARPBLOSSOM_CONFIGURED_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(1, 0.05F, 0), NexusBlocks.WARPBLOSSOM_SAPLING.get()));

        /*
            PURPLE PETALS
         */

        List<PlacementModifier> flowerPurplePetalModifiers = new ArrayList<>();

        flowerPurplePetalModifiers.add(NoiseThresholdCountPlacement.of(-0.8D, 0, 2));
        flowerPurplePetalModifiers.add(InSquarePlacement.spread());
        flowerPurplePetalModifiers.add(PlacementUtils.HEIGHTMAP);
        flowerPurplePetalModifiers.add(BlockPredicateFilter.forPredicate(BlockPredicate.matchesBlocks(new Vec3i(0, -1, 0), NexusBlocks.CHRONOWARPED_GRASS.get())));
        flowerPurplePetalModifiers.add(BiomeFilter.biome());

        register(context, FLOWER_PURPLE_PETAL_PLACED_KEY, configuredFeatures.getOrThrow(NexusConfiguredFeatures.FLOWER_PURPLE_PETAL_CONFIGURED_KEY), flowerPurplePetalModifiers);

        /*
            CRYSTAL CLUSTERS
         */

        registerCluster(context, AMETHYST_CLUSTER_PLACED_KEY, NexusConfiguredFeatures.AMETHYST_CLUSTER_CONFIGURED_KEY, false, 2, 0.0D);
        registerCluster(context, AMETHYST_CLUSTER_SUBMERGED_PLACED_KEY, NexusConfiguredFeatures.AMETHYST_CLUSTER_CONFIGURED_KEY, true, 3, 0.0D);
        registerCluster(context, CHRONO_CLUSTER_PLACED_KEY, NexusConfiguredFeatures.CHRONO_CLUSTER_CONFIGURED_KEY, false, 1, -0.25D);
        registerCluster(context, CHRONO_CLUSTER_SUBMERGED_PLACED_KEY, NexusConfiguredFeatures.CHRONO_CLUSTER_CONFIGURED_KEY, true, 2, 0.25D);

        /*
            CHASMS
         */

        List<PlacementModifier> chasmModifiers = new ArrayList<>();

        chasmModifiers.add(RarityFilter.onAverageOnceEvery(300));
        chasmModifiers.add(InSquarePlacement.spread());
        chasmModifiers.add(PlacementUtils.HEIGHTMAP);
        chasmModifiers.add(BiomeFilter.biome());

        register(context, CHASM, configuredFeatures.getOrThrow(NexusConfiguredFeatures.CHASM_CONFIGURED_KEY), chasmModifiers);
    }

    private static void registerCluster(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> placedFeatureRK, ResourceKey<ConfiguredFeature<?, ?>> configuredFeatureRK, boolean submerged, int count, double noiseLevel) {

        var configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        List<PlacementModifier> modifiers = new ArrayList<>();

        modifiers.add(NoiseThresholdCountPlacement.of(noiseLevel, 0, count));
        modifiers.add(InSquarePlacement.spread());

        if (submerged) {
            modifiers.add(PlacementUtils.HEIGHTMAP_OCEAN_FLOOR);
            modifiers.add(BlockPredicateFilter.forPredicate(BlockPredicate.allOf(BlockPredicate.matchesFluids(Fluids.WATER), BlockPredicate.hasSturdyFace(new Vec3i(0, -1, 0), Direction.UP))));
        }

        else {
            modifiers.add(PlacementUtils.HEIGHTMAP);
            modifiers.add(BlockPredicateFilter.forPredicate(BlockPredicate.hasSturdyFace(new Vec3i(0, -1, 0), Direction.UP)));
        }

        modifiers.add(BiomeFilter.biome());

        register(context, placedFeatureRK, configuredFeatures.getOrThrow(configuredFeatureRK), modifiers);
    }

    public static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, NexusRef.rl(name));
    }

    public static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> config, List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(config, List.copyOf(modifiers)));
    }
}
