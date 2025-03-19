package com.calemi.nexus.compat;

import com.calemi.nexus.blockentity.NexusPortalCoreBlockEntity;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.IServerDataProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;

public enum NexusJadePortalCoreProvider implements IBlockComponentProvider, IServerDataProvider<BlockAccessor> {

    INSTANCE;

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendTooltip(ITooltip tooltip, BlockAccessor accessor, IPluginConfig config) {
        getTooltip(tooltip, accessor, config);
    }

    public static void getTooltip(ITooltip tooltip, BlockAccessor accessor, IPluginConfig config) {
        CompoundTag tag = accessor.getServerData();

        if (tag.getBoolean("HasPortalCoreAtDestination")) {

            if (tag.contains("DestinationPosX") && tag.contains("DestinationPosY") && tag.contains("DestinationPosZ") && tag.contains("DestinationDimPath")) {

                BlockPos destinationPos = new BlockPos(tag.getInt("DestinationPosX"), tag.getInt("DestinationPosY"), tag.getInt("DestinationPosZ"));

                tooltip.add(NexusPortalCoreBlockEntity.getFormattedCurrentDestinationText().append(": "));

                tooltip.add(Component.literal("- ").append(NexusPortalCoreBlockEntity.getFormattedDestinationNameText(tag.getString("DestinationPOIName"), tag.getString("DestinationDimPath"), ChatFormatting.GOLD, ChatFormatting.GRAY)));
                tooltip.add(Component.literal("- ").append(NexusPortalCoreBlockEntity.getFormattedDestinationPositionText(destinationPos, ChatFormatting.GOLD, ChatFormatting.GRAY)));
            }
        }

        else {
            tooltip.add(Component.translatable("screen.nexus.nexus_portal_core.text.no_destination"));
        }
    }

    @Override
    public void appendServerData(CompoundTag data, BlockAccessor accessor) {
        getServerData(data,  (NexusPortalCoreBlockEntity) accessor.getBlockEntity());
    }

    public static void getServerData(CompoundTag data, NexusPortalCoreBlockEntity coreBlockEntity) {

        data.putBoolean("HasPortalCoreAtDestination", coreBlockEntity.hasPortalCoreAtDestination());

        if (coreBlockEntity.hasPortalCoreAtDestination()) {

            if (coreBlockEntity.getDestinationPos() != null) {
                data.putInt("DestinationPosX", coreBlockEntity.getDestinationPos().getX());
                data.putInt("DestinationPosY", coreBlockEntity.getDestinationPos().getY());
                data.putInt("DestinationPosZ", coreBlockEntity.getDestinationPos().getZ());
            }

            if (coreBlockEntity.getDestinationDimensionRL() != null) {
                data.putString("DestinationDimPath", coreBlockEntity.getDestinationDimensionRL().getPath());
            }

            NexusPortalCoreBlockEntity destinationBlockEntity = coreBlockEntity.getDestinationPortalCore();

            if (destinationBlockEntity != null && destinationBlockEntity.getPoiName() != null) {
                data.putString("DestinationPOIName", destinationBlockEntity.getPoiName().getString());
            }
        }
    }

    @Override
    public ResourceLocation getUid() {
        return NexusJadePlugin.NEXUS_PORTAL_CORE;
    }

}