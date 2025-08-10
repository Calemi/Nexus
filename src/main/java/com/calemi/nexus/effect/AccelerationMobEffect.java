package com.calemi.nexus.effect;

import com.calemi.nexus.main.NexusRef;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class AccelerationMobEffect extends MobEffect {

    protected AccelerationMobEffect() {
        super(MobEffectCategory.BENEFICIAL, 0x56CBFD);
        addAttributeModifier(Attributes.ATTACK_SPEED, NexusRef.rl("effect.acceleration_attack_speed"), 0.25F, AttributeModifier.Operation.ADD_VALUE);
        addAttributeModifier(Attributes.MINING_EFFICIENCY, NexusRef.rl("effect.acceleration_mining_efficiency"), 0.5F, AttributeModifier.Operation.ADD_VALUE);
        addAttributeModifier(Attributes.MOVEMENT_SPEED, NexusRef.rl("effect.acceleration_movement_speed"), 0.01F, AttributeModifier.Operation.ADD_VALUE);
    }
}
