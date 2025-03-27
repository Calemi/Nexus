package com.calemi.nexus.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.AmethystBlock;
import net.minecraft.world.level.block.AmethystClusterBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;

public class BuddingChronoBlock extends AmethystBlock {

    public static final int GROWTH_CHANCE = 5;
    private static final Direction[] DIRECTIONS = Direction.values();

    public BuddingChronoBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {

        if (random.nextInt(5) == 0) {

            Direction direction = DIRECTIONS[random.nextInt(DIRECTIONS.length)];
            BlockPos blockpos = pos.relative(direction);
            BlockState blockstate = level.getBlockState(blockpos);
            Block block = null;

            if (canClusterGrowAtState(blockstate)) {
                block = NexusBlocks.SMALL_CHRONO_BUD.get();
            }

            else if (blockstate.is(NexusBlocks.SMALL_CHRONO_BUD.get()) && blockstate.getValue(AmethystClusterBlock.FACING) == direction) {
                block = NexusBlocks.MEDIUM_CHRONO_BUD.get();
            }

            else if (blockstate.is(NexusBlocks.MEDIUM_CHRONO_BUD.get()) && blockstate.getValue(AmethystClusterBlock.FACING) == direction) {
                block = NexusBlocks.LARGE_CHRONO_BUD.get();
            }

            else if (blockstate.is(NexusBlocks.LARGE_CHRONO_BUD.get()) && blockstate.getValue(AmethystClusterBlock.FACING) == direction) {
                block = NexusBlocks.CHRONO_CLUSTER.get();
            }

            if (block != null) {
                BlockState clusterState = block.defaultBlockState()
                        .setValue(AmethystClusterBlock.FACING, direction)
                        .setValue(AmethystClusterBlock.WATERLOGGED, blockstate.getFluidState().getType() == Fluids.WATER);
                level.setBlockAndUpdate(blockpos, clusterState);
            }
        }
    }

    public static boolean canClusterGrowAtState(BlockState state) {
        return state.isAir() || state.is(Blocks.WATER) && state.getFluidState().getAmount() == 8;
    }

    public static final MapCodec<BuddingChronoBlock> CODEC = simpleCodec(BuddingChronoBlock::new);

    @Override
    public MapCodec<BuddingChronoBlock> codec() {
        return CODEC;
    }
}
