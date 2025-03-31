package com.calemi.nexus.compat;

import com.calemi.nexus.main.NexusRef;
import com.calemi.nexus.item.NexusItems;
import com.calemi.nexus.util.NexusLists;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

@JeiPlugin
public class NexusJEIPlugin implements IModPlugin {

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        registration.addIngredientInfo(new ItemStack(NexusItems.CHRONO_UPGRADE_SMITHING_TEMPLATE.get()), VanillaTypes.ITEM_STACK, Component.translatable("description.nexus.chrono_upgrade_smithing_template"));
        registration.addIngredientInfo(NexusLists.toItemStackListFromBlock(NexusLists.NEXUS_PORTAL_CORE_BLOCKS), VanillaTypes.ITEM_STACK, Component.translatable("description.nexus.nexus_portal_core"));
    }

    @Override
    public ResourceLocation getPluginUid() {
        return NexusRef.rl("jei_plugin");
    }
}
