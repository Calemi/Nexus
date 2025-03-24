package com.calemi.nexus.datagen;

import com.calemi.nexus.regsitry.NexusBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.registries.datamaps.builtin.Compostable;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;

import java.util.concurrent.CompletableFuture;

public class NexusDataMapProvider extends DataMapProvider {

    public NexusDataMapProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(packOutput, lookupProvider);
    }

    @Override
    protected void gather() {

        builder(NeoForgeDataMaps.COMPOSTABLES)
                .replace(false)
                .add(NexusBlocks.WARPBLOSSOM_LEAVES.getId(), new Compostable(0.3F), false)
                .add(NexusBlocks.WARPBLOSSOM_SAPLING.getId(), new Compostable(0.3F), false);
    }
}