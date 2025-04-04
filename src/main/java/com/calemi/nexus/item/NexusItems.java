package com.calemi.nexus.item;

import com.calemi.ccore.api.item2.CBoatItem;
import com.calemi.ccore.api.item2.SmithingTemplateHelper;
import com.calemi.nexus.block.NexusBlocks;
import com.calemi.nexus.item.boat.NexusBoatTypes;
import com.calemi.nexus.main.Nexus;
import com.calemi.nexus.main.NexusRef;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;
import java.util.function.Supplier;

public class NexusItems {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(NexusRef.ID);

    public static final DeferredItem<Item> CHRONO_SHARD_FRAGMENT = regItem("chrono_shard_fragment", () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> CHRONO_SHARD = regItem("chrono_shard", () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> CHRONO_UPGRADE_SMITHING_TEMPLATE = regItem("chrono_upgrade_smithing_template",
            () -> SmithingTemplateHelper.createCustomUpgradeTemplate(NexusRef.ID, "chrono", List.of("amethyst_shard"), List.of("ender_pearl")));

    public static final DeferredItem<Item> RAW_ACCELERITE = regItem("raw_accelerite",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> DORMANT_ACCELERITE_INGOT = regItem("dormant_accelerite_ingot",
            () -> new DormantAcceleriteIngotItem(new Item.Properties()));

    public static final DeferredItem<Item> CHARGED_ACCELERITE_INGOT = regItem("charged_accelerite_ingot",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> ACCELERITE_SWORD = regItem("accelerite_sword",
            () -> new SwordItem(NexusTiers.ACCELERITE, new Item.Properties().attributes(SwordItem.createAttributes(NexusTiers.ACCELERITE, 3, -2.4F))));

    public static final DeferredItem<Item> TOTEM_OF_WARPING = regItem("totem_of_warping",
            () -> new Item(new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON)));

    public static final DeferredItem<Item> SPEEDOMETER = regItem("speedometer",
            () -> new SpeedometerItem(new Item.Properties().stacksTo(1)));

    public static final DeferredItem<Item> FALLBREAKERS = regItem("fallbreakers",
            () -> new FallbreakersItem(new Item.Properties().stacksTo(1)));

    public static final DeferredItem<Item> WARPBLOSSOM_DOOR = regItem("warpblossom_door",
            () -> new DoubleHighBlockItem(NexusBlocks.WARPBLOSSOM_DOOR.get(), new Item.Properties()));

    public static final DeferredItem<Item> WARPBLOSSOM_SIGN = regItem("warpblossom_sign",
            () -> new SignItem(new Item.Properties(), NexusBlocks.WARPBLOSSOM_SIGN.get(), NexusBlocks.WARPBLOSSOM_WALL_SIGN.get()));

    public static final DeferredItem<Item> WARPBLOSSOM_HANGING_SIGN = regItem("warpblossom_hanging_sign",
            () -> new HangingSignItem(NexusBlocks.WARPBLOSSOM_HANGING_SIGN.get(), NexusBlocks.WARPBLOSSOM_WALL_HANGING_SIGN.get(), new Item.Properties()));

    public static final DeferredItem<Item> WARPBLOSSOM_BOAT = regItem("warpblossom_boat",
            () -> new CBoatItem(NexusBoatTypes.WARPBLOSSOM, false, new Item.Properties().stacksTo(1)));

    public static final DeferredItem<Item> WARPBLOSSOM_CHEST_BOAT = regItem("warpblossom_chest_boat",
            () -> new CBoatItem(NexusBoatTypes.WARPBLOSSOM, true, new Item.Properties().stacksTo(1)));

    public static DeferredItem<Item> regItem(String name, Supplier<Item> sup) {
        return ITEMS.register(name, sup);
    }

    public static void init() {
        Nexus.LOGGER.info("Registering: Items - Start");
        ITEMS.register(Nexus.MOD_EVENT_BUS);
        Nexus.LOGGER.info("Registering: Items - End");
    }
}