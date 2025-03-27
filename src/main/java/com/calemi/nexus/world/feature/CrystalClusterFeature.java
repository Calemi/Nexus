package com.calemi.nexus.world.feature;

import com.calemi.ccore.api.math.MathHelper;
import com.calemi.nexus.world.feature.configured.CrystalClusterConfiguration;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;

public class CrystalClusterFeature extends Feature<CrystalClusterConfiguration> {

    public CrystalClusterFeature(Codec<CrystalClusterConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<CrystalClusterConfiguration> context) {

        WorldGenLevel level = context.level();
        BlockPos origin = context.origin();

        BlockState stateToReplace = level.getBlockState(origin);
        FluidState fluidStateToReplace = level.getFluidState(origin);

        BlockState crystalState = context.config().clusterBlock().getState(context.random(), origin);
        BlockState buddingState = context.config().buddingBlock().getState(context.random(), origin);

        double buddingChance = context.config().buddingChance();

        if (fluidStateToReplace.is(Fluids.WATER)) {
            placeCrystal(level, origin, crystalState, true, buddingState, buddingChance);
            return true;
        }

        else if (stateToReplace.canBeReplaced()) {
            placeCrystal(level, origin, crystalState, false, buddingState, buddingChance);
            return true;
        }

        return false;
    }

    private void placeCrystal(WorldGenLevel level, BlockPos origin, BlockState crystalState, boolean waterlogged, BlockState buddingState, double buddingChance) {

        if (waterlogged && crystalState.hasProperty(BlockStateProperties.WATERLOGGED)) {
            level.setBlock(origin, crystalState.setValue(BlockStateProperties.WATERLOGGED, true), 2);
        }

        else level.setBlock(origin, crystalState, 2);

        if (MathHelper.rollChance(buddingChance)) {
            level.setBlock(origin.below(), buddingState, 2);
        }
    }
}
