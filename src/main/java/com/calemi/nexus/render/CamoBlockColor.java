package com.calemi.nexus.render;

import com.calemi.nexus.block.NexusPortalCoreBlock;
import com.calemi.nexus.blockentity.NexusPortalCoreBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class CamoBlockColor implements BlockColor {

    @Override
    public int getColor(@Nonnull BlockState state, @Nullable BlockAndTintGetter lightReader, @Nullable BlockPos pos, int tintIndex) {

        if (lightReader == null || pos == null) {
            return -1;
        }

        if (state.getBlock() instanceof NexusPortalCoreBlock && lightReader.getBlockEntity(pos) instanceof NexusPortalCoreBlockEntity blockEntity) {
            if (blockEntity.getCamoState() != null) {
                return Minecraft.getInstance().getBlockColors().getColor(blockEntity.getCamoState(), lightReader, pos, tintIndex);
            }
        }
        return -1;
    }
}