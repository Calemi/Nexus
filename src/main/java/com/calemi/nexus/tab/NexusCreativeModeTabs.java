package com.calemi.nexus.tab;

import com.calemi.ccore.api.list.ListHelper;
import com.calemi.nexus.util.NexusLists;
import com.calemi.nexus.main.Nexus;
import com.calemi.nexus.main.NexusRef;
import com.calemi.nexus.item.NexusItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class NexusCreativeModeTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, NexusRef.ID);

    public static final Supplier<CreativeModeTab> MAIN_TAB = CREATIVE_MODE_TABS.register("nexus_main", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup." + NexusRef.ID + ".main"))
            .icon(() -> new ItemStack(NexusItems.CHRONO_SHARD.get()))
            .displayItems((params, output) -> output.acceptAll(ListHelper.toItemStackListFromItemLike(NexusLists.TAB_NEXUS_MAIN_ITEMS)))
            .build()
    );

    public static void init() {
        Nexus.LOGGER.info("Registering: Creative Mode Tabs - Start");
        CREATIVE_MODE_TABS.register(Nexus.MOD_EVENT_BUS);
        Nexus.LOGGER.info("Registering: Creative Mode Tabs - End");
    }
}
