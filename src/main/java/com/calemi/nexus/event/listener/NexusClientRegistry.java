package com.calemi.nexus.event.listener;

import com.calemi.nexus.main.Nexus;
import com.calemi.nexus.main.NexusRef;
import com.calemi.nexus.regsitry.NexusBlocks;
import com.calemi.nexus.regsitry.NexusLists;
import com.calemi.nexus.render.CamoBlockColor;
import com.calemi.nexus.render.NexusPortalCoreBakedModel;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.world.level.GrassColor;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ModelEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;

@EventBusSubscriber(modid = NexusRef.ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class NexusClientRegistry {

    @SubscribeEvent
    public static void onBlockColorHandlersRegistry(RegisterColorHandlersEvent.Block event) {

        Nexus.LOGGER.info("Registering: Block Colors - Start");

        event.register(new CamoBlockColor(), NexusLists.toBlockArray(NexusLists.NEXUS_PORTAL_CORE_BLOCKS));

        event.register((state, blockAndTintGetter, pos, tintIndex) -> {
            if (tintIndex != 0) return blockAndTintGetter != null && pos != null ? BiomeColors.getAverageGrassColor(blockAndTintGetter, pos) : GrassColor.getDefaultColor();
            else return -1;
        }, NexusBlocks.PURPLE_PETALS.get());

        Nexus.LOGGER.info("Registering: Block Colors - End");
    }

    @SubscribeEvent
    public static void onModelBake(ModelEvent.ModifyBakingResult event) {

        Nexus.LOGGER.info("Registering: Block Models - Start");

        event.getModels().entrySet().stream()
                .filter(entry -> entry.getKey().id().getNamespace().equals(NexusRef.ID) && entry.getKey().id().getPath().contains("nexus_portal_core"))
                .forEach(entry -> event.getModels().put(entry.getKey(), new NexusPortalCoreBakedModel(entry.getValue())));

        Nexus.LOGGER.info("Registering: Block Models - End");
    }
}