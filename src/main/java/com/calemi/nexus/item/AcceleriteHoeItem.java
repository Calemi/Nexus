package com.calemi.nexus.item;

import com.calemi.nexus.config.NexusConfig;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.ItemStack;

public class AcceleriteHoeItem extends HoeItem implements ChargeableBySpeedItem, AccelerationEffectItem {

    public AcceleriteHoeItem(Properties properties) {
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
        return NexusConfig.server.acceleriteAxeRepairSpeedRequirement.get();
    }

    @Override
    public int getAccelerationEffectDuration() {
        return NexusConfig.server.acceleriteAxeEffectDuration.get();
    }

    @Override
    public int getMaxAccelerationEffectStacks() {
        return NexusConfig.server.maxAcceleriteAxeEffectStack.get() - 1;
    }
}
