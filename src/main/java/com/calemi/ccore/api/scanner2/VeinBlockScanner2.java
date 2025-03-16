package com.calemi.ccore.api.scanner2;

import com.calemi.ccore.api.location.Location;
import com.calemi.ccore.api.log.LogHelper;
import com.calemi.nexus.main.NexusRef;

import java.util.ArrayList;
import java.util.List;

public class VeinBlockScanner2 extends BlockScanner2 {

    /**
     * Creates a BlockScanner
     * @param origin      The origin Location of the scan.
     * @param maxScanSize The maximum amount of Blocks to scan.
     */
    public VeinBlockScanner2(Location origin, int maxScanSize) {
        super(origin, maxScanSize);
    }

    @Override
    public boolean shouldCollect(Location scannedLocation) {
        return scannedLocation.getBlock().equals(origin.getBlock());
    }

    @Override
    public boolean branchOnFailedCollect() {
        return false;
    }

    @Override
    public List<Location> nextLocationsToScan(Location scannedLocation) {

        List<Location> nextLocations = new ArrayList<>();

        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                for (int z = -1; z <= 1; z++) {

                    Location nextLocation = new Location(scannedLocation.getLevel(), scannedLocation.getX() + x, scannedLocation.getY() + y, scannedLocation.getZ() + z);
                    scan(nextLocation);
                }
            }
        }

        return List.of();
    }
}
