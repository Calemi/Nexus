package com.calemi.nexus.item;

import com.calemi.ccore.api.message.OverlayMessageHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
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

        ChargeableBySpeed currentSpeedChargeItem = null;
        ItemStack currentChargingStack = ItemStack.EMPTY;

        for (ItemStack invStack : serverPlayer.getInventory().items) {

            if (invStack.getItem() instanceof ChargeableBySpeed speedChargeItem) {

                if (speedChargeItem.canCharge(serverPlayer, invStack)) {
                    currentSpeedChargeItem = speedChargeItem;
                    currentChargingStack = invStack;
                    break;
                }
            }
        }

        if (!currentChargingStack.isEmpty()) {

            double requiredSpeed = currentSpeedChargeItem.getRequiredSpeed();
            currentPercentage = (currentSpeed / requiredSpeed) * 100;

            if (currentPercentage >= 100) {

                if (currentChargeCooldown <= 0) {
                    currentSpeedChargeItem.onSpeedRequirementMet(serverPlayer, currentChargingStack);
                    currentChargeCooldown = Mth.clamp(10 - ((currentSpeed - requiredSpeed) * 5), 0, 10);
                }
            }
        }

        ItemStack heldStack = ItemStack.EMPTY;

        if (serverPlayer.getMainHandItem().getItem() instanceof SpeedometerItem) {
            heldStack = serverPlayer.getMainHandItem();
        }

        else if (serverPlayer.getOffhandItem().getItem() instanceof SpeedometerItem) {
            heldStack = serverPlayer.getOffhandItem();
        }

        if (!heldStack.isEmpty()) {

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
}
