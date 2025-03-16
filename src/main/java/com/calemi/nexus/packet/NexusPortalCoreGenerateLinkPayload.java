package com.calemi.nexus.packet;

import com.calemi.ccore.api.container.CContainerHelper;
import com.calemi.nexus.block.NexusPortalCoreBlock;
import com.calemi.nexus.blockentity.NexusPortalCoreBlockEntity;
import com.calemi.nexus.main.NexusRef;
import com.calemi.nexus.util.NexusHelper;
import com.calemi.nexus.util.NexusSoundHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
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

            Player player = context.player();
            ServerLevel originLevel = (ServerLevel) player.level();

            if (!(originLevel.getBlockEntity(payload.portalCorePosition()) instanceof NexusPortalCoreBlockEntity originBlockEntity)) {
                return;
            }

            NexusPortalCoreBlock originBlock = (NexusPortalCoreBlock) originBlockEntity.getBlockState().getBlock();

            if (player.getInventory().countItem(originBlock.asItem()) < 1 && !player.isCreative()) {
                player.sendSystemMessage(Component.translatable("message.nexus.generate_link.no_core").withStyle(ChatFormatting.RED));
                NexusSoundHelper.playErrorSound(player);
                return;
            }

            Level destinationLevel = originBlockEntity.getDestinationLevel();

            if (destinationLevel == null) {
                player.sendSystemMessage(Component.translatable("message.nexus.invalid_dimension").withStyle(ChatFormatting.RED));
                NexusSoundHelper.playErrorSound(player);
                return;
            }

            BlockPos calcPos = NexusHelper.getDynamicBlockDestination(player.level(), originBlockEntity.getBlockPos(), originBlock.getCoordinateScale());

            if (!NexusHelper.isDestinationValid(destinationLevel, calcPos)) {
                player.sendSystemMessage(Component.translatable("message.nexus.invalid_destination").withStyle(ChatFormatting.RED));
                NexusSoundHelper.playErrorSound(player);
                return;
            }

            int yLevel = NexusHelper.getSolidGroundLevel(destinationLevel, calcPos);

            //REMEMBER TO CHECK WORLD BORDER

            if (yLevel == Integer.MAX_VALUE) {
                player.sendSystemMessage(Component.translatable("message.nexus.generate_link.no_valid_spawn").withStyle(ChatFormatting.RED));
                NexusSoundHelper.playErrorSound(player);
                return;
            }

            BlockPos destinationPos = calcPos.atY(yLevel);

            originBlockEntity.setDestinationDimResourceLocation(destinationLevel.dimension().location());
            originBlockEntity.setDestinationPos(destinationPos);
            originBlockEntity.markUpdated();

            destinationLevel.setBlockAndUpdate(destinationPos, originBlock.defaultBlockState());

            if (destinationLevel.getBlockEntity(destinationPos) instanceof NexusPortalCoreBlockEntity destinationBlockEntity) {

                destinationBlockEntity.setDestinationDimResourceLocation(originLevel.dimension().location());
                destinationBlockEntity.setDestinationPos(originBlockEntity.getBlockPos());
                destinationBlockEntity.markUpdated();
            }

            if (!player.isCreative()) CContainerHelper.consumeItems(player.getInventory(), originBlock.asItem(), 1);

            player.sendSystemMessage(Component.translatable("message.nexus.generate_link.success").withStyle(ChatFormatting.GREEN));
            NexusSoundHelper.playSuccessSound(player);
        });
    }

    public static final Type<NexusPortalCoreGenerateLinkPayload> TYPE = new Type<>(NexusRef.rl("nexus_portal_core_generate_link"));

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}