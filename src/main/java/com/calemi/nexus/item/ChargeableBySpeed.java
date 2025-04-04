package com.calemi.nexus.item;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;

public interface ChargeableBySpeed {

    void onSpeedRequirementMet(ServerPlayer player, ItemStack stack);

    boolean canCharge(ServerPlayer player, ItemStack stack);

    double getRequiredSpeed();
}
