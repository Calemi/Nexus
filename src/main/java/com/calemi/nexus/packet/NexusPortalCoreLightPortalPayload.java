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
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record NexusPortalCoreLightPortalPayload(BlockPos portalCorePosition) implements CustomPacketPayload {

    public static final StreamCodec<RegistryFriendlyByteBuf, NexusPortalCoreLightPortalPayload> CODEC = CustomPacketPayload.codec(
            NexusPortalCoreLightPortalPayload::write,
            NexusPortalCoreLightPortalPayload::new);

    public NexusPortalCoreLightPortalPayload(RegistryFriendlyByteBuf buf) {
        this(buf.readBlockPos());
    }

    public void write(FriendlyByteBuf buf) {
        buf.writeBlockPos(portalCorePosition);
    }

    public static void handle(final NexusPortalCoreLightPortalPayload payload, final IPayloadContext context) {

        context.enqueueWork(() -> {

            Player player = context.player();
            ServerLevel originLevel = (ServerLevel) player.level();

            if (!(originLevel.getBlockEntity(payload.portalCorePosition()) instanceof NexusPortalCoreBlockEntity portalCoreBlockEntity)) return;

            portalCoreBlockEntity.togglePortal(player);
        });
    }

    public static final Type<NexusPortalCoreLightPortalPayload> TYPE = new Type<>(NexusRef.rl("nexus_portal_core_light_portal"));

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}