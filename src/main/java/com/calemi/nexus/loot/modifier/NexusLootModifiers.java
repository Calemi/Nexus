package com.calemi.nexus.loot.modifier;

import com.calemi.nexus.main.Nexus;
import com.calemi.nexus.main.NexusRef;
import com.mojang.serialization.MapCodec;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class NexusLootModifiers {

    public static final DeferredRegister<MapCodec<? extends IGlobalLootModifier>> LOOT_MODIFIERS = DeferredRegister.create(NeoForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, NexusRef.ID);

    public static final Supplier<MapCodec<BonusItemLootModifier>> BONUS_ITEM =
            LOOT_MODIFIERS.register("bonus_item", () -> BonusItemLootModifier.CODEC);

    public static void init() {
        Nexus.LOGGER.info("Registering: Loot Modifiers - Start");
        LOOT_MODIFIERS.register(Nexus.MOD_EVENT_BUS);
        Nexus.LOGGER.info("Registering: Loot Modifiers - End");
    }
}