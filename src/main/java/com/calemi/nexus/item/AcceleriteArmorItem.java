package com.calemi.nexus.item;

import com.calemi.nexus.config.NexusConfig;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;

public class AcceleriteArmorItem extends ArmorItem implements ChargeableBySpeedItem, AccelerationEffectItem {

    public AcceleriteArmorItem(Type type, Properties properties) {
        super(NexusArmorMaterials.ACCELERITE, type, properties);
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
        return NexusConfig.server.acceleriteArmorRepairSpeedRequirement.get();
    }

    @Override
    public int getAccelerationEffectDuration() {
        return NexusConfig.server.acceleriteArmorEffectDuration.get();
    }

    @Override
    public int getMaxAccelerationEffectStacks() {
        return NexusConfig.server.maxAcceleriteArmorEffectStack.get() - 1;
    }
}