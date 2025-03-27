package com.calemi.nexus.regsitry;

import com.calemi.nexus.main.Nexus;
import com.calemi.nexus.main.NexusRef;
import com.calemi.nexus.world.CrystalClusterConfiguration;
import com.calemi.nexus.world.CrystalClusterFeature;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class NexusFeatures {

    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(Registries.FEATURE, NexusRef.ID);

    public static final DeferredHolder<Feature<?>, Feature<CrystalClusterConfiguration>> CRYSTAL_CLUSTER = FEATURES.register("crystal_cluster", () -> new CrystalClusterFeature(CrystalClusterConfiguration.CODEC));

    public static void init() {
        Nexus.LOGGER.info("Registering: Features - Start");

        FEATURES.register(Nexus.MOD_EVENT_BUS);

        Nexus.LOGGER.info("Registering: Features - End");
    }
}
