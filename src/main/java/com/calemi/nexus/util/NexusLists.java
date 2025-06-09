package com.calemi.nexus.util;

import com.calemi.ccore.api.block.family.CBlockFamily;
import com.calemi.nexus.block.NexusBlocks;
import com.calemi.nexus.block.family.NexusBlockFamilies;
import com.calemi.nexus.client.partclie.NexusParticles;
import com.calemi.nexus.item.NexusItems;
import com.calemi.nexus.main.Nexus;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.ArrayList;
import java.util.List;

public class NexusLists {

    /*
        BLOCKS
     */

    public static final List<Block> NEXUS_PORTAL_CORE_BLOCKS = new ArrayList<>();

    static {
        NEXUS_PORTAL_CORE_BLOCKS.add(NexusBlocks.NEXUS_PORTAL_CORE.get());
        NEXUS_PORTAL_CORE_BLOCKS.add(NexusBlocks.IRON_NEXUS_PORTAL_CORE.get());
        NEXUS_PORTAL_CORE_BLOCKS.add(NexusBlocks.GOLD_NEXUS_PORTAL_CORE.get());
        NEXUS_PORTAL_CORE_BLOCKS.add(NexusBlocks.DIAMOND_NEXUS_PORTAL_CORE.get());
        NEXUS_PORTAL_CORE_BLOCKS.add(NexusBlocks.NETHERITE_NEXUS_PORTAL_CORE.get());
        NEXUS_PORTAL_CORE_BLOCKS.add(NexusBlocks.STARLIGHT_NEXUS_PORTAL_CORE.get());
    }

    public static final List<Block> NEXUS_PORTAL_BLOCKS = new ArrayList<>();

    static {
        NEXUS_PORTAL_BLOCKS.add(NexusBlocks.WHITE_NEXUS_PORTAL.get());
        NEXUS_PORTAL_BLOCKS.add(NexusBlocks.ORANGE_NEXUS_PORTAL.get());
        NEXUS_PORTAL_BLOCKS.add(NexusBlocks.MAGENTA_NEXUS_PORTAL.get());
        NEXUS_PORTAL_BLOCKS.add(NexusBlocks.LIGHT_BLUE_NEXUS_PORTAL.get());
        NEXUS_PORTAL_BLOCKS.add(NexusBlocks.YELLOW_NEXUS_PORTAL.get());
        NEXUS_PORTAL_BLOCKS.add(NexusBlocks.LIME_NEXUS_PORTAL.get());
        NEXUS_PORTAL_BLOCKS.add(NexusBlocks.PINK_NEXUS_PORTAL.get());
        NEXUS_PORTAL_BLOCKS.add(NexusBlocks.GRAY_NEXUS_PORTAL.get());
        NEXUS_PORTAL_BLOCKS.add(NexusBlocks.LIGHT_GRAY_NEXUS_PORTAL.get());
        NEXUS_PORTAL_BLOCKS.add(NexusBlocks.CYAN_NEXUS_PORTAL.get());
        NEXUS_PORTAL_BLOCKS.add(NexusBlocks.PURPLE_NEXUS_PORTAL.get());
        NEXUS_PORTAL_BLOCKS.add(NexusBlocks.BLUE_NEXUS_PORTAL.get());
        NEXUS_PORTAL_BLOCKS.add(NexusBlocks.BROWN_NEXUS_PORTAL.get());
        NEXUS_PORTAL_BLOCKS.add(NexusBlocks.GREEN_NEXUS_PORTAL.get());
        NEXUS_PORTAL_BLOCKS.add(NexusBlocks.RED_NEXUS_PORTAL.get());
        NEXUS_PORTAL_BLOCKS.add(NexusBlocks.BLACK_NEXUS_PORTAL.get());
    }

    public static final List<Block> CHRONO_CLUSTER_BLOCKS = new ArrayList<>();

    static {
        CHRONO_CLUSTER_BLOCKS.add(NexusBlocks.SMALL_CHRONO_BUD.get());
        CHRONO_CLUSTER_BLOCKS.add(NexusBlocks.MEDIUM_CHRONO_BUD.get());
        CHRONO_CLUSTER_BLOCKS.add(NexusBlocks.LARGE_CHRONO_BUD.get());
        CHRONO_CLUSTER_BLOCKS.add(NexusBlocks.CHRONO_CLUSTER.get());
    }

    public static final List<Block> CHRONOWARPED_LOGS = new ArrayList<>();

    static {
        CHRONOWARPED_LOGS.add(NexusBlocks.WARPBLOSSOM_LOG.get());
        CHRONOWARPED_LOGS.add(NexusBlocks.WARPBLOSSOM_WOOD.get());
        CHRONOWARPED_LOGS.add(NexusBlocks.STRIPPED_WARPBLOSSOM_LOG.get());
        CHRONOWARPED_LOGS.add(NexusBlocks.STRIPPED_WARPBLOSSOM_WOOD.get());
    }

    public static final List<Block> ALL_BLOCKS_BUT_FAMILIES = new ArrayList<>();

    static {
        ALL_BLOCKS_BUT_FAMILIES.addAll(NEXUS_PORTAL_CORE_BLOCKS);
        ALL_BLOCKS_BUT_FAMILIES.addAll(NEXUS_PORTAL_BLOCKS);
        ALL_BLOCKS_BUT_FAMILIES.add(NexusBlocks.ROAD.get());
        ALL_BLOCKS_BUT_FAMILIES.add(NexusBlocks.ROAD_SLAB.get());
        ALL_BLOCKS_BUT_FAMILIES.add(NexusBlocks.JUMP_PAD.get());
        ALL_BLOCKS_BUT_FAMILIES.add(NexusBlocks.CHRONOWARPED_GRASS.get());
        ALL_BLOCKS_BUT_FAMILIES.add(NexusBlocks.CHRONOWARPED_DIRT.get());
        ALL_BLOCKS_BUT_FAMILIES.add(NexusBlocks.CHRONOWARPED_SAND.get());
        ALL_BLOCKS_BUT_FAMILIES.add(NexusBlocks.CHRONO_BLOCK.get());
        ALL_BLOCKS_BUT_FAMILIES.add(NexusBlocks.BUDDING_CHRONO.get());
        ALL_BLOCKS_BUT_FAMILIES.addAll(CHRONO_CLUSTER_BLOCKS);
        ALL_BLOCKS_BUT_FAMILIES.add(NexusBlocks.WARPSLATE.get());
        ALL_BLOCKS_BUT_FAMILIES.add(NexusBlocks.WARPSLATE_ACCELERITE_ORE.get());
        ALL_BLOCKS_BUT_FAMILIES.add(NexusBlocks.RAW_ACCELERITE_BLOCK.get());
        ALL_BLOCKS_BUT_FAMILIES.add(NexusBlocks.DORMANT_ACCELERITE_BLOCK.get());
        ALL_BLOCKS_BUT_FAMILIES.add(NexusBlocks.CHARGED_ACCELERITE_BLOCK.get());
        ALL_BLOCKS_BUT_FAMILIES.add(NexusBlocks.WARPBLOSSOM_SAPLING.get());
        ALL_BLOCKS_BUT_FAMILIES.add(NexusBlocks.POTTED_WARPBLOSSOM_SAPLING.get());
        ALL_BLOCKS_BUT_FAMILIES.add(NexusBlocks.WARPBLOSSOM_LEAVES.get());
        ALL_BLOCKS_BUT_FAMILIES.add(NexusBlocks.PURPLE_PETALS.get());
    }

    public static final List<Block> ALL_BLOCKS = new ArrayList<>();

    static {
        ALL_BLOCKS.addAll(ALL_BLOCKS_BUT_FAMILIES);
        NexusBlockFamilies.ALL.forEach(family -> ALL_BLOCKS.addAll(family.getAllBlocks()));
    }

    public static final List<Item> ACCELERITE_DIGGERS = new ArrayList<>();

    static {
        ACCELERITE_DIGGERS.add(NexusItems.ACCELERITE_SHOVEL.get());
        ACCELERITE_DIGGERS.add(NexusItems.ACCELERITE_PICKAXE.get());
        ACCELERITE_DIGGERS.add(NexusItems.ACCELERITE_AXE.get());
        ACCELERITE_DIGGERS.add(NexusItems.ACCELERITE_HOE.get());
    }

    public static final List<Item> ACCELERITE_TOOLS = new ArrayList<>();

    static {
        ACCELERITE_TOOLS.add(NexusItems.ACCELERITE_SWORD.get());
        ACCELERITE_TOOLS.addAll(ACCELERITE_DIGGERS);
    }

    public static final List<Item> ACCELERITE_ARMOR = new ArrayList<>();

    static {
        ACCELERITE_ARMOR.add(NexusItems.ACCELERITE_HELMET.get());
        ACCELERITE_ARMOR.add(NexusItems.ACCELERITE_CHESTPLATE.get());
        ACCELERITE_ARMOR.add(NexusItems.ACCELERITE_LEGGINGS.get());
        ACCELERITE_ARMOR.add(NexusItems.ACCELERITE_BOOTS.get());
    }

    public static final List<Item> ACCELERITE_EQUIPMENT = new ArrayList<>();

    static {
        ACCELERITE_EQUIPMENT.addAll(ACCELERITE_TOOLS);
        ACCELERITE_EQUIPMENT.addAll(ACCELERITE_ARMOR);
    }

    public static final List<Item> ALL_ITEMS = new ArrayList<>();

    static {
        ALL_ITEMS.add(NexusItems.CHRONO_SHARD_FRAGMENT.get());
        ALL_ITEMS.add(NexusItems.CHRONO_SHARD.get());
        ALL_ITEMS.add(NexusItems.CHRONO_UPGRADE_SMITHING_TEMPLATE.get());
        ALL_ITEMS.add(NexusItems.RAW_ACCELERITE.get());
        ALL_ITEMS.add(NexusItems.DORMANT_ACCELERITE_INGOT.get());
        ALL_ITEMS.add(NexusItems.CHARGED_ACCELERITE_INGOT.get());
        ALL_ITEMS.addAll(ACCELERITE_EQUIPMENT);
        ALL_ITEMS.add(NexusItems.TOTEM_OF_WARPING.get());
        ALL_ITEMS.add(NexusItems.SPEEDOMETER.get());
        ALL_ITEMS.add(NexusItems.FALLBREAKERS.get());
        ALL_ITEMS.add(NexusItems.WARPBLOSSOM_BOAT.get());
        ALL_ITEMS.add(NexusItems.WARPBLOSSOM_CHEST_BOAT.get());
        ALL_ITEMS.add(NexusItems.WARPBLOSSOM_STICK.get());
    }

    public static final List<Item> ALL_BLOCKS_AND_ITEMS = new ArrayList<>();

    static {
        ALL_BLOCKS_AND_ITEMS.addAll(toItemListFromBlock(ALL_BLOCKS));
        ALL_BLOCKS_AND_ITEMS.addAll(ALL_ITEMS);

        Nexus.LOGGER.debug("ALL BLOCKS AND ITEMS");
        ALL_BLOCKS_AND_ITEMS.forEach(item -> Nexus.LOGGER.debug("entry: {}", item.getDescriptionId()));
    }

    public static final List<ItemLike> TAB_NEXUS_MAIN_ITEMS = new ArrayList<>();

    static {
        TAB_NEXUS_MAIN_ITEMS.addAll(toItemListFromBlock(NEXUS_PORTAL_CORE_BLOCKS));
        TAB_NEXUS_MAIN_ITEMS.add(NexusItems.CHRONO_SHARD_FRAGMENT);
        TAB_NEXUS_MAIN_ITEMS.add(NexusItems.CHRONO_SHARD);
        TAB_NEXUS_MAIN_ITEMS.add(NexusItems.CHRONO_UPGRADE_SMITHING_TEMPLATE);
        TAB_NEXUS_MAIN_ITEMS.add(NexusBlocks.ROAD);
        TAB_NEXUS_MAIN_ITEMS.add(NexusBlocks.ROAD_SLAB);
        TAB_NEXUS_MAIN_ITEMS.add(NexusBlocks.JUMP_PAD);
        TAB_NEXUS_MAIN_ITEMS.add(NexusBlocks.CHRONOWARPED_GRASS);
        TAB_NEXUS_MAIN_ITEMS.add(NexusBlocks.CHRONOWARPED_DIRT);
        TAB_NEXUS_MAIN_ITEMS.add(NexusBlocks.CHRONOWARPED_SAND);
        TAB_NEXUS_MAIN_ITEMS.add(NexusBlocks.CHRONO_BLOCK);
        TAB_NEXUS_MAIN_ITEMS.add(NexusBlocks.BUDDING_CHRONO);
        TAB_NEXUS_MAIN_ITEMS.addAll(CHRONO_CLUSTER_BLOCKS);
        TAB_NEXUS_MAIN_ITEMS.addAll(NexusBlockFamilies.POLISHED_CALCITE.getAllTabItems());
        TAB_NEXUS_MAIN_ITEMS.addAll(NexusBlockFamilies.CALCITE_BRICKS.getAllTabItems());
        TAB_NEXUS_MAIN_ITEMS.addAll(NexusBlockFamilies.CALCITE_TILES.getAllTabItems());
        TAB_NEXUS_MAIN_ITEMS.add(NexusBlocks.WARPSLATE);
        TAB_NEXUS_MAIN_ITEMS.addAll(NexusBlockFamilies.COBBLED_WARPSLATE.getAllTabItems());
        TAB_NEXUS_MAIN_ITEMS.addAll(NexusBlockFamilies.POLISHED_WARPSLATE.getAllTabItems());
        TAB_NEXUS_MAIN_ITEMS.addAll(NexusBlockFamilies.WARPSLATE_BRICK.getAllTabItems());
        TAB_NEXUS_MAIN_ITEMS.addAll(NexusBlockFamilies.WARPSLATE_TILE.getAllTabItems());
        TAB_NEXUS_MAIN_ITEMS.add(NexusBlocks.WARPSLATE_ACCELERITE_ORE);
        TAB_NEXUS_MAIN_ITEMS.add(NexusBlocks.RAW_ACCELERITE_BLOCK);
        TAB_NEXUS_MAIN_ITEMS.add(NexusBlocks.DORMANT_ACCELERITE_BLOCK);
        TAB_NEXUS_MAIN_ITEMS.add(NexusBlocks.CHARGED_ACCELERITE_BLOCK);
        TAB_NEXUS_MAIN_ITEMS.add(NexusItems.RAW_ACCELERITE);
        TAB_NEXUS_MAIN_ITEMS.add(NexusItems.DORMANT_ACCELERITE_INGOT);
        TAB_NEXUS_MAIN_ITEMS.add(NexusItems.CHARGED_ACCELERITE_INGOT);
        TAB_NEXUS_MAIN_ITEMS.add(NexusItems.ACCELERITE_SWORD);
        TAB_NEXUS_MAIN_ITEMS.addAll(ACCELERITE_TOOLS);
        TAB_NEXUS_MAIN_ITEMS.add(NexusItems.TOTEM_OF_WARPING.get());
        TAB_NEXUS_MAIN_ITEMS.add(NexusItems.SPEEDOMETER.get());
        TAB_NEXUS_MAIN_ITEMS.add(NexusItems.FALLBREAKERS.get());
        TAB_NEXUS_MAIN_ITEMS.addAll(ACCELERITE_ARMOR);
        TAB_NEXUS_MAIN_ITEMS.add(NexusBlocks.PURPLE_PETALS);
        TAB_NEXUS_MAIN_ITEMS.add(NexusBlocks.WARPBLOSSOM_SAPLING);
        TAB_NEXUS_MAIN_ITEMS.add(NexusBlocks.WARPBLOSSOM_LEAVES);
        TAB_NEXUS_MAIN_ITEMS.addAll(NexusBlockFamilies.WARPBLOSSOM.getAllTabItems());
        TAB_NEXUS_MAIN_ITEMS.add(NexusItems.WARPBLOSSOM_BOAT);
        TAB_NEXUS_MAIN_ITEMS.add(NexusItems.WARPBLOSSOM_CHEST_BOAT);
        TAB_NEXUS_MAIN_ITEMS.add(NexusItems.WARPBLOSSOM_STICK);
    }

    /*
        PARTICLES
     */

    public static final List<DeferredHolder<ParticleType<?>, SimpleParticleType>> NEXUS_PORTAL_PARTICLES = new ArrayList<>();

    static {
        NEXUS_PORTAL_PARTICLES.add(NexusParticles.WHITE_PORTAL_PARTICLES);
        NEXUS_PORTAL_PARTICLES.add(NexusParticles.ORANGE_PORTAL_PARTICLES);
        NEXUS_PORTAL_PARTICLES.add(NexusParticles.YELLOW_PORTAL_PARTICLES);
        NEXUS_PORTAL_PARTICLES.add(NexusParticles.PURPLE_PORTAL_PARTICLES);
        NEXUS_PORTAL_PARTICLES.add(NexusParticles.CYAN_PORTAL_PARTICLES);
        NEXUS_PORTAL_PARTICLES.add(NexusParticles.LIGHT_GRAY_PORTAL_PARTICLES);
        NEXUS_PORTAL_PARTICLES.add(NexusParticles.GRAY_PORTAL_PARTICLES);
        NEXUS_PORTAL_PARTICLES.add(NexusParticles.PINK_PORTAL_PARTICLES);
        NEXUS_PORTAL_PARTICLES.add(NexusParticles.LIME_PORTAL_PARTICLES);
        NEXUS_PORTAL_PARTICLES.add(NexusParticles.LIGHT_BLUE_PORTAL_PARTICLES);
        NEXUS_PORTAL_PARTICLES.add(NexusParticles.MAGENTA_PORTAL_PARTICLES);
        NEXUS_PORTAL_PARTICLES.add(NexusParticles.GREEN_PORTAL_PARTICLES);
        NEXUS_PORTAL_PARTICLES.add(NexusParticles.BLUE_PORTAL_PARTICLES);
        NEXUS_PORTAL_PARTICLES.add(NexusParticles.RED_PORTAL_PARTICLES);
        NEXUS_PORTAL_PARTICLES.add(NexusParticles.BROWN_PORTAL_PARTICLES);
        NEXUS_PORTAL_PARTICLES.add(NexusParticles.BLACK_PORTAL_PARTICLES);
    }

    /*
        METHODS
     */

    public static List<ResourceKey<Block>> toBlockResourceKeyList(List<Block> blocks) {
        return new ArrayList<>(blocks.stream().map(block -> BuiltInRegistries.BLOCK.getResourceKey(block).orElse(null)).toList());
    }

    public static List<ResourceKey<Item>> toItemResourceKeyList(List<Item> blocks) {
        return new ArrayList<>(blocks.stream().map(item -> BuiltInRegistries.ITEM.getResourceKey(item).orElse(null)).toList());
    }

    public static List<Block> toBlockListFromBlockSet(List<CBlockFamily> blockSet) {
        List<Block> list = new ArrayList<>();
        blockSet.forEach(set -> list.addAll(set.getAllBlocks()));
        return list;
    }

    public static Block[] toBlockArray(List <Block> blocks) {
        return blocks.toArray(Block[]::new);
    }

    public static Item[] toItemArray(List<Block> blocks) {
        return blocks.stream().map(Block::asItem).toArray(Item[]::new);
    }

    public static List<Item> toItemListFromBlock(List<Block> blocks) {
        return new ArrayList<>(blocks.stream().map(Block::asItem).filter(item -> item instanceof BlockItem).toList());
    }

    public static List<ItemStack> toItemStackListFromBlock(List<Block> blocks) {
        return new ArrayList<>(blocks.stream().map(block -> new ItemStack(block.asItem())).toList());
    }

    public static List<ItemStack> toItemStackListFromItem(List<Item> items) {
        return new ArrayList<>(items.stream().map((ItemStack::new)).toList());
    }

    public static List<ItemStack> toItemStackListFromItemLike(List<ItemLike> items) {
        return new ArrayList<>(items.stream().map(ItemStack::new).toList());
    }
}

