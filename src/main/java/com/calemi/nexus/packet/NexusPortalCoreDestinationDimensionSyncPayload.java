package com.calemi.nexus.packet;

import com.calemi.nexus.blockentity.NexusPortalCoreBlockEntity;
import com.calemi.nexus.main.NexusRef;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record NexusPortalCoreDestinationDimensionSyncPayload(BlockPos portalCorePosition, ResourceLocation destinationDimension) implements CustomPacketPayload {

    public static final StreamCodec<RegistryFriendlyByteBuf, NexusPortalCoreDestinationDimensionSyncPayload> CODEC = CustomPacketPayload.codec(
            NexusPortalCoreDestinationDimensionSyncPayload::write,
            NexusPortalCoreDestinationDimensionSyncPayload::new);

    public NexusPortalCoreDestinationDimensionSyncPayload(RegistryFriendlyByteBuf buf) {
        this(buf.readBlockPos(), buf.readResourceLocation());
    }

    public void write(FriendlyByteBuf buf) {
        buf.writeBlockPos(portalCorePosition);
        buf.writeResourceLocation(destinationDimension);
    }

    public static void handle(final NexusPortalCoreDestinationDimensionSyncPayload payload, final IPayloadContext context) {

        context.enqueueWork(() -> {

            if (context.player().level().getBlockEntity(payload.portalCorePosition()) instanceof NexusPortalCoreBlockEntity portalCoreBlockEntity) {

                if (portalCoreBlockEntity.getDestinationPos() != null) {
                    return;
                }

                portalCoreBlockEntity.setDestinationDimensionRL(payload.destinationDimension());
                portalCoreBlockEntity.setChanged();
            }
        });
    }

    public static final Type<NexusPortalCoreDestinationDimensionSyncPayload> TYPE = new Type<>(NexusRef.rl("nexus_portal_core_destination_dimension_sync"));

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}