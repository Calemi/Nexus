package com.calemi.nexus.loot.condition;

import com.calemi.nexus.regsitry.NexusLootItemConditions;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;

import java.util.List;

public record LootTableCheck(List<ResourceLocation> lootTablePaths) implements LootItemCondition {

    public static final MapCodec<LootTableCheck> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(
                            ExtraCodecs.nonEmptyList(ResourceLocation.CODEC.listOf()).fieldOf("loot_table_paths").forGetter(LootTableCheck::lootTablePaths))
                    .apply(instance, LootTableCheck::new)
    );

    @Override
    public LootItemConditionType getType() {
        return NexusLootItemConditions.LOOT_TABLE_CHECK.get();
    }

    @Override
    public boolean test(LootContext lootContext) {
        return lootTablePaths.contains(lootContext.getQueriedLootTableId());
    }
}
