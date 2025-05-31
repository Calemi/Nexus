package com.calemi.nexus.datagen;

import com.calemi.nexus.item.AcceleriteArmorItem;
import com.calemi.nexus.item.FallbreakersItem;
import com.calemi.nexus.item.NexusItems;
import com.calemi.nexus.item.SpeedometerItem;
import com.calemi.nexus.main.NexusRef;
import com.calemi.nexus.util.NexusLists;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.TieredItem;
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

            ResourceLocation itemKeyLocation = BuiltInRegistries.ITEM.getKey(item);
            String itemName = itemKeyLocation.getPath();
            ResourceLocation itemModelLocation = NexusRef.rl("item/" + itemName);

            ModelFile.UncheckedModelFile vanillaBaseParent = new ModelFile.UncheckedModelFile("item/generated");
            ModelFile.UncheckedModelFile vanillaHandheldParent = new ModelFile.UncheckedModelFile("item/handheld");

            if (item instanceof SpeedometerItem speedometerItem) {

                ItemModelBuilder speedometerBuilder = factory.apply(itemModelLocation)
                        .parent(vanillaBaseParent)
                        .texture("layer0", itemModelLocation.withSuffix("_00"));

                for (int i = 1; i < 16; i++) {

                    ItemModelBuilder.OverrideBuilder override = speedometerBuilder.override();
                    override.predicate(NexusRef.rl("speed"), (float) i / 16);
                    override.model(basicItem(itemKeyLocation.withSuffix(String.format(Locale.ROOT, "_%02d", i))));
                }

                generatedModels.put(itemKeyLocation, speedometerBuilder);
                return;
            }

            if (item instanceof FallbreakersItem fallbreakersItem) {

                ItemModelBuilder fallbreakersBuilder = factory.apply(itemModelLocation)
                        .parent(vanillaBaseParent)
                        .texture("layer0", itemModelLocation);

                for (int i = 0; i < ItemModelGenerators.GENERATED_TRIM_MODELS.size(); i++) {

                    ItemModelGenerators.TrimModelData trimData = ItemModelGenerators.GENERATED_TRIM_MODELS.get(i);

                    String trimName = trimData.name();

                    String parentTrimName = trimName + (trimName.equals("iron") ? "_darker" : "");
                    String finalTrimName = trimName + (trimName.equals("amethyst") ? "_darker" : "");

                    ItemModelBuilder trimModel = getBuilder("item/" + itemName + "_" + finalTrimName + "_trim")
                            .parent(new ModelFile.UncheckedModelFile("item/iron_boots_" + parentTrimName + "_trim"))
                            .texture("layer0", itemModelLocation);

                    ItemModelBuilder.OverrideBuilder override = fallbreakersBuilder.override();
                    override.predicate(ResourceLocation.withDefaultNamespace("trim_type"), trimData.itemModelIndex());
                    override.model(trimModel);
                }

                generatedModels.put(itemKeyLocation, fallbreakersBuilder);
                return;
            }

            if (item instanceof AcceleriteArmorItem acceleriteArmorItem) {

                ItemModelBuilder acceleriteArmorBuilder = factory.apply(itemModelLocation)
                        .parent(vanillaBaseParent)
                        .texture("layer0", itemModelLocation);

                for (int i = 0; i < ItemModelGenerators.GENERATED_TRIM_MODELS.size(); i++) {

                    ItemModelGenerators.TrimModelData trimData = ItemModelGenerators.GENERATED_TRIM_MODELS.get(i);

                    String trimName = trimData.name();

                    String parentTrimName = trimName + (trimName.equals("iron") ? "_darker" : "");

                    ItemModelBuilder trimModel = getBuilder("item/" + itemName + "_" + trimName + "_trim")
                            .parent(new ModelFile.UncheckedModelFile("item/iron_boots_" + parentTrimName + "_trim"))
                            .texture("layer0", itemModelLocation);

                    ItemModelBuilder.OverrideBuilder override = acceleriteArmorBuilder.override();
                    override.predicate(ResourceLocation.withDefaultNamespace("trim_type"), trimData.itemModelIndex());
                    override.model(trimModel);
                }

                generatedModels.put(itemKeyLocation, acceleriteArmorBuilder);
                return;
            }

            if (item instanceof TieredItem || item.equals(NexusItems.WARPBLOSSOM_STICK.get())) {

                ItemModelBuilder tieredItemModel = factory.apply(itemModelLocation)
                        .parent(vanillaHandheldParent)
                        .texture("layer0", itemModelLocation);

                generatedModels.put(itemKeyLocation, tieredItemModel);
                return;
            }

            basicItem(item);
        });
    }
}
