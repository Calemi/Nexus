package com.calemi.nexus.block.family;

import com.calemi.ccore.api.block.family.CBlockFamily;
import com.calemi.ccore.api.block.family.CBlockFamily.FamilyType;
import com.calemi.ccore.api.block.family.CBlockFamily.MemberType;
import com.calemi.ccore.api.block.family.CBlockFamilyMember;
import com.calemi.nexus.block.NexusBlocks;
import com.calemi.nexus.tag.NexusTags;
import net.minecraft.world.level.block.Blocks;

import java.util.ArrayList;
import java.util.List;

public class NexusBlockFamilies {

    public static final List<CBlockFamily> ALL = new ArrayList<>();

    public static final CBlockFamily CALCITE = register(new CBlockFamily.Builder(FamilyType.STONE)
            .member(MemberType.BASE, new CBlockFamilyMember.Builder(Blocks.CALCITE).dontGenOrShow())
    );

    public static final CBlockFamily POLISHED_CALCITE = register(new CBlockFamily.Builder(FamilyType.STONE)
            .member(MemberType.BASE, new CBlockFamilyMember.Builder(NexusBlocks.POLISHED_CALCITE))
            .member(MemberType.STAIRS, new CBlockFamilyMember.Builder(NexusBlocks.POLISHED_CALCITE_STAIRS))
            .member(MemberType.SLAB, new CBlockFamilyMember.Builder(NexusBlocks.POLISHED_CALCITE_SLAB))
            .member(MemberType.WALL, new CBlockFamilyMember.Builder(NexusBlocks.POLISHED_CALCITE_WALL))
            .member(MemberType.PILLAR, new CBlockFamilyMember.Builder(NexusBlocks.CALCITE_PILLAR))
            .member(MemberType.CHISELED, new CBlockFamilyMember.Builder(NexusBlocks.CHISELED_CALCITE))
            .ancestor(CALCITE)
    );

    public static final CBlockFamily CALCITE_BRICKS = register(new CBlockFamily.Builder(FamilyType.STONE)
            .member(MemberType.BASE, new CBlockFamilyMember.Builder(NexusBlocks.CALCITE_BRICKS))
            .member(MemberType.CRACKED, new CBlockFamilyMember.Builder(NexusBlocks.CRACKED_CALCITE_BRICKS))
            .member(MemberType.STAIRS, new CBlockFamilyMember.Builder(NexusBlocks.CALCITE_BRICK_STAIRS))
            .member(MemberType.SLAB, new CBlockFamilyMember.Builder(NexusBlocks.CALCITE_BRICK_SLAB))
            .member(MemberType.WALL, new CBlockFamilyMember.Builder(NexusBlocks.CALCITE_BRICK_WALL))
            .ancestor(POLISHED_CALCITE)
            .ancestor(CALCITE)
    );

    public static final CBlockFamily CALCITE_TILES = register(new CBlockFamily.Builder(FamilyType.STONE)
            .member(MemberType.BASE, new CBlockFamilyMember.Builder(NexusBlocks.CALCITE_TILES))
            .member(MemberType.CRACKED, new CBlockFamilyMember.Builder(NexusBlocks.CRACKED_CALCITE_TILES))
            .member(MemberType.STAIRS, new CBlockFamilyMember.Builder(NexusBlocks.CALCITE_TILE_STAIRS))
            .member(MemberType.SLAB, new CBlockFamilyMember.Builder(NexusBlocks.CALCITE_TILE_SLAB))
            .member(MemberType.WALL, new CBlockFamilyMember.Builder(NexusBlocks.CALCITE_TILE_WALL))
            .ancestor(CALCITE_BRICKS)
            .ancestor(POLISHED_CALCITE)
            .ancestor(CALCITE)
    );

    public static final CBlockFamily COBBLED_WARPSLATE = register(new CBlockFamily.Builder(FamilyType.COBBLESTONE)
            .member(MemberType.BASE, new CBlockFamilyMember.Builder(NexusBlocks.COBBLED_WARPSLATE))
            .member(MemberType.STAIRS, new CBlockFamilyMember.Builder(NexusBlocks.COBBLED_WARPSLATE_STAIRS))
            .member(MemberType.SLAB, new CBlockFamilyMember.Builder(NexusBlocks.COBBLED_WARPSLATE_SLAB))
            .member(MemberType.WALL, new CBlockFamilyMember.Builder(NexusBlocks.COBBLED_WARPSLATE_WALL))
            .member(MemberType.CHISELED, new CBlockFamilyMember.Builder(NexusBlocks.CHISELED_WARPSLATE))
    );

    public static final CBlockFamily POLISHED_WARPSLATE = register(new CBlockFamily.Builder(FamilyType.STONE)
            .member(MemberType.BASE, new CBlockFamilyMember.Builder(NexusBlocks.POLISHED_WARPSLATE))
            .member(MemberType.STAIRS, new CBlockFamilyMember.Builder(NexusBlocks.POLISHED_WARPSLATE_STAIRS))
            .member(MemberType.SLAB, new CBlockFamilyMember.Builder(NexusBlocks.POLISHED_WARPSLATE_SLAB))
            .member(MemberType.WALL, new CBlockFamilyMember.Builder(NexusBlocks.POLISHED_WARPSLATE_WALL))
            .ancestor(COBBLED_WARPSLATE)
    );

    public static final CBlockFamily WARPSLATE_BRICK = register(new CBlockFamily.Builder(FamilyType.STONE)
            .member(MemberType.BASE, new CBlockFamilyMember.Builder(NexusBlocks.WARPSLATE_BRICKS))
            .member(MemberType.CRACKED, new CBlockFamilyMember.Builder(NexusBlocks.CRACKED_WARPSLATE_BRICKS))
            .member(MemberType.STAIRS, new CBlockFamilyMember.Builder(NexusBlocks.WARPSLATE_BRICK_STAIRS))
            .member(MemberType.SLAB, new CBlockFamilyMember.Builder(NexusBlocks.WARPSLATE_BRICK_SLAB))
            .member(MemberType.WALL, new CBlockFamilyMember.Builder(NexusBlocks.WARPSLATE_BRICK_WALL))
            .ancestor(POLISHED_WARPSLATE)
            .ancestor(COBBLED_WARPSLATE)
    );

    public static final CBlockFamily WARPSLATE_TILE = register(new CBlockFamily.Builder(FamilyType.STONE)
            .member(MemberType.BASE, new CBlockFamilyMember.Builder(NexusBlocks.WARPSLATE_TILES))
            .member(MemberType.CRACKED, new CBlockFamilyMember.Builder(NexusBlocks.CRACKED_WARPSLATE_TILES))
            .member(MemberType.STAIRS, new CBlockFamilyMember.Builder(NexusBlocks.WARPSLATE_TILE_STAIRS))
            .member(MemberType.SLAB, new CBlockFamilyMember.Builder(NexusBlocks.WARPSLATE_TILE_SLAB))
            .member(MemberType.WALL, new CBlockFamilyMember.Builder(NexusBlocks.WARPSLATE_TILE_WALL))
            .ancestor(WARPSLATE_BRICK)
            .ancestor(POLISHED_WARPSLATE)
            .ancestor(COBBLED_WARPSLATE)
    );

    public static final CBlockFamily WARPBLOSSOM = register(new CBlockFamily.Builder(FamilyType.PLANKS)
            .logTag(NexusTags.Items.WARPBLOSSOM_LOGS)
            .member(MemberType.LOG, new CBlockFamilyMember.Builder(NexusBlocks.WARPBLOSSOM_LOG))
            .member(MemberType.WOOD, new CBlockFamilyMember.Builder(NexusBlocks.WARPBLOSSOM_WOOD))
            .member(MemberType.STRIPPED_LOG, new CBlockFamilyMember.Builder(NexusBlocks.STRIPPED_WARPBLOSSOM_LOG))
            .member(MemberType.STRIPPED_WOOD, new CBlockFamilyMember.Builder(NexusBlocks.STRIPPED_WARPBLOSSOM_WOOD))
            .member(MemberType.BASE, new CBlockFamilyMember.Builder(NexusBlocks.WARPBLOSSOM_PLANKS))
            .member(MemberType.STAIRS, new CBlockFamilyMember.Builder(NexusBlocks.WARPBLOSSOM_STAIRS))
            .member(MemberType.SLAB, new CBlockFamilyMember.Builder(NexusBlocks.WARPBLOSSOM_SLAB))
            .member(MemberType.FENCE, new CBlockFamilyMember.Builder(NexusBlocks.WARPBLOSSOM_FENCE))
            .member(MemberType.FENCE_GATE, new CBlockFamilyMember.Builder(NexusBlocks.WARPBLOSSOM_FENCE_GATE))
            .member(MemberType.DOOR, new CBlockFamilyMember.Builder(NexusBlocks.WARPBLOSSOM_DOOR))
            .member(MemberType.TRAPDOOR, new CBlockFamilyMember.Builder(NexusBlocks.WARPBLOSSOM_TRAPDOOR))
            .member(MemberType.PRESSURE_PLATE, new CBlockFamilyMember.Builder(NexusBlocks.WARPBLOSSOM_PRESSURE_PLATE))
            .member(MemberType.BUTTON, new CBlockFamilyMember.Builder(NexusBlocks.WARPBLOSSOM_BUTTON))
            .member(MemberType.SIGN, new CBlockFamilyMember.Builder(NexusBlocks.WARPBLOSSOM_SIGN))
            .member(MemberType.WALL_SIGN, new CBlockFamilyMember.Builder(NexusBlocks.WARPBLOSSOM_WALL_SIGN))
            .member(MemberType.HANGING_SIGN, new CBlockFamilyMember.Builder(NexusBlocks.WARPBLOSSOM_HANGING_SIGN))
            .member(MemberType.WALL_HANGING_SIGN, new CBlockFamilyMember.Builder(NexusBlocks.WARPBLOSSOM_WALL_HANGING_SIGN))
    );

    public static CBlockFamily register(CBlockFamily.Builder builder) {
        CBlockFamily family = builder.getFamily();
        ALL.add(family);
        return family;
    }

    public static List<CBlockFamily> getFamiliesOfType(FamilyType type) {
        return ALL.stream().filter(family -> family.getFamilyType().equals(type)).toList();
    }
}
