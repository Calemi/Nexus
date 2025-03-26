package com.calemi.ccore.api.client.render;

import com.calemi.ccore.api.boat.CBoatType;
import com.calemi.ccore.api.boat.CBoatTypeRegistry;
import com.calemi.ccore.api.init.CEntities;
import com.calemi.nexus.main.Nexus;
import com.calemi.nexus.main.NexusRef;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@EventBusSubscriber(modid = NexusRef.ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class CClientRegistry {

    @SubscribeEvent
    private static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {

        Nexus.LOGGER.debug("ENTITY RENDER REGISTRY");

        event.registerEntityRenderer(CEntities.BOAT.get(), context -> new CBoatRenderer(context, false));
        event.registerEntityRenderer(CEntities.CHEST_BOAT.get(), context -> new CBoatRenderer(context, true));
    }

    @SubscribeEvent
    private static void registerEntityRenderers(EntityRenderersEvent.RegisterLayerDefinitions event) {

        Nexus.LOGGER.debug("ENTITY LAYER REGISTRY");

        LayerDefinition boatLayerDefinition = BoatModel.createBodyModel();
        LayerDefinition chestBoatLayerDefinition = ChestBoatModel.createBodyModel();

        for (CBoatType type : CBoatTypeRegistry.getTypes())
        {
            event.registerLayerDefinition(CBoatRenderer.createBoatModelName(type), () -> boatLayerDefinition);
            event.registerLayerDefinition(CBoatRenderer.createChestBoatModelName(type), () -> chestBoatLayerDefinition);
        }
    }
}