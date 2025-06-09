package com.calemi.nexus.block;

import com.calemi.ccore.api.math.MathHelper;
import com.calemi.nexus.config.NexusConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;

public class WarpslateBlock extends RotatedPillarBlock {

    public WarpslateBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean movedByPiston) {

        if (movedByPiston && MathHelper.rollChance(NexusConfig.server.acceleriteOreFromMovingWarpslateChance.get())) {
            level.setBlockAndUpdate(pos, NexusBlocks.WARPSLATE_ACCELERITE_ORE.get().defaultBlockState());
        }
    }
}