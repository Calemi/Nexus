package com.calemi.nexus.blockentity;

import com.calemi.ccore.api.blockentity.BaseBlockEntity;
import com.calemi.ccore.api.location.Location;
import com.calemi.nexus.block.NexusPortalBlock;
import com.calemi.nexus.block.NexusPortalCoreBlock;
import com.calemi.nexus.capability.NexusCapabilityHandler;
import com.calemi.nexus.client.model.NexusPortalCoreBakedModel;
import com.calemi.nexus.config.NexusConfig;
import com.calemi.nexus.util.NexusMessengers;
import com.calemi.nexus.util.NexusSoundHelper;
import com.calemi.nexus.util.TeleportHelper;
import com.calemi.nexus.util.scanner.PortalScanner;
import com.calemi.nexus.util.scanner.PortalSpaceScanner;
import com.calemi.nexus.world.dimension.NexusDimensionHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.model.data.ModelData;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NexusPortalCoreBlockEntity extends BaseBlockEntity {

    private ResourceLocation destinationDimensionRL;
    private BlockPos destinationPos;
    private Component poiName;
    private BlockState camoState;

    private DyeColor portalColorBase;
    private final HashMap<BlockPos, DyeColor> portalColorPattern;

    public NexusPortalCoreBlockEntity(BlockPos pos, BlockState blockState) {
        super(NexusBlockEntities.NEXUS_PORTAL_CORE.get(), pos, blockState);
        portalColorBase = DyeColor.PURPLE;
        portalColorPattern = new HashMap<>();
    }

    public ResourceLocation getDestinationDimensionRL() {
        return destinationDimensionRL;
    }

    public void setDestinationDimensionRL(ResourceLocation destinationDimensionRL) {
        this.destinationDimensionRL = destinationDimensionRL;
    }

    public BlockPos getDestinationPos() {
        return destinationPos;
    }

    public void setDestinationPos(@Nullable BlockPos destinationPos) {
        this.destinationPos = destinationPos;

        if (destinationPos == null && isPortalActive()) {
            unlightPortal();
        }
    }

    public Component getPoiName() {
        return poiName;
    }

    public void setPoiName(Component poiName) {
        this.poiName = poiName;
    }

    public BlockState getCamoState() {
        return camoState;
    }

    public void setCamoState(BlockState camoState) {
        this.camoState = camoState;
    }

    public boolean isValidCamoState(BlockState state) {
        if (!NexusConfig.server.portalCoreCamo.get()) return false;
        if (state == null) return true;
        if (state.getBlock() == Blocks.AIR) return false;
        if (state.getBlock() instanceof NexusPortalCoreBlock) return false;
        if (state.getRenderShape() != RenderShape.MODEL) return false;
        if (getCamoState() != null && getCamoState().is(state.getBlock())) return false;
        return state.isCollisionShapeFullBlock(getLevel(), getBlockPos());
    }

    public DyeColor getPortalColorBase() {
        return portalColorBase;
    }

    public void setPortalColorBase(DyeColor portalColorBase) {
        this.portalColorBase = portalColorBase;
    }

    public ResourceKey<Level> getDestinationDimensionRK() {

        ResourceLocation destinationDimensionRL = getDestinationDimensionRL();

        if (destinationDimensionRL == null) return null;

        return ResourceKey.create(Registries.DIMENSION, destinationDimensionRL);
    }

    public Level getDestinationLevel() {

        Level originLevel = getLevel();

        if (originLevel == null) return null;
        if (!(originLevel instanceof ServerLevel originServerLevel)) return null;

        ResourceKey<Level> destinationDimensionRK = getDestinationDimensionRK();

        if (destinationDimensionRK == null) return null;

        return originServerLevel.getServer().getLevel(destinationDimensionRK);
    }

    public NexusPortalCoreBlockEntity getDestinationPortalCore() {

        BlockPos destinationPos = getDestinationPos();

        if (destinationPos == null) return null;

        Level destinationLevel = getDestinationLevel();

        if (destinationLevel == null) return null;

        if (!(destinationLevel.getBlockEntity(getDestinationPos()) instanceof NexusPortalCoreBlockEntity destinationBlockEntity)) return null;

        return destinationBlockEntity;
    }

    public boolean hasPortalCoreAtDestination() {
        return getDestinationPortalCore() != null;
    }

    public Direction getProjectionDirection() {

        BlockState state = getBlockState();

        if (!(state.getBlock() instanceof NexusPortalCoreBlock)) return Direction.UP;

        return getBlockState().getValue(NexusPortalCoreBlock.FACING);
    }

    public Direction.Axis getProjectionAxis() {

        Direction projectionDirection = getProjectionDirection();

        if (projectionDirection.getAxis().isHorizontal()) {
            return Direction.Axis.Y;
        }

        BlockState state = getBlockState();

        if (!(state.getBlock() instanceof NexusPortalCoreBlock)) return Direction.Axis.X;

        return state.getValue(NexusPortalCoreBlock.AXIS);
    }

    public BlockPos getProjectionPosition() {
        return getBlockPos().relative(getProjectionDirection());
    }

    public boolean hasValidDestination(@Nullable Entity feedbackEntity) {

        if (!NexusDimensionHelper.isDestinationValid(getDestinationLevel(), getDestinationPos())) {

            setDestinationPos(null);
            setChanged();

            NexusMessengers.NEXUS_PORTAL_CORE.send(Component.translatable("message.nexus.invalid_destination").withStyle(ChatFormatting.RED), feedbackEntity);
            NexusSoundHelper.playErrorSound(getLocation());

            return false;
        }

        NexusPortalCoreBlockEntity destinationPortalCore = getDestinationPortalCore();

        if (destinationPortalCore == null) {

            setDestinationPos(null);
            setChanged();

            NexusMessengers.NEXUS_PORTAL_CORE.send(Component.translatable("message.nexus.no_core").withStyle(ChatFormatting.RED), feedbackEntity);
            NexusSoundHelper.playErrorSound(getLocation());

            return false;
        }

        NexusPortalCoreBlockEntity destinationPortalCoreDestination = destinationPortalCore.getDestinationPortalCore();

        if (destinationPortalCoreDestination == null || !destinationPortalCoreDestination.equals(this)) {

            setDestinationPos(null);
            setChanged();

            NexusMessengers.NEXUS_PORTAL_CORE.send(Component.translatable("message.nexus.no_link").withStyle(ChatFormatting.RED), feedbackEntity);
            NexusSoundHelper.playErrorSound(getLocation());

            return false;
        }

        return true;
    }

    public boolean isPortalActive() {
        return level.getBlockState(getProjectionPosition()).getBlock() instanceof NexusPortalBlock;
    }

    public List<BlockPos> getPortalPositions() {
        PortalScanner scanner = new PortalScanner(new Location(level, getProjectionPosition()), getProjectionAxis(), true, NexusConfig.server.maxPortalSize.get());
        scanner.start();
        return scanner.getCollectedPositions();
    }

    public void savePortalColorPattern(List<BlockPos> portalLocations) {

        Level level = getLevel();

        if (level == null) return;

        portalColorPattern.clear();

        portalLocations.forEach(blockPos -> {

            if (level.getBlockState(blockPos).getBlock() instanceof NexusPortalBlock portal) {
                portalColorPattern.put(blockPos, portal.getColor());
            }
        });
    }

    public void togglePortal(@Nullable Entity feedbackEntity) {

        Level level = getLevel();

        if (level == null) return;

        BlockPos projectionPosition = getProjectionPosition();

        BlockState projectionState = level.getBlockState(projectionPosition);

        if (!(projectionState.getBlock() instanceof NexusPortalBlock) && !level.getBlockState(projectionPosition).isAir()) {
            NexusMessengers.NEXUS_PORTAL_CORE.send(Component.translatable("message.nexus.light_portal.obstructed").withStyle(ChatFormatting.RED), feedbackEntity);
            NexusSoundHelper.playErrorSound(getLocation());
            return;
        }

        if (isPortalActive()) {
            unlightPortal();
        }

        else lightPortal(feedbackEntity);
    }

    public void unlightPortal() {

        Level level = getLevel();

        if (level == null) return;

        List<BlockPos> portalPositions = getPortalPositions();

        savePortalColorPattern(portalPositions);

        portalPositions.forEach(blockPos -> level.setBlock(blockPos, Blocks.AIR.defaultBlockState(), 2));
    }

    public void lightPortal(@Nullable Entity feedbackEntity) {

        if (!hasValidDestination(feedbackEntity)) return;

        Level level = getLevel();

        if (level == null) return;

        BlockPos projectionPosition = getProjectionPosition();
        Direction.Axis projectionAxis = getProjectionAxis();

        PortalSpaceScanner scanner = new PortalSpaceScanner(new Location(level, projectionPosition), projectionAxis, NexusConfig.server.maxPortalSize.get() + 1);
        scanner.start();

        List<BlockPos> collectedPositions = scanner.getCollectedPositions();

        if (scanner.isHalted()) {
            NexusMessengers.NEXUS_PORTAL_CORE.send(Component.translatable("message.nexus.light_portal.invalid_frame").withStyle(ChatFormatting.RED), feedbackEntity);
            NexusSoundHelper.playErrorSound(getLocation());
        }

        else if (collectedPositions.size() > scanner.getMaxCollectionSize() - 1) {
            NexusMessengers.NEXUS_PORTAL_CORE.send(Component.translatable("message.nexus.light_portal.overflowing_frame").withStyle(ChatFormatting.RED), feedbackEntity);
            NexusSoundHelper.playErrorSound(getLocation());
        }

        else {
            scanner.getCollectedPositions().forEach(blockPos -> {

                Block portalBlock = NexusPortalBlock.fromDye(portalColorPattern.getOrDefault(blockPos, getPortalColorBase()));
                level.setBlock(blockPos, portalBlock.defaultBlockState().setValue(NexusPortalBlock.AXIS, scanner.getAxis()), 3);

                if (level.getBlockEntity(blockPos) instanceof NexusPortalBlockEntity portalBlockEntity) {
                    portalBlockEntity.setCorePosition(getBlockPos());
                }
            });

            NexusMessengers.NEXUS_PORTAL_CORE.send(Component.translatable("message.nexus.light_portal.success").withStyle(ChatFormatting.GREEN), feedbackEntity);
            NexusSoundHelper.playSuccessSound(getLocation());
        }
    }

    public void teleportEntity(Entity feedbackEntity) {

        if (!hasValidDestination(feedbackEntity)) return;

        Player player = null;

        if (feedbackEntity instanceof Player) player = (Player) feedbackEntity;

        if (player != null && !NexusCapabilityHandler.isDimensionUnlocked(player, getDestinationDimensionRL())) {
            feedbackEntity.sendSystemMessage(Component.translatable("message.nexus.teleport.locked_dimension").withStyle(ChatFormatting.RED));
            NexusSoundHelper.playErrorSound(new Location(feedbackEntity));
            return;
        }

        NexusPortalCoreBlockEntity destinationPortalCore = getDestinationPortalCore();

        if (player != null) NexusSoundHelper.playTeleportSound(new Location(feedbackEntity));
        TeleportHelper.teleportToWorld(feedbackEntity, destinationPortalCore.getProjectionPosition(), getDestinationDimensionRK());
        if (player != null) NexusSoundHelper.playTeleportSound(new Location(feedbackEntity));
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
    public ModelData getModelData() {
        return ModelData.builder().with(NexusPortalCoreBakedModel.CAMO_STATE, camoState).build();
    }

    @Override
    public void setChanged() {
        super.setChanged();

        if (level != null && !level.isClientSide()) {

            level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), Block.UPDATE_ALL);
            level.getLightEngine().checkBlock(getBlockPos());
        }

        requestModelDataUpdate();

        var auxLightManager = level != null ? level.getAuxLightManager(worldPosition) : null;
        if (auxLightManager == null) {
            return;
        }

        auxLightManager.setLightAt(
                worldPosition,
                camoState != null ? camoState.getLightEmission(level, worldPosition) : 0
        );
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);

        if (destinationDimensionRL != null) {
            tag.putString("DestinationDimNamespace", destinationDimensionRL.getNamespace());
            tag.putString("DestinationDimPath", destinationDimensionRL.getPath());
        }

        if (destinationPos != null) {
            tag.putInt("DestinationPosX", destinationPos.getX());
            tag.putInt("DestinationPosY", destinationPos.getY());
            tag.putInt("DestinationPosZ", destinationPos.getZ());
        }

        if (poiName != null) {
            tag.putString("POIName", Component.Serializer.toJson(poiName, registries));
        }

        if (camoState != null) tag.put("CamoState", NbtUtils.writeBlockState(camoState));

        tag.putInt("PortalColorBaseId", portalColorBase.getId());

        ListTag portalColorPatternList = new ListTag();

        for (Map.Entry<BlockPos, DyeColor> entry : portalColorPattern.entrySet()) {

            CompoundTag entryTag = new CompoundTag();
            entryTag.putInt("PosX", entry.getKey().getX());
            entryTag.putInt("PosY", entry.getKey().getY());
            entryTag.putInt("PosZ", entry.getKey().getZ());
            entryTag.putInt("ColorId", entry.getValue().getId());

            portalColorPatternList.add(entryTag);
        }

        tag.put("PortalColorPattern", portalColorPatternList);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);

        if (tag.contains("DestinationDimNamespace") && tag.contains("DestinationDimPath")) {
            destinationDimensionRL = ResourceLocation.fromNamespaceAndPath(tag.getString("DestinationDimNamespace"), tag.getString("DestinationDimPath"));
        }

        if (tag.contains("DestinationPosX") && tag.contains("DestinationPosY") && tag.contains("DestinationPosZ")) {
            destinationPos = new BlockPos(tag.getInt("DestinationPosX"), tag.getInt("DestinationPosY"), tag.getInt("DestinationPosZ"));
        }

        else destinationPos = null;

        if (tag.contains("POIName", 8)) {
            poiName = parseCustomNameSafe(tag.getString("POIName"), registries);
        }

        if (tag.contains("CamoState")) {
            camoState =  NbtUtils.readBlockState(level != null ? level.holderLookup(Registries.BLOCK) : BuiltInRegistries.BLOCK.asLookup(), tag.getCompound("CamoState"));
        }

        else camoState = null;

        portalColorBase = DyeColor.byId(tag.getInt("PortalColorBaseId"));

        portalColorPattern.clear();

        ListTag list = tag.getList("PortalColorPattern", Tag.TAG_COMPOUND);

        for (int i = 0; i < list.size(); i++) {

            CompoundTag entryTag = list.getCompound(i);

            int x = entryTag.getInt("PosX");
            int y = entryTag.getInt("PosY");
            int z = entryTag.getInt("PosZ");

            int colorId = entryTag.getInt("ColorId");

            portalColorPattern.put(new BlockPos(x, y, z), DyeColor.byId(colorId));
        }
    }

    @Override
    protected void applyImplicitComponents(BlockEntity.DataComponentInput componentInput) {
        super.applyImplicitComponents(componentInput);
        poiName = componentInput.get(DataComponents.CUSTOM_NAME);
    }
}