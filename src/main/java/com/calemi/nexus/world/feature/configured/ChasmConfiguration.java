package com.calemi.nexus.world.feature.configured;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

public record ChasmConfiguration(int radiusMinXZ, int radiusMaxXZ, int radiusMinY, int radiusMaxY) implements FeatureConfiguration {

    public static final Codec<ChasmConfiguration> CODEC = RecordCodecBuilder.create(configuration -> configuration.group(
                    Codec.INT.fieldOf("radiusMinXZ").forGetter(configuration1 -> configuration1.radiusMinXZ),
                    Codec.INT.fieldOf("radiusMaxXZ").forGetter(configuration1 -> configuration1.radiusMaxXZ),
                    Codec.INT.fieldOf("radiusMinY").forGetter(configuration1 -> configuration1.radiusMinY),
                    Codec.INT.fieldOf("radiusMaxY").forGetter(configuration1 -> configuration1.radiusMaxY))
            .apply(configuration, ChasmConfiguration::new)
    );

    @Override
    public int radiusMinXZ() {
        return radiusMinXZ;
    }

    @Override
    public int radiusMaxXZ() {
        return radiusMaxXZ;
    }

    @Override
    public int radiusMinY() {
        return radiusMinY;
    }

    @Override
    public int radiusMaxY() {
        return radiusMaxY;
    }
}
