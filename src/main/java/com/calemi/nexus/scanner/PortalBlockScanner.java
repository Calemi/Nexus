package com.calemi.nexus.scanner;

import com.calemi.ccore.api.location.Location;
import com.calemi.ccore.api.log.LogHelper;
import com.calemi.ccore.api.scanner2.BlockScanner2;
import com.calemi.nexus.main.NexusRef;
import net.minecraft.core.Direction;

import java.util.ArrayList;
import java.util.List;

public class PortalBlockScanner extends BlockScanner2 {

    private final Direction.Axis axis;

    public PortalBlockScanner(Location origin, Direction.Axis axis, int maxScanSize) {
        super(origin, maxScanSize);
        this.axis = axis;
    }

    public Direction.Axis getAxis() {
        return axis;
    }

    @Override
    public boolean shouldCollect(Location location) {

        LogHelper.log(NexusRef.MOD_NAME, "Scanning: " + location.getBlockPos());

        if (!location.getBlockState().isAir() && !location.getBlockState().isViewBlocking(location.getLevel(), location.getBlockPos())) {
            LogHelper.log(NexusRef.MOD_NAME, "Found non-suffocating block: " + location.getBlock());
            halted = true;
            return false;
        }

        return location.getBlockState().isAir();
    }

    @Override
    public boolean branchOnFailedCollect() {
        return false;
    }

    @Override
    public List<Location> nextLocationsToScan(Location location) {

        if (collectedLocations.size() >= maxScanSize) {
            halted = true;
            return new ArrayList<>();
        }

        List<Location> locations = new ArrayList<>();

        locations.add(new Location(location.getLevel(), location.getBlockPos().relative(Direction.UP)));
        locations.add(new Location(location.getLevel(), location.getBlockPos().relative(Direction.DOWN)));
        locations.add(new Location(location.getLevel(), location.getBlockPos().relative(Direction.fromAxisAndDirection(axis, Direction.AxisDirection.NEGATIVE))));
        locations.add(new Location(location.getLevel(), location.getBlockPos().relative(Direction.fromAxisAndDirection(axis, Direction.AxisDirection.POSITIVE))));

        LogHelper.log(NexusRef.MOD_NAME, locations);

        return locations;
    }
}
