package com.calemi.nexus.datagen;

import com.calemi.nexus.loot.modifier.BonusItemLootModifier;
import com.calemi.nexus.loot.condition.LootTableCheck;
import com.calemi.nexus.main.NexusRef;
import com.calemi.nexus.item.NexusItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class NexusGlobalLootModifierProvider extends GlobalLootModifierProvider {

    public NexusGlobalLootModifierProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, NexusRef.ID);
    }

    @Override
    protected void start() {
        add("chrono_upgrade_from_chamber_chest", new BonusItemLootModifier(new LootItemCondition[]{
                new LootTableCheck(List.of(
                        ResourceLocation.withDefaultNamespace("chests/trial_chambers/corridor"),
                        ResourceLocation.withDefaultNamespace("chests/trial_chambers/intersection"),
                        ResourceLocation.withDefaultNamespace("chests/trial_chambers/intersection_barrel"))),
        }, 0.25F, new ItemStack(NexusItems.CHRONO_UPGRADE_SMITHING_TEMPLATE.get(), 2)));

        add("chrono_upgrade_from_chamber_vault", new BonusItemLootModifier(new LootItemCondition[]{
                new LootTableCheck(List.of(ResourceLocation.withDefaultNamespace("chests/trial_chambers/reward")))
        }, 0.75F, new ItemStack(NexusItems.CHRONO_UPGRADE_SMITHING_TEMPLATE.get(), 2)));

        add("chrono_shard_from_chamber_vault", new BonusItemLootModifier(new LootItemCondition[]{
                new LootTableCheck(List.of(ResourceLocation.withDefaultNamespace("chests/trial_chambers/reward")))
        }, 0.25F, new ItemStack(NexusItems.CHRONO_SHARD.get(), 1)));
    }
}
