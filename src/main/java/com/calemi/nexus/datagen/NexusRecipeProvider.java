package com.calemi.nexus.datagen;

import com.calemi.ccore.api.family.CBlockFamily;
import com.calemi.nexus.main.NexusRef;
import com.calemi.nexus.regsitry.NexusBlockFamilies;
import com.calemi.nexus.regsitry.NexusBlocks;
import com.calemi.nexus.regsitry.NexusItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.registries.DeferredBlock;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class NexusRecipeProvider extends RecipeProvider {

    public NexusRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
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
                Ingredient.of(Items.BARRIER),
                Ingredient.of(Items.ECHO_SHARD),
                recipeOutput
        );

        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(NexusItems.CHRONO_UPGRADE_SMITHING_TEMPLATE),
                        Ingredient.of(Tags.Items.GEMS_AMETHYST),
                        Ingredient.of(Tags.Items.ENDER_PEARLS),
                        RecipeCategory.MISC,
                        NexusItems.CHRONO_SHARD.get()
                )
                .unlocks("has_ender_pearl", has(Tags.Items.ENDER_PEARLS))
                .save(recipeOutput, NexusRef.rl("chrono_shard"));

        ShapedRecipeBuilder.shaped(RecipeCategory.TRANSPORTATION, NexusItems.CHRONO_UPGRADE_SMITHING_TEMPLATE, 2)
                .define('T', NexusItems.CHRONO_UPGRADE_SMITHING_TEMPLATE)
                .define('M', Items.AMETHYST_SHARD)
                .define('B', Blocks.CALCITE)
                .pattern("MTM")
                .pattern("MBM")
                .pattern("MMM")
                .unlockedBy("has_chrono_upgrade", has(NexusItems.CHRONO_UPGRADE_SMITHING_TEMPLATE))
                .save(recipeOutput);

        smeltingResultFromBase(recipeOutput, NexusBlocks.WARPSLATE, NexusBlocks.COBBLED_WARPSLATE);

        twoByTwo(NexusBlocks.POLISHED_WARPSLATE, 4, NexusBlocks.COBBLED_WARPSLATE, null, recipeOutput);
        twoByTwo(NexusBlocks.WARPSLATE_BRICKS, 4, NexusBlocks.POLISHED_WARPSLATE, null, recipeOutput);
        twoByTwo(NexusBlocks.WARPSLATE_TILES, 4, NexusBlocks.WARPSLATE_BRICKS, null, recipeOutput);

        /*
            ITEMS
         */

        woodenBoat(recipeOutput, NexusItems.WARPBLOSSOM_BOAT, NexusBlocks.WARPBLOSSOM_PLANKS);
        chestBoat(recipeOutput, NexusItems.WARPBLOSSOM_CHEST_BOAT, NexusItems.WARPBLOSSOM_BOAT);

        oneToOneConversionRecipe(recipeOutput, Items.PURPLE_DYE, NexusBlocks.PURPLE_PETALS, "purple_dye", 1);
    }

    private void family(CBlockFamily family, RecipeOutput recipeOutput) {

        DeferredBlock<Block> baseBlock = family.get(CBlockFamily.Variant.BASE);
        DeferredBlock<Block> log = family.get(CBlockFamily.Variant.LOG);
        DeferredBlock<Block> wood = family.get(CBlockFamily.Variant.WOOD);
        DeferredBlock<Block> strippedLog = family.get(CBlockFamily.Variant.STRIPPED_LOG);
        DeferredBlock<Block> strippedWood = family.get(CBlockFamily.Variant.STRIPPED_WOOD);
        DeferredBlock<Block> crackedBlock = family.get(CBlockFamily.Variant.CRACKED);
        DeferredBlock<Block> chiseled = family.get(CBlockFamily.Variant.CHISELED);
        DeferredBlock<Block> stairs = family.get(CBlockFamily.Variant.STAIRS);
        DeferredBlock<Block> slab = family.get(CBlockFamily.Variant.SLAB);
        DeferredBlock<Block> wall = family.get(CBlockFamily.Variant.WALL);
        DeferredBlock<Block> fence = family.get(CBlockFamily.Variant.FENCE);
        DeferredBlock<Block> fenceGate = family.get(CBlockFamily.Variant.FENCE_GATE);
        DeferredBlock<Block> door = family.get(CBlockFamily.Variant.DOOR);
        DeferredBlock<Block> trapDoor = family.get(CBlockFamily.Variant.TRAPDOOR);
        DeferredBlock<Block> pressurePlate = family.get(CBlockFamily.Variant.PRESSURE_PLATE);
        DeferredBlock<Block> button = family.get(CBlockFamily.Variant.BUTTON);
        DeferredBlock<Block> sign = family.get(CBlockFamily.Variant.SIGN);
        DeferredBlock<Block> hangingSign = family.get(CBlockFamily.Variant.HANGING_SIGN);

        if (log != null && baseBlock != null && family.getLogTag() != null) planks(family, recipeOutput);
        if (log != null && wood != null) wood(family, recipeOutput);
        if (strippedLog != null && strippedWood != null) strippedWood(family, recipeOutput);
        if (crackedBlock != null) smeltingResultFromBase(recipeOutput, crackedBlock, baseBlock);
        if (chiseled != null) chiseled(family, recipeOutput);
        if (stairs != null) stairs(family, recipeOutput);
        if (slab != null) slab(family, recipeOutput);
        if (wall != null) wall(family, recipeOutput);
        if (fence != null) fence(family, recipeOutput);
        if (fenceGate != null) fenceGate(family, recipeOutput);
        if (door != null) door(family, recipeOutput);
        if (trapDoor != null) trapDoor(family, recipeOutput);
        if (pressurePlate != null) pressurePlate(family, recipeOutput);
        if (button != null) button(family, recipeOutput);
        if (sign != null) sign(family, recipeOutput);
        if (hangingSign != null) hangingSign(family, recipeOutput);
    }

    private void twoByTwo(ItemLike result, int count, ItemLike ingredient, @Nullable String group, RecipeOutput recipeOutput) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, result, count)
                .define('#', ingredient)
                .pattern("##")
                .pattern("##")
                .group(group)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(recipeOutput);
    }

    private void oneByTwoVertical(ItemLike result, int count, ItemLike ingredient, RecipeOutput recipeOutput) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TRANSPORTATION, result, count)
                .define('X', ingredient)
                .pattern("X")
                .pattern("X")
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(recipeOutput);
    }

    private void wood(CBlockFamily family, RecipeOutput recipeOutput) {

        Block result = family.get(CBlockFamily.Variant.WOOD).get();
        Block ingredient =family.get(CBlockFamily.Variant.LOG).get();

        twoByTwo(result, 3, ingredient, "bark", recipeOutput);
    }

    private void strippedWood(CBlockFamily family, RecipeOutput recipeOutput) {

        Block result = family.get(CBlockFamily.Variant.STRIPPED_WOOD).get();
        Block ingredient = family.get(CBlockFamily.Variant.STRIPPED_LOG).get();

        twoByTwo(result, 3, ingredient, "bark", recipeOutput);
    }

    private void planks(CBlockFamily family, RecipeOutput recipeOutput) {

        Block result = family.get(CBlockFamily.Variant.BASE).get();
        TagKey<Item> ingredient = family.getLogTag();

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, result, 4)
                .requires(ingredient)
                .group("planks")
                .unlockedBy("has_logs", has(ingredient))
                .save(recipeOutput);
    }

    private void chiseled(CBlockFamily family, RecipeOutput recipeOutput) {

        Block result = family.get(CBlockFamily.Variant.CHISELED).get();
        Block ingredient = family.get(CBlockFamily.Variant.BASE).get();
        Block slab = family.get(CBlockFamily.Variant.SLAB).get();

        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, result, ingredient);
        oneByTwoVertical(result, 1, slab, recipeOutput);
    }

    private void stairs(CBlockFamily family, RecipeOutput recipeOutput) {

        Block result = family.get(CBlockFamily.Variant.STAIRS).get();
        Block ingredient = family.get(CBlockFamily.Variant.BASE).get();

        stairBuilder(result, Ingredient.of(ingredient))
                .group(family.getType().equals(CBlockFamily.Type.PLANKS) ? "wooden_stairs" : null)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(recipeOutput);

        if (family.getType().isStone()) stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, result, ingredient);
    }

    private void slab(CBlockFamily family, RecipeOutput recipeOutput) {

        Block result = family.get(CBlockFamily.Variant.SLAB).get();
        Block ingredient = family.get(CBlockFamily.Variant.BASE).get();

        slabBuilder(RecipeCategory.BUILDING_BLOCKS, result, Ingredient.of(ingredient))
                .group(family.getType().equals(CBlockFamily.Type.PLANKS) ? "wooden_slab" : null)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(recipeOutput);

        if (family.getType().isStone()) stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, result, ingredient);
    }

    private void wall(CBlockFamily family, RecipeOutput recipeOutput) {

        Block result = family.get(CBlockFamily.Variant.WALL).get();
        Block ingredient = family.get(CBlockFamily.Variant.BASE).get();

        wallBuilder(RecipeCategory.BUILDING_BLOCKS, result, Ingredient.of(ingredient))
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(recipeOutput);

        if (family.getType().isStone()) stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, result, ingredient);
    }

    private void fence(CBlockFamily family, RecipeOutput recipeOutput) {

        Block result = family.get(CBlockFamily.Variant.FENCE).get();
        Block ingredient = family.get(CBlockFamily.Variant.BASE).get();

        fenceBuilder(result, Ingredient.of(ingredient))
                .group(family.getType().equals(CBlockFamily.Type.PLANKS) ? "wooden_fence" : null)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(recipeOutput);
    }

    private void fenceGate(CBlockFamily family, RecipeOutput recipeOutput) {

        Block result = family.get(CBlockFamily.Variant.FENCE_GATE).get();
        Block ingredient = family.get(CBlockFamily.Variant.BASE).get();

        fenceGateBuilder(result, Ingredient.of(ingredient))
                .group(family.getType().equals(CBlockFamily.Type.PLANKS) ? "wooden_fence_gate" : null)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(recipeOutput);
    }

    private void door(CBlockFamily family, RecipeOutput recipeOutput) {

        Block result = family.get(CBlockFamily.Variant.DOOR).get();
        Block ingredient = family.get(CBlockFamily.Variant.BASE).get();

        doorBuilder(result, Ingredient.of(ingredient))
                .group(family.getType().equals(CBlockFamily.Type.PLANKS) ? "wooden_door" : null)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(recipeOutput);
    }

    private void trapDoor(CBlockFamily family, RecipeOutput recipeOutput) {

        Block result = family.get(CBlockFamily.Variant.TRAPDOOR).get();
        Block ingredient = family.get(CBlockFamily.Variant.BASE).get();

        trapdoorBuilder(result, Ingredient.of(ingredient))
                .group(family.getType().equals(CBlockFamily.Type.PLANKS) ? "wooden_trapdoor" : null)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(recipeOutput);
    }

    private void pressurePlate(CBlockFamily family, RecipeOutput recipeOutput) {

        Block result = family.get(CBlockFamily.Variant.PRESSURE_PLATE).get();
        Block ingredient = family.get(CBlockFamily.Variant.BASE).get();

        pressurePlateBuilder(RecipeCategory.REDSTONE, result, Ingredient.of(ingredient))
                .group(family.getType().equals(CBlockFamily.Type.PLANKS) ? "wooden_pressure_plate" : null)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(recipeOutput);
    }

    private void button(CBlockFamily family, RecipeOutput recipeOutput) {

        Block result = family.get(CBlockFamily.Variant.BUTTON).get();
        Block ingredient = family.get(CBlockFamily.Variant.BASE).get();

        buttonBuilder(result, Ingredient.of(ingredient))
                .group(family.getType().equals(CBlockFamily.Type.PLANKS) ? "wooden_button" : null)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(recipeOutput);
    }

    private void sign(CBlockFamily family, RecipeOutput recipeOutput) {

        Block result = family.get(CBlockFamily.Variant.SIGN).get();
        Block ingredient = family.get(CBlockFamily.Variant.BASE).get();

        signBuilder(result, Ingredient.of(ingredient))
                .group(family.getType().equals(CBlockFamily.Type.PLANKS) ? "wooden_sign" : null)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(recipeOutput);
    }

    private void hangingSign(CBlockFamily family, RecipeOutput recipeOutput) {

        Block result = family.get(CBlockFamily.Variant.HANGING_SIGN).get();
        Block ingredient = family.get(CBlockFamily.Variant.BASE).get();

        hangingSign(recipeOutput, result, ingredient);
    }

    private void portalCore(ItemLike output, Ingredient prevPortalCore, Ingredient tier, Ingredient center, RecipeOutput recipeOutput) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TRANSPORTATION, output, 2)
                .define('I', NexusItems.CHRONO_SHARD)
                .define('T', tier)
                .define('C', prevPortalCore)
                .define('S', center)
                .pattern("TCT")
                .pattern("ISI")
                .pattern("TCT")
                .unlockedBy("has_chrono_shard", has(NexusItems.CHRONO_SHARD))
                .save(recipeOutput);
    }
}
