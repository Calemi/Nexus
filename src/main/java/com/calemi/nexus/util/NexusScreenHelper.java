package com.calemi.nexus.util;

import com.calemi.nexus.block.entity.NexusPortalCoreBlockEntity;
import com.calemi.nexus.client.screen.NexusPortalCoreScreen;
import net.minecraft.client.Minecraft;

public class NexusScreenHelper {

    public static void openNexusPortalCoreScreen(NexusPortalCoreBlockEntity portalCoreBlockEntity) {
        Minecraft.getInstance().setScreen(new NexusPortalCoreScreen(portalCoreBlockEntity));
    }
}
