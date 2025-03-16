package com.calemi.nexus.main;

import net.minecraft.resources.ResourceLocation;

public class NexusRef {

    public static final String MOD_NAME = "Nexus";
    public static final String MOD_ID = "nexus";

    public static final ResourceLocation rl(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }
}