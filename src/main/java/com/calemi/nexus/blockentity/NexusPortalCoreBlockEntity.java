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
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

public class NexusPortalCoreBlockEntity extends BaseBlockEntity {

    private ResourceLocation destinationDimResourceLocation;
    private BlockPos destinationPos;
    private DyeColor portalDyeColor;

    @Nullable
    private Component poiName;

    public NexusPortalCoreBlockEntity(BlockPos pos, BlockState blockState) {
        super(NexusBlockEntities.NEXUS_PORTAL_CORE.get(), pos, blockState);
        portalDyeColor = DyeColor.PURPLE;
    }

    public ResourceLocation getDestinationDimResourceLocation() {
        return destinationDimResourceLocation;
    }

    public void setDestinationDimResourceLocation(ResourceLocation destinationDimResourceLocation) {
        this.destinationDimResourceLocation = destinationDimResourceLocation;
    }

    public BlockPos getDestinationPos() {
        return destinationPos;
    }

    public void setDestinationPos(BlockPos destinationPos) {
        this.destinationPos = destinationPos;

        if (destinationPos == null && level != null) {

            if (level.getBlockState(getBlockPos().above()).getBlock() instanceof NexusPortalBlock) {
                level.setBlock(getBlockPos().above(), Blocks.AIR.defaultBlockState(), 2);
            }
        }
    }

    public DyeColor getPortalDyeColor() {
        return portalDyeColor;
    }

    public void setPortalDyeColor(DyeColor portalDyeColor) {
        this.portalDyeColor = portalDyeColor;
    }

    @Nullable
    public Component getPoiName() {
        return poiName;
    }

    public void setPoiName(@Nullable Component poiName) {
        this.poiName = poiName;
    }

    public ResourceKey<Level> getDestinationDimResourceKey() {
        return ResourceKey.create(Registries.DIMENSION, getDestinationDimResourceLocation());
    }

    public Level getDestinationLevel() {

        if (level != null && !level.isClientSide() && level instanceof ServerLevel serverLevel && getDestinationDimResourceLocation() != null) {
            return serverLevel.getServer().getLevel(getDestinationDimResourceKey());
        }

        return null;
    }

    public boolean hasPortalCoreAtDestination() {
        return getDestinationPortalCore() != null;
    }

    public NexusPortalCoreBlockEntity getDestinationPortalCore() {

        BlockPos destinationPos = getDestinationPos();

        if (destinationPos == null) return null;

        Level destinationLevel = getDestinationLevel();

        if (destinationLevel == null) return null;

        if (!(destinationLevel.getBlockEntity(getDestinationPos()) instanceof NexusPortalCoreBlockEntity destinationBlockEntity)) return null;

        return destinationBlockEntity;
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

    public static MutableComponent getFormattedCurrentDestinationText() {
        return Component.translatable("screen.nexus.nexus_portal_core.text.current_destination").withStyle(ChatFormatting.LIGHT_PURPLE);
    }

    public static MutableComponent getFormattedDestinationNameText(String destinationPOIName, String destinationDimensionPath, ChatFormatting highlight, ChatFormatting normal) {

        if (!destinationPOIName.isEmpty()) {
            return Component.empty()
                    .append(highlight + destinationPOIName)
                    .append(" (")
                    .append(getFormattedDestinationDimensionText(destinationDimensionPath))
                    .append(")")
                    .withStyle(normal);
        }

        return getFormattedDestinationDimensionText(destinationDimensionPath).withStyle(highlight);
    }

    public static MutableComponent getFormattedDestinationDimensionText(String destinationDimensionPath) {
        return Component.literal(destinationDimensionPath.toUpperCase().replaceAll("_", " "));
    }

    public static MutableComponent getFormattedDestinationPositionText(BlockPos destinationPos, ChatFormatting highlight, ChatFormatting normal) {
        return Component
                .literal("[x")
                .append(String.valueOf(highlight) + destinationPos.getX())
                .append(", y")
                .append(String.valueOf(highlight) + destinationPos.getY())
                .append(", z")
                .append(String.valueOf(highlight) + destinationPos.getZ())
                .append("]")
                .withStyle(normal);
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

        if (poiName != null) {
            tag.putString("POIName", Component.Serializer.toJson(poiName, registries));
        }
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

        if (tag.contains("POIName", 8)) {
            poiName = parseCustomNameSafe(tag.getString("POIName"), registries);
        }
    }

    @Override
    protected void applyImplicitComponents(BlockEntity.DataComponentInput componentInput) {
        super.applyImplicitComponents(componentInput);
        poiName = componentInput.get(DataComponents.CUSTOM_NAME);
    }
}
