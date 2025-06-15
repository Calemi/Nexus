package com.calemi.nexus.datagen;

import com.calemi.ccore.api.datagen.CLanguageProvider;
import com.calemi.nexus.block.NexusBlocks;
import com.calemi.nexus.item.NexusItems;
import com.calemi.nexus.main.Nexus;
import com.calemi.nexus.main.NexusRef;
import com.calemi.nexus.util.NexusLists;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;

public class NexusEnglishLanguageProvider extends CLanguageProvider {

    private final String CHRONO_UPGRADE = getPrefixedKey("item", "smithing_template.chrono_upgrade.");

    private final String ENCHANTMENT = getPrefixedKey("enchantment", "");

    private final String SCREEN = getPrefixedKey("screen", "");
    private final String SCREEN_NEXUS_PORTAL_CORE = SCREEN + "nexus_portal_core.";
    private final String SCREEN_NEXUS_PORTAL_CORE_TEXT = SCREEN_NEXUS_PORTAL_CORE + "text.";
    private final String SCREEN_NEXUS_PORTAL_CORE_BUTTON = SCREEN_NEXUS_PORTAL_CORE + "button.";
    private final String SCREEN_NEXUS_PORTAL_CORE_BUTTON_DIMENSION_SELECT = SCREEN_NEXUS_PORTAL_CORE_BUTTON + "dimension_select.";
    private final String SCREEN_NEXUS_PORTAL_CORE_BUTTON_TELEPORT = SCREEN_NEXUS_PORTAL_CORE_BUTTON + "teleport.";
    private final String SCREEN_NEXUS_PORTAL_CORE_BUTTON_FIND_LINK = SCREEN_NEXUS_PORTAL_CORE_BUTTON + "find_link.";
    private final String SCREEN_NEXUS_PORTAL_CORE_BUTTON_GENERATE_LINK = SCREEN_NEXUS_PORTAL_CORE_BUTTON + "generate_link.";
    private final String SCREEN_NEXUS_PORTAL_CORE_BUTTON_LIGHT_PORTAL = SCREEN_NEXUS_PORTAL_CORE_BUTTON + "light_portal.";
    private final String SCREEN_NEXUS_PORTAL_CORE_BUTTON_UNLINK = SCREEN_NEXUS_PORTAL_CORE_BUTTON + "unlink.";
    private final String SCREEN_NEXUS_PORTAL_CORE_BUTTON_CLOSE = SCREEN_NEXUS_PORTAL_CORE_BUTTON + "close.";

    private final String MESSAGE = getPrefixedKey("message", "");
    private final String MESSAGE_TELEPORT = MESSAGE + "teleport.";
    private final String MESSAGE_GENERATE_LINK = MESSAGE + "generate_link.";
    private final String MESSAGE_FIND_LINK = MESSAGE + "find_link.";
    private final String MESSAGE_LIGHT_PORTAL = MESSAGE + "light_portal.";
    private final String MESSAGE_UNLINK = MESSAGE + "unlink.";

    private final String JEI_DESCRIPTION = getPrefixedKey("description", "");
    private final String JADE = "config.jade.plugin_" + NexusRef.ID + ".";

    public NexusEnglishLanguageProvider(PackOutput output) {
        super(NexusRef.ID, output, "en_us");
    }

    @Override
    protected void addTranslations() {

        /*
            BLOCKS & ITEMS
         */

        NexusLists.ALL_BLOCKS.forEach(block -> {

            Nexus.LOGGER.debug("Generating en_us for {}", BuiltInRegistries.BLOCK.getKey(block).getPath());

            if (block.equals(NexusBlocks.WARPBLOSSOM_WALL_SIGN.get())) {
                return;
            }

            if (block.equals(NexusBlocks.WARPBLOSSOM_WALL_HANGING_SIGN.get())) {
                return;
            }

            addAutoBlock(block);
        });

        NexusLists.ALL_ITEMS.forEach(item -> {

            if (item.equals(NexusItems.CHRONO_UPGRADE_SMITHING_TEMPLATE.asItem())) {
                add(item, "Smithing Template");
                return;
            }

            addAutoItem(item);
        });

        /*
            TOOLTIPS
         */

        add(getPrefixedKey("upgrade", "smithing_upgrade"), "Chrono Upgrade");
        add(CHRONO_UPGRADE + "applies_to", "Amethyst Shard");
        add(CHRONO_UPGRADE + "ingredients", "Ender Pearl");
        add(CHRONO_UPGRADE + "base_slot_description", "Add Amethyst Shard");
        add(CHRONO_UPGRADE + "additions_slot_description", "Add Ender Pearl");

        add(getPrefixedKey("hover_text", "nexus_portal_core"), "Coordinate Scale");
        add(getPrefixedKey("hover_text", "fallbreakers_1"), "Fall Damage Immunity");
        add(getPrefixedKey("hover_text", "fallbreakers_2"), "Breaks on Impact");

        /*
            ENCHANTMENTS
         */

        add(ENCHANTMENT + "acceleration", "Acceleration");
        add(ENCHANTMENT + "acceleration.desc", "Attacking, mining, or taking damage (for armor) applies a stacking effect that increases move, attack, and mining speed.");
        add(ENCHANTMENT + "speed_mending", "Speed Mending");
        add(ENCHANTMENT + "speed_mending.desc", "The item is slowly repaired when reaching a certain speed.");

        /*
            CREATIVE TABS
         */

        add(getPrefixedKey("itemGroup", "main"), NexusRef.NAME);

        /*
            SCREENS
         */

        add(SCREEN_NEXUS_PORTAL_CORE + "title", "Nexus Portal Core");
        add(SCREEN_NEXUS_PORTAL_CORE_TEXT + "no_destination", "No Destination");
        add(SCREEN_NEXUS_PORTAL_CORE_TEXT + "current_destination", "Current Destination");
        add(SCREEN_NEXUS_PORTAL_CORE_BUTTON_DIMENSION_SELECT + "title", "Target Dimension");
        add(SCREEN_NEXUS_PORTAL_CORE_BUTTON_DIMENSION_SELECT + "cant_set", "You can only change Target Dimension from The Nexus.");
        add(SCREEN_NEXUS_PORTAL_CORE_BUTTON_TELEPORT + "title", "Teleport");
        add(SCREEN_NEXUS_PORTAL_CORE_BUTTON_FIND_LINK + "title", "Find Link");
        add(SCREEN_NEXUS_PORTAL_CORE_BUTTON_FIND_LINK + "info", "Searches for another core in the target dimension.");
        add(SCREEN_NEXUS_PORTAL_CORE_BUTTON_GENERATE_LINK + "title", "Generate Link");
        add(SCREEN_NEXUS_PORTAL_CORE_BUTTON_GENERATE_LINK + "requires", "Requires");
        add(SCREEN_NEXUS_PORTAL_CORE_BUTTON_GENERATE_LINK + "info", "Creates a new link to the target dimension.");
        add(SCREEN_NEXUS_PORTAL_CORE_BUTTON_LIGHT_PORTAL + "light_info", "Light Portal");
        add(SCREEN_NEXUS_PORTAL_CORE_BUTTON_LIGHT_PORTAL + "destroy_info", "Unlight Portal");
        add(SCREEN_NEXUS_PORTAL_CORE_BUTTON_UNLINK + "info_1", "Unlink");
        add(SCREEN_NEXUS_PORTAL_CORE_BUTTON_UNLINK + "info_2", "Hold shift to take the linked Nexus Portal Core.");
        add(SCREEN_NEXUS_PORTAL_CORE_BUTTON_CLOSE + "title", "Close");

        add(getPrefixedKey("hud", "calculated_destination"), "Calculated Destination");
        add(getPrefixedKey("hud", "speed"), "Speed");

        /*
            MOB EFFECTS
         */

        add(getPrefixedKey("effect", "acceleration"), "Acceleration");
        add(getPrefixedKey("effect", "road"), "Road");
        add(getPrefixedKey("effect", "jump_pad"), "Jump Pad");

        /*
            BIOMES
         */

        add(getPrefixedKey("biome", "chronowarped_fields"), "Chronowarped Fields");

        /*
            MESSAGES
         */

        add(MESSAGE + "unlock_dimension", "You can now travel to this dimension from The Nexus.");
        add(MESSAGE + "invalid_dimension", "Destination dimension could not be found!");
        add(MESSAGE + "invalid_destination", "Destination is outside the world border for that dimension! Removing invalid link...");
        add(MESSAGE + "no_core", "Destination Nexus Portal Core could not be found! Removing invalid link...");
        add(MESSAGE + "no_link", "Destination Nexus Portal Core is not linked to this one! Removing invalid link...");
        add(MESSAGE_TELEPORT + "prohibited_dimension", "You can't travel to this dimension. It is not allowed!");
        add(MESSAGE_TELEPORT + "locked_dimension", "You can't travel to this dimension. You haven't been there before!");
        add(MESSAGE_GENERATE_LINK + "no_core", "You do not have a Nexus Portal Core in your main-hand!");
        add(MESSAGE_GENERATE_LINK + "no_valid_spawn", "Could not find suitable y-level to generate Destination Nexus Portal Core!");
        add(MESSAGE_GENERATE_LINK + "success", "New link generated successfully!");
        add(MESSAGE_FIND_LINK + "searching", "Searching for similar Nexus Portal Core...");
        add(MESSAGE_FIND_LINK + "success", "Found similar Nexus Portal Core and successfully linked to it!");
        add(MESSAGE_FIND_LINK + "failure", "Could not find similar Nexus Portal Core!");
        add(MESSAGE_LIGHT_PORTAL + "success", "Successfully created portal!");
        add(MESSAGE_LIGHT_PORTAL + "obstructed", "The projection position is obstructed!");
        add(MESSAGE_LIGHT_PORTAL + "overflowing_frame", "The frame size is too large!");
        add(MESSAGE_LIGHT_PORTAL + "invalid_frame", "Invalid frame! Make sure no non-full blocks are inside or make up the frame!");
        add(MESSAGE_UNLINK + "success", "Successfully unlinked!");
        add(getPrefixedKey("networking", "unlocked_dimension_list_message.failed"), "Failed to synchronize unlocked dimension list! %s");

        /*
            CONFIG
         */

        //CLIENT
        addAutoConfig("portalCoreHUDOverlay");
        addAutoConfig("portalCoreWorldOverlay");

        //SERVER
        addAutoConfig("outsideDimensionList");
        addAutoConfig("outsideDimensionBlacklist");

        addAutoConfig("maxPortalSize");
        addAutoConfig("portalTransitionTime");
        addAutoConfig("nexusPortalCoreCamo");

        addAutoConfig("nexusPortalCoreCoordinateScale");
        addAutoConfig("ironPortalCoreCoordinateScale");
        addAutoConfig("goldPortalCoreCoordinateScale");
        addAutoConfig("diamondPortalCoreCoordinateScale");
        addAutoConfig("netheritePortalCoreCoordinateScale");
        addAutoConfig("starlightPortalCoreCoordinateScale");

        addAutoConfig("roadBaseEffectStack");
        addAutoConfig("roadSprintEffectStackAdd");
        addAutoConfig("roadAcceleriteArmorEffectStackAdd");
        addAutoConfig("roadUpgradeEffectStackPerBlock");

        addAutoConfig("jumpPadBaseEffectStack");
        addAutoConfig("jumpPadUpgradeEffectStackPerBlock");

        addAutoConfig("acceleriteOreFromMovingWarpslateChance");
        addAutoConfig("dormantAcceleriteIngotChargeSpeedRequirement");

        addAutoConfig("maxAcceleriteSwordEffectStack");
        addAutoConfig("maxAcceleriteShovelEffectStack");
        addAutoConfig("maxAcceleritePickaxeEffectStack");
        addAutoConfig("maxAcceleriteAxeEffectStack");
        addAutoConfig("maxAcceleriteHoeEffectStack");
        addAutoConfig("maxAcceleriteArmorEffectStack");
        addAutoConfig("maxAccelerationEnchantEffectStack");

        addAutoConfig("acceleriteSwordEffectDuration");
        addAutoConfig("acceleriteShovelEffectDuration");
        addAutoConfig("acceleritePickaxeEffectDuration");
        addAutoConfig("acceleriteAxeEffectDuration");
        addAutoConfig("acceleriteHoeEffectDuration");
        addAutoConfig("acceleriteArmorEffectDuration");
        addAutoConfig("accelerationEnchantEffectDuration");

        addAutoConfig("acceleriteSwordRepairSpeedRequirement");
        addAutoConfig("acceleriteShovelRepairSpeedRequirement");
        addAutoConfig("acceleritePickaxeRepairSpeedRequirement");
        addAutoConfig("acceleriteAxeRepairSpeedRequirement");
        addAutoConfig("acceleriteHoeRepairSpeedRequirement");
        addAutoConfig("acceleriteArmorRepairSpeedRequirement");
        addAutoConfig("accelerationEnchantRepairSpeedRequirement");

        /*
            JEI
         */

        add(JEI_DESCRIPTION + "chrono_upgrade_smithing_template", "Used to craft Chrono Shards. Found in Trial Chamber Chest & Vaults.");
        add(JEI_DESCRIPTION + "nexus_portal_core", "Allows traversal to The Nexus. Each portal core has its own coordinate scale. The blocks traveled in The Nexus are multiplied by this scale when leaving the dimension. You can use it in an Anvil to give your portal a name when placed. You can also disguise this block as another by using with a held block.");
        add(JEI_DESCRIPTION + "accelerite_ore", "Accelerite Ore is found in Nexus Chasms. These chasms spawn in The Nexus dimension and are massive holes that reach down to the void.");
        add(JEI_DESCRIPTION + "accelerite_ore_from_warpslate", "Accelerite Ore can also be obtained moving Warpslate with a Piston. Config Value Chance: %s%%");
        add(JEI_DESCRIPTION + "accelerite_ingot", "To obtain a Charged Accelerite Ingot from a Dormant one, you must reach a certain speed while it is in your inventory.");
        add(JEI_DESCRIPTION + "accelerite_tools_acceleration", "Attacking or mining with Accelerite tools applies a stacking effect that increases move, attack, and mining speed.");
        add(JEI_DESCRIPTION + "accelerite_armor_acceleration", "Taking damage while wearing Accelerite armor has a chance to apply a stacking effect that increases move, attack, and mining speed. Each piece of armor grants +25% to the chance for a total of 100% for a full set.");
        add(JEI_DESCRIPTION + "accelerite_equipment_repair", "Accelerite equipment is slowly repaired when reaching a certain speed.");
        add(JEI_DESCRIPTION + "road", "Roads grant any entity walking on it a speed boost. Sprinting will greatly increase the effect.");
        add(JEI_DESCRIPTION + "road_armor", "Wearing a full set of Accelerite armor will increase the effect stacks of Roads by %s.");
        add(JEI_DESCRIPTION + "road_upgrade", "The effects from Roads & Jump Pads can be upgraded by placing a Charged Accelerite Block under it. Each blocks placed under will increase the effect by (%s for Roads, %s for Jump Pads)");
        add(JEI_DESCRIPTION + "jump_pad", "Jump Pads grant any entity walking on it a jump boost. Entities will also not take any damage when falling on them.");

        /*
            JADE
         */

        add(JADE + "nexus_portal_core", "Nexus Portal Core");
        add(JADE + "nexus_portal", "Nexus Portal");
    }
}
