package com.calemi.nexus.world.feature.tree;

import com.calemi.nexus.main.Nexus;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import org.jetbrains.annotations.NotNull;

public class WarpblossomFoliagePlacer extends FoliagePlacer {

    public static final MapCodec<WarpblossomFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec(
            placerInstance -> foliagePlacerParts(placerInstance)
                    .apply(placerInstance, WarpblossomFoliagePlacer::new)
    );

    public WarpblossomFoliagePlacer(IntProvider radius, IntProvider offset) {
        super(radius, offset);
    }

    @Override
    protected void createFoliage(LevelSimulatedReader level, FoliagePlacer.FoliageSetter foliageSetter, RandomSource random, TreeConfiguration config, int maxFreeTreeHeight, FoliagePlacer.@NotNull FoliageAttachment attachment, int foliageHeight, int foliageRadius, int offset) {

        Nexus.LOGGER.debug("TREE HEIGHT: {}", maxFreeTreeHeight);

        if (maxFreeTreeHeight <= 3) {
            Nexus.LOGGER.debug("TREE HEIGHT TOO SMALL!");
        }

        int topLeavesHeight = Math.clamp(maxFreeTreeHeight / 3 + 1, 0, 3);

        for (int y = 0; y < topLeavesHeight; y++) {
            placeLeavesDiamond(attachment.pos(), y, 1, level, foliageSetter, random, config);
        }

        int leavesHeight = Math.clamp(maxFreeTreeHeight, 0, 9);

        for (int y = 1; y < leavesHeight - 1; y++) {

            int radius = 2;

            if (y > 2 && y < leavesHeight - 3) {
                radius = 3;
            }

            placeLeavesDiamond(attachment.pos(), -y, radius, level, foliageSetter, random, config);
        }
    }

    private void placeLeavesDiamond(BlockPos center, int localY, int radius, LevelSimulatedReader level, FoliagePlacer.FoliageSetter foliageSetter, RandomSource random, TreeConfiguration config) {

        for (int x = -radius + 1; x < radius; x++) {

            for (int z = -radius + 1; z < radius; z++) {

                if (Math.abs(x) + Math.abs(z) < radius) {
                    BlockPos pos = center.offset(x, localY, z);
                    tryPlaceLeaf(level, foliageSetter, random, config, pos);
                }
            }
        }
    }

    @Override
    public int foliageRadius(RandomSource random, int radius) {
        return 0;
    }

    @Override
    public int foliageHeight(RandomSource random, int height, TreeConfiguration config) {
        return 0;
    }

    @Override
    protected boolean shouldSkipLocation(RandomSource random, int localX, int localY, int localZ, int range, boolean large) {
        return localX == range && localZ == range && range > 0;
    }

    @Override
    protected FoliagePlacerType<?> type() {
        return NexusFoliagePlacers.WARPBLOSSOM.get();
    }
}
