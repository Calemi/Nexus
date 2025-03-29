package com.calemi.nexus.block;

import com.calemi.nexus.block.wood.NexusWoodTypes;
import com.calemi.nexus.client.partclie.NexusParticles;
import com.calemi.nexus.config.NexusConfig;
import com.calemi.nexus.item.NexusItems;
import com.calemi.nexus.block.family.NexusBlockSetTypes;
import com.calemi.nexus.main.Nexus;
import com.calemi.nexus.main.NexusRef;
import com.calemi.nexus.world.feature.tree.NexusTreeGrowers;
import net.minecraft.util.ColorRGBA;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class NexusBlocks {

    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(NexusRef.ID);



    public static final DeferredBlock<Block> NEXUS_PORTAL_CORE             = regBlock("nexus_portal_core", () ->
            new NexusPortalCoreBlock(NexusConfig.server.portalCoreCoordinateScale));

    public static final DeferredBlock<Block> IRON_NEXUS_PORTAL_CORE        = regBlock("iron_nexus_portal_core", () ->
            new NexusPortalCoreBlock(NexusConfig.server.ironPortalCoreCoordinateScale));

    public static final DeferredBlock<Block> GOLD_NEXUS_PORTAL_CORE        = regBlock("gold_nexus_portal_core", () ->
            new NexusPortalCoreBlock(NexusConfig.server.goldPortalCoreCoordinateScale));

    public static final DeferredBlock<Block> DIAMOND_NEXUS_PORTAL_CORE     = regBlock("diamond_nexus_portal_core", () ->
            new NexusPortalCoreBlock(NexusConfig.server.diamondPortalCoreCoordinateScale));

    public static final DeferredBlock<Block> NETHERITE_NEXUS_PORTAL_CORE   = regBlock("netherite_nexus_portal_core", () ->
            new NexusPortalCoreBlock(NexusConfig.server.netheritePortalCoreCoordinateScale));

    public static final DeferredBlock<Block> STARLIGHT_NEXUS_PORTAL_CORE   = regBlock("starlight_nexus_portal_core", () ->
            new NexusPortalCoreBlock(NexusConfig.server.starlightPortalCoreCoordinateScale));



    public static final DeferredBlock<Block> WHITE_NEXUS_PORTAL            = regBlockSolo("white_nexus_portal", () ->
            new NexusPortalBlock(DyeColor.WHITE, NexusParticles.WHITE_PORTAL_PARTICLES));

    public static final DeferredBlock<Block> ORANGE_NEXUS_PORTAL           = regBlockSolo("orange_nexus_portal", () ->
            new NexusPortalBlock(DyeColor.ORANGE, NexusParticles.ORANGE_PORTAL_PARTICLES));

    public static final DeferredBlock<Block> MAGENTA_NEXUS_PORTAL          = regBlockSolo("magenta_nexus_portal", () ->
            new NexusPortalBlock(DyeColor.MAGENTA, NexusParticles.MAGENTA_PORTAL_PARTICLES));

    public static final DeferredBlock<Block> LIGHT_BLUE_NEXUS_PORTAL       = regBlockSolo("light_blue_nexus_portal", () ->
            new NexusPortalBlock(DyeColor.LIGHT_BLUE, NexusParticles.LIGHT_BLUE_PORTAL_PARTICLES));

    public static final DeferredBlock<Block> YELLOW_NEXUS_PORTAL           = regBlockSolo("yellow_nexus_portal", () ->
            new NexusPortalBlock(DyeColor.YELLOW, NexusParticles.YELLOW_PORTAL_PARTICLES));

    public static final DeferredBlock<Block> LIME_NEXUS_PORTAL             = regBlockSolo("lime_nexus_portal", () ->
            new NexusPortalBlock(DyeColor.LIME, NexusParticles.LIME_PORTAL_PARTICLES));

    public static final DeferredBlock<Block> PINK_NEXUS_PORTAL             = regBlockSolo("pink_nexus_portal", () ->
            new NexusPortalBlock(DyeColor.PINK, NexusParticles.PINK_PORTAL_PARTICLES));

    public static final DeferredBlock<Block> GRAY_NEXUS_PORTAL             = regBlockSolo("gray_nexus_portal", () ->
            new NexusPortalBlock(DyeColor.GRAY, NexusParticles.GRAY_PORTAL_PARTICLES));

    public static final DeferredBlock<Block> LIGHT_GRAY_NEXUS_PORTAL       = regBlockSolo("light_gray_nexus_portal", () ->
            new NexusPortalBlock(DyeColor.LIGHT_GRAY, NexusParticles.LIGHT_GRAY_PORTAL_PARTICLES));

    public static final DeferredBlock<Block> CYAN_NEXUS_PORTAL             = regBlockSolo("cyan_nexus_portal", () ->
            new NexusPortalBlock(DyeColor.CYAN, NexusParticles.CYAN_PORTAL_PARTICLES));

    public static final DeferredBlock<Block> PURPLE_NEXUS_PORTAL           = regBlockSolo("purple_nexus_portal", () ->
            new NexusPortalBlock(DyeColor.PURPLE, NexusParticles.PURPLE_PORTAL_PARTICLES));

    public static final DeferredBlock<Block> BLUE_NEXUS_PORTAL             = regBlockSolo("blue_nexus_portal", () ->
            new NexusPortalBlock(DyeColor.BLUE, NexusParticles.BLUE_PORTAL_PARTICLES));

    public static final DeferredBlock<Block> BROWN_NEXUS_PORTAL            = regBlockSolo("brown_nexus_portal", () ->
            new NexusPortalBlock(DyeColor.BROWN, NexusParticles.BROWN_PORTAL_PARTICLES));

    public static final DeferredBlock<Block> GREEN_NEXUS_PORTAL           = regBlockSolo("green_nexus_portal", () ->
            new NexusPortalBlock(DyeColor.GREEN, NexusParticles.GREEN_PORTAL_PARTICLES));

    public static final DeferredBlock<Block> RED_NEXUS_PORTAL              = regBlockSolo("red_nexus_portal", () ->
            new NexusPortalBlock(DyeColor.RED, NexusParticles.RED_PORTAL_PARTICLES));

    public static final DeferredBlock<Block> BLACK_NEXUS_PORTAL            = regBlockSolo("black_nexus_portal", () ->
            new NexusPortalBlock(DyeColor.BLACK, NexusParticles.BLACK_PORTAL_PARTICLES));



    public static final DeferredBlock<Block> CHRONOWARPED_GRASS            = regBlock("chronowarped_grass",
            ChronowarpedGrassBlock::new);

    public static final DeferredBlock<Block> CHRONOWARPED_DIRT             = regBlock("chronowarped_dirt", () ->
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DIRT).mapColor(MapColor.TERRACOTTA_BLUE)));

    public static final DeferredBlock<Block> CHRONOWARPED_SAND             = regBlock("chronowarped_sand", () ->
            new ColoredFallingBlock(new ColorRGBA(5324121), BlockBehaviour.Properties.ofFullCopy(Blocks.SAND).mapColor(MapColor.TERRACOTTA_BLUE)));




    public static final DeferredBlock<Block> CHRONO_BLOCK                  = regBlock("chrono_block", () ->
            new AmethystBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK)));

    public static final DeferredBlock<Block> BUDDING_CHRONO                = regBlock("budding_chrono", () ->
            new BuddingChronoBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BUDDING_AMETHYST)));

    public static final DeferredBlock<Block> SMALL_CHRONO_BUD              = regBlock("small_chrono_bud", () ->
            new AmethystClusterBlock(3.0F, 4.0F, BlockBehaviour.Properties.ofFullCopy(Blocks.SMALL_AMETHYST_BUD)));

    public static final DeferredBlock<Block> MEDIUM_CHRONO_BUD             = regBlock("medium_chrono_bud", () ->
            new AmethystClusterBlock(4.0F, 3.0F, BlockBehaviour.Properties.ofFullCopy(Blocks.MEDIUM_AMETHYST_BUD)));

    public static final DeferredBlock<Block> LARGE_CHRONO_BUD              = regBlock("large_chrono_bud", () ->
            new AmethystClusterBlock(5.0F, 3.0F, BlockBehaviour.Properties.ofFullCopy(Blocks.LARGE_AMETHYST_BUD)));

    public static final DeferredBlock<Block> CHRONO_CLUSTER                = regBlock("chrono_cluster", () ->
            new AmethystClusterBlock(7.0F, 3.0F, BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_CLUSTER)));



    public static final DeferredBlock<Block> WARPSLATE                     = regBlock("warpslate", () ->
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE).strength(1.25F, 6.0F).mapColor(MapColor.TERRACOTTA_CYAN)));



    public static final DeferredBlock<Block> COBBLED_WARPSLATE             = regBlock("cobbled_warpslate", () ->
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE).strength(1.75F, 6.0F).mapColor(MapColor.TERRACOTTA_CYAN)));

    public static final DeferredBlock<Block> COBBLED_WARPSLATE_STAIRS      = regBlock("cobbled_warpslate_stairs", () ->
            stair(COBBLED_WARPSLATE.get()));

    public static final DeferredBlock<Block> COBBLED_WARPSLATE_SLAB        = regBlock("cobbled_warpslate_slab", () ->
            slab(COBBLED_WARPSLATE.get()));

    public static final DeferredBlock<Block> COBBLED_WARPSLATE_WALL        = regBlock("cobbled_warpslate_wall", () ->
            wall(COBBLED_WARPSLATE.get()));



    public static final DeferredBlock<Block> CHISELED_WARPSLATE            = regBlock("chiseled_warpslate", () ->
            new Block(BlockBehaviour.Properties.ofFullCopy(COBBLED_WARPSLATE.get()).sound(SoundType.DEEPSLATE_BRICKS)));



    public static final DeferredBlock<Block> POLISHED_WARPSLATE            = regBlock("polished_warpslate", () ->
            new Block(BlockBehaviour.Properties.ofFullCopy(COBBLED_WARPSLATE.get()).sound(SoundType.POLISHED_DEEPSLATE)));

    public static final DeferredBlock<Block> POLISHED_WARPSLATE_STAIRS     = regBlock("polished_warpslate_stairs", () ->
            stair(POLISHED_WARPSLATE.get()));

    public static final DeferredBlock<Block> POLISHED_WARPSLATE_SLAB       = regBlock("polished_warpslate_slab", () ->
            slab(POLISHED_WARPSLATE.get()));

    public static final DeferredBlock<Block>POLISHED_WARPSLATE_WALL        = regBlock("polished_warpslate_wall", () ->
            wall(POLISHED_WARPSLATE.get()));



    public static final DeferredBlock<Block> WARPSLATE_TILES               = regBlock("warpslate_tiles", () ->
            new Block(BlockBehaviour.Properties.ofFullCopy(COBBLED_WARPSLATE.get()).sound(SoundType.DEEPSLATE_TILES)));

    public static final DeferredBlock<Block> CRACKED_WARPSLATE_TILES       = regBlock("cracked_warpslate_tiles", () ->
            new Block(BlockBehaviour.Properties.ofFullCopy(WARPSLATE_TILES.get())));

    public static final DeferredBlock<Block> WARPSLATE_TILE_STAIRS         = regBlock("warpslate_tile_stairs", () ->
            stair(POLISHED_WARPSLATE.get()));

    public static final DeferredBlock<Block> WARPSLATE_TILE_SLAB           = regBlock("warpslate_tile_slab", () ->
            slab(POLISHED_WARPSLATE.get()));

    public static final DeferredBlock<Block> WARPSLATE_TILE_WALL           = regBlock("warpslate_tile_wall", () ->
            wall(POLISHED_WARPSLATE.get()));



    public static final DeferredBlock<Block> WARPSLATE_BRICKS              = regBlock("warpslate_bricks", () ->
            new Block(BlockBehaviour.Properties.ofFullCopy(COBBLED_WARPSLATE.get()).sound(SoundType.DEEPSLATE_TILES)));

    public static final DeferredBlock<Block> CRACKED_WARPSLATE_BRICKS      = regBlock("cracked_warpslate_bricks", () ->
            new Block(BlockBehaviour.Properties.ofFullCopy(WARPSLATE_BRICKS.get())));

    public static final DeferredBlock<Block> WARPSLATE_BRICK_STAIRS        = regBlock("warpslate_brick_stairs", () ->
            stair(POLISHED_WARPSLATE.get()));

    public static final DeferredBlock<Block> WARPSLATE_BRICK_SLAB          = regBlock("warpslate_brick_slab", () ->
            slab(POLISHED_WARPSLATE.get()));

    public static final DeferredBlock<Block> WARPSLATE_BRICK_WALL          = regBlock("warpslate_brick_wall", () ->
            wall(POLISHED_WARPSLATE.get()));



    public static final DeferredBlock<Block> WARPBLOSSOM_LOG               = regBlock("warpblossom_log", () ->
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG)));

    public static final DeferredBlock<Block> WARPBLOSSOM_WOOD              = regBlock("warpblossom_wood", () ->
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD)));

    public static final DeferredBlock<Block> STRIPPED_WARPBLOSSOM_LOG      = regBlock("stripped_warpblossom_log", () ->
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG)));

    public static final DeferredBlock<Block> STRIPPED_WARPBLOSSOM_WOOD     = regBlock("stripped_warpblossom_wood", () ->
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD)));

    public static final DeferredBlock<Block> WARPBLOSSOM_LEAVES            = regBlock("warpblossom_leaves",
            WarpblossomLeavesBlock::new);

    public static final DeferredBlock<Block> WARPBLOSSOM_SAPLING           = regBlock("warpblossom_sapling", () ->
            new SaplingBlock(NexusTreeGrowers.WARPBLOSSOM, BlockBehaviour.Properties.ofFullCopy(Blocks.CHERRY_SAPLING)));

    public static final DeferredBlock<Block> POTTED_WARPBLOSSOM_SAPLING    = regBlock("potted_warpblossom_sapling", () ->
            flowerPot(WARPBLOSSOM_SAPLING.get()));

    public static final DeferredBlock<Block> PURPLE_PETALS                 = regBlock("purple_petals", () ->
            new PinkPetalsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.PINK_PETALS)));



    public static final DeferredBlock<Block> WARPBLOSSOM_PLANKS            = regBlock("warpblossom_planks", () ->
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHERRY_PLANKS)));

    public static final DeferredBlock<Block> WARPBLOSSOM_STAIRS            = regBlock("warpblossom_stairs", () ->
            stair(WARPBLOSSOM_PLANKS.get()));

    public static final DeferredBlock<Block> WARPBLOSSOM_SLAB              = regBlock("warpblossom_slab", () ->
            slab(WARPBLOSSOM_PLANKS.get()));

    public static final DeferredBlock<Block> WARPBLOSSOM_FENCE             = regBlock("warpblossom_fence", () ->
            fence(WARPBLOSSOM_PLANKS.get()));

    public static final DeferredBlock<Block> WARPBLOSSOM_FENCE_GATE        = regBlock("warpblossom_fence_gate", () ->
            fenceGate(WARPBLOSSOM_PLANKS.get(), NexusWoodTypes.WARPBLOSSOM));

    public static final DeferredBlock<Block> WARPBLOSSOM_DOOR              = regBlockSolo("warpblossom_door", () ->
            door(WARPBLOSSOM_PLANKS.get(), NexusBlockSetTypes.WARPBLOSSOM));

    public static final DeferredBlock<Block> WARPBLOSSOM_TRAPDOOR          = regBlock("warpblossom_trapdoor", () ->
            trapdoor(WARPBLOSSOM_PLANKS.get(), NexusBlockSetTypes.WARPBLOSSOM));

    public static final DeferredBlock<Block> WARPBLOSSOM_PRESSURE_PLATE    = regBlock("warpblossom_pressure_plate", () ->
            pressurePlate(WARPBLOSSOM_PLANKS.get(), NexusBlockSetTypes.WARPBLOSSOM));

    public static final DeferredBlock<Block> WARPBLOSSOM_BUTTON            = regBlock("warpblossom_button", () ->
            button(WARPBLOSSOM_PLANKS.get(), 30, NexusBlockSetTypes.WARPBLOSSOM));

    public static final DeferredBlock<Block> WARPBLOSSOM_SIGN              = regBlockSolo("warpblossom_sign", () ->
            sign(WARPBLOSSOM_PLANKS.get(), NexusWoodTypes.WARPBLOSSOM));

    public static final DeferredBlock<Block> WARPBLOSSOM_WALL_SIGN         = regBlockSolo("warpblossom_wall_sign", () ->
            wallSign(WARPBLOSSOM_PLANKS.get(), WARPBLOSSOM_SIGN.get(), NexusWoodTypes.WARPBLOSSOM));

    public static final DeferredBlock<Block> WARPBLOSSOM_HANGING_SIGN      = regBlockSolo("warpblossom_hanging_sign", () ->
            hangingSign(WARPBLOSSOM_PLANKS.get(), NexusWoodTypes.WARPBLOSSOM));

    public static final DeferredBlock<Block> WARPBLOSSOM_WALL_HANGING_SIGN = regBlockSolo("warpblossom_wall_hanging_sign", () ->
            wallHangingSign(WARPBLOSSOM_PLANKS.get(), WARPBLOSSOM_HANGING_SIGN.get(), NexusWoodTypes.WARPBLOSSOM));


    private static DeferredBlock<Block> regBlock(String name, Supplier<Block> supplier) {
        DeferredBlock<Block> registeredBlock = BLOCKS.register(name, supplier);
        NexusItems.ITEMS.register(name, () -> new BlockItem(registeredBlock.get(), new Item.Properties()));
        return registeredBlock;
    }

    private static DeferredBlock<Block> regBlockSolo(String name, Supplier<Block> supplier) {
        return BLOCKS.register(name, supplier);
    }

    private static Block stair(Block baseBlock) {
        return new StairBlock(baseBlock.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(baseBlock));
    }

    private static Block slab(Block baseBlock) {
        return new SlabBlock(BlockBehaviour.Properties.ofFullCopy(baseBlock));
    }

    private static Block wall(Block baseBlock) {
        return new WallBlock(BlockBehaviour.Properties.ofFullCopy(baseBlock).forceSolidOn());
    }

    private static Block fence(Block baseBlock) {
        return new FenceBlock(BlockBehaviour.Properties.ofFullCopy(baseBlock).forceSolidOn());
    }

    private static Block fenceGate(Block baseBlock, WoodType woodType) {
        return new FenceGateBlock(woodType, BlockBehaviour.Properties.ofFullCopy(baseBlock).forceSolidOn());
    }

    private static Block door(Block baseBlock, BlockSetType blockSetType) {
        return new DoorBlock(blockSetType, BlockBehaviour.Properties.ofFullCopy(baseBlock).strength(3.0F).noOcclusion().pushReaction(PushReaction.DESTROY));
    }

    private static Block trapdoor(Block baseBlock, BlockSetType blockSetType) {
        return new TrapDoorBlock(blockSetType, BlockBehaviour.Properties.ofFullCopy(baseBlock).strength(3.0F).noOcclusion().isValidSpawn(Blocks::never));
    }

    private static Block pressurePlate(Block baseBlock, BlockSetType blockSetType) {
        return new PressurePlateBlock(blockSetType, BlockBehaviour.Properties.ofFullCopy(baseBlock).strength(0.5F).noCollission().forceSolidOn().pushReaction(PushReaction.DESTROY));
    }

    private static Block button(Block baseBlock, int ticksToStayPressed, BlockSetType blockSetType) {
        return new ButtonBlock(blockSetType, ticksToStayPressed, BlockBehaviour.Properties.ofFullCopy(baseBlock).strength(0.5F).noCollission().pushReaction(PushReaction.DESTROY));
    }

    private static Block sign(Block baseBlock, WoodType woodType) {
        return new StandingSignBlock(woodType, BlockBehaviour.Properties.ofFullCopy(baseBlock).strength(1.0F).noCollission().forceSolidOn());
    }

    @SuppressWarnings("deprecation")
    private static Block wallSign(Block baseBlock, Block standingSign, WoodType woodType) {
        return new WallSignBlock(woodType, BlockBehaviour.Properties.ofFullCopy(baseBlock).strength(1.0F).dropsLike(standingSign).noCollission().forceSolidOn());
    }

    private static Block hangingSign(Block baseBlock, WoodType woodType) {
        return new CeilingHangingSignBlock(woodType, BlockBehaviour.Properties.ofFullCopy(baseBlock).strength(1.0F).noCollission().forceSolidOn());
    }

    @SuppressWarnings("deprecation")
    private static Block wallHangingSign(Block baseBlock, Block ceilingSign, WoodType woodType) {
        return new WallHangingSignBlock(woodType, BlockBehaviour.Properties.ofFullCopy(baseBlock).strength(1.0F).dropsLike(ceilingSign).noCollission().forceSolidOn());
    }

    private static Block flowerPot(Block plant) {
        return new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, () -> plant, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT));
    }

    public static void init() {
        Nexus.LOGGER.info("Registering: Blocks - Start");

        BLOCKS.register(Nexus.MOD_EVENT_BUS);

        ((FlowerPotBlock)Blocks.FLOWER_POT).addPlant(NexusRef.rl("warpblossom_sapling"), POTTED_WARPBLOSSOM_SAPLING);

        Nexus.LOGGER.info("Registering: Blocks - End");
    }
}
