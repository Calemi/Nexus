package com.calemi.nexus.item;

import com.calemi.ccore.api.item.ItemSpawnProfile;
import com.calemi.nexus.main.Nexus;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;

public class FallbreakersImmunityAction {

    @SubscribeEvent
    public void onLivingDamage(LivingDamageEvent.Pre event) {

        LivingEntity entity = event.getEntity();
        Level level = entity.level();

        if (level.isClientSide()) return;
        if (!event.getSource().is(DamageTypes.FALL)) return;

        if (entity.getHealth() - event.getNewDamage() <= 0) {

            ItemStack fallbreakersStack = entity.getItemBySlot(EquipmentSlot.FEET);

            if (fallbreakersStack.getItem() instanceof FallbreakersItem) {

                event.setNewDamage(0);

                int itemDamage = EnchantmentHelper.processDurabilityChange((ServerLevel) level, fallbreakersStack, 1);

                if (itemDamage > 0) {

                    if (entity instanceof ServerPlayer serverPlayer) {
                        entity.onEquippedItemBroken(fallbreakersStack.getItem(), EquipmentSlot.FEET);
                    }

                    fallbreakersStack.shrink(1);

                    new ItemSpawnProfile().setItem(Items.AMETHYST_SHARD).setAmount(entity.getRandom().nextInt(3)).setDestination(entity).spawn();
                }
            }
        }
    }
}
