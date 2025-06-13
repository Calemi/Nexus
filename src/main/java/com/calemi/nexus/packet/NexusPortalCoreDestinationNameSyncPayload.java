package com.calemi.nexus.packet;

import com.calemi.nexus.block.entity.NexusPortalCoreBlockEntity;
import com.calemi.nexus.main.NexusRef;
import com.calemi.nexus.client.screen.NexusPortalCoreScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record NexusPortalCoreDestinationNameSyncPayload(BlockPos portalCorePosition, String destinationPOIName) implements CustomPacketPayload {

    public static final StreamCodec<RegistryFriendlyByteBuf, NexusPortalCoreDestinationNameSyncPayload> CODEC = CustomPacketPayload.codec(
            NexusPortalCoreDestinationNameSyncPayload::write,
            NexusPortalCoreDestinationNameSyncPayload::new);

    public NexusPortalCoreDestinationNameSyncPayload(RegistryFriendlyByteBuf buf) {
        this(buf.readBlockPos(), buf.readUtf(50));
    }

    public void write(FriendlyByteBuf buf) {
        buf.writeBlockPos(portalCorePosition);
        buf.writeUtf(destinationPOIName, 50);
    }

    public static void handle(final NexusPortalCoreDestinationNameSyncPayload payload, final IPayloadContext context) {

        context.enqueueWork(() -> {

            Player player = context.player();

            //SENT TO SERVER
            if (player instanceof ServerPlayer serverPlayer) {

                ServerLevel originLevel = (ServerLevel) player.level();

                if (!(originLevel.getBlockEntity(payload.portalCorePosition()) instanceof NexusPortalCoreBlockEntity originBlockEntity)) {
                    return;
                }

                NexusPortalCoreBlockEntity destinationBlockEntity = originBlockEntity.getDestinationPortalCore();

                if (destinationBlockEntity == null) return;

                Component destinationPOIName = destinationBlockEntity.getPoiName();

                if (destinationPOIName == null) return;

                PacketDistributor.sendToPlayer(serverPlayer, new NexusPortalCoreDestinationNameSyncPayload(payload.portalCorePosition(), destinationPOIName.getString()));
            }

            //SENT TO CLIENT
            else {

                if (Minecraft.getInstance().screen instanceof NexusPortalCoreScreen portalCoreScreen) {
                    portalCoreScreen.setDestinationName(payload.destinationPOIName());
                }
            }
        });
    }

    public static final Type<NexusPortalCoreDestinationNameSyncPayload> TYPE = new Type<>(NexusRef.rl("nexus_portal_core_destination_name_sync"));

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}