package com.calemi.nexus.main;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

public class NexusRef {

    public static final String NAME = "Nexus";
    public static final String ID = "nexus";

    public static ResourceLocation rl(String path) {
        return ResourceLocation.fromNamespaceAndPath(ID, path);
    }

    public static <T> ResourceKey<T> createKey(String name, ResourceKey<? extends Registry<T>> registryKey) {
        return ResourceKey.create(registryKey, rl(name));
    }

    public static <T> ResourceKey<T> createKey(ResourceLocation rl, ResourceKey<? extends Registry<T>> registryKey) {
        return ResourceKey.create(registryKey, rl);
    }
}