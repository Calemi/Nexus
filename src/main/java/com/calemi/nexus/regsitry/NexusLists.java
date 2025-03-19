package com.calemi.nexus.regsitry;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.ArrayList;
import java.util.List;

public class NexusLists {

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

    public static List<ResourceKey<Block>> toResourceKeyList(List<DeferredBlock<Block>> blocks) {
        return new ArrayList<>(blocks.stream().map(DeferredBlock::getKey).toList());
    }

    public static List<Block> toBlockList(List<DeferredBlock<Block>> blocks) {
        return new ArrayList<>(blocks.stream().map(DeferredHolder::get).toList());
    }

    public static Block[] toBlockArray(List<DeferredBlock<Block>> blocks) {
        return blocks.stream().map(DeferredHolder::get).toArray(Block[]::new);
    }

    public static List<ItemStack> toItemStackList(List<DeferredBlock<Block>> blocks) {
        return new ArrayList<>(blocks.stream().map(block -> new ItemStack(block.asItem())).toList());
    }
}
