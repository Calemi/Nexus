package com.calemi.nexus.regsitry;

import com.calemi.nexus.main.NexusRef;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.DimensionTypes;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelSettings;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.WorldGenSettings;
import net.minecraft.world.level.levelgen.WorldOptions;

import java.util.OptionalLong;

public class NexusDimensions {

    public static final ResourceLocation NEXUS_RESOURCE_LOCATION = ResourceLocation.fromNamespaceAndPath(NexusRef.ID, "the_nexus");
    public static final ResourceKey<Level> NEXUS_KEY = ResourceKey.create(Registries.DIMENSION, NEXUS_RESOURCE_LOCATION);

    public static final ResourceKey<DimensionType> NEXUS_DIM_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE, ResourceLocation.fromNamespaceAndPath(NexusRef.ID, "the_nexus_type"));
    public static final ResourceKey<LevelStem> NEXUS_LEVEL_STEM = ResourceKey.create(Registries.LEVEL_STEM, NEXUS_RESOURCE_LOCATION);

    public static void init(BootstrapContext<DimensionType> context) {
        context.register(NEXUS_DIM_TYPE, nexusDimensionType());
    }

    private static DimensionType nexusDimensionType() {
        return new DimensionType(
                OptionalLong.empty(), //fixed time
                true, //skylight
                false, //ceiling
                false, //ultrawarm
                true, //natural
                1, //coordinate scale
                false, //bed works
                false, //respawn anchor works
                0, // Minimum Y Level
                256, // Height + Min Y = Max Y
                256, // Logical Height
                BlockTags.INFINIBURN_OVERWORLD, //infiburn
                ResourceLocation.fromNamespaceAndPath("minecraft", "overworld"), // DimensionRenderInfo
                0.0F,
                new DimensionType.MonsterSettings(false, false, UniformInt.of(0, 0), 0)
        );
    }
}
