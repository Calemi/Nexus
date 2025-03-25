package com.calemi.ccore.api.family;

import com.google.common.collect.Maps;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.*;

public class CBlockFamily {

    private final Type type;
    private final LinkedHashMap<Variant, DeferredBlock<Block>> variants;
    private TagKey<Item> logTag;

    CBlockFamily(Type type) {
        this.type = type;
        this.variants = Maps.newLinkedHashMap();
        logTag = null;
    }

    public Type getType() {
        return type;
    }

    public DeferredBlock<Block> get(CBlockFamily.Variant variant) {
        return variants.get(variant);
    }

    public Map<CBlockFamily.Variant, DeferredBlock<Block>> getVariants() {
        return variants;
    }

    public List<DeferredBlock<Block>> getAllBlocks() {
        return new ArrayList<>((getVariants().values()));
    }

    public TagKey<Item> getLogTag() {
        return logTag;
    }

    public static class Builder {

        private final CBlockFamily family;

        public Builder(Type type) {
            family = new CBlockFamily(type);
        }

        public CBlockFamily getFamily() {
            return family;
        }

        public CBlockFamily.Builder logTag(TagKey<Item> logTag) {
            family.logTag = logTag;
            return this;
        }

        public CBlockFamily.Builder log(DeferredBlock<Block> logBlock) {
            family.variants.put(Variant.LOG, logBlock);
            return this;
        }

        public CBlockFamily.Builder wood(DeferredBlock<Block> woodBlock) {
            family.variants.put(Variant.WOOD, woodBlock);
            return this;
        }

        public CBlockFamily.Builder strippedLog(DeferredBlock<Block> strippedLogBlock) {
            family.variants.put(Variant.STRIPPED_LOG, strippedLogBlock);
            return this;
        }

        public CBlockFamily.Builder strippedWood(DeferredBlock<Block> strippedWoodBlock) {
            family.variants.put(Variant.STRIPPED_WOOD, strippedWoodBlock);
            return this;
        }

        public CBlockFamily.Builder base(DeferredBlock<Block> baseBlock) {
            family.variants.put(Variant.BASE, baseBlock);
            return this;
        }

        public CBlockFamily.Builder button(DeferredBlock<Block> buttonBlock) {
            family.variants.put(CBlockFamily.Variant.BUTTON, buttonBlock);
            return this;
        }

        public CBlockFamily.Builder cracked(DeferredBlock<Block> crackedBlock) {
            family.variants.put(CBlockFamily.Variant.CRACKED, crackedBlock);
            return this;
        }

        public CBlockFamily.Builder door(DeferredBlock<Block> doorBlock) {
            family.variants.put(CBlockFamily.Variant.DOOR, doorBlock);
            return this;
        }

        public CBlockFamily.Builder fence(DeferredBlock<Block> fenceBlock) {
            family.variants.put(CBlockFamily.Variant.FENCE, fenceBlock);
            return this;
        }

        public CBlockFamily.Builder fenceGate(DeferredBlock<Block> fenceGateBlock) {
            family.variants.put(CBlockFamily.Variant.FENCE_GATE, fenceGateBlock);
            return this;
        }

        public CBlockFamily.Builder slab(DeferredBlock<Block> slabBlock) {
            family.variants.put(CBlockFamily.Variant.SLAB, slabBlock);
            return this;
        }

        public CBlockFamily.Builder stairs(DeferredBlock<Block> stairsBlock) {
            family.variants.put(CBlockFamily.Variant.STAIRS, stairsBlock);
            return this;
        }

        public CBlockFamily.Builder pressurePlate(DeferredBlock<Block> pressurePlateBlock) {
            family.variants.put(CBlockFamily.Variant.PRESSURE_PLATE, pressurePlateBlock);
            return this;
        }

        public CBlockFamily.Builder trapdoor(DeferredBlock<Block> trapdoorBlock) {
            family.variants.put(CBlockFamily.Variant.TRAPDOOR, trapdoorBlock);
            return this;
        }

        public CBlockFamily.Builder wall(DeferredBlock<Block> wallBlock) {
            family.variants.put(CBlockFamily.Variant.WALL, wallBlock);
            return this;
        }

        public CBlockFamily.Builder chiseled(DeferredBlock<Block> chiseledBlock) {
            family.variants.put(CBlockFamily.Variant.CHISELED, chiseledBlock);
            return this;
        }

        public CBlockFamily.Builder sign(DeferredBlock<Block> signBlock, DeferredBlock<Block> wallSignBlock) {
            family.variants.put(CBlockFamily.Variant.SIGN, signBlock);
            family.variants.put(CBlockFamily.Variant.WALL_SIGN, wallSignBlock);
            return this;
        }

        public CBlockFamily.Builder hangingSign(DeferredBlock<Block> ceilingHangingSignBlock, DeferredBlock<Block> wallHangingSignBlock) {
            family.variants.put(CBlockFamily.Variant.HANGING_SIGN, ceilingHangingSignBlock);
            family.variants.put(CBlockFamily.Variant.WALL_HANGING_SIGN, wallHangingSignBlock);
            return this;
        }
    }

    public enum Type {
        STONE,
        COBBLESTONE,
        PLANKS;

        public boolean isStone() {
            return this == STONE || this == COBBLESTONE;
        }
    }

    public enum Variant {
        LOG,
        WOOD,
        STRIPPED_LOG,
        STRIPPED_WOOD,
        BASE,
        CRACKED,
        STAIRS,
        SLAB,
        WALL,
        CHISELED,
        FENCE,
        FENCE_GATE,
        DOOR,
        TRAPDOOR,
        PRESSURE_PLATE,
        BUTTON,
        SIGN,
        WALL_SIGN,
        HANGING_SIGN,
        WALL_HANGING_SIGN;
    }
}
