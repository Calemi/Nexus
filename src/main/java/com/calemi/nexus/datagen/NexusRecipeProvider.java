package com.calemi.nexus.datagen;

import com.calemi.ccore.api.datagen.CRecipeProvider;
import com.calemi.nexus.block.NexusBlocks;
import com.calemi.nexus.block.family.NexusBlockFamilies;
import com.calemi.nexus.item.NexusItems;
import com.calemi.nexus.main.NexusRef;
import com.calemi.nexus.tag.NexusTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.Tags;
import org.jetbrains.annotations.Nullable;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class NexusRecipeProvider extends CRecipeProvider {

    public NexusRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(NexusRef.ID, output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {

        /*
            BLOCKS
         */

        NexusBlockFamilies.ALL.forEach(set -> family(set, recipeOutput));

        portalCore(
                NexusBlocks.NEXUS_PORTAL_CORE,
                Ingredient.of(Tags.Items.OBSIDIANS_NORMAL),
                Ingredient.of(Tags.Items.COBBLESTONES_DEEPSLATE),
                Ingredient.of(Items.COMPASS),
                recipeOutput
        );

        portalCore(
                NexusBlocks.IRON_NEXUS_PORTAL_CORE,
                Ingredient.of(NexusBlocks.NEXUS_PORTAL_CORE),
                Ingredient.of(Tags.Items.INGOTS_IRON),
                Ingredient.of(Items.POPPY),
                recipeOutput
        );

        portalCore(
                NexusBlocks.GOLD_NEXUS_PORTAL_CORE,
                Ingredient.of(NexusBlocks.IRON_NEXUS_PORTAL_CORE),
                Ingredient.of(Tags.Items.INGOTS_GOLD),
                Ingredient.of(Items.GOLDEN_APPLE),
                recipeOutput
        );

        portalCore(
                NexusBlocks.DIAMOND_NEXUS_PORTAL_CORE,
                Ingredient.of(NexusBlocks.GOLD_NEXUS_PORTAL_CORE),
                Ingredient.of(Tags.Items.GEMS_DIAMOND),
                Ingredient.of(Tags.Items.OBSIDIANS_NORMAL),
                recipeOutput
        );

        portalCore(
                NexusBlocks.NETHERITE_NEXUS_PORTAL_CORE,
                Ingredient.of(NexusBlocks.DIAMOND_NEXUS_PORTAL_CORE),
                Ingredient.of(Tags.Items.INGOTS_NETHERITE),
                Ingredient.of(Items.GHAST_TEAR),
                recipeOutput
        );

        portalCore(
                NexusBlocks.STARLIGHT_NEXUS_PORTAL_CORE,
                Ingredient.of(NexusBlocks.NETHERITE_NEXUS_PORTAL_CORE),
                Ingredient.of(Items.ECHO_SHARD),
                Ingredient.of(Items.NETHER_STAR),
                recipeOutput
        );

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, NexusBlocks.ROAD, 16)
                .define('X', NexusItems.CHARGED_ACCELERITE_INGOT)
                .define('W', NexusBlocks.COBBLED_WARPSLATE)
                .pattern("XWX")
                .pattern("WWW")
                .pattern("XWX")
                .unlockedBy("has_charged_accelerite_ingot", has(NexusItems.CHARGED_ACCELERITE_INGOT))
                .save(recipeOutput);

        slab(recipeOutput, RecipeCategory.MISC, NexusBlocks.ROAD_SLAB, NexusBlocks.ROAD);
        stonecutter(recipeOutput, RecipeCategory.BUILDING_BLOCKS, NexusBlocks.ROAD_SLAB, NexusBlocks.ROAD, 2);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, NexusBlocks.JUMP_PAD, 1)
                .define('X', NexusItems.CHRONO_SHARD)
                .define('W', NexusBlocks.COBBLED_WARPSLATE)
                .pattern("XWX")
                .pattern("WWW")
                .pattern("XWX")
                .unlockedBy("has_chrono_shard", has(NexusItems.CHRONO_SHARD))
                .save(recipeOutput);

        threeByThreePacker(recipeOutput, RecipeCategory.MISC, NexusItems.CHRONO_SHARD, NexusItems.CHRONO_SHARD_FRAGMENT);
        oneToOne(recipeOutput, NexusItems.CHRONO_SHARD_FRAGMENT, NexusItems.CHRONO_SHARD, null, 9);

        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(NexusItems.CHRONO_UPGRADE_SMITHING_TEMPLATE),
                        Ingredient.of(Tags.Items.GEMS_AMETHYST),
                        Ingredient.of(Tags.Items.ENDER_PEARLS),
                        RecipeCategory.MISC,
                        NexusItems.CHRONO_SHARD.get()
                )
                .unlocks("has_ender_pearl", has(Tags.Items.ENDER_PEARLS))
                .save(recipeOutput, NexusRef.rl("chrono_shard_from_smithing"));

        ShapedRecipeBuilder.shaped(RecipeCategory.TRANSPORTATION, NexusItems.CHRONO_UPGRADE_SMITHING_TEMPLATE, 2)
                .define('T', NexusItems.CHRONO_UPGRADE_SMITHING_TEMPLATE)
                .define('M', Items.AMETHYST_SHARD)
                .define('B', Blocks.CALCITE)
                .pattern("MTM")
                .pattern("MBM")
                .pattern("MMM")
                .unlockedBy("has_chrono_upgrade", has(NexusItems.CHRONO_UPGRADE_SMITHING_TEMPLATE))
                .save(recipeOutput);

        twoByTwoPacker(recipeOutput, RecipeCategory.DECORATIONS, NexusBlocks.CHRONO_BLOCK, NexusItems.CHRONO_SHARD);

        smeltingResultFromBase(recipeOutput, NexusBlocks.WARPSLATE, NexusBlocks.COBBLED_WARPSLATE);

        /*
            ITEMS
         */

        oreSmelting(recipeOutput, List.of(NexusItems.RAW_ACCELERITE.get(), NexusBlocks.WARPSLATE_ACCELERITE_ORE.asItem()), RecipeCategory.MISC, NexusItems.DORMANT_ACCELERITE_INGOT.get(), 1F, 200, "accelerite_ingot");
        oreBlasting(recipeOutput, List.of(NexusItems.RAW_ACCELERITE.get(), NexusBlocks.WARPSLATE_ACCELERITE_ORE.asItem()), RecipeCategory.MISC, NexusItems.DORMANT_ACCELERITE_INGOT.get(), 1F, 100, "accelerite_ingot");

        threeByThreePacker(recipeOutput, RecipeCategory.MISC, NexusBlocks.RAW_ACCELERITE_BLOCK, NexusItems.RAW_ACCELERITE);
        oneToOne(recipeOutput, NexusItems.RAW_ACCELERITE, NexusBlocks.RAW_ACCELERITE_BLOCK, null, 9);

        threeByThreePacker(recipeOutput, RecipeCategory.MISC, NexusBlocks.DORMANT_ACCELERITE_BLOCK, NexusItems.DORMANT_ACCELERITE_INGOT);
        oneToOne(recipeOutput, NexusItems.DORMANT_ACCELERITE_INGOT, NexusBlocks.DORMANT_ACCELERITE_BLOCK, null, 9);

        threeByThreePacker(recipeOutput, RecipeCategory.MISC, NexusBlocks.CHARGED_ACCELERITE_BLOCK, NexusItems.CHARGED_ACCELERITE_INGOT);
        oneToOne(recipeOutput, NexusItems.CHARGED_ACCELERITE_INGOT, NexusBlocks.CHARGED_ACCELERITE_BLOCK, null, 9);

        oneByTwoVertical(recipeOutput, NexusItems.WARPBLOSSOM_STICK, 4, NexusBlocks.WARPBLOSSOM_PLANKS);

        sword(recipeOutput, NexusItems.ACCELERITE_SWORD, NexusItems.CHARGED_ACCELERITE_INGOT);
        shovel(recipeOutput, NexusItems.ACCELERITE_SHOVEL, NexusItems.CHARGED_ACCELERITE_INGOT);
        pickaxe(recipeOutput, NexusItems.ACCELERITE_PICKAXE, NexusItems.CHARGED_ACCELERITE_INGOT);
        axe(recipeOutput, NexusItems.ACCELERITE_AXE, NexusItems.CHARGED_ACCELERITE_INGOT);
        hoe(recipeOutput, NexusItems.ACCELERITE_HOE, NexusItems.CHARGED_ACCELERITE_INGOT);

        helmet(recipeOutput, NexusItems.ACCELERITE_HELMET, NexusItems.CHARGED_ACCELERITE_INGOT);
        chestplate(recipeOutput, NexusItems.ACCELERITE_CHESTPLATE, NexusItems.CHARGED_ACCELERITE_INGOT);
        leggings(recipeOutput, NexusItems.ACCELERITE_LEGGINGS, NexusItems.CHARGED_ACCELERITE_INGOT);
        boots(recipeOutput, NexusItems.ACCELERITE_BOOTS, NexusItems.CHARGED_ACCELERITE_INGOT);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, NexusItems.TOTEM_OF_WARPING, 1)
                .define('C', NexusItems.CHRONO_SHARD)
                .define('T', Items.TOTEM_OF_UNDYING)
                .pattern(" C ")
                .pattern("CTC")
                .pattern(" C ")
                .unlockedBy("has_totem_of_undying", has(Items.TOTEM_OF_UNDYING))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, NexusItems.SPEEDOMETER, 1)
                .define('I', NexusTags.Items.ACCELERITE_INGOTS)
                .define('S', Items.AMETHYST_SHARD.asItem())
                .pattern(" I ")
                .pattern("ISI")
                .pattern(" I ")
                .unlockedBy("has_accelerite", has(NexusTags.Items.ACCELERITE_INGOTS))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, NexusItems.FALLBREAKERS, 1)
                .define('S', Tags.Items.GEMS_AMETHYST)
                .define('W', ItemTags.WOOL)
                .pattern("S S")
                .pattern("W W")
                .unlockedBy("has_amethyst_shard", has(Tags.Items.GEMS_AMETHYST))
                .save(recipeOutput);

        woodenBoat(recipeOutput, NexusItems.WARPBLOSSOM_BOAT, NexusBlocks.WARPBLOSSOM_PLANKS);
        chestBoat(recipeOutput, NexusItems.WARPBLOSSOM_CHEST_BOAT, NexusItems.WARPBLOSSOM_BOAT);

        oneToOne(recipeOutput, Items.PURPLE_DYE, NexusBlocks.PURPLE_PETALS, "purple_dye", 1);
    }

    private void portalCore(ItemLike result, Ingredient prevPortalCore, Ingredient tier, Ingredient center, RecipeOutput recipeOutput) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TRANSPORTATION, result, 2)
                .define('I', NexusItems.CHRONO_SHARD)
                .define('T', tier)
                .define('C', prevPortalCore)
                .define('S', center)
                .pattern("TCT")
                .pattern("ISI")
                .pattern("TCT")
                .unlockedBy("has_chrono_shard", has(NexusItems.CHRONO_SHARD))
                .save(recipeOutput, NexusRef.rl(getItemName(result)));
    }
}
