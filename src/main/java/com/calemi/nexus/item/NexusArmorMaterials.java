package com.calemi.nexus.item;

import com.calemi.nexus.main.Nexus;
import com.calemi.nexus.main.NexusRef;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.EnumMap;
import java.util.List;

public class NexusArmorMaterials {

    public static final DeferredRegister<ArmorMaterial> ARMOR_MATERIALS = DeferredRegister.create(Registries.ARMOR_MATERIAL, NexusRef.ID);

    public static final Holder<ArmorMaterial> FALLBREAKERS = ARMOR_MATERIALS.register("fallbreakers", () -> new ArmorMaterial(

            Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                map.put(ArmorItem.Type.BOOTS, 0);
                map.put(ArmorItem.Type.LEGGINGS, 0);
                map.put(ArmorItem.Type.CHESTPLATE, 0);
                map.put(ArmorItem.Type.HELMET, 0);
                map.put(ArmorItem.Type.BODY, 0);
            }),
            10,
            SoundEvents.ARMOR_EQUIP_WOLF,
            () -> Ingredient.EMPTY,
            List.of(
                    new ArmorMaterial.Layer(
                            NexusRef.rl("fallbreakers")
                    )
            ),
            0.0F,
            0.0F
    ));

    public static final Holder<ArmorMaterial> ACCELERITE = ARMOR_MATERIALS.register("accelerite", () -> new ArmorMaterial(

            Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                map.put(ArmorItem.Type.BOOTS, 2);
                map.put(ArmorItem.Type.LEGGINGS, 5);
                map.put(ArmorItem.Type.CHESTPLATE, 6);
                map.put(ArmorItem.Type.HELMET, 2);
                map.put(ArmorItem.Type.BODY, 5);
            }),
            9,
            SoundEvents.ARMOR_EQUIP_IRON,
            () -> Ingredient.of(NexusItems.CHARGED_ACCELERITE_INGOT),
            List.of(
                    new ArmorMaterial.Layer(
                            NexusRef.rl("accelerite")
                    )
            ),
            0.0F,
            0.0F
    ));

    public static void init() {
        Nexus.LOGGER.info("Registering: Armor Materials - Start");
        ARMOR_MATERIALS.register(Nexus.MOD_EVENT_BUS);
        Nexus.LOGGER.info("Registering: Armor Materials - End");
    }
}
