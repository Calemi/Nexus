package com.calemi.nexus.config;

import com.calemi.nexus.main.Nexus;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.ModConfigSpec;

public class NexusConfig {

    private static final ModConfigSpec.Builder CLIENT_BUILDER = new ModConfigSpec.Builder();
    private static final ModConfigSpec.Builder COMMON_BUILDER = new ModConfigSpec.Builder();
    private static final ModConfigSpec.Builder SERVER_BUILDER = new ModConfigSpec.Builder();

    public static final CategoryClient client = new CategoryClient(CLIENT_BUILDER);
    public static final CategoryCommon common = new CategoryCommon(COMMON_BUILDER);
    public static final CategoryServer server = new CategoryServer(SERVER_BUILDER);

    public static void init() {
        Nexus.MOD_CONTAINER.registerConfig(ModConfig.Type.CLIENT, CLIENT_BUILDER.build());
        Nexus.MOD_CONTAINER.registerConfig(ModConfig.Type.COMMON, COMMON_BUILDER.build());
        Nexus.MOD_CONTAINER.registerConfig(ModConfig.Type.SERVER, SERVER_BUILDER.build());
    }

    public static class CategoryClient {

        public final ModConfigSpec.ConfigValue<Boolean> portalCoreHUDOverlay;

        public CategoryClient (ModConfigSpec.Builder builder) {

            portalCoreHUDOverlay = builder.comment("Portal Core HUD Overlay")
                    .comment("Enables the HUD overlay text above the action bar.")
                    .comment("This overlay shows the destination coordinates of the Nexus Portal Core if placed where standing.")
                    .define("portalCoreHUDOverlay", true);
        }
    }

    public static class CategoryCommon {

        public CategoryCommon (ModConfigSpec.Builder builder) {

        }
    }

    public static class CategoryServer {

        public CategoryServer (ModConfigSpec.Builder builder) {

        }
    }
}