package com.calemi.nexus.regsitry;

import com.calemi.nexus.main.Nexus;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.ArrayList;
import java.util.List;

public class NexusLists {

    /*
        BLOCKS
     */

    public static final List<DeferredBlock<Block>> NEXUS_PORTAL_CORE_BLOCKS = new ArrayList<>();

    static {
        NEXUS_PORTAL_CORE_BLOCKS.add(NexusBlocks.NEXUS_PORTAL_CORE);
        NEXUS_PORTAL_CORE_BLOCKS.add(NexusBlocks.IRON_NEXUS_PORTAL_CORE);
        NEXUS_PORTAL_CORE_BLOCKS.add(NexusBlocks.GOLD_NEXUS_PORTAL_CORE);
        NEXUS_PORTAL_CORE_BLOCKS.add(NexusBlocks.DIAMOND_NEXUS_PORTAL_CORE);
        NEXUS_PORTAL_CORE_BLOCKS.add(NexusBlocks.NETHERITE_NEXUS_PORTAL_CORE);
        NEXUS_PORTAL_CORE_BLOCKS.add(NexusBlocks.STARLIGHT_NEXUS_PORTAL_CORE);
    }

    public static final List<DeferredBlock<Block>> NEXUS_PORTAL_BLOCKS = new ArrayList<>();

    static {
        NEXUS_PORTAL_BLOCKS.add(NexusBlocks.WHITE_NEXUS_PORTAL);
        NEXUS_PORTAL_BLOCKS.add(NexusBlocks.ORANGE_NEXUS_PORTAL);
        NEXUS_PORTAL_BLOCKS.add(NexusBlocks.MAGENTA_NEXUS_PORTAL);
        NEXUS_PORTAL_BLOCKS.add(NexusBlocks.LIGHT_BLUE_NEXUS_PORTAL);
        NEXUS_PORTAL_BLOCKS.add(NexusBlocks.YELLOW_NEXUS_PORTAL);
        NEXUS_PORTAL_BLOCKS.add(NexusBlocks.LIME_NEXUS_PORTAL);
        NEXUS_PORTAL_BLOCKS.add(NexusBlocks.PINK_NEXUS_PORTAL);
        NEXUS_PORTAL_BLOCKS.add(NexusBlocks.GRAY_NEXUS_PORTAL);
        NEXUS_PORTAL_BLOCKS.add(NexusBlocks.LIGHT_GRAY_NEXUS_PORTAL);
        NEXUS_PORTAL_BLOCKS.add(NexusBlocks.CYAN_NEXUS_PORTAL);
        NEXUS_PORTAL_BLOCKS.add(NexusBlocks.PURPLE_NEXUS_PORTAL);
        NEXUS_PORTAL_BLOCKS.add(NexusBlocks.BLUE_NEXUS_PORTAL);
        NEXUS_PORTAL_BLOCKS.add(NexusBlocks.BROWN_NEXUS_PORTAL);
        NEXUS_PORTAL_BLOCKS.add(NexusBlocks.GREEN_NEXUS_PORTAL);
        NEXUS_PORTAL_BLOCKS.add(NexusBlocks.RED_NEXUS_PORTAL);
        NEXUS_PORTAL_BLOCKS.add(NexusBlocks.BLACK_NEXUS_PORTAL);
    }

    /*
        BLOCK SETS
     */

    public static final BlockSet COBBLED_WARPSLATE_BLOCKSET = new BlockSet()
            .setBlock(NexusBlocks.COBBLED_WARPSLATE)
            .addStairs(NexusBlocks.COBBLED_WARPSLATE_STAIRS)
            .addSlab(NexusBlocks.COBBLED_WARPSLATE_SLAB)
            .addWall(NexusBlocks.COBBLED_WARPSLATE_WALL);

    public static final BlockSet POLISHED_WARPSLATE_BLOCKSET = new BlockSet()
            .setBlock(NexusBlocks.POLISHED_WARPSLATE)
            .addStairs(NexusBlocks.POLISHED_WARPSLATE_STAIRS)
            .addSlab(NexusBlocks.POLISHED_WARPSLATE_SLAB)
            .addWall(NexusBlocks.POLISHED_WARPSLATE_WALL);

    public static final BlockSet WARPSLATE_BRICK_BLOCKSET = new BlockSet()
            .setBlock(NexusBlocks.WARPSLATE_BRICKS)
            .setCrackedBlock(NexusBlocks.CRACKED_WARPSLATE_BRICKS)
            .addStairs(NexusBlocks.WARPSLATE_BRICK_STAIRS)
            .addSlab(NexusBlocks.WARPSLATE_BRICK_SLAB)
            .addWall(NexusBlocks.WARPSLATE_BRICK_WALL);

    public static final BlockSet WARPSLATE_TILE_BLOCKSET = new BlockSet()
            .setBlock(NexusBlocks.WARPSLATE_TILES)
            .setCrackedBlock(NexusBlocks.CRACKED_WARPSLATE_TILES)
            .addStairs(NexusBlocks.WARPSLATE_TILE_STAIRS)
            .addSlab(NexusBlocks.WARPSLATE_TILE_SLAB)
            .addWall(NexusBlocks.WARPSLATE_TILE_WALL);

    public static final List<BlockSet> ALL_STONE_BLOCKSETS = new ArrayList<>();

    static {
        ALL_STONE_BLOCKSETS.add(COBBLED_WARPSLATE_BLOCKSET);
        ALL_STONE_BLOCKSETS.add(POLISHED_WARPSLATE_BLOCKSET);
        ALL_STONE_BLOCKSETS.add(WARPSLATE_TILE_BLOCKSET);
        ALL_STONE_BLOCKSETS.add(WARPSLATE_BRICK_BLOCKSET);
    }

    public static final List<BlockSet> ALL_BLOCKSETS = new ArrayList<>();

    static {
        ALL_BLOCKSETS.addAll(ALL_STONE_BLOCKSETS);
    }

    public static final List<DeferredBlock<Block>> ALL_BLOCKS = new ArrayList<>();

    static {
        ALL_BLOCKS.addAll(NEXUS_PORTAL_CORE_BLOCKS);
        ALL_BLOCKS.addAll(NEXUS_PORTAL_BLOCKS);
        ALL_BLOCKS.add(NexusBlocks.CHRONOWARPED_GRASS);
        ALL_BLOCKS.add(NexusBlocks.CHRONOWARPED_DIRT);
        ALL_BLOCKS.add(NexusBlocks.WARPSLATE);
        ALL_BLOCKS.addAll(toDefBlockListFromBlockSet(ALL_BLOCKSETS));
        ALL_BLOCKS.add(NexusBlocks.CHISELED_WARPSLATE);
        ALL_BLOCKS.add(NexusBlocks.CRACKED_WARPSLATE_BRICKS);
        ALL_BLOCKS.add(NexusBlocks.CRACKED_WARPSLATE_TILES);
    }

    public static final List<DeferredItem<Item>> ALL_ITEMS = new ArrayList<>();

    static {
        ALL_ITEMS.add(NexusItems.CHRONO_SHARD);
        ALL_ITEMS.add(NexusItems.CHRONO_UPGRADE_SMITHING_TEMPLATE);
    }

    public static final List<Item> ALL_BLOCKS_AND_ITEMS = new ArrayList<>();

    static {
        ALL_BLOCKS_AND_ITEMS.addAll(toItemListFromDefBlock(ALL_BLOCKS));
        ALL_BLOCKS_AND_ITEMS.addAll(toItemListFromDefItem(ALL_ITEMS));

        Nexus.LOGGER.debug("ALL BLOCKS AND ITEMS");
        ALL_BLOCKS_AND_ITEMS.forEach(item -> Nexus.LOGGER.debug("entry: {}", item.getDescriptionId()));
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

    public static List<ResourceKey<Block>> toResourceKeyList(List<DeferredBlock<Block>> blocks) {
        return new ArrayList<>(blocks.stream().map(DeferredBlock::getKey).toList());
    }

    public static List<Block> toBlockList(List<DeferredBlock<Block>> blocks) {
        return new ArrayList<>(blocks.stream().map(DeferredHolder::get).toList());
    }

    public static List<DeferredBlock<Block>> toDefBlockListFromBlockSet(List<BlockSet> blockSet) {
        List<DeferredBlock<Block>> list = new ArrayList<>();
        blockSet.forEach(set -> list.addAll(set.getAll()));
        return list;
    }

    public static Block[] toBlockArray(List<DeferredBlock<Block>> blocks) {
        return blocks.stream().map(DeferredHolder::get).toArray(Block[]::new);
    }

    public static Item[] toItemArray(List<DeferredBlock<Block>> blocks) {
        return blocks.stream().map(DeferredBlock::asItem).toArray(Item[]::new);
    }

    public static List<Item> toItemListFromDefBlock(List<DeferredBlock<Block>> blocks) {
        return new ArrayList<>(blocks.stream().map(DeferredBlock::asItem).filter(item -> item instanceof BlockItem).toList());
    }

    public static List<Item> toItemListFromDefItem(List<DeferredItem<Item>> items) {
        return new ArrayList<>(items.stream().map(DeferredItem::get).toList());
    }

    public static List<ItemStack> toItemStackListFromDefBlock(List<DeferredBlock<Block>> blocks) {
        return new ArrayList<>(blocks.stream().map(block -> new ItemStack(block.asItem())).toList());
    }

    public static List<ItemStack> toItemStackListFromItem(List<Item> items) {
        return new ArrayList<>(items.stream().map(ItemStack::new).toList());
    }

    /*
        BLOCKSET
     */

    public static class BlockSet {

        List<DeferredBlock<Block>> all = new ArrayList<>();

        private DeferredBlock<Block> block;
        private DeferredBlock<Block> crackedBlock;
        private DeferredBlock<Block> stairs;
        private DeferredBlock<Block> slab;
        private DeferredBlock<Block> wall;

        public BlockSet setBlock(DeferredBlock<Block> block) {
            this.block = block;
            all.add(block);
            return this;
        }

        public DeferredBlock<Block> getBlock() {
            return block;
        }

        public BlockSet setCrackedBlock(DeferredBlock<Block> crackedBlock) {
            this.crackedBlock = crackedBlock;
            all.add(crackedBlock);
            return this;
        }

        public DeferredBlock<Block> getCrackedBlock() {
            return crackedBlock;
        }

        public DeferredBlock<Block> getStairs() {
            return stairs;
        }

        private BlockSet addStairs(DeferredBlock<Block> stairs) {
            this.stairs = stairs;
            all.add(stairs);
            return this;
        }

        public DeferredBlock<Block> getSlab() {
            return slab;
        }

        private BlockSet addSlab(DeferredBlock<Block> slab) {
            this.slab = slab;
            all.add(slab);
            return this;
        }

        public DeferredBlock<Block> getWall() {
            return wall;
        }

        private BlockSet addWall(DeferredBlock<Block> wall) {
            this.wall = wall;
            all.add(wall);
            return this;
        }

        public List<DeferredBlock<Block>> getAll() {
            return all;
        }
    }
}

