package com.calemi.nexus.item;

import com.calemi.nexus.tag.NexusTags;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;

public class NexusTiers {

    public static final Tier ACCELERITE = new SimpleTier(NexusTags.Blocks.INCORRECT_FOR_ACCELERITE_TOOL, 500, 6.0F, 2.0F, 16, () -> Ingredient.of(NexusItems.CHARGED_ACCELERITE_INGOT));
}
