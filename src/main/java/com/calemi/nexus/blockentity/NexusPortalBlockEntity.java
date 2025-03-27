package com.calemi.nexus.blockentity;

import com.calemi.ccore.api.blockentity.BaseBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.state.BlockState;

public class NexusPortalBlockEntity extends BaseBlockEntity {

    private BlockPos corePosition;

    public NexusPortalBlockEntity(BlockPos pos, BlockState state) {
        super(NexusBlockEntities.NEXUS_PORTAL.get(), pos, state);
    }

    public BlockPos getCorePosition() {
        return corePosition;
    }

    public void setCorePosition(BlockPos corePosition) {
        this.corePosition = corePosition;
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);

        if (corePosition != null) {
            tag.putInt("PortalCorePosX", corePosition.getX());
            tag.putInt("PortalCorePosY", corePosition.getY());
            tag.putInt("PortalCorePosZ", corePosition.getZ());
        }
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);

        if (tag.contains("PortalCorePosX") && tag.contains("PortalCorePosY") && tag.contains("PortalCorePosZ")) {
            corePosition = new BlockPos(tag.getInt("PortalCorePosX"), tag.getInt("PortalCorePosY"), tag.getInt("PortalCorePosZ"));
        }

        else {
            corePosition = null;
        }
    }
}
