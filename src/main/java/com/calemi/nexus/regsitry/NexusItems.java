package com.calemi.nexus.regsitry;

import com.calemi.ccore.api.item2.SmithingTemplateHelper;
import com.calemi.nexus.main.Nexus;
import com.calemi.nexus.main.NexusRef;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;
import java.util.function.Supplier;

public class NexusItems {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(NexusRef.ID);

    public static final DeferredItem<Item> CHRONO_SHARD = regItem("chrono_shard", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> CHRONO_UPGRADE_SMITHING_TEMPLATE = regItem("chrono_upgrade_smithing_template",
            () -> SmithingTemplateHelper.createCustomUpgradeTemplate(NexusRef.ID, "chrono", List.of("amethyst_shard"), List.of("ender_pearl")));

    public static DeferredItem<Item> regItem(String name, Supplier<Item> sup) {
        return ITEMS.register(name, sup);
    }

    public static void init() {
        Nexus.LOGGER.info("Registering: Items - Start");
        ITEMS.register(Nexus.MOD_EVENT_BUS);
        Nexus.LOGGER.info("Registering: Items - End");
    }
}