package com.calemi.nexus.regsitry;

import com.calemi.nexus.main.NexusRef;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

public class NexusPlacedFeatures {

    public static final ResourceKey<PlacedFeature> WARPBLOSSOM_PLACED_KEY = registerKey("warpblossom");

    public static void init(BootstrapContext<PlacedFeature> context) {

        var configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, WARPBLOSSOM_PLACED_KEY, configuredFeatures.getOrThrow(NexusConfiguredFeatures.WARPBLOSSOM_CONFIGURED_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(1, 0.05F, 0), NexusBlocks.WARPBLOSSOM_SAPLING.get()));
    }

    public static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, NexusRef.rl(name));
    }

    public static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> config, List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(config, List.copyOf(modifiers)));
    }
}
