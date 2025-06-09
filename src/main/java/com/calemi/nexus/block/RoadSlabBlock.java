package com.calemi.nexus.block;

import com.calemi.nexus.tag.NexusTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;

public class RoadSlabBlock extends SlabBlock {

    public static final BooleanProperty CHARGED = NexusBlockStateProperties.CHARGED;

    public RoadSlabBlock(Properties properties) {
        super(properties);
        registerDefaultState(defaultBlockState().setValue(CHARGED, false));
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {

        if (entity instanceof LivingEntity livingEntity) {
            RoadBlock.giveRoadEffect(level, pos, livingEntity);
        }
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return super.getStateForPlacement(context).setValue(CHARGED, context.getLevel().getBlockState(context.getClickedPos().below()).is(NexusTags.Blocks.UPGRADES_ROAD_BLOCK));
    }

    @Override
    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block neighborBlock, BlockPos neighborPos, boolean movedByPiston) {
        level.setBlockAndUpdate(pos, state.setValue(CHARGED, level.getBlockState(pos.below()).is(NexusTags.Blocks.UPGRADES_ROAD_BLOCK)));
        super.neighborChanged(state, level, pos, neighborBlock, neighborPos, movedByPiston);
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        if (state.getValue(CHARGED)) spawnParticles(level, pos);
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

                if (!level.getBlockState(pos).getValue(TYPE).equals(SlabType.DOUBLE)) {
                    yOffset *= 0.5D;
                }

                if (level.getBlockState(pos).getValue(TYPE).equals(SlabType.TOP)) {
                    yOffset += 0.5D;
                }

                level.addParticle(
                        new DustParticleOptions(Vec3.fromRGB24(0x008CFF).toVector3f(), 1F), (double) pos.getX() + xOffset, (double) pos.getY() + yOffset, (double) pos.getZ() + zOffset, 0.0, 0.0, 0.0
                );
            }
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(CHARGED);
    }
}
