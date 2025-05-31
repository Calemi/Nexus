package com.calemi.nexus.effect;

import com.calemi.nexus.main.Nexus;
import com.calemi.nexus.main.NexusRef;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class NexusMobEffects {

    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(Registries.MOB_EFFECT, NexusRef.ID);

    public static final DeferredHolder<MobEffect, MobEffect> ACCELERATION = MOB_EFFECTS.register("acceleration", AccelerationMobEffect::new);

    public static void init() {
        Nexus.LOGGER.info("Registering: Mob Effects - Start");
        MOB_EFFECTS.register(Nexus.MOD_EVENT_BUS);
        Nexus.LOGGER.info("Registering: Mob Effects - End");
    }
}
