package com.calemi.nexus.packet;

import com.calemi.ccore.api.location.Location;
import com.calemi.nexus.block.NexusPortalBlock;
import com.calemi.nexus.blockentity.NexusPortalCoreBlockEntity;
import com.calemi.nexus.main.NexusRef;
import com.calemi.nexus.scanner.PortalBlockScanner;
import com.calemi.nexus.util.NexusSoundHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import java.util.ArrayList;

public record NexusPortalCoreLightPortalPayload(BlockPos abovePortalCorePosition) implements CustomPacketPayload {

    public static final StreamCodec<RegistryFriendlyByteBuf, NexusPortalCoreLightPortalPayload> CODEC = CustomPacketPayload.codec(
            NexusPortalCoreLightPortalPayload::write,
            NexusPortalCoreLightPortalPayload::new);

    public NexusPortalCoreLightPortalPayload(RegistryFriendlyByteBuf buf) {
        this(buf.readBlockPos());
    }

    public void write(FriendlyByteBuf buf) {
        buf.writeBlockPos(abovePortalCorePosition);
    }

    public static void handle(final NexusPortalCoreLightPortalPayload payload, final IPayloadContext context) {

        context.enqueueWork(() -> {

            BlockPos abovePortalCorePosition = payload.abovePortalCorePosition();
            Player player = context.player();
            ServerLevel originLevel = (ServerLevel) player.level();

            if (originLevel.getBlockState(abovePortalCorePosition).getBlock() instanceof NexusPortalBlock) {
                originLevel.setBlock(abovePortalCorePosition, Blocks.AIR.defaultBlockState(), 2);
                return;
            }

            if (!(originLevel.getBlockEntity(abovePortalCorePosition.below()) instanceof NexusPortalCoreBlockEntity blockEntity)) {
                return;
            }

            ArrayList<Location> collectedLocations = new ArrayList<>();
            PortalBlockScanner scanner = new PortalBlockScanner(new Location(originLevel, abovePortalCorePosition), Direction.Axis.X, 1000);
            scanner.start();

            if (!scanner.halted) {
                collectedLocations.addAll(scanner.collectedLocations);
            }

            else {
                scanner = new PortalBlockScanner(new Location(originLevel, abovePortalCorePosition), Direction.Axis.Z, 1000);
                scanner.start();

                if (scanner.halted) {
                    player.sendSystemMessage(Component.translatable("message.nexus.light_portal.invalid_frame").withStyle(ChatFormatting.RED));
                    NexusSoundHelper.playErrorSound(player);
                    return;
                }

                collectedLocations.addAll(scanner.collectedLocations);
            }

            if (!collectedLocations.isEmpty()) {

                PortalBlockScanner finalScanner = scanner;

                collectedLocations.forEach(location -> {
                    location.getLevel().setBlock(location.getBlockPos(), NexusPortalBlock.fromDye(blockEntity.getPortalDyeColor()).defaultBlockState().setValue(NexusPortalBlock.AXIS, finalScanner.getAxis()), 3);
                });

                player.sendSystemMessage(Component.translatable("message.nexus.light_portal.success").withStyle(ChatFormatting.GREEN));
                NexusSoundHelper.playSuccessSound(player);
            }
        });
    }

    public static final Type<NexusPortalCoreLightPortalPayload> TYPE = new Type<>(NexusRef.rl("nexus_portal_core_light_portal"));

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}