package com.calemi.nexus.packet;

import com.calemi.ccore.api.container.CContainerHelper;
import com.calemi.ccore.api.location.Location;
import com.calemi.nexus.block.NexusPortalCoreBlock;
import com.calemi.nexus.blockentity.NexusPortalCoreBlockEntity;
import com.calemi.nexus.main.NexusRef;
import com.calemi.nexus.util.NexusMessengers;
import com.calemi.nexus.world.dimension.NexusDimensionHelper;
import com.calemi.nexus.util.NexusSoundHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record NexusPortalCoreGenerateLinkPayload(BlockPos portalCorePosition) implements CustomPacketPayload {

    public static final StreamCodec<RegistryFriendlyByteBuf, NexusPortalCoreGenerateLinkPayload> CODEC = CustomPacketPayload.codec(
            NexusPortalCoreGenerateLinkPayload::write,
            NexusPortalCoreGenerateLinkPayload::new);

    public NexusPortalCoreGenerateLinkPayload(RegistryFriendlyByteBuf buf) {
        this(buf.readBlockPos());
    }

    public void write(FriendlyByteBuf buf) {
        buf.writeBlockPos(portalCorePosition);
    }

    public static void handle(final NexusPortalCoreGenerateLinkPayload payload, final IPayloadContext context) {

        context.enqueueWork(() -> {

            BlockPos originPos = payload.portalCorePosition();
            Player player = context.player();
            ServerLevel originLevel = (ServerLevel) player.level();
            Location originLocation = new Location(originLevel, originPos);

            if (!(originLevel.getBlockEntity(originPos) instanceof NexusPortalCoreBlockEntity originBlockEntity)) {
                return;
            }

            NexusPortalCoreBlock originBlock = (NexusPortalCoreBlock) originBlockEntity.getBlockState().getBlock();

            ItemStack heldStack = player.getMainHandItem();

            if (!heldStack.getItem().equals(originBlock.asItem()) && !player.isCreative()) {
                NexusMessengers.NEXUS_PORTAL_CORE.send(Component.translatable("message.nexus.generate_link.no_core").withStyle(ChatFormatting.RED), player);
                NexusSoundHelper.playErrorSound(originLocation);
                return;
            }

            Level destinationLevel = originBlockEntity.getDestinationLevel();

            if (destinationLevel == null) {
                NexusMessengers.NEXUS_PORTAL_CORE.send(Component.translatable("message.nexus.invalid_dimension").withStyle(ChatFormatting.RED), player);
                NexusSoundHelper.playErrorSound(originLocation);
                return;
            }

            BlockPos calcPos = NexusDimensionHelper.getDynamicBlockDestination(player.level(), originBlockEntity.getBlockPos(), originBlock.getCoordinateScale());

            if (!NexusDimensionHelper.isDestinationValid(destinationLevel, calcPos)) {
                NexusMessengers.NEXUS_PORTAL_CORE.send(Component.translatable("message.nexus.invalid_destination").withStyle(ChatFormatting.RED), player);
                NexusSoundHelper.playErrorSound(originLocation);
                return;
            }

            int yLevel = NexusDimensionHelper.getSolidGroundLevel(destinationLevel, calcPos);

            //REMEMBER TO CHECK WORLD BORDER

            if (yLevel == Integer.MAX_VALUE) {
                NexusMessengers.NEXUS_PORTAL_CORE.send(Component.translatable("message.nexus.generate_link.no_valid_spawn").withStyle(ChatFormatting.RED), player);
                NexusSoundHelper.playErrorSound(originLocation);
                return;
            }

            BlockPos destinationPos = calcPos.atY(yLevel);

            originBlockEntity.setDestinationDimensionRL(destinationLevel.dimension().location());
            originBlockEntity.setDestinationPos(destinationPos);
            originBlockEntity.setChanged();

            destinationLevel.setBlockAndUpdate(destinationPos, originBlock.defaultBlockState());

            if (destinationLevel.getBlockEntity(destinationPos) instanceof NexusPortalCoreBlockEntity destinationBlockEntity) {

                destinationBlockEntity.setDestinationDimensionRL(originLevel.dimension().location());
                destinationBlockEntity.setDestinationPos(originBlockEntity.getBlockPos());

                if (heldStack.has(DataComponents.CUSTOM_NAME)) {
                    destinationBlockEntity.setPoiName(heldStack.get(DataComponents.CUSTOM_NAME));
                }

                destinationBlockEntity.setChanged();
            }

            if (!player.isCreative()) CContainerHelper.consumeItems(player.getInventory(), originBlock.asItem(), 1);

            NexusMessengers.NEXUS_PORTAL_CORE.send(Component.translatable("message.nexus.generate_link.success").withStyle(ChatFormatting.GREEN), player);
            NexusSoundHelper.playSuccessSound(originLocation);
        });
    }

    public static final Type<NexusPortalCoreGenerateLinkPayload> TYPE = new Type<>(NexusRef.rl("nexus_portal_core_generate_link"));

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}