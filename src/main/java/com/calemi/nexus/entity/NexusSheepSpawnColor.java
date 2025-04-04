package com.calemi.nexus.entity;

import com.calemi.nexus.main.Nexus;
import com.calemi.nexus.world.dimension.NexusDimensionHelper;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;

import java.util.List;

public class NexusSheepSpawnColor {

    @SubscribeEvent
    public void onPlayerTick(EntityJoinLevelEvent event) {

        if (event.loadedFromDisk()) return;

        Entity entity = event.getEntity();
        Level level = entity.level();

        if (level.isClientSide()) return;

        if (entity instanceof Sheep sheep) {

            if (NexusDimensionHelper.isInNexus(entity)) {

                List<DyeColor> possibleColors = List.of(DyeColor.WHITE, DyeColor.LIME, DyeColor.LIGHT_BLUE, DyeColor.CYAN, DyeColor.PURPLE, DyeColor.MAGENTA);

                sheep.setColor(possibleColors.get(level.getRandom().nextInt(possibleColors.size())));
                Nexus.LOGGER.debug(sheep.getColor());
            }
        }
    }
}
