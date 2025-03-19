package com.calemi.nexus.main;

import net.minecraft.resources.ResourceLocation;

public class NexusRef {

    public static final String NAME = "Nexus";
    public static final String ID = "nexus";

    public static final ResourceLocation rl(String path) {
        return ResourceLocation.fromNamespaceAndPath(ID, path);
    }
}