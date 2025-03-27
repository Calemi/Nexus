package com.calemi.nexus.world.dimension;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;

public class NexusDimensionHelper {

    public static boolean isInNexus(Entity entity) {
        return isInNexus(entity.level());
    }

    public static boolean isInNexus(Level level) {
        return level.dimension().equals(NexusDimensions.NEXUS_LEVEL);
    }

    public static boolean isDestinationValid(Level destinationLevel, BlockPos destinationPosition) {
        return destinationLevel != null && destinationPosition != null && destinationLevel.isInWorldBounds(destinationPosition);
    }

    public static BlockPos getDynamicBlockDestination(Level originLevel, BlockPos originPosition, int coordinateScale) {

        if (NexusDimensionHelper.isInNexus(originLevel)) {
            return NexusDimensionHelper.getOutsideBlockDestination(originPosition, coordinateScale);
        }

        return NexusDimensionHelper.getNexusBlockDestination(originPosition, coordinateScale);
    }

    public static BlockPos getNexusBlockDestination(BlockPos originPosition, int coordinateScale) {

        int x = originPosition.getX();
        int z = originPosition.getZ();

        x = Math.floorDiv(x, coordinateScale);
        z = Math.floorDiv(z, coordinateScale);

        return new BlockPos(x, 64, z);
    }

    public static BlockPos getOutsideBlockDestination(BlockPos originPosition, int coordinateScale) {

        int x = originPosition.getX();
        int y;
        int z = originPosition.getZ();

        x *= coordinateScale;
        z *= coordinateScale;

        return new BlockPos(x, originPosition.getY(), z);
    }

    public static int getSolidGroundLevel(Level destinationLevel, BlockPos blockPos) {

        int airCount = 0;

        for (int yCheck = destinationLevel.dimensionType().logicalHeight(); yCheck > destinationLevel.getMinBuildHeight(); yCheck--) {

            BlockPos checkPos = new BlockPos(blockPos.getX(), yCheck, blockPos.getZ());
            BlockState checkState = destinationLevel.getBlockState(checkPos);

            if (checkState.isSuffocating(destinationLevel, checkPos) || checkState.getBlock() instanceof LiquidBlock) {

                if (airCount >= 3) {
                    return yCheck + 1;
                }

                else {
                    airCount = 0;
                }
            }

            else {
                airCount++;
            }
        }

        return Integer.MAX_VALUE;
    }
}
