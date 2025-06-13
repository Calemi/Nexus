package com.calemi.nexus.datagen;

import com.calemi.nexus.block.NexusBlocks;
import com.calemi.nexus.item.NexusItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.registries.datamaps.builtin.Compostable;
import net.neoforged.neoforge.registries.datamaps.builtin.FurnaceFuel;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;

import java.util.concurrent.CompletableFuture;

public class NexusDataMapProvider extends DataMapProvider {

    public NexusDataMapProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(packOutput, lookupProvider);
    }

    @Override
    protected void gather(HolderLookup.Provider provider) {

        builder(NeoForgeDataMaps.COMPOSTABLES)
                .replace(false)
                .add(NexusBlocks.WARPBLOSSOM_LEAVES.getId(), new Compostable(0.3F), false)
                .add(NexusBlocks.WARPBLOSSOM_SAPLING.getId(), new Compostable(0.3F), false)
                .add(NexusBlocks.PURPLE_PETALS.getId(), new Compostable(0.3F), false);

        builder(NeoForgeDataMaps.FURNACE_FUELS)
                .replace(false)
                .add(NexusItems.WARPBLOSSOM_STICK.getId(), new FurnaceFuel(100), false);
    }
}