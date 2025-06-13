package com.calemi.nexus.compat;

import com.calemi.nexus.block.NexusPortalBlock;
import com.calemi.nexus.block.NexusPortalCoreBlock;
import com.calemi.nexus.block.entity.NexusPortalBlockEntity;
import com.calemi.nexus.block.entity.NexusPortalCoreBlockEntity;
import com.calemi.nexus.main.NexusRef;
import net.minecraft.resources.ResourceLocation;
import snownee.jade.api.IWailaClientRegistration;
import snownee.jade.api.IWailaCommonRegistration;
import snownee.jade.api.IWailaPlugin;
import snownee.jade.api.WailaPlugin;

@WailaPlugin
public class NexusJadePlugin implements IWailaPlugin {

    public static final ResourceLocation NEXUS_PORTAL_CORE = NexusRef.rl("nexus_portal_core");
    public static final ResourceLocation NEXUS_PORTAL = NexusRef.rl("nexus_portal");

    @Override
    public void register(IWailaCommonRegistration registration) {
        registration.registerBlockDataProvider(NexusJadePortalCoreProvider.INSTANCE, NexusPortalCoreBlockEntity.class);
        registration.registerBlockDataProvider(NexusJadePortalProvider.INSTANCE, NexusPortalBlockEntity.class);
    }

    @Override
    public void registerClient(IWailaClientRegistration registration) {
        registration.registerBlockComponent(NexusJadePortalCoreProvider.INSTANCE, NexusPortalCoreBlock.class);
        registration.registerBlockComponent(NexusJadePortalProvider.INSTANCE, NexusPortalBlock.class);
    }
}
