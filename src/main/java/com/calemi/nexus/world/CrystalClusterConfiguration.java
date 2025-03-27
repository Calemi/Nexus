package com.calemi.nexus.world;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public record CrystalClusterConfiguration(BlockStateProvider clusterBlock, BlockStateProvider buddingBlock, double buddingChance) implements FeatureConfiguration {

    public static final Codec<CrystalClusterConfiguration> CODEC = RecordCodecBuilder.create(configuration -> configuration.group(
                    BlockStateProvider.CODEC.fieldOf("clusterBlock").forGetter(configuration1 -> configuration1.clusterBlock),
                    BlockStateProvider.CODEC.fieldOf("buddingBlock").forGetter(configuration2 -> configuration2.buddingBlock),
                    Codec.DOUBLE.fieldOf("buddingChance").forGetter(configuration3 -> configuration3.buddingChance))
            .apply(configuration, CrystalClusterConfiguration::new)
    );

    @Override
    public BlockStateProvider clusterBlock() {
        return clusterBlock;
    }

    @Override
    public BlockStateProvider buddingBlock() {
        return buddingBlock;
    }

    @Override
    public double buddingChance() {
        return buddingChance;
    }
}
