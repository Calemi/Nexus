package com.calemi.nexus.compat;

import com.calemi.nexus.block.NexusPortalBlock;
import com.calemi.nexus.block.entity.NexusPortalCoreBlockEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.IServerDataProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;

public enum NexusJadePortalProvider implements IBlockComponentProvider, IServerDataProvider<BlockAccessor> {

    INSTANCE;

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendTooltip(ITooltip tooltip, BlockAccessor accessor, IPluginConfig config) {
        NexusJadePortalCoreProvider.getTooltip(tooltip, accessor, config);
    }

    @Override
    public void appendServerData(CompoundTag data, BlockAccessor accessor) {

        NexusPortalCoreBlockEntity coreBlockEntity = NexusPortalBlock.getPortalCore(accessor.getLevel(), accessor.getPosition());

        if (coreBlockEntity == null) return;

        NexusJadePortalCoreProvider.getServerData(data, coreBlockEntity);
    }

    @Override
    public ResourceLocation getUid() {
        return NexusJadePlugin.NEXUS_PORTAL;
    }
}