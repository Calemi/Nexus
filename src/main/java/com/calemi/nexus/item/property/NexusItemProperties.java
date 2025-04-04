package com.calemi.nexus.item.property;

import com.calemi.nexus.item.NexusItems;
import com.calemi.nexus.main.NexusRef;
import net.minecraft.client.renderer.item.ItemProperties;

public class NexusItemProperties {

    public static void init() {
        ItemProperties.register(NexusItems.SPEEDOMETER.asItem(), NexusRef.rl("speed"), new SpeedometerProperty());
    }
}
