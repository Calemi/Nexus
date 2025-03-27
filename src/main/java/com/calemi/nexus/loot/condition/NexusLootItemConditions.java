package com.calemi.nexus.loot.condition;

import com.calemi.nexus.main.Nexus;
import com.calemi.nexus.main.NexusRef;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class NexusLootItemConditions {

    public static final DeferredRegister<LootItemConditionType> LOOT_ITEM_CONDITION_TYPES = DeferredRegister.create(Registries.LOOT_CONDITION_TYPE, NexusRef.ID);

    public static final Supplier<LootItemConditionType> LOOT_TABLE_CHECK  =
            LOOT_ITEM_CONDITION_TYPES.register("loot_table_check", () -> new LootItemConditionType(LootTableCheck.CODEC));

    public static void init() {
        Nexus.LOGGER.info("Registering: Loot Item Conditions - Start");
        LOOT_ITEM_CONDITION_TYPES.register(Nexus.MOD_EVENT_BUS);
        Nexus.LOGGER.info("Registering: Loot Item Conditions - End");
    }
}