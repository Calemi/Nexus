package com.calemi.nexus.item;

import com.calemi.ccore.api.location.Location;
import com.calemi.nexus.packet.TotemOfWarpingPayload;
import com.calemi.nexus.util.NexusSoundHelper;
import com.calemi.nexus.util.TeleportHelper;
import com.calemi.nexus.world.dimension.NexusDimensionHelper;
import com.calemi.nexus.world.dimension.NexusDimensions;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;

public class TotemOfWarpingImmunityAction {

    @SubscribeEvent
    public void onLivingDamage(LivingDamageEvent.Pre event) {

        LivingEntity entity = event.getEntity();
        Level level = entity.level();

        if (level.isClientSide()) return;

        if (entity.getHealth() - event.getNewDamage() <= 0) {

            ItemStack totem = null;

            for (InteractionHand hand : InteractionHand.values()) {

                ItemStack heldStack = entity.getItemInHand(hand);

                if (heldStack.is(NexusItems.TOTEM_OF_WARPING)) {
                    totem = heldStack.copy();
                    heldStack.shrink(1);
                    break;
                }
            }

            if (totem != null) {

                if (!NexusDimensionHelper.isInNexus(entity)) {

                    if (level instanceof ServerLevel serverLevel) {

                        Level destinationLevel = serverLevel.getServer().getLevel(NexusDimensions.NEXUS_LEVEL);
                        int yLevel = NexusDimensionHelper.getSolidGroundLevel(destinationLevel, entity.blockPosition());

                        if (yLevel != Integer.MAX_VALUE) {
                            NexusSoundHelper.playTeleportSound(new Location(entity));
                            TeleportHelper.teleportToWorld(entity, new BlockPos(entity.getBlockX(), yLevel, entity.getBlockZ()), NexusDimensions.NEXUS_LEVEL);
                            NexusSoundHelper.playTeleportSound(new Location(entity));
                        }
                    }
                }

                event.setNewDamage(0);

                if (entity instanceof ServerPlayer serverplayer) {
                    serverplayer.awardStat(Stats.ITEM_USED.get(NexusItems.TOTEM_OF_WARPING.get()), 1);
                    CriteriaTriggers.USED_TOTEM.trigger(serverplayer, totem);
                    entity.gameEvent(GameEvent.ITEM_INTERACT_FINISH);
                    serverplayer.connection.send(new TotemOfWarpingPayload());
                }

                entity.setHealth(1.0F);
                entity.removeEffectsCuredBy(net.neoforged.neoforge.common.EffectCures.PROTECTED_BY_TOTEM);
                entity.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 900, 1));
                entity.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 100, 1));
                entity.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 800, 0));
            }
        }
    }
}
