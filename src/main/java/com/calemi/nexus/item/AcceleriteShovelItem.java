package com.calemi.nexus.item;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShovelItem;

public class AcceleriteShovelItem extends ShovelItem implements ChargeableBySpeedItem, AccelerationEffectItem {

    public AcceleriteShovelItem(Properties properties) {
        super(NexusTiers.ACCELERITE, properties);
    }

    @Override
    public void onSpeedRequirementMet(ServerPlayer player, ItemStack stack) {
        stack.setDamageValue(Math.clamp(stack.getDamageValue() - 1, 0, stack.getMaxDamage()));
    }

    @Override
    public boolean canCharge(ServerPlayer player, ItemStack stack) {
        return stack.isDamaged();
    }

    @Override
    public double getRequiredSpeed() {
        return 0.5F;
    }

    @Override
    public int getEffectDuration() {
        return 100;
    }
}
