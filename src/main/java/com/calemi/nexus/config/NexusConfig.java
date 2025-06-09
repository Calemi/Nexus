package com.calemi.nexus.config;

import com.calemi.ccore.api.string2.StringHelper2;
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

            portalCoreHUDOverlay = booleanValue(builder, "portalCoreHUDOverlay", true,
                    "Enables the HUD overlay text above the action bar.",
                    "This overlay shows the destination coordinates of the Nexus Portal Core if placed where standing."
            );

            portalCoreWorldOverlay = booleanValue(builder, "portalCoreWorldOverlay", true,
                    "Enables the HUD overlay text above the action bar.",
                    "Enables the lines that show the bounds that are equivalent to a single block in the Nexus."
            );
        }
    }

    public static class CategoryServer {

        public final ModConfigSpec.ConfigValue<Integer> maxPortalSize;
        public final ModConfigSpec.ConfigValue<Integer> portalTransitionTime;
        public final ModConfigSpec.ConfigValue<Boolean> nexusPortalCoreCamo;

        public final ModConfigSpec.ConfigValue<Integer> nexusPortalCoreCoordinateScale;
        public final ModConfigSpec.ConfigValue<Integer> ironPortalCoreCoordinateScale;
        public final ModConfigSpec.ConfigValue<Integer> goldPortalCoreCoordinateScale;
        public final ModConfigSpec.ConfigValue<Integer> diamondPortalCoreCoordinateScale;
        public final ModConfigSpec.ConfigValue<Integer> netheritePortalCoreCoordinateScale;
        public final ModConfigSpec.ConfigValue<Integer> starlightPortalCoreCoordinateScale;

        public final ModConfigSpec.ConfigValue<Integer> roadBaseEffectStack;
        public final ModConfigSpec.ConfigValue<Integer> roadSprintEffectStackAdd;
        public final ModConfigSpec.ConfigValue<Integer> roadAcceleriteArmorEffectStackAdd;
        public final ModConfigSpec.ConfigValue<Integer> roadUpgradeEffectStackPerBlock;

        public final ModConfigSpec.ConfigValue<Integer> jumpPadBaseEffectStack;
        public final ModConfigSpec.ConfigValue<Integer> jumpPadUpgradeEffectStackPerBlock;

        public final ModConfigSpec.ConfigValue<Double> acceleriteOreFromMovingWarpslateChance;

        public final ModConfigSpec.ConfigValue<Double> dormantAcceleriteIngotChargeSpeedRequirement;

        public final ModConfigSpec.ConfigValue<Integer> maxAcceleriteSwordEffectStack;
        public final ModConfigSpec.ConfigValue<Integer> maxAcceleriteShovelEffectStack;
        public final ModConfigSpec.ConfigValue<Integer> maxAcceleritePickaxeEffectStack;
        public final ModConfigSpec.ConfigValue<Integer> maxAcceleriteAxeEffectStack;
        public final ModConfigSpec.ConfigValue<Integer> maxAcceleriteHoeEffectStack;
        public final ModConfigSpec.ConfigValue<Integer> maxAcceleriteArmorEffectStack;
        public final ModConfigSpec.ConfigValue<Integer> maxAccelerationEnchantEffectStack;

        public final ModConfigSpec.ConfigValue<Integer> acceleriteSwordEffectDuration;
        public final ModConfigSpec.ConfigValue<Integer> acceleriteShovelEffectDuration;
        public final ModConfigSpec.ConfigValue<Integer> acceleritePickaxeEffectDuration;
        public final ModConfigSpec.ConfigValue<Integer> acceleriteAxeEffectDuration;
        public final ModConfigSpec.ConfigValue<Integer> acceleriteHoeEffectDuration;
        public final ModConfigSpec.ConfigValue<Integer> acceleriteArmorEffectDuration;
        public final ModConfigSpec.ConfigValue<Integer> accelerationEnchantEffectDuration;

        public final ModConfigSpec.ConfigValue<Double> acceleriteSwordRepairSpeedRequirement;
        public final ModConfigSpec.ConfigValue<Double> acceleriteShovelRepairSpeedRequirement;
        public final ModConfigSpec.ConfigValue<Double> acceleritePickaxeRepairSpeedRequirement;
        public final ModConfigSpec.ConfigValue<Double> acceleriteAxeRepairSpeedRequirement;
        public final ModConfigSpec.ConfigValue<Double> acceleriteHoeRepairSpeedRequirement;
        public final ModConfigSpec.ConfigValue<Double> acceleriteArmorRepairSpeedRequirement;
        public final ModConfigSpec.ConfigValue<Double> accelerationEnchantRepairSpeedRequirement;

        public CategoryServer (ModConfigSpec.Builder builder) {

            maxPortalSize = intValue(builder, "maxPortalSize", 1024, 1, 4096,
                    "The max size (in total blocks) a Nexus Portal can be."
            );

            portalTransitionTime = intValue(builder, "portalTransitionTime", 80, 1, Integer.MAX_VALUE,
                    "The time (in ticks) it takes for a player to stand in the portal to teleport.",
                    "Set to 1 for instant teleport.",
                    "If you are having a bug that's causing an infinite transition time, try dyeing the portal block to update it."
            );

            nexusPortalCoreCamo = booleanValue(builder, "nexusPortalCoreCamo", true,
                    "Enables the ability to camouflage a Nexus Portal Core with any full-block of your choosing."
            );

            nexusPortalCoreCoordinateScale = portalCoreCoordinateScale(builder, "nexus", 1);
            ironPortalCoreCoordinateScale = portalCoreCoordinateScale(builder, "iron", 8);
            goldPortalCoreCoordinateScale = portalCoreCoordinateScale(builder, "gold", 16);
            diamondPortalCoreCoordinateScale = portalCoreCoordinateScale(builder, "diamond", 32);
            netheritePortalCoreCoordinateScale = portalCoreCoordinateScale(builder, "netherite", 64);
            starlightPortalCoreCoordinateScale = portalCoreCoordinateScale(builder, "starlight", 128);

            roadBaseEffectStack = intValue(builder, "roadBaseEffectStack", 2, 0, 256,
                    "The base effect stack Roads grant."
            );

            roadSprintEffectStackAdd = intValue(builder, "roadSprintEffectStackAdd", 2, 0, 256,
                    "The added effect stack Roads grant when sprinting."
            );

            roadAcceleriteArmorEffectStackAdd = intValue(builder, "roadAcceleriteArmorEffectStackAdd", 2, 0, 256,
                    "The added effect stack Roads grant when wearing a full set of Accelerite Armor."
            );

            roadUpgradeEffectStackPerBlock = intValue(builder, "roadUpgradeEffectStackPerBlock", 1, 0, 256,
                    "The effect stack per Accelerite Block placed under Roads.",
                    "Set to 0 to disable upgrading Roads."
            );

            jumpPadBaseEffectStack = intValue(builder, "jumpPadBaseEffectStack", 4, 0, 256,
                    "The base effect stack Jump Pads grant."
            );

            jumpPadUpgradeEffectStackPerBlock = intValue(builder, "jumpPadUpgradeEffectStackPerBlock", 1, 0, 256,
                    "The effect stack per Accelerite Block placed under Jump Pads.",
                    "Set to 0 to disable upgrading Jump Pads."
            );

            acceleriteOreFromMovingWarpslateChance = doubleValue(builder, "acceleriteOreFromMovingWarpslateChance", 0.001D, 0, 1,
                    "The chance when Warpslate is moved by a piston to convert into Accelerite Ore."
            );

            dormantAcceleriteIngotChargeSpeedRequirement = doubleValue(builder, "dormantAcceleriteIngotChargeSpeedRequirement", 1, 0.001D, Double.MAX_VALUE,
                    "The player's speed requirement in order to charge a Dormant Accelerite Ingot."
            );

            maxAcceleriteSwordEffectStack = maxAccelerationEffectStack(builder, "Sword", 10);
            maxAcceleriteShovelEffectStack = maxAccelerationEffectStack(builder, "Shovel", 10);
            maxAcceleritePickaxeEffectStack = maxAccelerationEffectStack(builder, "Pickaxe", 10);
            maxAcceleriteAxeEffectStack = maxAccelerationEffectStack(builder, "Axe", 10);
            maxAcceleriteHoeEffectStack = maxAccelerationEffectStack(builder, "Hoe", 10);
            maxAcceleriteArmorEffectStack = maxAccelerationEffectStack(builder, "Armor", 10);
            maxAccelerationEnchantEffectStack = intValue(builder, "maxAccelerationEnchantEffectStack", 10, 0, 256,
                    "The maximum Acceleration effect stacks that can be obtained by items enchanted with Acceleration.",
                    "Set to 0 to disable the enchantment's functionality."
            );

            acceleriteSwordEffectDuration = accelerationEffectDuration(builder, "Sword", 100);
            acceleriteShovelEffectDuration = accelerationEffectDuration(builder, "Shovel", 100);
            acceleritePickaxeEffectDuration = accelerationEffectDuration(builder, "Pickaxe", 100);
            acceleriteAxeEffectDuration = accelerationEffectDuration(builder, "Axe", 100);
            acceleriteHoeEffectDuration = accelerationEffectDuration(builder, "Hoe", 100);
            acceleriteArmorEffectDuration = accelerationEffectDuration(builder, "Armor", 100);
            accelerationEnchantEffectDuration = intValue(builder, "accelerationEnchantEffectDuration", 100, 0, Integer.MAX_VALUE,
                    "The duration of the Acceleration effect obtained by items enchanted with Acceleration.",
                    "The duration value is multiplied by the stack of the Acceleration enchantment.",
                    "Set to 0 to disable the enchantment's functionality."
            );

            acceleriteSwordRepairSpeedRequirement = acceleriteRepairSpeedRequirement(builder, "Sword", 0.5D);
            acceleriteShovelRepairSpeedRequirement = acceleriteRepairSpeedRequirement(builder, "Shovel", 0.5D);
            acceleritePickaxeRepairSpeedRequirement = acceleriteRepairSpeedRequirement(builder, "Pickaxe", 0.5D);
            acceleriteAxeRepairSpeedRequirement = acceleriteRepairSpeedRequirement(builder, "Axe", 0.5D);
            acceleriteHoeRepairSpeedRequirement = acceleriteRepairSpeedRequirement(builder, "Hoe", 0.5D);
            acceleriteArmorRepairSpeedRequirement = acceleriteRepairSpeedRequirement(builder, "Armor", 0.5D);

            accelerationEnchantRepairSpeedRequirement = doubleValue(builder, "accelerationEnchantRepairSpeedRequirement", 0.5D, 0, Double.MAX_VALUE,
                    "The player's speed requirement in order to repair items enchanted with Acceleration.",
                    "Set to 0 to disable the enchantment's functionality."
            );
        }
    }

    private static ModConfigSpec.IntValue portalCoreCoordinateScale(ModConfigSpec.Builder builder, String typeKey, int defaultValue) {

        String camelCaseName = typeKey + "PortalCoreCoordinateScale";

        String titleName = StringHelper2.camelToTitle(camelCaseName);

        return intValue(builder, camelCaseName, defaultValue, 1, 1000000,
                "The distance in the Nexus, that will be multiplied by this value when determining the outside destination.",
                "For example: setting this value to 8 and placing the core in the Nexus at x100, z100 will bring you to x800, z800 in the outside dimension."
        );
    }

    private static ModConfigSpec.IntValue maxAccelerationEffectStack(ModConfigSpec.Builder builder, String toolTypeTitle, int defaultValue) {

        String camelCaseName = "maxAccelerite" + toolTypeTitle + "EffectStack";

        return intValue(builder, camelCaseName, defaultValue, 0, 256,
                "The maximum Acceleration effect stacks that can be obtained by Accelerite " + toolTypeTitle + ".",
                "Set to 0 to disable the effect being applied from this item."
        );
    }

    private static ModConfigSpec.IntValue accelerationEffectDuration(ModConfigSpec.Builder builder, String toolTypeTitle, int defaultValue) {

        String camelCaseName = "accelerite" + toolTypeTitle + "EffectDuration";

        return intValue(builder, camelCaseName, defaultValue, 0, Integer.MAX_VALUE,
                "The duration of the Acceleration effect obtained by Accelerite " + toolTypeTitle + ".",
                "Set to 0 to disable the effect being applied from this item."
        );
    }

    private static ModConfigSpec.DoubleValue acceleriteRepairSpeedRequirement(ModConfigSpec.Builder builder, String toolTypeTitle, double defaultValue) {

        String camelCaseName = "accelerite" + toolTypeTitle + "RepairSpeedRequirement";

        return doubleValue(builder, camelCaseName, defaultValue, 0, Double.MAX_VALUE,
                "The player's speed requirement in order to repair Accelerite " + toolTypeTitle + ".",
                "Set to 0 to prevent repairing by speed charging for this item."
        );
    }

    private static ModConfigSpec.BooleanValue booleanValue(ModConfigSpec.Builder builder, String camelCaseName, boolean defaultValue, String... desc) {
        return startBuilder(builder, camelCaseName, desc)
                .define(camelCaseName, defaultValue);
    }

    private static ModConfigSpec.IntValue intValue(ModConfigSpec.Builder builder, String camelCaseName, int defaultValue, int min, int max, String... desc) {
        return startBuilder(builder, camelCaseName, desc)
                .defineInRange(camelCaseName, defaultValue, min, max);
    }

    private static ModConfigSpec.DoubleValue doubleValue(ModConfigSpec.Builder builder, String camelCaseName, double defaultValue, double min, double max, String... desc) {
        return startBuilder(builder, camelCaseName, desc)
                .defineInRange(camelCaseName, defaultValue, min, max);
    }

    private static ModConfigSpec.Builder startBuilder(ModConfigSpec.Builder builder, String camelCaseName, String... desc) {
        return builder
                .translation(getPrefixedKey(StringHelper2.camelToSnake(camelCaseName)))
                .comment(StringHelper2.camelToTitle(camelCaseName))
                .comment(desc);
    }

    private static String getPrefixedKey(String suffix) {
        return "config." + NexusRef.ID + "." + suffix;
    }
}