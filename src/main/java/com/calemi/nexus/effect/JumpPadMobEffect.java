package com.calemi.nexus.effect;

import com.calemi.nexus.main.NexusRef;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class JumpPadMobEffect extends MobEffect {

    protected JumpPadMobEffect() {
        super(MobEffectCategory.BENEFICIAL, 0x99FF6D);
        addAttributeModifier(Attributes.JUMP_STRENGTH, NexusRef.rl("effect.jump_pad_jump_strength"), 0.11F, AttributeModifier.Operation.ADD_VALUE);
    }
}
