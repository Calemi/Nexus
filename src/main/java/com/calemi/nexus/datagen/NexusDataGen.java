package com.calemi.nexus.datagen;

import com.calemi.nexus.main.NexusRef;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = NexusRef.ID, bus = EventBusSubscriber.Bus.MOD)
public class NexusDataGen {

    @SubscribeEvent
    static void onGatherData(GatherDataEvent event) {

        DataGenerator generator = event.getGenerator();
        PackOutput output = event.getGenerator().getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        NexusBlockTagProvider blockTagProvider = new NexusBlockTagProvider(output, lookupProvider, existingFileHelper);

        //BLOCKSTATES
        generator.addProvider(event.includeClient(), new NexusBlockStateProvider(output, existingFileHelper));

        //BLOCK LOOT TABLES
        generator.addProvider(event.includeServer(), new LootTableProvider(output, Collections.emptySet(),
                List.of(new LootTableProvider.SubProviderEntry(NexusBlockLootTableProvider::new, LootContextParamSets.BLOCK)), lookupProvider));

        //ITEM MODELS
        generator.addProvider(event.includeClient(), new NexusItemModelProvider(output, existingFileHelper));

        //BLOCK TAGS
        generator.addProvider(event.includeServer(), blockTagProvider);

        //ITEM TAGS
        generator.addProvider(event.includeServer(), new NexusItemTagProvider(output, lookupProvider, blockTagProvider.contentsGetter(), existingFileHelper));

        //PARTICLES
        generator.addProvider(event.includeClient(), new NexusParticleDescriptionProvider(output, existingFileHelper));

        //RECIPES
        generator.addProvider(event.includeServer(), new NexusRecipeProvider(output, lookupProvider));

        //DATAMAPS
        generator.addProvider(event.includeServer(), new NexusDataMapProvider(output, lookupProvider));

        //DATAPACK
        generator.addProvider(event.includeServer(), new NexusDatapackProvider(output, lookupProvider));
    }
}