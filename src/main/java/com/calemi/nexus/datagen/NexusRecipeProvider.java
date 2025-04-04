package com.calemi.nexus.datagen;

import com.calemi.ccore.api.block.family.CBlockFamily;
import com.calemi.ccore.api.block.family.CBlockFamily.MemberType;
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

    private void family(CBlockFamily family, RecipeOutput recipeOutput) {

        Block baseBlock = family.getBlock(MemberType.BASE);
        Block log = family.getBlock(MemberType.LOG);
        Block wood = family.getBlock(MemberType.WOOD);
        Block strippedLog = family.getBlock(MemberType.STRIPPED_LOG);
        Block strippedWood = family.getBlock(MemberType.STRIPPED_WOOD);
        Block crackedBlock = family.getBlock(MemberType.CRACKED);
        Block chiseled = family.getBlock(MemberType.CHISELED);
        Block pillar = family.getBlock(MemberType.PILLAR);
        Block stairs = family.getBlock(MemberType.STAIRS);
        Block slab = family.getBlock(MemberType.SLAB);
        Block wall = family.getBlock(MemberType.WALL);
        Block fence = family.getBlock(MemberType.FENCE);
        Block fenceGate = family.getBlock(MemberType.FENCE_GATE);
        Block door = family.getBlock(MemberType.DOOR);
        Block trapDoor = family.getBlock(MemberType.TRAPDOOR);
        Block pressurePlate = family.getBlock(MemberType.PRESSURE_PLATE);
        Block button = family.getBlock(MemberType.BUTTON);
        Block sign = family.getBlock(MemberType.SIGN);
        Block hangingSign = family.getBlock(MemberType.HANGING_SIGN);

        for (CBlockFamily ancestor : family.getAncestors()) {

            Block ancestorBaseBlock = ancestor.getBlock(MemberType.BASE);

            if (ancestorBaseBlock == null) continue;

            if (baseBlock != null) {
                twoByTwo(baseBlock, 4, ancestorBaseBlock, null, recipeOutput);
                stonecutter(recipeOutput, RecipeCategory.BUILDING_BLOCKS, baseBlock, ancestorBaseBlock);
            }

            if (stairs != null) stonecutter(recipeOutput, RecipeCategory.BUILDING_BLOCKS, stairs, ancestorBaseBlock);
            if (slab != null) stonecutter(recipeOutput, RecipeCategory.BUILDING_BLOCKS, slab, ancestorBaseBlock, 2);
            if (wall != null) stonecutter(recipeOutput, RecipeCategory.BUILDING_BLOCKS, wall, ancestorBaseBlock);
            if (chiseled != null) stonecutter(recipeOutput, RecipeCategory.BUILDING_BLOCKS, chiseled, ancestorBaseBlock);
            if (pillar != null) stonecutter(recipeOutput, RecipeCategory.BUILDING_BLOCKS, pillar, ancestorBaseBlock);
        }

        if (log != null && baseBlock != null && family.getLogTag() != null) planks(family, recipeOutput);
        if (log != null && wood != null) wood(family, recipeOutput);
        if (strippedLog != null && strippedWood != null) strippedWood(family, recipeOutput);
        if (crackedBlock != null) smeltingResultFromBase(recipeOutput, crackedBlock, baseBlock);
        if (chiseled != null) chiseled(family, recipeOutput);
        if (pillar != null) pillar(family, recipeOutput);
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

    protected void stonecutter(RecipeOutput recipeOutput, RecipeCategory category, ItemLike result, ItemLike material) {
        stonecutter(recipeOutput, category, result, material, 1);
    }

    protected void stonecutter(RecipeOutput recipeOutput, RecipeCategory category, ItemLike result, ItemLike material, int resultCount) {
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(material), category, result, resultCount)
                .unlockedBy(getHasName(material), has(material))
                .save(recipeOutput, NexusRef.rl(getConversionRecipeName(result, material) + "_stonecutting"));
    }

    protected static void oneToOne(RecipeOutput recipeOutput, ItemLike result, ItemLike ingredient, @Nullable String group, int resultCount) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, result, resultCount)
                .requires(ingredient)
                .group(group)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(recipeOutput, NexusRef.rl(getConversionRecipeName(result, ingredient)));
    }

    private void twoByTwo(ItemLike result, int count, ItemLike ingredient, @Nullable String group, RecipeOutput recipeOutput) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, result, count)
                .define('#', ingredient)
                .pattern("##")
                .pattern("##")
                .group(group)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(recipeOutput, NexusRef.rl(getItemName(result) + "_from_" + getItemName(ingredient)));
    }

    private void oneByTwoVertical(ItemLike result, int count, ItemLike ingredient, RecipeOutput recipeOutput) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TRANSPORTATION, result, count)
                .define('X', ingredient)
                .pattern("X")
                .pattern("X")
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(recipeOutput, NexusRef.rl(getItemName(result)));
    }

    private void wood(CBlockFamily family, RecipeOutput recipeOutput) {

        Block result = family.getBlock(MemberType.WOOD);
        Block ingredient =family.getBlock(MemberType.LOG);

        twoByTwo(result, 3, ingredient, "bark", recipeOutput);
    }

    private void strippedWood(CBlockFamily family, RecipeOutput recipeOutput) {

        Block result = family.getBlock(MemberType.STRIPPED_WOOD);
        Block ingredient = family.getBlock(MemberType.STRIPPED_LOG);

        twoByTwo(result, 3, ingredient, "bark", recipeOutput);
    }

    private void planks(CBlockFamily family, RecipeOutput recipeOutput) {

        Block result = family.getBlock(MemberType.BASE);
        TagKey<Item> ingredient = family.getLogTag();

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, result, 4)
                .requires(ingredient)
                .group("planks")
                .unlockedBy("has_logs", has(ingredient))
                .save(recipeOutput, NexusRef.rl(getItemName(result)));
    }

    private void chiseled(CBlockFamily family, RecipeOutput recipeOutput) {

        Block result = family.getBlock(MemberType.CHISELED);
        Block ingredient = family.getBlock(MemberType.BASE);
        Block slab = family.getBlock(MemberType.SLAB);

        stonecutter(recipeOutput, RecipeCategory.BUILDING_BLOCKS, result, ingredient);
        oneByTwoVertical(result, 1, slab, recipeOutput);
    }

    private void pillar(CBlockFamily family, RecipeOutput recipeOutput) {

        Block result = family.getBlock(MemberType.PILLAR);
        Block ingredient = family.getBlock(MemberType.BASE);

        stonecutter(recipeOutput, RecipeCategory.BUILDING_BLOCKS, result, ingredient);
        oneByTwoVertical(result, 1, ingredient, recipeOutput);
    }

    private void stairs(CBlockFamily family, RecipeOutput recipeOutput) {

        Block result = family.getBlock(MemberType.STAIRS);
        Block ingredient = family.getBlock(MemberType.BASE);

        stairBuilder(result, Ingredient.of(ingredient))
                .group(family.getFamilyType().equals(CBlockFamily.FamilyType.PLANKS) ? "wooden_stairs" : null)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(recipeOutput, NexusRef.rl(getItemName(result)));

        if (family.getFamilyType().isStone()) stonecutter(recipeOutput, RecipeCategory.BUILDING_BLOCKS, result, ingredient);
    }

    private void slab(CBlockFamily family, RecipeOutput recipeOutput) {

        Block result = family.getBlock(MemberType.SLAB);
        Block ingredient = family.getBlock(MemberType.BASE);

        slabBuilder(RecipeCategory.BUILDING_BLOCKS, result, Ingredient.of(ingredient))
                .group(family.getFamilyType().equals(CBlockFamily.FamilyType.PLANKS) ? "wooden_slab" : null)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(recipeOutput, NexusRef.rl(getItemName(result)));

        if (family.getFamilyType().isStone()) stonecutter(recipeOutput, RecipeCategory.BUILDING_BLOCKS, result, ingredient, 2);
    }

    private void wall(CBlockFamily family, RecipeOutput recipeOutput) {

        Block result = family.getBlock(MemberType.WALL);
        Block ingredient = family.getBlock(MemberType.BASE);

        wallBuilder(RecipeCategory.BUILDING_BLOCKS, result, Ingredient.of(ingredient))
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(recipeOutput, NexusRef.rl(getItemName(result)));

        if (family.getFamilyType().isStone()) stonecutter(recipeOutput, RecipeCategory.BUILDING_BLOCKS, result, ingredient);
    }

    private void fence(CBlockFamily family, RecipeOutput recipeOutput) {

        Block result = family.getBlock(MemberType.FENCE);
        Block ingredient = family.getBlock(MemberType.BASE);

        fenceBuilder(result, Ingredient.of(ingredient))
                .group(family.getFamilyType().equals(CBlockFamily.FamilyType.PLANKS) ? "wooden_fence" : null)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(recipeOutput, NexusRef.rl(getItemName(result)));
    }

    private void fenceGate(CBlockFamily family, RecipeOutput recipeOutput) {

        Block result = family.getBlock(MemberType.FENCE_GATE);
        Block ingredient = family.getBlock(MemberType.BASE);

        fenceGateBuilder(result, Ingredient.of(ingredient))
                .group(family.getFamilyType().equals(CBlockFamily.FamilyType.PLANKS) ? "wooden_fence_gate" : null)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(recipeOutput, NexusRef.rl(getItemName(result)));
    }

    private void door(CBlockFamily family, RecipeOutput recipeOutput) {

        Block result = family.getBlock(MemberType.DOOR);
        Block ingredient = family.getBlock(MemberType.BASE);

        doorBuilder(result, Ingredient.of(ingredient))
                .group(family.getFamilyType().equals(CBlockFamily.FamilyType.PLANKS) ? "wooden_door" : null)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(recipeOutput, NexusRef.rl(getItemName(result)));
    }

    private void trapDoor(CBlockFamily family, RecipeOutput recipeOutput) {

        Block result = family.getBlock(MemberType.TRAPDOOR);
        Block ingredient = family.getBlock(MemberType.BASE);

        trapdoorBuilder(result, Ingredient.of(ingredient))
                .group(family.getFamilyType().equals(CBlockFamily.FamilyType.PLANKS) ? "wooden_trapdoor" : null)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(recipeOutput, NexusRef.rl(getItemName(result)));
    }

    private void pressurePlate(CBlockFamily family, RecipeOutput recipeOutput) {

        Block result = family.getBlock(MemberType.PRESSURE_PLATE);
        Block ingredient = family.getBlock(MemberType.BASE);

        pressurePlateBuilder(RecipeCategory.REDSTONE, result, Ingredient.of(ingredient))
                .group(family.getFamilyType().equals(CBlockFamily.FamilyType.PLANKS) ? "wooden_pressure_plate" : null)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(recipeOutput, NexusRef.rl(getItemName(result)));
    }

    private void button(CBlockFamily family, RecipeOutput recipeOutput) {

        Block result = family.getBlock(MemberType.BUTTON);
        Block ingredient = family.getBlock(MemberType.BASE);

        buttonBuilder(result, Ingredient.of(ingredient))
                .group(family.getFamilyType().equals(CBlockFamily.FamilyType.PLANKS) ? "wooden_button" : null)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(recipeOutput, NexusRef.rl(getItemName(result)));
    }

    private void sign(CBlockFamily family, RecipeOutput recipeOutput) {

        Block result = family.getBlock(MemberType.SIGN);
        Block ingredient = family.getBlock(MemberType.BASE);

        signBuilder(result, Ingredient.of(ingredient))
                .group(family.getFamilyType().equals(CBlockFamily.FamilyType.PLANKS) ? "wooden_sign" : null)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(recipeOutput, NexusRef.rl(getItemName(result)));
    }

    private void hangingSign(CBlockFamily family, RecipeOutput recipeOutput) {

        Block result = family.getBlock(MemberType.HANGING_SIGN);
        Block ingredient = family.getBlock(MemberType.BASE);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, result, 6)
                .group("hanging_sign")
                .define('#', ingredient)
                .define('X', Items.CHAIN)
                .pattern("X X")
                .pattern("###")
                .pattern("###")
                .unlockedBy("has_stripped_logs", has(ingredient))
                .save(recipeOutput, NexusRef.rl(getItemName(result)));
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
