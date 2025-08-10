package com.calemi.nexus.effect;

import com.calemi.nexus.main.NexusRef;
import com.calemi.nexus.tag.NexusTags;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.block.state.BlockState;

public class RoadMobEffect extends MobEffect {

    protected RoadMobEffect() {
        super(MobEffectCategory.BENEFICIAL, 0x56CBFD);
        addAttributeModifier(Attributes.MOVEMENT_SPEED, NexusRef.rl("effect.road_movement_speed"), 0.015F, AttributeModifier.Operation.ADD_VALUE);
    }

    @Override
    public boolean applyEffectTick(LivingEntity livingEntity, int amplifier) {

        BlockState stateOn = livingEntity.getBlockStateOn();

        if (stateOn.isAir()) {
            livingEntity.addEffect(new MobEffectInstance(NexusMobEffects.ROAD, 20, amplifier, true, false, true));
        }

        return stateOn.isAir() || stateOn.is(NexusTags.Blocks.ROADLIKE);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }
}
