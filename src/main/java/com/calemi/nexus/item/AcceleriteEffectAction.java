package com.calemi.nexus.item;

import com.calemi.ccore.api.math.MathHelper;
import com.calemi.nexus.item.enchantment.NexusEnchantments;
import com.calemi.nexus.util.AccelerationMobEffectHelper;
import net.minecraft.core.Holder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.player.AttackEntityEvent;
import net.neoforged.neoforge.event.level.BlockEvent;

public class AcceleriteEffectAction {

    @SubscribeEvent
    public void onLivingDamage(LivingDamageEvent.Post event) {

        float chance = 0.0F;
        int effectDuration = 0;

        for (ItemStack armorItemStack : event.getEntity().getArmorSlots()) {

            if (armorItemStack.getItem() instanceof AccelerationEffectItem accelerationEffectItem) {
                chance += 0.25F;
                if (accelerationEffectItem.getEffectDuration() > effectDuration) effectDuration = accelerationEffectItem.getEffectDuration();
                continue;
            }

            int accelerationEnchantmentLevel = getAccelerationEnchantmentLevel(event.getEntity().level(), armorItemStack);

            if (accelerationEnchantmentLevel > 0) {
                chance += 0.25F;
                int enchantmentDuration = accelerationEnchantmentLevel * 100;
                if (enchantmentDuration > effectDuration) effectDuration = enchantmentDuration;
            }
        }

        if (MathHelper.rollChance(chance)) {
            AccelerationMobEffectHelper.applyAccelerationEffect(event.getEntity(), effectDuration);
        }
    }

    @SubscribeEvent
    public void onAttackEntity(AttackEntityEvent event) {

        if (event.getEntity().swinging) return;

        ItemStack heldStack = event.getEntity().getMainHandItem();

        if (heldStack.getItem() instanceof AccelerationEffectItem accelerationEffectItem) {
            AccelerationMobEffectHelper.applyAccelerationEffect(event.getEntity(), accelerationEffectItem.getEffectDuration());
        }

        int accelerationEnchantmentLevel = getAccelerationEnchantmentLevel(event.getEntity().level(), heldStack);

        if (accelerationEnchantmentLevel > 0) {
            AccelerationMobEffectHelper.applyAccelerationEffect(event.getEntity(), 100 * accelerationEnchantmentLevel);
        }
    }

    @SubscribeEvent
    public void onBlockBreak(BlockEvent.BreakEvent event) {

        if (event.getState().getDestroySpeed(event.getLevel(), event.getPos()) <= 0) return;

        ItemStack heldStack = event.getPlayer().getMainHandItem();

        if (heldStack.getItem() instanceof AccelerationEffectItem accelerationEffectItem) {
            AccelerationMobEffectHelper.applyAccelerationEffect(event.getPlayer(), accelerationEffectItem.getEffectDuration());
        }

        int accelerationEnchantmentLevel = getAccelerationEnchantmentLevel(event.getPlayer().level(), heldStack);

        if (accelerationEnchantmentLevel > 0) {
            AccelerationMobEffectHelper.applyAccelerationEffect(event.getPlayer(), 100 * accelerationEnchantmentLevel);
        }
    }

    private int getAccelerationEnchantmentLevel(Level level, ItemStack stack) {
        Holder<Enchantment> enchantment = level.holder(NexusEnchantments.ACCELERATION).orElse(null);
        return EnchantmentHelper.getTagEnchantmentLevel(enchantment, stack);
    }
}
