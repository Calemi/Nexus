package com.calemi.nexus.util.scanner;

import com.calemi.ccore.api.location.BlockLocation;
import com.calemi.ccore.api.scanner.BlockScanner;
import com.calemi.nexus.block.NexusPortalBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;

import java.util.ArrayList;
import java.util.List;

public class PortalScanner extends BlockScanner {

    private final Direction.Axis axis;
    private final boolean scanDifferentColors;

    public PortalScanner(BlockLocation origin, Direction.Axis axis, boolean scanDifferentColors, int maxCollectionSize) {
        super(origin, maxCollectionSize);
        this.axis = axis;
        this.scanDifferentColors = scanDifferentColors;
    }

    public Direction.Axis getAxis() {
        return axis;
    }

    @Override
    public boolean shouldCollect(BlockPos scannedBlockPos) {

        if (!scanDifferentColors) {
            return getLevel().getBlockState(scannedBlockPos).getBlock().equals(getLevel().getBlockState(getOriginPosition()).getBlock());
        }

        return getLevel().getBlockState(scannedBlockPos).getBlock() instanceof NexusPortalBlock portalBlock;
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
