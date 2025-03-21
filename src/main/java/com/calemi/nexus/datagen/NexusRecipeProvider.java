package com.calemi.nexus.datagen;

import com.calemi.nexus.main.NexusRef;
import com.calemi.nexus.regsitry.NexusBlocks;
import com.calemi.nexus.regsitry.NexusItems;
import com.calemi.nexus.regsitry.NexusLists;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.concurrent.CompletableFuture;

public class NexusRecipeProvider extends RecipeProvider {

    public NexusRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {

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

        twoByTwo(NexusBlocks.POLISHED_WARPSLATE, 4, NexusBlocks.COBBLED_WARPSLATE, recipeOutput);
        twoByTwo(NexusBlocks.WARPSLATE_BRICKS, 4, NexusBlocks.POLISHED_WARPSLATE, recipeOutput);
        twoByTwo(NexusBlocks.WARPSLATE_TILES, 4, NexusBlocks.WARPSLATE_BRICKS, recipeOutput);

        NexusLists.ALL_BLOCKSETS.forEach(set -> blockSet(set, recipeOutput));

        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, NexusBlocks.CHISELED_WARPSLATE, NexusBlocks.COBBLED_WARPSLATE);
        oneByTwoVertical(NexusBlocks.CHISELED_WARPSLATE, 1, NexusBlocks.COBBLED_WARPSLATE_SLAB, recipeOutput);
    }

    private void twoByTwo(DeferredBlock<Block> result, int count, DeferredBlock<Block> ingredient, RecipeOutput recipeOutput) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, result, count)
                .define('#', ingredient)
                .pattern("##")
                .pattern("##")
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(recipeOutput);
    }

    private void oneByTwoVertical(DeferredBlock<Block> result, int count, DeferredBlock<Block> ingredient, RecipeOutput recipeOutput) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TRANSPORTATION, result, count)
                .define('X', ingredient)
                .pattern("X")
                .pattern("X")
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(recipeOutput);
    }

    private void blockSet(NexusLists.BlockSet blockSet, RecipeOutput recipeOutput) {

        DeferredBlock<Block> block = blockSet.getBlock();
        DeferredBlock<Block> crackedBlock = blockSet.getCrackedBlock();
        DeferredBlock<Block> stairs = blockSet.getStairs();
        DeferredBlock<Block> slab = blockSet.getSlab();
        DeferredBlock<Block> wall = blockSet.getWall();

        if (crackedBlock != null) smeltingResultFromBase(recipeOutput, crackedBlock, block);
        if (stairs != null) stairs(stairs, block, recipeOutput);
        if (slab != null) slab(slab, block, recipeOutput);
        if (wall != null) wall(wall, block, recipeOutput);
    }

    private void stairs(DeferredBlock<Block> result, DeferredBlock<Block> ingredient, RecipeOutput recipeOutput) {
        stairBuilder(result, Ingredient.of(ingredient))
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(recipeOutput);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, result, ingredient);
    }

    private void slab(DeferredBlock<Block> result, DeferredBlock<Block> ingredient, RecipeOutput recipeOutput) {
        slabBuilder(RecipeCategory.BUILDING_BLOCKS, result, Ingredient.of(ingredient))
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(recipeOutput);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, result, ingredient);
    }

    private void wall(DeferredBlock<Block> result, DeferredBlock<Block> ingredient, RecipeOutput recipeOutput) {
        wallBuilder(RecipeCategory.BUILDING_BLOCKS, result, Ingredient.of(ingredient))
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(recipeOutput);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, result, ingredient);
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
