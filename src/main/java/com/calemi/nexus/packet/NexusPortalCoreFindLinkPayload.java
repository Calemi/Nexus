package com.calemi.nexus.packet;

import com.calemi.ccore.api.location.Location;
import com.calemi.nexus.block.NexusPortalCoreBlock;
import com.calemi.nexus.blockentity.NexusPortalCoreBlockEntity;
import com.calemi.nexus.main.NexusRef;
import com.calemi.nexus.util.NexusMessengers;
import com.calemi.nexus.world.dimension.NexusDimensionHelper;
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
import net.minecraft.world.level.chunk.ChunkAccess;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import java.util.ArrayList;
import java.util.List;

public record NexusPortalCoreFindLinkPayload(BlockPos portalCorePosition) implements CustomPacketPayload {

    public static final StreamCodec<RegistryFriendlyByteBuf, NexusPortalCoreFindLinkPayload> CODEC = CustomPacketPayload.codec(
            NexusPortalCoreFindLinkPayload::write,
            NexusPortalCoreFindLinkPayload::new);

    public NexusPortalCoreFindLinkPayload(RegistryFriendlyByteBuf buf) {
        this(buf.readBlockPos());
    }

    public void write(FriendlyByteBuf buf) {
        buf.writeBlockPos(portalCorePosition);
    }

    public static void handle(final NexusPortalCoreFindLinkPayload payload, final IPayloadContext context) {

        context.enqueueWork(() -> {

            BlockPos originPos = payload.portalCorePosition();
            Player player = context.player();
            ServerLevel originLevel = (ServerLevel) player.level();
            Location originLocation = new Location(originLevel, originPos);

            if (!(originLevel.getBlockState(originPos).getBlock() instanceof NexusPortalCoreBlock originBlock)) return;
            if (!(originLevel.getBlockEntity(originPos) instanceof NexusPortalCoreBlockEntity originBlockEntity)) return;

            Level destinationLevel = originBlockEntity.getDestinationLevel();

            if (destinationLevel == null) {
                NexusMessengers.NEXUS_PORTAL_CORE.send(Component.translatable("message.nexus.invalid_dimension").withStyle(ChatFormatting.RED), player);
                NexusSoundHelper.playErrorSound(originLocation);
                return;
            }

            int coordinateScale = originBlock.getCoordinateScale();
            BlockPos calcPos = NexusDimensionHelper.getDynamicBlockDestination(originLevel, originPos, coordinateScale);

            if (!NexusDimensionHelper.isDestinationValid(destinationLevel, calcPos)) {
                NexusMessengers.NEXUS_PORTAL_CORE.send(Component.translatable("message.nexus.invalid_destination").withStyle(ChatFormatting.RED), player);
                NexusSoundHelper.playErrorSound(originLocation);
                return;
            }

            if (!NexusDimensionHelper.isInNexus(originLevel)) {
                coordinateScale = 1;
            }

            NexusMessengers.NEXUS_PORTAL_CORE.send(Component.translatable("message.nexus.find_link.searching").withStyle(ChatFormatting.GRAY), player);

            List<BlockPos> collectedBlockEntityPositions = new ArrayList<>();

            for (int x = 0; x < coordinateScale; x += 16) {

                for (int z = 0; z < coordinateScale; z += 16) {

                    ChunkAccess checkChunk = destinationLevel.getChunk(new BlockPos(calcPos.getX() + x, originPos.getY(), calcPos.getZ() + z));
                    collectedBlockEntityPositions.addAll(checkChunk.getBlockEntitiesPos());
                }
            }

            final int finalCoordinateScale = coordinateScale;

            collectedBlockEntityPositions.removeIf(
                    pos -> pos.getX() < calcPos.getX() || pos.getX() >= calcPos.getX() + finalCoordinateScale ||
                            pos.getZ() < calcPos.getZ() || pos.getZ() >= calcPos.getZ() + finalCoordinateScale
            );

            collectedBlockEntityPositions.removeIf(
                    pos -> !(destinationLevel.getBlockState(pos).getBlock().equals(originBlock))
            );

            collectedBlockEntityPositions.removeIf(
                    pos -> !(destinationLevel.getBlockEntity(pos) instanceof NexusPortalCoreBlockEntity)
            );

            if (!collectedBlockEntityPositions.isEmpty()) {

                BlockPos destinationPos = collectedBlockEntityPositions.getFirst();
                NexusPortalCoreBlockEntity destinationBlockEntity = (NexusPortalCoreBlockEntity) destinationLevel.getBlockEntity(destinationPos);

                if (destinationBlockEntity != null) {

                    originBlockEntity.setDestinationPos(destinationPos);
                    originBlockEntity.setChanged();

                    destinationBlockEntity.setDestinationDimensionRL(originLevel.dimension().location());
                    destinationBlockEntity.setDestinationPos(originBlockEntity.getBlockPos());
                    destinationBlockEntity.setChanged();

                    NexusMessengers.NEXUS_PORTAL_CORE.send(Component.translatable("message.nexus.find_link.success").withStyle(ChatFormatting.GREEN), player);
                    NexusSoundHelper.playSuccessSound(originLocation);

                    return;
                }
            }

            NexusMessengers.NEXUS_PORTAL_CORE.send(Component.translatable("message.nexus.find_link.failure").withStyle(ChatFormatting.RED), player);

            NexusSoundHelper.playErrorSound(originLocation);
        });
    }

    public static final Type<NexusPortalCoreFindLinkPayload> TYPE = new Type<>(NexusRef.rl("nexus_portal_core_find_link"));

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}