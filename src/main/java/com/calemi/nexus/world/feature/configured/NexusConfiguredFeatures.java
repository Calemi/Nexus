package com.calemi.nexus.world.feature.configured;

import com.calemi.nexus.block.NexusBlocks;
import com.calemi.nexus.world.feature.tree.WarpblossomFoliagePlacer;
import com.calemi.nexus.main.NexusRef;
import com.calemi.nexus.world.feature.NexusFeatures;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.PinkPetalsBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;

public class NexusConfiguredFeatures {

    public static final ResourceKey<ConfiguredFeature<?, ?>> WARPBLOSSOM_CONFIGURED_KEY = NexusRef.createKey("warpblossom", Registries.CONFIGURED_FEATURE);
    public static final ResourceKey<ConfiguredFeature<?, ?>> FLOWER_PURPLE_PETAL_CONFIGURED_KEY = NexusRef.createKey("flower_purple_petal", Registries.CONFIGURED_FEATURE);
    public static final ResourceKey<ConfiguredFeature<?, ?>> AMETHYST_CLUSTER_CONFIGURED_KEY = NexusRef.createKey("amethyst_cluster", Registries.CONFIGURED_FEATURE);
    public static final ResourceKey<ConfiguredFeature<?, ?>> CHRONO_CLUSTER_CONFIGURED_KEY = NexusRef.createKey("chrono_cluster", Registries.CONFIGURED_FEATURE);
    public static final ResourceKey<ConfiguredFeature<?, ?>> CHASM_CONFIGURED_KEY = NexusRef.createKey("chasm", Registries.CONFIGURED_FEATURE);

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {

        register(context, WARPBLOSSOM_CONFIGURED_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(NexusBlocks.WARPBLOSSOM_LOG.get()),
                new StraightTrunkPlacer(4, 8, 0),

                BlockStateProvider.simple(NexusBlocks.WARPBLOSSOM_LEAVES.get()),
                new WarpblossomFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0)),

                new TwoLayersFeatureSize(1, 0, 2)).dirt(BlockStateProvider.simple(NexusBlocks.CHRONOWARPED_DIRT.get())).forceDirt().build()
        );

        SimpleWeightedRandomList.Builder<BlockState> flowerPurplePetalBuilder = SimpleWeightedRandomList.builder();

        for (int i = 1; i <= 4; i++) {
            for (Direction direction : Direction.Plane.HORIZONTAL) {
                flowerPurplePetalBuilder.add(NexusBlocks.PURPLE_PETALS.get().defaultBlockState().setValue(PinkPetalsBlock.AMOUNT, i).setValue(PinkPetalsBlock.FACING, direction), 1);
            }
        }

        register(context, FLOWER_PURPLE_PETAL_CONFIGURED_KEY, Feature.FLOWER, new RandomPatchConfiguration(
                96, 6, 2, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new WeightedStateProvider(flowerPurplePetalBuilder)))
        ));

        registerCluster(context,
                AMETHYST_CLUSTER_CONFIGURED_KEY,
                Blocks.SMALL_AMETHYST_BUD,
                Blocks.MEDIUM_AMETHYST_BUD,
                Blocks.LARGE_AMETHYST_BUD,
                Blocks.AMETHYST_CLUSTER,
                Blocks.BUDDING_AMETHYST);

        registerCluster(context,
                CHRONO_CLUSTER_CONFIGURED_KEY,
                NexusBlocks.SMALL_CHRONO_BUD.get(),
                NexusBlocks.MEDIUM_CHRONO_BUD.get(),
                NexusBlocks.LARGE_CHRONO_BUD.get(),
                NexusBlocks.CHRONO_CLUSTER.get(),
                NexusBlocks.BUDDING_CHRONO.get());

        register(context, CHASM_CONFIGURED_KEY, NexusFeatures.CHASM.get(), new ChasmConfiguration(7, 14, 80, 100));
    }

    private static void registerCluster(BootstrapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> configuredFeatureRK, Block smallBud, Block mediumBud, Block largeBud, Block cluster, Block budding) {

        SimpleWeightedRandomList.Builder<BlockState> chronoBuilder = SimpleWeightedRandomList.builder();

        chronoBuilder.add(smallBud.defaultBlockState(), 2);
        chronoBuilder.add(mediumBud.defaultBlockState(), 2);
        chronoBuilder.add(largeBud.defaultBlockState(), 2);
        chronoBuilder.add(cluster.defaultBlockState(), 1);

        context.register(configuredFeatureRK, new ConfiguredFeature<>(NexusFeatures.CRYSTAL_CLUSTER.get(),
                new CrystalClusterConfiguration(
                        new WeightedStateProvider(chronoBuilder),
                        BlockStateProvider.simple(budding.defaultBlockState()),
                        0.05D
                )));
    }

    public static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC config) {
        context.register(key, new ConfiguredFeature<>(feature, config));
    }
}
