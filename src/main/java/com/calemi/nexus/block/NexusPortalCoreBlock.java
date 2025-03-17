package com.calemi.nexus.block;

import com.calemi.ccore.api.log.LogHelper;
import com.calemi.nexus.blockentity.NexusPortalCoreBlockEntity;
import com.calemi.nexus.main.NexusRef;
import com.calemi.nexus.regsitry.NexusDimensions;
import com.calemi.nexus.screen.NexusPortalCoreScreen;
import com.calemi.nexus.util.NexusHelper;
import com.mojang.serialization.MapCodec;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class NexusPortalCoreBlock extends BaseEntityBlock {

    public static final MapCodec<NexusPortalCoreBlock> CODEC = simpleCodec(NexusPortalCoreBlock::new);

    private int coordinateScale;

    public NexusPortalCoreBlock(int coordinateScale) {
        this(BlockBehaviour.Properties.of()
                .mapColor(MapColor.QUARTZ)
                .requiresCorrectToolForDrops()
                .strength(1.5F, 6.0F));
        this.coordinateScale = coordinateScale;
    }

    public NexusPortalCoreBlock(Properties properties) {
        super(properties);
    }

    public int getCoordinateScale() {
        return coordinateScale;
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable("hover_text.nexus.nexus_portal_core").append(": x" + getCoordinateScale()).withStyle(ChatFormatting.GRAY));
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

                if (NexusHelper.isInNexus(level)) {
                    originBlockEntity.setDestinationDimResourceLocation(ResourceLocation.withDefaultNamespace("overworld"));
                }

                else {
                    originBlockEntity.setDestinationDimResourceLocation(NexusDimensions.NEXUS_RESOURCE_LOCATION);
                }

                originBlockEntity.setDestinationPos(null);
            }
        }
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState originState, Level level, BlockPos originPos, Player player, BlockHitResult hitResult) {

        if (level.isClientSide()) {

            if (level.getBlockEntity(originPos) instanceof NexusPortalCoreBlockEntity originBlockEntity) {

                Minecraft.getInstance().setScreen(new NexusPortalCoreScreen(originBlockEntity));
            }
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block neighborBlock, BlockPos neighborPos, boolean movedByPiston) {

        LogHelper.log(NexusRef.MOD_NAME, "UPDATE");

        if (level.getBlockEntity(pos) instanceof NexusPortalCoreBlockEntity blockEntity) {

            if (level.getBlockState(pos.above()).getBlock() instanceof NexusPortalBlock portal) {
                LogHelper.log(NexusRef.MOD_NAME, "CHANGING COLOR " + portal.getColor().getName());
                blockEntity.setPortalDyeColor(portal.getColor());
                blockEntity.markUpdated();
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
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }
}
