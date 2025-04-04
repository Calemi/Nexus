package com.calemi.nexus.item.property;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.ClampedItemPropertyFunction;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public class SpeedometerProperty implements ClampedItemPropertyFunction {

    @Override
    public float unclampedCall(ItemStack stack, @Nullable ClientLevel level, @Nullable LivingEntity entity, int seed) {

        if (entity != null) {
            float speed = entity.getKnownMovement().toVector3f().length();
            float maxSpeed = 2F;

            return speed / maxSpeed;
        }

        return 0.0F;
    }
}
