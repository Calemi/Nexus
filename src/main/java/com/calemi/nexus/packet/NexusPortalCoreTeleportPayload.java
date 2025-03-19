package com.calemi.nexus.packet;

import com.calemi.nexus.blockentity.NexusPortalCoreBlockEntity;
import com.calemi.nexus.main.NexusRef;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record NexusPortalCoreTeleportPayload(BlockPos portalCorePosition) implements CustomPacketPayload {

    public static final StreamCodec<RegistryFriendlyByteBuf, NexusPortalCoreTeleportPayload> CODEC = CustomPacketPayload.codec(
            NexusPortalCoreTeleportPayload::write,
            NexusPortalCoreTeleportPayload::new);

    public NexusPortalCoreTeleportPayload(RegistryFriendlyByteBuf buf) {
        this(buf.readBlockPos());
    }

    public void write(FriendlyByteBuf buf) {
        buf.writeBlockPos(portalCorePosition);
    }

    public static void handle(final NexusPortalCoreTeleportPayload payload, final IPayloadContext context) {

        context.enqueueWork(() -> {

            Player player = context.player();
            ServerLevel originLevel = (ServerLevel) player.level();
            BlockPos originPos = payload.portalCorePosition();
            BlockState originState = originLevel.getBlockState(originPos);

            if (!(originLevel.getBlockEntity(originPos) instanceof NexusPortalCoreBlockEntity originPortalCore)) return;

            NexusPortalCoreBlockEntity destinationPortalCore = originPortalCore.getDestinationPortalCore();

            if (destinationPortalCore == null) return;

            if (destinationPortalCore.isPortalActive()) {
                player.setPortalCooldown();
            }

            originPortalCore.teleportEntity(player);
        });
    }

    public static final Type<NexusPortalCoreTeleportPayload> TYPE = new Type<>(NexusRef.rl("nexus_portal_core_teleport"));

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}