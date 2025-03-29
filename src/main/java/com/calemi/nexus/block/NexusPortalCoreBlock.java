package com.calemi.nexus.block;

import com.calemi.ccore.api.location.Location;
import com.calemi.ccore.api.string.StringHelper;
import com.calemi.nexus.blockentity.NexusBlockEntities;
import com.calemi.nexus.blockentity.NexusPortalCoreBlockEntity;
import com.calemi.nexus.main.Nexus;
import com.calemi.nexus.screen.NexusPortalCoreScreen;
import com.calemi.nexus.util.NexusSoundHelper;
import com.calemi.nexus.world.dimension.NexusDimensionHelper;
import com.calemi.nexus.world.dimension.NexusDimensions;
import com.mojang.serialization.MapCodec;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.neoforge.common.ModConfigSpec;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class NexusPortalCoreBlock extends AbstractCamoBlock {

    public static final MapCodec<NexusPortalCoreBlock> CODEC = simpleCodec(NexusPortalCoreBlock::new);

    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;

    private ModConfigSpec.ConfigValue<Integer> configOption;

    public NexusPortalCoreBlock(ModConfigSpec.ConfigValue<Integer> configOption) {
        this(BlockBehaviour.Properties.of()
                .mapColor(MapColor.QUARTZ)
                .requiresCorrectToolForDrops()
                .noOcclusion()
                .strength(1.5F, 6.0F));
        this.configOption = configOption;
        registerDefaultState(stateDefinition.any().setValue(FACING, Direction.UP).setValue(AXIS, Direction.Axis.X).setValue(POWERED, false));
    }

    public NexusPortalCoreBlock(Properties properties) {
        super(properties);
    }

    public int getCoordinateScale() {
        return configOption.get();
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable("hover_text.nexus.nexus_portal_core").append(": x" + StringHelper.insertCommas(getCoordinateScale())).withStyle(ChatFormatting.GRAY));
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @javax.annotation.Nullable LivingEntity placer, ItemStack stack) {

        if (stack.has(DataComponents.CUSTOM_NAME)) {

            if (level.getBlockEntity(pos) instanceof NexusPortalCoreBlockEntity blockEntity) {

                blockEntity.setPoiName(stack.get(DataComponents.CUSTOM_NAME));
            }
        }

        super.setPlacedBy(level, pos, state, placer, stack);
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootParams.Builder builder) {

        List<ItemStack> drops = super.getDrops(state, builder);

        if (builder.getOptionalParameter(LootContextParams.BLOCK_ENTITY) instanceof NexusPortalCoreBlockEntity blockEntity && blockEntity.getPoiName() != null) {

            ItemStack drop = new ItemStack(this);
            drop.set(DataComponents.CUSTOM_NAME, blockEntity.getPoiName());

            drops.clear();
            drops.add(drop);
        }

        return drops;
    }

    @Override
    protected void onPlace(BlockState originState, Level level, BlockPos originPos, BlockState oldState, boolean movedByPiston) {
        super.onPlace(originState, level, originPos, oldState, movedByPiston);

        if (!level.isClientSide()) {

            if (level.getBlockEntity(originPos) instanceof NexusPortalCoreBlockEntity originBlockEntity) {

                if (originBlockEntity.getDestinationDimensionRL() != null) {
                    return;
                }

                if (NexusDimensionHelper.isInNexus(level)) {
                    originBlockEntity.setDestinationDimensionRL(ResourceLocation.withDefaultNamespace("overworld"));
                }

                else {
                    originBlockEntity.setDestinationDimensionRL(NexusDimensions.NEXUS_RL);
                }
            }
        }
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState originState, Level level, BlockPos originPos, Player player, BlockHitResult hitResult) {

        if (level.getBlockEntity(originPos) instanceof NexusPortalCoreBlockEntity originBlockEntity) {

            if (player.isCrouching() && originBlockEntity.getCamoState() != null) {

                originBlockEntity.setCamoState(null);
                originBlockEntity.setChanged();

                NexusSoundHelper.playTeleportSound(new Location(level, originPos));
                Nexus.LOGGER.debug("Removed camo state.");
            }

            else if (level.isClientSide()) {
                Minecraft.getInstance().setScreen(new NexusPortalCoreScreen(originBlockEntity));
            }
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {

        if (!(stack.getItem() instanceof BlockItem blockItem))
            return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;

        if (!(level.getBlockEntity(pos) instanceof NexusPortalCoreBlockEntity portalCoreBlockEntity))
            return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;

        BlockPlaceContext context = new BlockPlaceContext(player, hand, stack, hitResult);
        BlockState heldState = blockItem.getBlock().getStateForPlacement(context);

        if (!portalCoreBlockEntity.isValidCamoState(heldState))
            return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;

        portalCoreBlockEntity.setCamoState(heldState);
        portalCoreBlockEntity.setChanged();

        NexusSoundHelper.playTeleportSound(portalCoreBlockEntity.getLocation());
        Nexus.LOGGER.debug("Set camo state: {}", heldState);

        return ItemInteractionResult.SUCCESS;
    }

    @Override
    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block neighborBlock, BlockPos neighborPos, boolean movedByPiston) {

        if (!level.isClientSide()) return;

        if (level.getBlockEntity(pos) instanceof NexusPortalCoreBlockEntity blockEntity) {

            if (level.getBlockState(blockEntity.getProjectionPosition()).getBlock() instanceof NexusPortalBlock portal) {
                blockEntity.setPortalColorBase(portal.getColor());
                blockEntity.setChanged();
            }

            boolean isPowered = level.hasNeighborSignal(pos);

            if (isPowered != state.getValue(POWERED)) {

                level.setBlock(pos, state.setValue(POWERED, isPowered), 2);

                if (isPowered) blockEntity.togglePortal(null);
            }
        }

        super.neighborChanged(state, level, pos, neighborBlock, neighborPos, movedByPiston);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new NexusPortalCoreBlockEntity(pos, state);
    }

    @Override
    public Optional<BlockState> getCamoState(BlockGetter world, BlockPos pos) {
        return getPortalCore(world, pos).map(NexusPortalCoreBlockEntity::getCamoState);
    }

    @Override
    public Optional<NexusPortalCoreBlockEntity> getPortalCore(BlockGetter world, BlockPos pos) {
        if (world == null || pos == null) {
            return Optional.empty();
        }

        return world.getBlockEntity(pos, NexusBlockEntities.NEXUS_PORTAL_CORE.get());
    }

    @Override
    protected int getAnalogOutputSignal(BlockState state, Level level, BlockPos pos) {
        if (!(level.getBlockEntity(pos) instanceof NexusPortalCoreBlockEntity blockEntity)) return 0;
        return blockEntity.isPortalActive() ? 15 : 0;
    }

    @Override
    protected boolean hasAnalogOutputSignal(BlockState state) {
        return true;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(POWERED);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }
}
