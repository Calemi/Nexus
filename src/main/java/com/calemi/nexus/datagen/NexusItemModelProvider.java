package com.calemi.nexus.datagen;

import com.calemi.nexus.item.SpeedometerItem;
import com.calemi.nexus.main.NexusRef;
import com.calemi.nexus.util.NexusLists;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.Locale;

public class NexusItemModelProvider extends ItemModelProvider {

    public NexusItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, NexusRef.ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        NexusLists.ALL_ITEMS.forEach(item -> {

            if (item instanceof SpeedometerItem) {

                ItemModelBuilder speedometer = factory.apply(NexusRef.rl("item/speedometer"))
                        .parent(new ModelFile.UncheckedModelFile("item/generated"))
                        .texture("layer0", NexusRef.rl("item/speedometer_00"));;

                for (int i = 1; i < 16; i++) {

                    ItemModelBuilder.OverrideBuilder override = speedometer.override();
                    override.predicate(NexusRef.rl("speed"), (float)i/16);
                    override.model(basicItem(NexusRef.rl("speedometer" + String.format(Locale.ROOT, "_%02d", i))));
                }

                generatedModels.put(NexusRef.rl("speedometer"), speedometer);

                return;
            }

            basicItem(item);
        });
    }
}
