package com.calemi.nexus.packet;

import com.calemi.ccore.api.item.ItemHelper;
import com.calemi.ccore.api.location.Location;
import com.calemi.nexus.blockentity.NexusPortalCoreBlockEntity;
import com.calemi.nexus.main.NexusRef;
import com.calemi.nexus.util.NexusMessengers;
import com.calemi.nexus.util.NexusSoundHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record NexusPortalCoreUnlinkPayload(BlockPos portalCorePosition, boolean takeLinkedPortalCore) implements CustomPacketPayload {

    public static final StreamCodec<RegistryFriendlyByteBuf, NexusPortalCoreUnlinkPayload> CODEC = CustomPacketPayload.codec(
            NexusPortalCoreUnlinkPayload::write,
            NexusPortalCoreUnlinkPayload::new);

    public NexusPortalCoreUnlinkPayload(RegistryFriendlyByteBuf buf) {
        this(buf.readBlockPos(), buf.readBoolean());
    }

    public void write(FriendlyByteBuf buf) {
        buf.writeBlockPos(portalCorePosition);
        buf.writeBoolean(takeLinkedPortalCore);
    }

    public static void handle(final NexusPortalCoreUnlinkPayload payload, final IPayloadContext context) {

        context.enqueueWork(() -> {

            Player player = context.player();
            ServerLevel originLevel = (ServerLevel) player.level();
            BlockPos originPos = payload.portalCorePosition();
            BlockPos abovePortalCorePosition = originPos.above();
            Location originLocation = new Location(originLevel, originPos);

            if (!(originLevel.getBlockEntity(originPos) instanceof NexusPortalCoreBlockEntity originBlockEntity)) {
                return;
            }

            if (payload.takeLinkedPortalCore()) {

                NexusPortalCoreBlockEntity destinationBlockEntity = originBlockEntity.getDestinationPortalCore();

                if (destinationBlockEntity != null) {

                    Level destinationLevel = destinationBlockEntity.getLevel();

                    if (destinationLevel != null) {

                        ItemStack stackToGive = new ItemStack(destinationLevel.getBlockState(destinationBlockEntity.getBlockPos()).getBlock().asItem());

                        Component destinationPOIName = destinationBlockEntity.getPoiName();

                        if (destinationBlockEntity.getPoiName() != null) {
                            stackToGive.set(DataComponents.CUSTOM_NAME, destinationPOIName);
                        }

                        ItemHelper.giveItem(player, stackToGive);
                        destinationLevel.setBlock(originBlockEntity.getDestinationPos(), Blocks.AIR.defaultBlockState(), 3);
                    }
                }
            }

            originBlockEntity.setDestinationPos(null);
            originBlockEntity.setChanged();

            NexusMessengers.NEXUS_PORTAL_CORE.send(Component.translatable("message.nexus.unlink.success").withStyle(ChatFormatting.GREEN), player);
            NexusSoundHelper.playErrorSound(originLocation);
        });
    }

    public static final Type<NexusPortalCoreUnlinkPayload> TYPE = new Type<>(NexusRef.rl("nexus_portal_core_unlink"));

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}