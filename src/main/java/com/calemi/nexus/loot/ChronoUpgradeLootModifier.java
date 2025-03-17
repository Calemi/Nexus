package com.calemi.nexus.loot;

import com.calemi.ccore.api.log.LogHelper;
import com.calemi.ccore.api.math.MathHelper;
import com.calemi.nexus.main.NexusRef;
import com.calemi.nexus.regsitry.NexusItems;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;

public class ChronoUpgradeLootModifier extends LootModifier {

    public ChronoUpgradeLootModifier(LootItemCondition[] conditionsIn) {
        super(conditionsIn);
    }

    @Override
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {

        LogHelper.log(NexusRef.MOD_ID, context.getQueriedLootTableId().getPath());

        if (context.getQueriedLootTableId().getPath().equals("chests/trial_chambers/corridor") ||
                context.getQueriedLootTableId().getPath().equals("chests/trial_chambers/intersection") ||
                context.getQueriedLootTableId().getPath().equals("chests/trial_chambers/intersection_barrel")
        ) {

            if (MathHelper.rollChance(0.25F)) {
                generatedLoot.add(new ItemStack(NexusItems.CHRONO_UPGRADE_SMITHING_TEMPLATE.get(), 2));
            }
        }

        if (context.getQueriedLootTableId().getPath().equals("chests/trial_chambers/reward")) {

            if (MathHelper.rollChance(0.75F)) {
                generatedLoot.add(new ItemStack(NexusItems.CHRONO_UPGRADE_SMITHING_TEMPLATE.get(), 2));
            }

            else if (MathHelper.rollChance(0.1F)) {
                generatedLoot.add(new ItemStack(NexusItems.CHRONO_SHARD.get(), 1));
            }
        }

        return generatedLoot;
    }

    public static final MapCodec<ChronoUpgradeLootModifier> CODEC = RecordCodecBuilder.mapCodec(inst -> codecStart(inst).apply(inst, ChronoUpgradeLootModifier::new));

    @Override
    public MapCodec<? extends IGlobalLootModifier> codec() {
        return CODEC;
    }
}