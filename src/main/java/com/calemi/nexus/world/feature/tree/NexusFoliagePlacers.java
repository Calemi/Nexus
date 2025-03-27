package com.calemi.nexus.world.feature.tree;

import com.calemi.nexus.main.Nexus;
import com.calemi.nexus.main.NexusRef;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class NexusFoliagePlacers {

    public static final DeferredRegister<FoliagePlacerType<?>> FOLIAGE_PLACER_TYPES = DeferredRegister.create(Registries.FOLIAGE_PLACER_TYPE, NexusRef.ID);

    public static final Supplier<FoliagePlacerType<?>> WARPBLOSSOM = FOLIAGE_PLACER_TYPES.register("warpblossom", () -> new FoliagePlacerType<>(WarpblossomFoliagePlacer.CODEC));

    public static void init() {
        Nexus.LOGGER.info("Registering: Foliage Placers - Start");
        FOLIAGE_PLACER_TYPES.register(Nexus.MOD_EVENT_BUS);
        Nexus.LOGGER.info("Registering: Foliage Placers - End");
    }
}
