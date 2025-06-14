package com.calemi.nexus.packet;

import com.calemi.ccore.api.message.OverlayMessageHelper;
import com.calemi.nexus.block.NexusPortalCoreBlock;
import com.calemi.nexus.main.NexusRef;
import com.calemi.nexus.world.dimension.NexusDimensionHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record NexusPortalCoreHUDOverlayPayload() implements CustomPacketPayload {

    public static final StreamCodec<RegistryFriendlyByteBuf, NexusPortalCoreHUDOverlayPayload> CODEC = CustomPacketPayload.codec(
            NexusPortalCoreHUDOverlayPayload::write,
            NexusPortalCoreHUDOverlayPayload::new);

    public NexusPortalCoreHUDOverlayPayload(RegistryFriendlyByteBuf buf) {
        this();
    }

    public void write(FriendlyByteBuf buf) {
    }

    public static void handle(final NexusPortalCoreHUDOverlayPayload payload, final IPayloadContext context) {

        context.enqueueWork(() -> {

            ServerPlayer player = (ServerPlayer) context.player();
            ServerLevel level = (ServerLevel) player.level();

            if (player.getMainHandItem().getItem() instanceof BlockItem blockItem && blockItem.getBlock() instanceof NexusPortalCoreBlock originBlock) {

                BlockPos destinationPosition = NexusDimensionHelper.getDynamicBlockDestination(player.level(), player.blockPosition(), originBlock.getCoordinateScale());

                OverlayMessageHelper.displayMsg(Component.translatable("hud.nexus.calculated_destination").append(": [x")
                        .append("" + ChatFormatting.GOLD + destinationPosition.getX() + ChatFormatting.WHITE + " z" + ChatFormatting.GOLD + destinationPosition.getZ() + ChatFormatting.WHITE + "]"), player);
            }
        });
    }

    public static final Type<NexusPortalCoreHUDOverlayPayload> TYPE = new Type<>(NexusRef.rl("nexus_portal_core_hud_overlay"));

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}