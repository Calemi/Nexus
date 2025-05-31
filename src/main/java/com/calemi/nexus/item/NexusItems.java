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
            () -> new AcceleriteSwordItem(new Item.Properties().attributes(SwordItem.createAttributes(NexusTiers.ACCELERITE, 3, -2.4F))));

    public static final DeferredItem<Item> ACCELERITE_SHOVEL = regItem("accelerite_shovel",
            () -> new AcceleriteShovelItem(new Item.Properties().attributes(ShovelItem.createAttributes(NexusTiers.ACCELERITE, 1.5F, -3.0F))));

    public static final DeferredItem<Item> ACCELERITE_PICKAXE = regItem("accelerite_pickaxe",
            () -> new AcceleritePickaxeItem(new Item.Properties().attributes(PickaxeItem.createAttributes(NexusTiers.ACCELERITE, 1.0F, -2.8F))));

    public static final DeferredItem<Item> ACCELERITE_AXE = regItem("accelerite_axe",
            () -> new AcceleriteAxeItem(new Item.Properties().attributes(AxeItem.createAttributes(NexusTiers.ACCELERITE, 6.0F, -3.1F))));

    public static final DeferredItem<Item> ACCELERITE_HOE = regItem("accelerite_hoe",
            () -> new AcceleriteHoeItem(new Item.Properties().attributes(HoeItem.createAttributes(NexusTiers.ACCELERITE, -2.0F, -1.0F))));

    public static final DeferredItem<Item> TOTEM_OF_WARPING = regItem("totem_of_warping",
            () -> new Item(new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON)));

    public static final DeferredItem<Item> SPEEDOMETER = regItem("speedometer",
            () -> new SpeedometerItem(new Item.Properties().stacksTo(1)));

    public static final DeferredItem<Item> FALLBREAKERS = regItem("fallbreakers",
            () -> new FallbreakersItem(new Item.Properties().stacksTo(1)));

    public static final DeferredItem<Item> ACCELERITE_HELMET = regItem("accelerite_helmet",
            () -> new AcceleriteArmorItem(ArmorItem.Type.HELMET, new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(20))));

    public static final DeferredItem<Item> ACCELERITE_CHESTPLATE = regItem("accelerite_chestplate",
            () -> new AcceleriteArmorItem(ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(20))));

    public static final DeferredItem<Item> ACCELERITE_LEGGINGS = regItem("accelerite_leggings",
            () -> new AcceleriteArmorItem(ArmorItem.Type.LEGGINGS, new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(20))));

    public static final DeferredItem<Item> ACCELERITE_BOOTS = regItem("accelerite_boots",
            () -> new AcceleriteArmorItem(ArmorItem.Type.BOOTS, new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(20))));

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

    public static final DeferredItem<Item> WARPBLOSSOM_STICK = regItem("warpblossom_stick",
            () -> new Item(new Item.Properties()));

    public static DeferredItem<Item> regItem(String name, Supplier<Item> sup) {
        return ITEMS.register(name, sup);
    }

    public static void init() {
        Nexus.LOGGER.info("Registering: Items - Start");
        ITEMS.register(Nexus.MOD_EVENT_BUS);
        Nexus.LOGGER.info("Registering: Items - End");
    }
}