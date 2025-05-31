package com.calemi.nexus.item.enchantment;

import com.calemi.nexus.main.NexusRef;
import com.calemi.nexus.tag.NexusTags;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.*;
import net.minecraft.world.level.block.Block;

public class NexusEnchantments {

    public static final ResourceKey<Enchantment> ACCELERATION = NexusRef.createKey("acceleration", Registries.ENCHANTMENT);
    public static final ResourceKey<Enchantment> SPEED_MENDING = NexusRef.createKey("speed_mending", Registries.ENCHANTMENT);

    public static void bootstrap(BootstrapContext<Enchantment> context) {
        HolderGetter<Enchantment> enchantments = context.lookup(Registries.ENCHANTMENT);
        HolderGetter<Item> items = context.lookup(Registries.ITEM);
        HolderGetter<Block> blocks = context.lookup(Registries.BLOCK);

        register(context, ACCELERATION, new Enchantment.Builder(Enchantment.definition(
                items.getOrThrow(NexusTags.Items.ACCELERATION_ENCHANTABLE),
                1,
                3,
                Enchantment.dynamicCost(5, 9),
                Enchantment.dynamicCost(20, 9),
                8,
                EquipmentSlotGroup.ANY)
        ));

        register(context, SPEED_MENDING, new Enchantment.Builder(Enchantment.definition(
                items.getOrThrow(NexusTags.Items.SPEED_MENDING_ENCHANTABLE),
                1,
                1,
                Enchantment.dynamicCost(25, 25),
                Enchantment.dynamicCost(75, 25),
                5,
                EquipmentSlotGroup.ANY)
        ).exclusiveWith(HolderSet.direct(enchantments.getOrThrow(Enchantments.MENDING))));
    }

    private static void register(BootstrapContext<Enchantment> context, ResourceKey<Enchantment> key, Enchantment.Builder builder) {
        context.register(key, builder.build(key.location()));
    }
}
