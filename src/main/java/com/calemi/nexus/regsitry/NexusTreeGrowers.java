package com.calemi.nexus.regsitry;

import net.minecraft.world.level.block.grower.TreeGrower;

import java.util.Optional;

public class NexusTreeGrowers {

    public static final TreeGrower WARPBLOSSOM = new TreeGrower(
            "warpblossom", Optional.empty(), Optional.of(NexusConfiguredFeatures.WARPBLOSSOM_CONFIGURED_KEY), Optional.empty()
    );
}
