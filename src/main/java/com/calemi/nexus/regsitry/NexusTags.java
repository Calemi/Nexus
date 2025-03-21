package com.calemi.nexus.regsitry;

import com.calemi.nexus.main.NexusRef;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class NexusTags {

    public static final TagKey<Block> DIRTLIKE = TagKey.create(Registries.BLOCK, NexusRef.rl("dirtlike"));
    public static final TagKey<Block> STONELIKE = TagKey.create(Registries.BLOCK, NexusRef.rl("stonelike"));
    public static final TagKey<Block> WOODLIKE = TagKey.create(Registries.BLOCK, NexusRef.rl("woodlike"));

    public static final TagKey<Block> WARPBLOSSOM_LOGS = TagKey.create(Registries.BLOCK, NexusRef.rl("warpblossom_logs"));

    public static final TagKey<Block> NEXUS_PORTAL_CORES = TagKey.create(Registries.BLOCK, NexusRef.rl("nexus_portal_cores"));
    public static final TagKey<Block> NEXUS_PORTALS = TagKey.create(Registries.BLOCK, NexusRef.rl("nexus_portals"));
}
