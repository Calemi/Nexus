package com.calemi.nexus.item;

import com.calemi.ccore.api.item.ItemHelper;
import com.calemi.ccore.api.sound2.SoundProfile2;
import com.calemi.nexus.config.NexusConfig;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class DormantAcceleriteIngotItem extends Item implements ChargeableBySpeedItem {

    public DormantAcceleriteIngotItem(Item.Properties properties) {
        super(properties);
    }

    @Override
    public void onSpeedRequirementMet(ServerPlayer player, ItemStack stack) {

        stack.shrink(1);
        ItemHelper.giveItem(player, new ItemStack(NexusItems.CHARGED_ACCELERITE_INGOT.get()));

        new SoundProfile2()
                .setEvent(SoundEvents.ZOMBIE_VILLAGER_CURE)
                .setLevel(player)
                .setPosition(player.position().add(player.getKnownMovement()))
                .setVolume(0.35F)
                .setPitch(1.5F, 2F)
                .playPacket(player);
    }

    @Override
    public boolean canCharge(ServerPlayer player, ItemStack stack) {
        return true;
    }

    @Override
    public double getRequiredSpeed() {
        return NexusConfig.server.dormantAcceleriteIngotChargeSpeedRequirement.get();
    }
}