package com.calemi.nexus.util.scanner;

import com.calemi.ccore.api.location.BlockLocation;
import com.calemi.ccore.api.scanner.BlockScanner;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.List;

public class PortalSpaceScanner extends BlockScanner {

    private final Direction.Axis axis;

    public PortalSpaceScanner(BlockLocation origin, Direction.Axis axis, int maxCollectionSize) {
        super(origin, maxCollectionSize);
        this.axis = axis;
    }

    public Direction.Axis getAxis() {
        return axis;
    }

    @Override
    public boolean shouldCollect(BlockPos scannedBlockPos) {

        BlockState state = getLevel().getBlockState(scannedBlockPos);

        if (!state.isAir() && !state.isCollisionShapeFullBlock(getLevel(), scannedBlockPos)) {
            setHalted(true);
            return false;
        }

        return state.isAir();
    }

    @Override
    public boolean branchOnFailedCollect() {
        return false;
    }

    @Override
    public List<BlockPos> nextPositionsToScan(BlockPos prevBlockPos) {

        List<BlockPos> positions = new ArrayList<>();

        if (axis.isVertical()) {

            positions.add(prevBlockPos.relative(Direction.NORTH));
            positions.add(prevBlockPos.relative(Direction.SOUTH));
            positions.add(prevBlockPos.relative(Direction.EAST));
            positions.add(prevBlockPos.relative(Direction.WEST));

            return positions;
        }

        positions.add(prevBlockPos.relative(Direction.UP));
        positions.add(prevBlockPos.relative(Direction.DOWN));
        positions.add(prevBlockPos.relative(Direction.fromAxisAndDirection(axis, Direction.AxisDirection.NEGATIVE)));
        positions.add(prevBlockPos.relative(Direction.fromAxisAndDirection(axis, Direction.AxisDirection.POSITIVE)));

        return positions;
    }
}
