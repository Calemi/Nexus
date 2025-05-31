package com.calemi.nexus.packet;

import com.calemi.nexus.item.NexusItems;
import com.calemi.nexus.main.NexusRef;
import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record TotemOfWarpingPayload() implements CustomPacketPayload {

    public static final StreamCodec<RegistryFriendlyByteBuf, TotemOfWarpingPayload> CODEC = CustomPacketPayload.codec(
            TotemOfWarpingPayload::write,
            TotemOfWarpingPayload::new);

    public TotemOfWarpingPayload(RegistryFriendlyByteBuf buf) {
        this();
    }

    public void write(FriendlyByteBuf buf) {}

    public static void handle(final TotemOfWarpingPayload payload, final IPayloadContext context) {

        context.enqueueWork(() -> {
            Minecraft mc = Minecraft.getInstance();
            Player player = context.player();
            mc.particleEngine.createTrackingEmitter(player, ParticleTypes.TOTEM_OF_UNDYING, 30);
            player.level().playLocalSound(player.getX(), player.getY(), player.getZ(), SoundEvents.TOTEM_USE, player.getSoundSource(), 1.0F, 1.0F, false);
            mc.gameRenderer.displayItemActivation(new ItemStack(NexusItems.TOTEM_OF_WARPING.get()));
        });
    }

    public static final Type<TotemOfWarpingPayload> TYPE = new Type<>(NexusRef.rl("totem_of_warping_payload"));

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}