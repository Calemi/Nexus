package com.calemi.nexus.datagen;

import com.calemi.nexus.main.NexusRef;
import com.calemi.nexus.tag.NexusTags;
import com.calemi.nexus.world.biome.NexusBiomes;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class NexusBiomeTagProvider extends BiomeTagsProvider {

    public NexusBiomeTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, provider, NexusRef.ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

        tag(NexusTags.Biomes.VALID_RUINED_NEXUS_PORTAL_BIOMES).add(NexusBiomes.CHRONOWARPED_FIELDS);
    }
}
