package com.calemi.nexus.blockentity;

import com.calemi.ccore.api.blockentity.BaseBlockEntity;
import com.calemi.nexus.block.NexusPortalBlock;
import com.calemi.nexus.regsitry.NexusBlockEntities;
import com.calemi.nexus.util.NexusHelper;
import com.calemi.nexus.util.NexusSoundHelper;
import com.calemi.nexus.util.TeleportHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class NexusPortalCoreBlockEntity extends BaseBlockEntity {

    private ResourceLocation destinationDimResourceLocation;
    private BlockPos destinationPos;
    private DyeColor portalDyeColor;

    public NexusPortalCoreBlockEntity(BlockPos pos, BlockState blockState) {
        super(NexusBlockEntities.NEXUS_PORTAL_CORE.get(), pos, blockState);
        portalDyeColor = DyeColor.PURPLE;
    }

    public BlockPos getDestinationPos() {
        return destinationPos;
    }

    public ResourceLocation getDestinationDimResourceLocation() {
        return destinationDimResourceLocation;
    }

    public ResourceKey<Level> getDestinationDimResourceKey() {
        return ResourceKey.create(Registries.DIMENSION, getDestinationDimResourceLocation());
    }

    public DyeColor getPortalDyeColor() {
        return portalDyeColor;
    }

    public void setDestinationDimResourceLocation(ResourceLocation destinationDimResourceLocation) {
        this.destinationDimResourceLocation = destinationDimResourceLocation;
    }

    public void setDestinationPos(BlockPos destinationPos) {
        this.destinationPos = destinationPos;

        if (destinationPos == null && level != null) {

            if (level.getBlockState(getBlockPos().above()).getBlock() instanceof NexusPortalBlock) {
                level.setBlock(getBlockPos().above(), Blocks.AIR.defaultBlockState(), 2);
            }
        }
    }

    public Level getDestinationLevel() {

        if (level != null && !level.isClientSide() && level instanceof ServerLevel serverLevel) {
            return serverLevel.getServer().getLevel(getDestinationDimResourceKey());
        }

        return null;
    }

    public void setPortalDyeColor(DyeColor portalDyeColor) {
        this.portalDyeColor = portalDyeColor;
    }

    public boolean hasPortalCoreAtDestination() {

        if (getDestinationDimResourceLocation() == null || getDestinationPos() == null) return false;

        if (!(level instanceof ServerLevel serverLevel)) return false;

        Level destinationLevel = serverLevel.getServer().getLevel(getDestinationDimResourceKey());

        if (destinationLevel == null) return false;

        return destinationLevel.getBlockState(getDestinationPos()).getBlock().equals(getBlockState().getBlock());
    }

    public void teleportEntity(Entity entity) {

        if (level == null) return;

        Player player = (entity instanceof Player) ? (Player) entity : null;

        if (!NexusHelper.isDestinationValid(getDestinationLevel(), getDestinationPos())) {

            if (player != null) {
                player.sendSystemMessage(Component.translatable("message.nexus.invalid_destination").withStyle(ChatFormatting.RED));
                NexusSoundHelper.playErrorSound(player);
            }

            return;
        }

        if (!hasPortalCoreAtDestination()) {

            setDestinationPos(null);
            markUpdated();

            if (player != null) {
                player.sendSystemMessage(Component.translatable("message.nexus.teleport.no_core").withStyle(ChatFormatting.RED));
                NexusSoundHelper.playErrorSound(player);
            }

            return;
        }

        if (player != null) NexusSoundHelper.playTeleportSound(player);
        TeleportHelper.teleportToWorld(entity, getDestinationPos(), getDestinationDimResourceKey());
        if (player != null) NexusSoundHelper.playTeleportSound(player);
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);

        if (destinationDimResourceLocation != null) {
            tag.putString("DestinationDimNamespace", destinationDimResourceLocation.getNamespace());
            tag.putString("DestinationDimPath", destinationDimResourceLocation.getPath());
        }

        if (destinationPos != null) {
            tag.putInt("DestinationPosX", destinationPos.getX());
            tag.putInt("DestinationPosY", destinationPos.getY());
            tag.putInt("DestinationPosZ", destinationPos.getZ());
        }

        tag.putInt("DyeColorId", portalDyeColor.getId());
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);

        if (tag.contains("DestinationDimNamespace") && tag.contains("DestinationDimPath")) {
            destinationDimResourceLocation = ResourceLocation.fromNamespaceAndPath(tag.getString("DestinationDimNamespace"), tag.getString("DestinationDimPath"));
        }

        if (tag.contains("DestinationPosX") && tag.contains("DestinationPosY") && tag.contains("DestinationPosZ")) {
            destinationPos = new BlockPos(tag.getInt("DestinationPosX"), tag.getInt("DestinationPosY"), tag.getInt("DestinationPosZ"));
        }

        else {
            destinationPos = null;
        }

        portalDyeColor = DyeColor.byId(tag.getInt("DyeColorId"));
    }
}
