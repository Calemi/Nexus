package com.calemi.nexus.util;

import com.calemi.nexus.effect.NexusMobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;

public class AccelerationMobEffectHelper {

    public static void applyAccelerationEffect(LivingEntity entity, int duration, int maxAmplifier) {

        if (duration <= 0 || maxAmplifier < 0) return;

        int amplifier = 0;

        MobEffectInstance existingEffect = entity.getEffect(NexusMobEffects.ACCELERATION);

        if (existingEffect != null) {
            amplifier = Math.min(maxAmplifier, existingEffect.getAmplifier() + 1);
        }

        entity.addEffect(new MobEffectInstance(NexusMobEffects.ACCELERATION, duration, amplifier, true, false, true));
    }
}
