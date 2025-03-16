package com.calemi.nexus.packet;

import com.calemi.nexus.capability.UnlockedDimensionsList;
import com.calemi.nexus.main.NexusRef;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record UnlockedDimensionsListSyncPayload(CompoundTag capabilityNBT) implements CustomPacketPayload {

    public static final StreamCodec<RegistryFriendlyByteBuf, UnlockedDimensionsListSyncPayload> CODEC = CustomPacketPayload.codec(
            UnlockedDimensionsListSyncPayload::write,
            UnlockedDimensionsListSyncPayload::new);

    public UnlockedDimensionsListSyncPayload(UnlockedDimensionsList foodList, HolderLookup.Provider provider) {
        this(foodList.serializeNBT(provider));
    }

    public UnlockedDimensionsListSyncPayload(FriendlyByteBuf buffer) {
        this(buffer.readNbt());
    }

    public void write(FriendlyByteBuf buffer) {
        buffer.writeNbt(capabilityNBT);
    }

    public static void handle(final UnlockedDimensionsListSyncPayload payload, final IPayloadContext context) {

        context.enqueueWork(() -> {
                    Player player = context.player();
                    UnlockedDimensionsList.get(player).deserializeNBT(player.registryAccess(), payload.capabilityNBT());
                })
                .exceptionally(e -> {
                    context.disconnect(Component.translatable("networking.nexus.unlocked_dimension_list_message.failed", e.getMessage()));
                    return null;
                });
    }

    public static final Type<UnlockedDimensionsListSyncPayload> TYPE = new Type<>(NexusRef.rl("unlocked_dimensions_list_sync"));

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}