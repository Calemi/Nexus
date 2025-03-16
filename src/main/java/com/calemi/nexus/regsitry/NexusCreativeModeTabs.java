package com.calemi.nexus.regsitry;

import com.calemi.nexus.main.Nexus;
import com.calemi.nexus.main.NexusRef;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class NexusCreativeModeTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, NexusRef.MOD_ID);

    public static final Supplier<CreativeModeTab> MAIN_TAB = CREATIVE_MODE_TABS.register("nexus_main", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup." + NexusRef.MOD_ID + ".main"))
            .icon(() -> new ItemStack(NexusBlocks.NEXUS_PORTAL_CORE.get()))
            .displayItems((params, output) -> {
                output.accept(NexusBlocks.NEXUS_PORTAL_CORE.get());
                output.accept(NexusBlocks.IRON_NEXUS_PORTAL_CORE.get());
                output.accept(NexusBlocks.GOLD_NEXUS_PORTAL_CORE.get());
                output.accept(NexusBlocks.DIAMOND_NEXUS_PORTAL_CORE.get());
                output.accept(NexusBlocks.NETHERITE_NEXUS_PORTAL_CORE.get());
                output.accept(NexusBlocks.STARLIGHT_NEXUS_PORTAL_CORE.get());
                output.accept(NexusItems.CHRONO_SHARD.get());
            })
            .build()
    );

    public static void init() {
        CREATIVE_MODE_TABS.register(Nexus.MOD_EVENT_BUS);
    }
}
