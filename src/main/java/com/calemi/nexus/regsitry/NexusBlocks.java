package com.calemi.nexus.regsitry;

import com.calemi.nexus.block.NexusPortalBlock;
import com.calemi.nexus.block.NexusPortalCoreBlock;
import com.calemi.nexus.main.Nexus;
import com.calemi.nexus.main.NexusRef;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class NexusBlocks {

    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(NexusRef.ID);

    public static final DeferredBlock<Block> NEXUS_PORTAL_CORE           = regBlock("nexus_portal_core", () -> new NexusPortalCoreBlock(1));
    public static final DeferredBlock<Block> IRON_NEXUS_PORTAL_CORE      = regBlock("iron_nexus_portal_core", () -> new NexusPortalCoreBlock(8));
    public static final DeferredBlock<Block> GOLD_NEXUS_PORTAL_CORE      = regBlock("gold_nexus_portal_core", () -> new NexusPortalCoreBlock(16));
    public static final DeferredBlock<Block> DIAMOND_NEXUS_PORTAL_CORE   = regBlock("diamond_nexus_portal_core", () -> new NexusPortalCoreBlock(32));
    public static final DeferredBlock<Block> NETHERITE_NEXUS_PORTAL_CORE = regBlock("netherite_nexus_portal_core", () -> new NexusPortalCoreBlock(64));
    public static final DeferredBlock<Block> STARLIGHT_NEXUS_PORTAL_CORE = regBlock("starlight_nexus_portal_core", () -> new NexusPortalCoreBlock(128));

    public static final DeferredBlock<Block> WHITE_NEXUS_PORTAL          = regBlockSolo("white_nexus_portal", () -> new NexusPortalBlock(DyeColor.WHITE, NexusParticles.WHITE_PORTAL_PARTICLES));
    public static final DeferredBlock<Block> ORANGE_NEXUS_PORTAL         = regBlockSolo("orange_nexus_portal", () -> new NexusPortalBlock(DyeColor.ORANGE, NexusParticles.ORANGE_PORTAL_PARTICLES));
    public static final DeferredBlock<Block> MAGENTA_NEXUS_PORTAL        = regBlockSolo("magenta_nexus_portal", () -> new NexusPortalBlock(DyeColor.MAGENTA, NexusParticles.MAGENTA_PORTAL_PARTICLES));
    public static final DeferredBlock<Block> LIGHT_BLUE_NEXUS_PORTAL     = regBlockSolo("light_blue_nexus_portal", () -> new NexusPortalBlock(DyeColor.LIGHT_BLUE, NexusParticles.LIGHT_BLUE_PORTAL_PARTICLES));
    public static final DeferredBlock<Block> YELLOW_NEXUS_PORTAL         = regBlockSolo("yellow_nexus_portal", () -> new NexusPortalBlock(DyeColor.YELLOW, NexusParticles.YELLOW_PORTAL_PARTICLES));
    public static final DeferredBlock<Block> LIME_NEXUS_PORTAL           = regBlockSolo("lime_nexus_portal", () -> new NexusPortalBlock(DyeColor.LIME, NexusParticles.LIME_PORTAL_PARTICLES));
    public static final DeferredBlock<Block> PINK_NEXUS_PORTAL           = regBlockSolo("pink_nexus_portal", () -> new NexusPortalBlock(DyeColor.PINK, NexusParticles.PINK_PORTAL_PARTICLES));
    public static final DeferredBlock<Block> GRAY_NEXUS_PORTAL           = regBlockSolo("gray_nexus_portal", () -> new NexusPortalBlock(DyeColor.GRAY, NexusParticles.GRAY_PORTAL_PARTICLES));
    public static final DeferredBlock<Block> LIGHT_GRAY_NEXUS_PORTAL     = regBlockSolo("light_gray_nexus_portal", () -> new NexusPortalBlock(DyeColor.LIGHT_GRAY, NexusParticles.LIGHT_GRAY_PORTAL_PARTICLES));
    public static final DeferredBlock<Block> CYAN_NEXUS_PORTAL           = regBlockSolo("cyan_nexus_portal", () -> new NexusPortalBlock(DyeColor.CYAN, NexusParticles.CYAN_PORTAL_PARTICLES));
    public static final DeferredBlock<Block> PURPLE_NEXUS_PORTAL         = regBlockSolo("purple_nexus_portal", () -> new NexusPortalBlock(DyeColor.PURPLE, NexusParticles.PURPLE_PORTAL_PARTICLES));
    public static final DeferredBlock<Block> BLUE_NEXUS_PORTAL           = regBlockSolo("blue_nexus_portal", () -> new NexusPortalBlock(DyeColor.BLUE, NexusParticles.BLUE_PORTAL_PARTICLES));
    public static final DeferredBlock<Block> BROWN_NEXUS_PORTAL          = regBlockSolo("brown_nexus_portal", () -> new NexusPortalBlock(DyeColor.BROWN, NexusParticles.BROWN_PORTAL_PARTICLES));
    public static final DeferredBlock<Block> GREEN_NEXUS_PORTAL          = regBlockSolo("green_nexus_portal", () -> new NexusPortalBlock(DyeColor.GREEN, NexusParticles.GREEN_PORTAL_PARTICLES));
    public static final DeferredBlock<Block> RED_NEXUS_PORTAL            = regBlockSolo("red_nexus_portal", () -> new NexusPortalBlock(DyeColor.RED, NexusParticles.RED_PORTAL_PARTICLES));
    public static final DeferredBlock<Block> BLACK_NEXUS_PORTAL          = regBlockSolo("black_nexus_portal", () -> new NexusPortalBlock(DyeColor.BLACK, NexusParticles.BLACK_PORTAL_PARTICLES));

    private static DeferredBlock<Block> regBlock(String name, Supplier<Block> supplier) {
        DeferredBlock<Block> registeredBlock = BLOCKS.register(name, supplier);
        NexusItems.ITEMS.register(name, () -> new BlockItem(registeredBlock.get(), new Item.Properties()));
        return registeredBlock;
    }

    private static DeferredBlock<Block> regBlockSolo(String name, Supplier<Block> supplier) {
        return BLOCKS.register(name, supplier);
    }

    public static void init() {
        Nexus.LOGGER.info("Registering: Blocks - Start");
        BLOCKS.register(Nexus.MOD_EVENT_BUS);
        Nexus.LOGGER.info("Registering: Blocks - End");
    }
}
