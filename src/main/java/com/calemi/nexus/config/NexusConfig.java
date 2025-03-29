package com.calemi.nexus.config;

import com.calemi.nexus.main.Nexus;
import com.calemi.nexus.main.NexusRef;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.ModConfigSpec;

public class NexusConfig {

    private static final ModConfigSpec.Builder CLIENT_BUILDER = new ModConfigSpec.Builder();
    private static final ModConfigSpec.Builder SERVER_BUILDER = new ModConfigSpec.Builder();

    public static final CategoryClient client = new CategoryClient(CLIENT_BUILDER);
    public static final CategoryServer server = new CategoryServer(SERVER_BUILDER);

    public static void init() {
        Nexus.MOD_CONTAINER.registerConfig(ModConfig.Type.CLIENT, CLIENT_BUILDER.build());
        Nexus.MOD_CONTAINER.registerConfig(ModConfig.Type.SERVER, SERVER_BUILDER.build());
    }

    public static class CategoryClient {

        public final ModConfigSpec.ConfigValue<Boolean> portalCoreHUDOverlay;
        public final ModConfigSpec.ConfigValue<Boolean> portalCoreWorldOverlay;

        public CategoryClient (ModConfigSpec.Builder builder) {

            portalCoreHUDOverlay = builder.translation(getPrefixedKey("portal_core_hud_overlay"))
                    .comment("Portal Core HUD Overlay")
                    .comment("Enables the HUD overlay text above the action bar.")
                    .comment("This overlay shows the destination coordinates of the Nexus Portal Core if placed where standing.")
                    .define("portalCoreHUDOverlay", true);

            portalCoreWorldOverlay = builder.translation(getPrefixedKey("portal_core_world_overlay"))
                    .comment("Portal Core World Overlay")
                    .comment("Enables the lines that show the bounds that are equivalent to a single block in the Nexus.")
                    .define("portalCoreWorldOverlay", true);
        }
    }

    public static class CategoryServer {

        public final ModConfigSpec.ConfigValue<Integer> maxPortalSize;
        public final ModConfigSpec.ConfigValue<Integer> portalTransitionTime;
        public final ModConfigSpec.ConfigValue<Boolean> portalCoreCamo;
        public final ModConfigSpec.ConfigValue<Integer> portalCoreCoordinateScale;
        public final ModConfigSpec.ConfigValue<Integer> ironPortalCoreCoordinateScale;
        public final ModConfigSpec.ConfigValue<Integer> goldPortalCoreCoordinateScale;
        public final ModConfigSpec.ConfigValue<Integer> diamondPortalCoreCoordinateScale;
        public final ModConfigSpec.ConfigValue<Integer> netheritePortalCoreCoordinateScale;
        public final ModConfigSpec.ConfigValue<Integer> starlightPortalCoreCoordinateScale;

        public CategoryServer (ModConfigSpec.Builder builder) {

            maxPortalSize = builder.translation(getPrefixedKey("max_portal_size"))
                    .comment("Max Portal Size")
                    .comment("The max size (in total blocks) a Nexus Portal can be.")
                    .defineInRange("maxPortalSize", 1024, 1, 4096);

            portalTransitionTime = builder.translation(getPrefixedKey("portal_transition_time"))
                    .comment("Portal Transition Time (in ticks)")
                    .comment("The time (in ticks) it takes for a player to stand in the portal to teleport.")
                    .comment("Set to 1 for instant teleport.")
                    .comment("If you are having a bug that's causing an infinite transition time, try dyeing the portal block to update it.")
                    .defineInRange("portalTransitionTime", 80, 1, Integer.MAX_VALUE);

            portalCoreCamo = builder.translation(getPrefixedKey("portal_core_camo"))
                    .comment("Portal Core Camo Ability")
                    .comment("Enables the ability to camouflage the Nexus Portal Block with any full-block of your choosing.")
                    .define("portalCoreCamo", true);

            portalCoreCoordinateScale = portalCoreCoordinateScale(builder, "basic", "Basic", 1);
            ironPortalCoreCoordinateScale = portalCoreCoordinateScale(builder, "iron", "Iron", 8);
            goldPortalCoreCoordinateScale = portalCoreCoordinateScale(builder, "gold", "Gold", 16);
            diamondPortalCoreCoordinateScale = portalCoreCoordinateScale(builder, "Diamond", "diamond", 32);
            netheritePortalCoreCoordinateScale = portalCoreCoordinateScale(builder, "netherite", "Netherite", 64);
            starlightPortalCoreCoordinateScale = portalCoreCoordinateScale(builder, "starlight", "Starlight", 128);
        }
    }

    private static ModConfigSpec.IntValue portalCoreCoordinateScale(ModConfigSpec.Builder builder, String typeKey, String typeName, int defaultValue) {
        return builder.translation(getPrefixedKey(typeKey + "_portal_core_coordinate_scale"))
                .comment(typeName + " Nexus Portal Core Coordinate Scale")
                .comment("For the " + typeName + " Nexus Portal Core.")
                .comment("The distance in the Nexus, that will be multiplied by this value when determining the outside destination.")
                .comment("For example: setting this value to 8 and placing the core in the Nexus at x100, z100 will bring you to x800, z800 in the outside dimension.")
                .defineInRange(typeKey + "PortalCoreCoordinateScale", defaultValue, 1, 1000000);
    }

    private static String getPrefixedKey(String suffix) {
        return "config." + NexusRef.ID + "." + suffix;
    }
}