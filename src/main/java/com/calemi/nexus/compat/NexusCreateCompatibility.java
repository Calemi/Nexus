package com.calemi.nexus.compat;

import com.calemi.nexus.block.NexusPortalBlock;
import com.calemi.nexus.block.entity.NexusPortalBlockEntity;
import com.calemi.nexus.block.entity.NexusPortalCoreBlockEntity;
import com.calemi.nexus.main.Nexus;
import com.calemi.nexus.util.NexusLists;
import com.simibubi.create.api.contraption.train.PortalTrackProvider;
import net.createmod.catnip.math.BlockFace;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class NexusCreateCompatibility {

    public static void init() {

        Nexus.LOGGER.info("Registering: Create Compatibility - Start");

        for (Block block : NexusLists.NEXUS_PORTAL_BLOCKS) {
            PortalTrackProvider.REGISTRY.register(block, NexusCreateCompatibility::portalProvider);
        }

        Nexus.LOGGER.info("Registering: Create Compatibility - End");
    }

    private static PortalTrackProvider.Exit portalProvider(ServerLevel level, BlockFace inboundTrack) {

        BlockPos portalPos = inboundTrack.getConnectedPos();
        BlockState portalState = level.getBlockState(portalPos);
        Direction.Axis portalAxis = portalState.getValue(NexusPortalBlock.AXIS);

        if (portalAxis.isVertical()) {
            return null;
        }

        if (!(level.getBlockEntity(portalPos) instanceof NexusPortalBlockEntity portalBlockEntity)) {
            return null;
        }

        if (!(level.getBlockEntity(portalBlockEntity.getCorePosition()) instanceof NexusPortalCoreBlockEntity originPortalCore)) {
            return null;
        }

        NexusPortalCoreBlockEntity destinationPortalCore = originPortalCore.getDestinationPortalCore();

        if (destinationPortalCore == null || !destinationPortalCore.isPortalActive()) {
            return null;
        }

        Level destinationLevel = originPortalCore.getDestinationLevel();

        if (destinationLevel == null) {
            return null;
        }

        BlockPos destinationPortalPos = destinationPortalCore.getProjectionPosition();
        BlockState destinationPortalState = destinationLevel.getBlockState(destinationPortalPos);

        Direction.Axis destinationPortalAxis = destinationPortalState.getValue(NexusPortalBlock.AXIS);

        if (destinationPortalAxis.isVertical()) {
            return null;
        }

        Direction targetDirection = inboundTrack.getFace();

        if (targetDirection.getAxis().equals(destinationPortalAxis)) {
            targetDirection = targetDirection.getClockWise();
        }

        BlockPos destinationTrackPos = destinationPortalPos.relative(targetDirection);

        return new PortalTrackProvider.Exit((ServerLevel) destinationLevel, new BlockFace(destinationTrackPos, targetDirection.getOpposite()));
    }
}
