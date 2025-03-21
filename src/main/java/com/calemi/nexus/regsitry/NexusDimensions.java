package com.calemi.nexus.regsitry;

import com.calemi.nexus.main.NexusRef;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;

public class NexusDimensions {

    public static final ResourceLocation NEXUS_RESOURCE_LOCATION = ResourceLocation.fromNamespaceAndPath(NexusRef.ID, "the_nexus");
    public static final ResourceKey<Level> NEXUS_KEY = ResourceKey.create(Registries.DIMENSION, NEXUS_RESOURCE_LOCATION);
}
