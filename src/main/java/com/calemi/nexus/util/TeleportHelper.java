package com.calemi.nexus.util;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.RelativeMovement;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;

public class TeleportHelper {

    public static Vec3 getEntityTeleportDestination(BlockPos destination) {

        double x = destination.getX();
        double y = destination.getY();
        double z = destination.getZ();

        x += 0.5D;
        z += 0.5D;

        return new Vec3(x, y, z);
    }

    public static void teleportToWorld(Entity entity, BlockPos destination, ResourceKey<Level> dimensionKey) {

        if (entity.level() instanceof ServerLevel level) {

            ServerLevel destLevel = level.getServer().getLevel(dimensionKey);

            if (destLevel == null) {
                return;
            }

            Vec3 teleportDestination = TeleportHelper.getEntityTeleportDestination(destination);

            entity.teleportTo(destLevel, teleportDestination.x, teleportDestination.y, teleportDestination.z, EnumSet.noneOf(RelativeMovement.class), entity.getYRot(), entity.getXRot());
        }
    }
}
