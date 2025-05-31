package com.calemi.nexus.item;

import com.calemi.ccore.api.message.OverlayMessageHelper;
import com.calemi.nexus.item.enchantment.NexusEnchantments;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class AcceleriteItemChargeAction {

    private long lastGameTime = 0;
    private long deltaTime = 0;
    private double currentChargeCooldown = 0;
    
    @SubscribeEvent
    public void onPlayerTick(EntityTickEvent.Post event) {

        Entity entity = event.getEntity();
        Level level = entity.level();

        if (level.isClientSide()) return;
        if (!(entity instanceof ServerPlayer serverPlayer)) return;
        if (serverPlayer.isSpectator()) return;

        if (level.getGameTime() != lastGameTime) {
                        
            if (lastGameTime != 0) {
                deltaTime = level.getGameTime() - lastGameTime;
            }

            lastGameTime = level.getGameTime();
        }

        if (currentChargeCooldown > 0) {
            currentChargeCooldown -= deltaTime;
        }

        double currentSpeed = serverPlayer.getKnownMovement().toVector3f().length();
        double currentPercentage = 0;

        ItemStack currentChargingStack = getPrioritizedChargeableItemStack(serverPlayer);

        if (!currentChargingStack.isEmpty()) {

            double requiredSpeed = 0.5F;

            if (currentChargingStack.getItem() instanceof ChargeableBySpeedItem currentSpeedChargeItem) {
                requiredSpeed = currentSpeedChargeItem.getRequiredSpeed();
            }

            currentPercentage = (currentSpeed / requiredSpeed) * 100;

            if (currentPercentage >= 100) {

                if (currentChargeCooldown <= 0) {

                    if (currentChargingStack.getItem() instanceof ChargeableBySpeedItem currentSpeedChargeItem) {
                        currentSpeedChargeItem.onSpeedRequirementMet(serverPlayer, currentChargingStack);
                    }

                    else {
                        currentChargingStack.setDamageValue(Math.clamp(currentChargingStack.getDamageValue() - 1, 0, currentChargingStack.getMaxDamage()));
                    }

                    currentChargeCooldown = Mth.clamp(10 - ((currentSpeed - requiredSpeed) * 5), 5, 10);
                }
            }
        }

        ItemStack heldSpeedometerItemStack = ItemStack.EMPTY;

        if (serverPlayer.getMainHandItem().getItem() instanceof SpeedometerItem) {
            heldSpeedometerItemStack = serverPlayer.getMainHandItem();
        }

        else if (serverPlayer.getOffhandItem().getItem() instanceof SpeedometerItem) {
            heldSpeedometerItemStack = serverPlayer.getOffhandItem();
        }

        if (!heldSpeedometerItemStack.isEmpty()) {

            NumberFormat decimal = new DecimalFormat("0.00");
            NumberFormat percentage = new DecimalFormat("0");
            MutableComponent overlayMessage = Component.translatable("hud.nexus.speed").append(": " + ChatFormatting.GOLD + decimal.format(currentSpeed));

            if (!currentChargingStack.isEmpty()) {

                ChatFormatting color = ChatFormatting.GOLD;

                if (currentPercentage >= 100) {
                    color = ChatFormatting.GREEN;
                }

                overlayMessage = Component.literal(currentChargingStack.getHoverName().getString())
                        .append(": " + color + percentage.format(currentPercentage) + "% ")
                        .append("(" + ChatFormatting.GOLD + decimal.format(currentSpeed))
                        .append(")");
            }

            OverlayMessageHelper.displayMsg(overlayMessage, serverPlayer);
        }
    }

    private ItemStack getPrioritizedChargeableItemStack(ServerPlayer serverPlayer) {

        ItemStack mainHandItemStack = serverPlayer.getMainHandItem();

        if (isItemStackChargeable(serverPlayer, mainHandItemStack)) {
            return mainHandItemStack;
        }

        ItemStack offHandItemStack = serverPlayer.getOffhandItem();

        if (isItemStackChargeable(serverPlayer, offHandItemStack)) {
            return offHandItemStack;
        }

        for (ItemStack invStack : serverPlayer.getInventory().armor) {

            if (isItemStackChargeable(serverPlayer, invStack)) {
                return invStack;
            }
        }

        for (ItemStack invStack : serverPlayer.getInventory().items) {

            if (isItemStackChargeable(serverPlayer, invStack)) {
                return invStack;
            }
        }

        return ItemStack.EMPTY;
    }

    private boolean isItemStackChargeable(ServerPlayer player, ItemStack stack) {

        if (stack.getItem() instanceof ChargeableBySpeedItem speedChargeItem) {
            return speedChargeItem.canCharge(player, stack);
        }

        return getSpeedMendingEnchantmentLevel(player.level(), stack) > 0 && stack.isDamaged();
    }

    private int getSpeedMendingEnchantmentLevel(Level level, ItemStack stack) {
        Holder<Enchantment> enchantment = level.holder(NexusEnchantments.SPEED_MENDING).orElse(null);
        return EnchantmentHelper.getTagEnchantmentLevel(enchantment, stack);
    }
}
