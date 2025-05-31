package com.calemi.nexus.effect;

import com.calemi.nexus.main.NexusRef;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class AccelerationMobEffect extends MobEffect {

    protected AccelerationMobEffect() {
        super(MobEffectCategory.HARMFUL, 0x56CBFD);
        addAttributeModifier(Attributes.ATTACK_SPEED, NexusRef.rl("effect.attack_speed"), 0.25F, AttributeModifier.Operation.ADD_VALUE);
        addAttributeModifier(Attributes.MINING_EFFICIENCY, NexusRef.rl("effect.mining_efficiency"), 0.5F, AttributeModifier.Operation.ADD_VALUE);
        addAttributeModifier(Attributes.MOVEMENT_SPEED, NexusRef.rl("effect.movement_speed"), 0.01F, AttributeModifier.Operation.ADD_VALUE);
    }
}
