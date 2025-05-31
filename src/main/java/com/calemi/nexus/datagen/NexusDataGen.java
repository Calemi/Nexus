package com.calemi.nexus.datagen;

import com.calemi.nexus.main.NexusRef;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
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

        /*
            CLIENT
         */

        //LANGUAGE
        generator.addProvider(event.includeClient(), new NexusEnglishLanguageProvider(output));

        //BLOCKSTATES
        generator.addProvider(event.includeClient(), new NexusBlockStateProvider(output, existingFileHelper));

        //ITEM MODELS
        generator.addProvider(event.includeClient(), new NexusItemModelProvider(output, existingFileHelper));

        //PARTICLES
        generator.addProvider(event.includeClient(), new NexusParticleDescriptionProvider(output, existingFileHelper));

        /*
            REGISTRY
         */

        DatapackBuiltinEntriesProvider datapackProvider = new NexusDatapackProvider(output, event.getLookupProvider());
        CompletableFuture<HolderLookup.Provider> lookupProvider = datapackProvider.getRegistryProvider();

        NexusBlockTagProvider blockTagProvider = new NexusBlockTagProvider(output, lookupProvider, existingFileHelper);

        //DATA PACK
        generator.addProvider(event.includeServer(), datapackProvider);

        /*
            SERVER
         */

        //DATAMAPS
        generator.addProvider(event.includeServer(), new NexusDataMapProvider(output, lookupProvider));

        //BLOCK TAGS
        generator.addProvider(event.includeServer(), blockTagProvider);

        //BIOME TAGS
        generator.addProvider(event.includeServer(), new NexusBiomeTagProvider(output, lookupProvider, existingFileHelper));

        //ITEM TAGS
        generator.addProvider(event.includeServer(), new NexusItemTagProvider(output, lookupProvider, blockTagProvider.contentsGetter(), existingFileHelper));

        //ENCHANTMENT TAGS
        generator.addProvider(event.includeServer(), new NexusEnchantmentTagsProvider(output, lookupProvider, existingFileHelper));

        //GLOBAL LOOT MODIFIERS
        generator.addProvider(event.includeServer(), new NexusGlobalLootModifierProvider(output, lookupProvider));

        //BLOCK LOOT TABLES
        generator.addProvider(event.includeServer(), new LootTableProvider(output, Collections.emptySet(),
                List.of(new LootTableProvider.SubProviderEntry(NexusBlockLootTableProvider::new, LootContextParamSets.BLOCK)), lookupProvider));

        //RECIPES
        generator.addProvider(event.includeServer(), new NexusRecipeProvider(output, lookupProvider));
    }
}