package com.calemi.nexus.capability;

import com.calemi.nexus.main.NexusRef;
import com.calemi.nexus.packet.UnlockedDimensionsListSyncPayload;
import com.calemi.nexus.regsitry.NexusAttachments;
import com.calemi.nexus.regsitry.NexusMessengers;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.capabilities.EntityCapability;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

@EventBusSubscriber(modid = NexusRef.ID)
public class NexusCapabilityHandler {

    public static final EntityCapability<UnlockedDimensionsList, Void> unlockedDimensionsCapability = EntityCapability.createVoid(
            NexusRef.rl("unlocked_dimensions"), UnlockedDimensionsList.class);

    @EventBusSubscriber(modid = NexusRef.ID, bus = EventBusSubscriber.Bus.MOD)
    private static final class Setup {

        @SubscribeEvent
        public static void registerCapabilities(RegisterCapabilitiesEvent event) {
            event.registerEntity(unlockedDimensionsCapability, EntityType.PLAYER, (player, ctx) -> player.getData(NexusAttachments.UNLOCKED_DIMENSIONS_ATTACHMENT.get()));
        }
    }

    @SubscribeEvent
    public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        tryUnlockDimension(event.getEntity());
        syncUnlockedDimensionList(event.getEntity());
    }

    @SubscribeEvent
    public static void onPlayerDimensionChange(PlayerEvent.PlayerChangedDimensionEvent event) {
        tryUnlockDimension(event.getEntity());
        syncUnlockedDimensionList(event.getEntity());
    }

    @SubscribeEvent
    public static void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
        syncUnlockedDimensionList(event.getEntity());
    }

    private static void tryUnlockDimension(Player player) {

        Level level = player.level();
        UnlockedDimensionsList unlockedDimensions = UnlockedDimensionsList.get(player);

        if (unlockedDimensions.unlock(player.level().dimension().location())) {

            if (!level.isClientSide()) {

                NexusMessengers.MAIN.send(Component.translatable("message.nexus.unlock_dimension").withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC), player);
            }
        }
    }

    public static boolean isDimensionUnlocked(Player player, ResourceLocation dimensionRL) {
        UnlockedDimensionsList unlockedDimensions = UnlockedDimensionsList.get(player);
        return unlockedDimensions.isUnlocked(dimensionRL);
    }

    public static void syncUnlockedDimensionList(Player player) {

        if (player instanceof ServerPlayer target) {
            target.connection.send(
                    new UnlockedDimensionsListSyncPayload(UnlockedDimensionsList.get(player), player.registryAccess())
            );
        }
    }
}
