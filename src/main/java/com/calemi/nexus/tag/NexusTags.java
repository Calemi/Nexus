package com.calemi.nexus.tag;

import com.calemi.nexus.main.NexusRef;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;

public class NexusTags {

    public static class Blocks {

        public static final TagKey<Block> DIRTLIKE = TagKey.create(Registries.BLOCK, NexusRef.rl("dirtlike"));
        public static final TagKey<Block> STONELIKE = TagKey.create(Registries.BLOCK, NexusRef.rl("stonelike"));
        public static final TagKey<Block> WOODLIKE = TagKey.create(Registries.BLOCK, NexusRef.rl("woodlike"));

        public static final TagKey<Block> WARPBLOSSOM_LOGS = TagKey.create(Registries.BLOCK, NexusRef.rl("warpblossom_logs"));

        public static final TagKey<Block> NEXUS_PORTAL_CORES = TagKey.create(Registries.BLOCK, NexusRef.rl("nexus_portal_cores"));
        public static final TagKey<Block> NEXUS_PORTALS = TagKey.create(Registries.BLOCK, NexusRef.rl("nexus_portals"));

        public static final TagKey<Block> NEEDS_ACCELERITE_TOOL = TagKey.create(Registries.BLOCK, NexusRef.rl("needs_accelerite_tool"));
        public static final TagKey<Block> INCORRECT_FOR_ACCELERITE_TOOL = TagKey.create(Registries.BLOCK, NexusRef.rl("incorrect_for_accelerite_tool"));
    }

    public static class Items {

        public static final TagKey<Item> ACCELERITE_INGOTS = TagKey.create(Registries.ITEM, NexusRef.rl("accelerite_ingots"));

        public static final TagKey<Item> WARPBLOSSOM_LOGS = TagKey.create(Registries.ITEM, NexusRef.rl("warpblossom_logs"));

        public static final TagKey<Item> ACCELERITE_ARMOR = TagKey.create(Registries.ITEM, NexusRef.rl("accelerite_armor"));
        public static final TagKey<Item> ACCELERITE_SWORD = TagKey.create(Registries.ITEM, NexusRef.rl("accelerite_sword"));
        public static final TagKey<Item> ACCELERITE_TOOLS = TagKey.create(Registries.ITEM, NexusRef.rl("accelerite_tools"));
        public static final TagKey<Item> ACCELERITE_EQUIPMENT = TagKey.create(Registries.ITEM, NexusRef.rl("accelerite_equipment"));

        public static final TagKey<Item> ACCELERATION_ENCHANTABLE = TagKey.create(Registries.ITEM, NexusRef.rl("acceleration_enchantable"));
        public static final TagKey<Item> SPEED_MENDING_ENCHANTABLE = TagKey.create(Registries.ITEM, NexusRef.rl("speed_mending_enchantable"));
    }

    public static class Biomes {

        public static final TagKey<Biome> VALID_RUINED_NEXUS_PORTAL_BIOMES = TagKey.create(Registries.BIOME, NexusRef.rl("has_structure/ruined_nexus_portal"));
    }
}
