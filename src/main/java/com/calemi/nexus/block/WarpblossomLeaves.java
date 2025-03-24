package com.calemi.nexus.block;

import com.calemi.nexus.regsitry.NexusParticles;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;

public class WarpblossomLeaves extends LeavesBlock {

    public WarpblossomLeaves() {
        super(Block.Properties.ofFullCopy(Blocks.CHERRY_LEAVES));
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {

        super.animateTick(state, level, pos, random);

        if (random.nextInt(10) == 0) {

            BlockPos blockpos = pos.below();
            BlockState blockstate = level.getBlockState(blockpos);

            if (!isFaceFull(blockstate.getCollisionShape(level, blockpos), Direction.UP)) {
                ParticleUtils.spawnParticleBelow(level, pos, random, NexusParticles.WARPBLOSSOM_PARTICLES.get());
            }
        }
    }
}
