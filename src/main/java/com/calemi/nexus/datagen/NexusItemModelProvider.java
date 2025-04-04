package com.calemi.nexus.datagen;

import com.calemi.nexus.item.FallbreakersItem;
import com.calemi.nexus.item.SpeedometerItem;
import com.calemi.nexus.main.NexusRef;
import com.calemi.nexus.util.NexusLists;
import net.minecraft.data.PackOutput;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.resources.ResourceLocation;
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

            if (item instanceof FallbreakersItem) {

                ItemModelBuilder fallbreakers = factory.apply(NexusRef.rl("item/fallbreakers"))
                        .parent(new ModelFile.UncheckedModelFile("item/generated"))
                        .texture("layer0", NexusRef.rl("item/fallbreakers"));;

                for (int i = 0; i < ItemModelGenerators.GENERATED_TRIM_MODELS.size(); i++) {

                    ItemModelGenerators.TrimModelData trimData = ItemModelGenerators.GENERATED_TRIM_MODELS.get(i);
                    String parentTrimName = trimData.name() + (trimData.name().equals("iron") ? "_darker" : "");
                    String trimName = trimData.name() + (trimData.name().equals("amethyst") ? "_darker" : "");

                    ItemModelBuilder trimModel = getBuilder("item/fallbreakers_" + trimName + "_trim")
                            .parent(new ModelFile.UncheckedModelFile("item/iron_boots_" + parentTrimName + "_trim"))
                            .texture("layer0", NexusRef.rl("item/fallbreakers"));

                    ItemModelBuilder.OverrideBuilder override = fallbreakers.override();
                    override.predicate(ResourceLocation.withDefaultNamespace("trim_type"), trimData.itemModelIndex());
                    override.model(trimModel);
                }

                generatedModels.put(NexusRef.rl("fallbreakers"), fallbreakers);
            }

            basicItem(item);
        });
    }
}
