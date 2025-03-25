package com.calemi.nexus.regsitry;

import com.calemi.ccore.api.family.CBlockFamily;

import java.util.ArrayList;
import java.util.List;

public class NexusBlockFamilies {

    public static final List<CBlockFamily> ALL = new ArrayList<>();

    public static final CBlockFamily COBBLED_WARPSLATE = register(new CBlockFamily.Builder(CBlockFamily.Type.COBBLESTONE)
            .base(NexusBlocks.COBBLED_WARPSLATE)
            .stairs(NexusBlocks.COBBLED_WARPSLATE_STAIRS)
            .slab(NexusBlocks.COBBLED_WARPSLATE_SLAB)
            .wall(NexusBlocks.COBBLED_WARPSLATE_WALL)
            .chiseled(NexusBlocks.CHISELED_WARPSLATE)
    );

    public static final CBlockFamily POLISHED_WARPSLATE = register(new CBlockFamily.Builder(CBlockFamily.Type.STONE)
            .base(NexusBlocks.POLISHED_WARPSLATE)
            .stairs(NexusBlocks.POLISHED_WARPSLATE_STAIRS)
            .slab(NexusBlocks.POLISHED_WARPSLATE_SLAB)
            .wall(NexusBlocks.POLISHED_WARPSLATE_WALL)
    );

    public static final CBlockFamily WARPSLATE_BRICK = register(new CBlockFamily.Builder(CBlockFamily.Type.STONE)
            .base(NexusBlocks.WARPSLATE_BRICKS)
            .cracked(NexusBlocks.CRACKED_WARPSLATE_BRICKS)
            .stairs(NexusBlocks.WARPSLATE_BRICK_STAIRS)
            .slab(NexusBlocks.WARPSLATE_BRICK_SLAB)
            .wall(NexusBlocks.WARPSLATE_BRICK_WALL)
    );

    public static final CBlockFamily WARPSLATE_TILE = register(new CBlockFamily.Builder(CBlockFamily.Type.STONE)
            .base(NexusBlocks.WARPSLATE_TILES)
            .cracked(NexusBlocks.CRACKED_WARPSLATE_TILES)
            .stairs(NexusBlocks.WARPSLATE_TILE_STAIRS)
            .slab(NexusBlocks.WARPSLATE_TILE_SLAB)
            .wall(NexusBlocks.WARPSLATE_TILE_WALL)
    );

    public static final CBlockFamily WARPBLOSSOM = register(new CBlockFamily.Builder(CBlockFamily.Type.PLANKS)
            .log(NexusBlocks.WARPBLOSSOM_LOG)
            .wood(NexusBlocks.WARPBLOSSOM_WOOD)
            .strippedLog(NexusBlocks.STRIPPED_WARPBLOSSOM_LOG)
            .strippedWood(NexusBlocks.STRIPPED_WARPBLOSSOM_WOOD)
            .base(NexusBlocks.WARPBLOSSOM_PLANKS)
            .logTag(NexusTags.Items.WARPBLOSSOM_LOGS)
            .stairs(NexusBlocks.WARPBLOSSOM_STAIRS)
            .slab(NexusBlocks.WARPBLOSSOM_SLAB)
            .fence(NexusBlocks.WARPBLOSSOM_FENCE)
            .fenceGate(NexusBlocks.WARPBLOSSOM_FENCE_GATE)
            .door(NexusBlocks.WARPBLOSSOM_DOOR)
            .trapdoor(NexusBlocks.WARPBLOSSOM_TRAPDOOR)
            .pressurePlate(NexusBlocks.WARPBLOSSOM_PRESSURE_PLATE)
            .button(NexusBlocks.WARPBLOSSOM_BUTTON)
            .sign(NexusBlocks.WARPBLOSSOM_SIGN, NexusBlocks.WARPBLOSSOM_WALL_SIGN)
            .hangingSign(NexusBlocks.WARPBLOSSOM_HANGING_SIGN, NexusBlocks.WARPBLOSSOM_WALL_HANGING_SIGN)
    );

    public static CBlockFamily register(CBlockFamily.Builder builder) {
        CBlockFamily family = builder.getFamily();
        ALL.add(family);
        return family;
    }

    public static List<CBlockFamily> getFamiliesOfType(CBlockFamily.Type type) {
        return ALL.stream().filter(family -> family.getType().equals(type)).toList();
    }
}
