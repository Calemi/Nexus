package com.calemi.nexus.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class ChargedAcceleriteBlock extends Block {

    public ChargedAcceleriteBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        spawnParticles(level, pos);
    }

    private static void spawnParticles(Level level, BlockPos pos) {

        double randomOffset = 0.5625;
        RandomSource randomsource = level.random;

        for (Direction direction : Direction.values()) {

            BlockPos blockpos = pos.relative(direction);

            if (!level.getBlockState(blockpos).isSolidRender(level, blockpos)) {

                Direction.Axis direction$axis = direction.getAxis();
                double xOffset = direction$axis == Direction.Axis.X ? 0.5 + randomOffset * (double) direction.getStepX() : (double) randomsource.nextFloat();
                double yOffset = direction$axis == Direction.Axis.Y ? 0.5 + randomOffset * (double) direction.getStepY() : (double) randomsource.nextFloat();
                double zOffset = direction$axis == Direction.Axis.Z ? 0.5 + randomOffset * (double) direction.getStepZ() : (double) randomsource.nextFloat();

                level.addParticle(
                        new DustParticleOptions(Vec3.fromRGB24(0x008CFF).toVector3f(), 1F), (double) pos.getX() + xOffset, (double) pos.getY() + yOffset, (double) pos.getZ() + zOffset, 0.0, 0.0, 0.0
                );
            }
        }
    }
}
