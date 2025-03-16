package com.calemi.ccore.api.scanner2;

import com.calemi.ccore.api.location.Location;

import java.util.ArrayList;
import java.util.List;

/**
 * Used to scan through blocks of the same kind. Collects all scanned blocks in a list.
 */
public abstract class BlockScanner2 {

    public final ArrayList<Location> collectedLocations = new ArrayList<>();

    public final Location origin;
    public final int maxScanSize;
    public boolean halted = false;

    /**
     * Creates a BlockScanner
     * @param origin The origin Location of the scan.
     * @param maxScanSize The maximum amount of Blocks to scan.
     */
    public BlockScanner2(Location origin, int maxScanSize) {
        this.origin = origin;
        this.maxScanSize = maxScanSize;
    }

    /**
     * @param scannedLocation The currently scanned Location.
     * @return whether the scanner should collect this location or not.
     */
    public abstract boolean shouldCollect(Location scannedLocation);

    public abstract boolean branchOnFailedCollect();

    public abstract List<Location> nextLocationsToScan(Location scannedLocation);

    /**
     * Starts a scan that will search adjacent Blocks.
     */
    public void start() {
        reset();
        scan(origin);
    }

    /**
     * Clears the buffer.
     */
    public void reset() {
        collectedLocations.clear();
        halted = false;
    }

    /**
     * Recursive method used to search through similar Blocks.
     * @param location The Location to search.
     */
    public void scan(Location location) {

        if (halted || collectedLocations.size() >= maxScanSize) {
            return;
        }

        if (!collectedLocations.contains(location)) {

            if (shouldCollect(location)) {
                collectedLocations.add(location);
            }

            else if (!branchOnFailedCollect()) {
                return;
            }

            for (Location nextLocationToScan : nextLocationsToScan(location)) {
                scan(nextLocationToScan);
            }
        }
    }

    /**
     * @param location The Location to test.
     * @return True, if the given location is in the buffer.
     */
    public boolean contains(Location location) {

        for (Location nextLocation : collectedLocations) {

            if (nextLocation.equals(location)) {
                return true;
            }
        }

        return false;
    }
}