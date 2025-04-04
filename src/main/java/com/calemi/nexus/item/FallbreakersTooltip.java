package com.calemi.nexus.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;

public class FallbreakersTooltip {

    @SubscribeEvent
    public void onItemTooltip(ItemTooltipEvent event) {

        if (event.getItemStack().getItem() instanceof FallbreakersItem) {

            event.getToolTip().add(Component.translatable("hover_text.nexus.fallbreakers_1").withStyle(ChatFormatting.BLUE));
            event.getToolTip().add(Component.translatable("hover_text.nexus.fallbreakers_2").withStyle(ChatFormatting.BLUE));
        }
    }
}
