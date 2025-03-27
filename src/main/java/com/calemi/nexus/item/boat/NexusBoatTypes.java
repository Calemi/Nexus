package com.calemi.nexus.item.boat;

import com.calemi.ccore.api.boat.CBoatType;
import com.calemi.ccore.api.boat.CBoatTypeRegistry;
import com.calemi.nexus.block.NexusBlocks;
import com.calemi.nexus.main.NexusRef;
import com.calemi.nexus.item.NexusItems;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;

public enum NexusBoatTypes implements CBoatType {

    WARPBLOSSOM ("warpblossom", NexusItems.WARPBLOSSOM_BOAT, NexusItems.WARPBLOSSOM_CHEST_BOAT, NexusBlocks.WARPBLOSSOM_PLANKS);

    private final String name;
    private final DeferredItem<Item> boatItem;
    private final DeferredItem<Item> chestBoatItem;
    private final DeferredBlock<Block> planks;

    NexusBoatTypes(String name, DeferredItem<Item> boatItem, DeferredItem<Item> chestBoatItem, DeferredBlock<Block> planks) {
        this.name = name;
        this.boatItem = boatItem;
        this.chestBoatItem = chestBoatItem;
        this.planks = planks;
        CBoatTypeRegistry.register(this);
    }

    @Override
    public String getModId() {
        return NexusRef.ID;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public DeferredItem<Item> getBoatItem() {
        return boatItem;
    }

    @Override
    public DeferredItem<Item> getChestBoatItem() {
        return chestBoatItem;
    }

    @Override
    public DeferredBlock<Block> getPlanks() {
        return planks;
    }
}
