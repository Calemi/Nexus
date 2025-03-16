package com.calemi.nexus.regsitry;

import com.calemi.nexus.main.Nexus;
import com.calemi.nexus.main.NexusRef;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class NexusItems {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(NexusRef.MOD_ID);

    public static final DeferredItem<Item> CHRONO_SHARD = regItem("chrono_shard", () -> new Item(new Item.Properties()));

    public static DeferredItem<Item> regItem(String name, Supplier<Item> sup) {
        return ITEMS.register(name, sup);
    }

    public static void init() {
        ITEMS.register(Nexus.MOD_EVENT_BUS);
    }
}