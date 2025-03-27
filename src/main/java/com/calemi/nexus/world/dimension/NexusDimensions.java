package com.calemi.nexus.world.dimension;

import com.calemi.nexus.main.NexusRef;
import com.calemi.nexus.world.noise.NexusNoiseSettings;
import com.calemi.nexus.world.biome.NexusBiomes;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.biome.FixedBiomeSource;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;

import java.util.OptionalLong;

public class NexusDimensions {

    public static final ResourceLocation NEXUS_RL = NexusRef.rl("the_nexus");

    public static final ResourceKey<DimensionType> NEXUS_DIMENSION_TYPE = NexusRef.createKey(NEXUS_RL, Registries.DIMENSION_TYPE);
    public static final ResourceKey<Level> NEXUS_LEVEL = NexusRef.createKey(NEXUS_RL, Registries.DIMENSION);
    public static final ResourceKey<LevelStem> NEXUS_LEVEL_STEM = NexusRef.createKey(NEXUS_RL, Registries.LEVEL_STEM);

    public static void initDimensionType(BootstrapContext<DimensionType> context) {
        context.register(NEXUS_DIMENSION_TYPE, nexusDimensionType());
    }

    public static void initLevelStem(BootstrapContext<LevelStem> context) {

        HolderGetter<DimensionType> dimensionTypes = context.lookup(Registries.DIMENSION_TYPE);
        HolderGetter<Biome> biomes = context.lookup(Registries.BIOME);
        HolderGetter<NoiseGeneratorSettings> noiseSettings = context.lookup(Registries.NOISE_SETTINGS);

        context.register(NEXUS_LEVEL_STEM, nexusLevelStem(dimensionTypes, biomes, noiseSettings));
    }

    private static DimensionType nexusDimensionType() {
        return new DimensionType(
                OptionalLong.empty(), //fixed time
                true,  // Has skylight
                false, // Has ceiling
                false, // Ultrawarm
                true,  // Natural
                1.0F,  // Coordinate scale
                false, // Bed works
                false, // Respawn anchor works
                0,     // Minimum Y Level
                256,   // Height
                256,   // Logical Height
                BlockTags.INFINIBURN_OVERWORLD,          // What blocks infinitely burn
                BuiltinDimensionTypes.OVERWORLD_EFFECTS, // DimensionRenderInfo
                0F,    //Ambient light
                new DimensionType.MonsterSettings(false, false, UniformInt.of(0, 0), 0)
        );
    }

    public static LevelStem nexusLevelStem(HolderGetter<DimensionType> dimensionTypes, HolderGetter<Biome> biomes, HolderGetter<NoiseGeneratorSettings> noiseSettings) {

        BiomeSource source = new FixedBiomeSource(biomes.getOrThrow(NexusBiomes.CHRONOWARPED_FIELDS));
        NoiseBasedChunkGenerator chunkGenerator = new NoiseBasedChunkGenerator(source, noiseSettings.getOrThrow(NexusNoiseSettings.NEXUS));

        return new LevelStem(dimensionTypes.getOrThrow(NEXUS_DIMENSION_TYPE), chunkGenerator);
    }
}
