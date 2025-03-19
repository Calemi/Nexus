package com.calemi.nexus.datagen;

import com.calemi.nexus.main.NexusRef;
import com.calemi.nexus.regsitry.NexusItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class NexusItemModelProvider extends ItemModelProvider {

    public NexusItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, NexusRef.ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(NexusItems.CHRONO_SHARD.get());
        basicItem(NexusItems.CHRONO_UPGRADE_SMITHING_TEMPLATE.get());
    }
}
