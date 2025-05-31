package com.calemi.nexus.item;

import com.calemi.nexus.util.AccelerationMobEffectHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class AcceleritePickaxeItem extends PickaxeItem implements ChargeableBySpeedItem, AccelerationEffectItem {

    public AcceleritePickaxeItem(Properties properties) {
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
