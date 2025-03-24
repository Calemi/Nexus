package com.calemi.nexus.regsitry;

import com.calemi.nexus.foliage.WarpblossomFoliagePlacer;
import com.calemi.nexus.main.NexusRef;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;

public class NexusConfiguredFeatures {

    public static final ResourceKey<ConfiguredFeature<?, ?>> WARPBLOSSOM_CONFIGURED_KEY = NexusRef.createKey("warpblossom", Registries.CONFIGURED_FEATURE);

    public static void init(BootstrapContext<ConfiguredFeature<?, ?>> context) {

        register(context, WARPBLOSSOM_CONFIGURED_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(NexusBlocks.WARPBLOSSOM_LOG.get()),
                new StraightTrunkPlacer(4, 8, 0),

                BlockStateProvider.simple(NexusBlocks.WARPBLOSSOM_LEAVES.get()),
                new WarpblossomFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0)),

                new TwoLayersFeatureSize(1, 0, 2)).dirt(BlockStateProvider.simple(NexusBlocks.CHRONOWARPED_DIRT.get())).forceDirt().build()
        );
    }

    public static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC config) {
        context.register(key, new ConfiguredFeature<>(feature, config));
    }
}
