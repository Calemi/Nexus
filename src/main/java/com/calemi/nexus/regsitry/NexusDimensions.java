package com.calemi.nexus.regsitry;

import com.calemi.nexus.main.NexusRef;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;

import java.util.OptionalLong;

public class NexusDimensions {

    public static final ResourceLocation NEXUS_RESOURCE_LOCATION = ResourceLocation.fromNamespaceAndPath(NexusRef.MOD_ID, "the_nexus");
    public static final ResourceKey<Level> NEXUS_KEY = ResourceKey.create(Registries.DIMENSION, NEXUS_RESOURCE_LOCATION);

    public static final ResourceKey<DimensionType> NEXUS_DIM_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE, ResourceLocation.fromNamespaceAndPath(NexusRef.MOD_ID, "the_nexus_type"));
    public static final ResourceKey<LevelStem> NEXUS_LEVEL_STEM = ResourceKey.create(Registries.LEVEL_STEM, NEXUS_RESOURCE_LOCATION);

    public static void init(BootstrapContext<DimensionType> context) {
        context.register(NEXUS_DIM_TYPE, nexusDimType());
    }

    private static DimensionType nexusDimType() {
        return new DimensionType(
                OptionalLong.of(13000L), //fixed time
                true, //skylight
                false, //ceiling
                false, //ultrawarm
                true, //natural
                1, //coordinate scale
                true, //bed works
                false, //respawn anchor works
                0, // Minimum Y Level
                256, // Height + Min Y = Max Y
                256, // Logical Height
                BlockTags.INFINIBURN_OVERWORLD, //infiburn
                ResourceLocation.fromNamespaceAndPath("minecraft", "overworld"), // DimensionRenderInfo
                1F, // Wish this could be set to -0.05 since it'll make the world truly blacked out if an area is not sky-lit (see: Dark Forests) Sadly this also messes up night vision so it gets 0
                new DimensionType.MonsterSettings(false, false, UniformInt.of(0, 0), 0)
        );
    }
}
