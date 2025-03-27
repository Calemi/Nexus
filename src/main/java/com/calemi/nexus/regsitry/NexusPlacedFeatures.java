package com.calemi.nexus.regsitry;

import com.calemi.nexus.main.NexusRef;
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

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {

        var configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, WARPBLOSSOM_PLACED_KEY, configuredFeatures.getOrThrow(NexusConfiguredFeatures.WARPBLOSSOM_CONFIGURED_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(1, 0.05F, 0), NexusBlocks.WARPBLOSSOM_SAPLING.get()));

        List<PlacementModifier> flowerPurplePetalModifiers = new ArrayList<>();

        flowerPurplePetalModifiers.add(NoiseThresholdCountPlacement.of(-0.8D, 0, 2));
        flowerPurplePetalModifiers.add(InSquarePlacement.spread());
        flowerPurplePetalModifiers.add(PlacementUtils.HEIGHTMAP);
        flowerPurplePetalModifiers.add(BlockPredicateFilter.forPredicate(BlockPredicate.matchesBlocks(new Vec3i(0, -1, 0), NexusBlocks.CHRONOWARPED_GRASS.get())));
        flowerPurplePetalModifiers.add(BiomeFilter.biome());

        register(context, FLOWER_PURPLE_PETAL_PLACED_KEY, configuredFeatures.getOrThrow(NexusConfiguredFeatures.FLOWER_PURPLE_PETAL_CONFIGURED_KEY), flowerPurplePetalModifiers);

        List<PlacementModifier> amethystClusterModifiers = new ArrayList<>();

        amethystClusterModifiers.add(NoiseThresholdCountPlacement.of(0.0D, 0, 2));
        amethystClusterModifiers.add(InSquarePlacement.spread());
        amethystClusterModifiers.add(PlacementUtils.HEIGHTMAP);
        amethystClusterModifiers.add(BlockPredicateFilter.forPredicate(BlockPredicate.hasSturdyFace(new Vec3i(0, -1, 0), Direction.UP)));
        amethystClusterModifiers.add(BiomeFilter.biome());

        register(context, AMETHYST_CLUSTER_PLACED_KEY, configuredFeatures.getOrThrow(NexusConfiguredFeatures.AMETHYST_CLUSTER_CONFIGURED_KEY), amethystClusterModifiers);

        List<PlacementModifier> amethystClusterSubmergedModifiers = new ArrayList<>();

        amethystClusterSubmergedModifiers.add(NoiseThresholdCountPlacement.of(0.0, 0, 3));
        amethystClusterSubmergedModifiers.add(InSquarePlacement.spread());
        amethystClusterSubmergedModifiers.add(PlacementUtils.HEIGHTMAP_OCEAN_FLOOR);
        amethystClusterSubmergedModifiers.add(BlockPredicateFilter.forPredicate(BlockPredicate.allOf(BlockPredicate.matchesFluids(Fluids.WATER), BlockPredicate.hasSturdyFace(new Vec3i(0, -1, 0), Direction.UP))));
        amethystClusterSubmergedModifiers.add(BiomeFilter.biome());

        register(context, AMETHYST_CLUSTER_SUBMERGED_PLACED_KEY, configuredFeatures.getOrThrow(NexusConfiguredFeatures.AMETHYST_CLUSTER_CONFIGURED_KEY), amethystClusterSubmergedModifiers);
    }

    public static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, NexusRef.rl(name));
    }

    public static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> config, List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(config, List.copyOf(modifiers)));
    }
}
