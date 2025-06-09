package com.calemi.nexus.compat;

import com.calemi.nexus.block.NexusBlocks;
import com.calemi.nexus.config.NexusConfig;
import com.calemi.nexus.item.NexusItems;
import com.calemi.nexus.main.Nexus;
import com.calemi.nexus.main.NexusRef;
import com.calemi.nexus.util.NexusLists;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

@JeiPlugin
public class NexusJEIPlugin implements IModPlugin {

    @Override
    public void registerRecipes(IRecipeRegistration registration) {

        registration.addIngredientInfo(NexusItems.CHRONO_UPGRADE_SMITHING_TEMPLATE.get(),
                Component.translatable("description.nexus.chrono_upgrade_smithing_template"));

        registration.addIngredientInfo(NexusLists.toItemStackListFromBlock(NexusLists.NEXUS_PORTAL_CORE_BLOCKS),
                VanillaTypes.ITEM_STACK,
                Component.translatable("description.nexus.nexus_portal_core"));

        registration.addIngredientInfo(NexusBlocks.WARPSLATE_ACCELERITE_ORE.asItem(),
                Component.translatable("description.nexus.accelerite_ore"));

        double acceleriteOreFromMovingWarpslateChance = NexusConfig.server.acceleriteOreFromMovingWarpslateChance.get();

        if (acceleriteOreFromMovingWarpslateChance > 0) {

            registration.addIngredientInfo(List.of(new ItemStack(NexusBlocks.WARPSLATE_ACCELERITE_ORE.get()), new ItemStack(NexusBlocks.WARPSLATE.get())),
                    VanillaTypes.ITEM_STACK,
                    Component.translatable("description.nexus.accelerite_ore_from_warpslate", acceleriteOreFromMovingWarpslateChance * 100));
        }

        registration.addIngredientInfo(List.of(new ItemStack(NexusItems.DORMANT_ACCELERITE_INGOT.get()), new ItemStack(NexusItems.CHARGED_ACCELERITE_INGOT.get())),
                VanillaTypes.ITEM_STACK,
                Component.translatable("description.nexus.accelerite_ingot"));

        registration.addIngredientInfo(NexusLists.toItemStackListFromItem(NexusLists.ACCELERITE_TOOLS),
                VanillaTypes.ITEM_STACK,
                Component.translatable("description.nexus.accelerite_tools_acceleration"));

        registration.addIngredientInfo(NexusLists.toItemStackListFromItem(NexusLists.ACCELERITE_ARMOR),
                VanillaTypes.ITEM_STACK,
                Component.translatable("description.nexus.accelerite_armor_acceleration"));

        registration.addIngredientInfo(NexusLists.toItemStackListFromItem(NexusLists.ACCELERITE_EQUIPMENT),
                VanillaTypes.ITEM_STACK,
                Component.translatable("description.nexus.accelerite_equipment_repair"));

        registration.addIngredientInfo(List.of(new ItemStack(NexusBlocks.ROAD.get()), new ItemStack(NexusBlocks.ROAD_SLAB.get())),
                VanillaTypes.ITEM_STACK,
                Component.translatable("description.nexus.road"));

        int roadAcceleriteArmorEffectStackAdd = NexusConfig.server.roadAcceleriteArmorEffectStackAdd.get();

        Nexus.LOGGER.debug("CONFIGURATION " + roadAcceleriteArmorEffectStackAdd);

        if (roadAcceleriteArmorEffectStackAdd > 0) {

            List<ItemStack> roadArmor = new ArrayList<>();
            roadArmor.add(new ItemStack(NexusBlocks.ROAD.get()));
            roadArmor.add(new ItemStack(NexusBlocks.ROAD_SLAB.get()));
            roadArmor.addAll(NexusLists.toItemStackListFromItem(NexusLists.ACCELERITE_ARMOR));

            registration.addIngredientInfo(roadArmor,
                    VanillaTypes.ITEM_STACK,
                    Component.translatable("description.nexus.road_armor", roadAcceleriteArmorEffectStackAdd));
        }

        registration.addIngredientInfo(List.of(new ItemStack(NexusBlocks.JUMP_PAD.get())),
                VanillaTypes.ITEM_STACK,
                Component.translatable("description.nexus.jump_pad"));

        int roadUpgradeEffectStackPerBlock = NexusConfig.server.roadUpgradeEffectStackPerBlock.get();
        int jumpPadUpgradeEffectStackPerBlock = NexusConfig.server.jumpPadUpgradeEffectStackPerBlock.get();

        if (roadUpgradeEffectStackPerBlock > 0 || jumpPadUpgradeEffectStackPerBlock > 0) {

            registration.addIngredientInfo(List.of(new ItemStack(NexusBlocks.ROAD.get()), new ItemStack(NexusBlocks.ROAD_SLAB.get()), new ItemStack(NexusBlocks.JUMP_PAD.get()), new ItemStack(NexusBlocks.CHARGED_ACCELERITE_BLOCK.get())),
                    VanillaTypes.ITEM_STACK,
                    Component.translatable("description.nexus.road_upgrade", roadUpgradeEffectStackPerBlock, jumpPadUpgradeEffectStackPerBlock));
        }
    }

    @Override
    public ResourceLocation getPluginUid() {
        return NexusRef.rl("jei_plugin");
    }
}
