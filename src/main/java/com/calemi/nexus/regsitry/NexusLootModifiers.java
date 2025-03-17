package com.calemi.nexus.regsitry;

import com.calemi.nexus.loot.ChronoUpgradeLootModifier;
import com.calemi.nexus.main.Nexus;
import com.calemi.nexus.main.NexusRef;
import com.mojang.serialization.MapCodec;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class NexusLootModifiers {

    public static final DeferredRegister<MapCodec<? extends IGlobalLootModifier>> LOOT_MODIFIERS = DeferredRegister.create(NeoForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, NexusRef.MOD_ID);

    public static final Supplier<MapCodec<ChronoUpgradeLootModifier>> CHRONO_UPGRADE  =
            LOOT_MODIFIERS.register("chrono_upgrade", () -> ChronoUpgradeLootModifier.CODEC);

    public static void init() {
        LOOT_MODIFIERS.register(Nexus.MOD_EVENT_BUS);
    }
}