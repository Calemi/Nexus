package com.calemi.ccore.api.boat;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.Arrays;

public interface CBoatType {

    String getModId();

    String getName();

    DeferredItem<Item> getBoatItem();

    DeferredItem<Item> getChestBoatItem();

    DeferredBlock<Block> getPlanks();
}
