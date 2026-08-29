package com.at_smp.item;

import com.at_smp.util.ModTags;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.EnumMap;
import java.util.List;

public class ModArmorMaterials {
    public static final DeferredRegister<ArmorMaterial> ARMOR_MATERIALS =
            DeferredRegister.create(Registries.ARMOR_MATERIAL, "at_smp");
    public static final Holder<ArmorMaterial> METEORITE = ARMOR_MATERIALS.register("meteorite", () -> {
        EnumMap<ArmorItem.Type, Integer> defensePoints = Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
            map.put(ArmorItem.Type.BOOTS, 3);
            map.put(ArmorItem.Type.LEGGINGS, 6);
            map.put(ArmorItem.Type.CHESTPLATE, 8);
            map.put(ArmorItem.Type.HELMET, 3);
        });
        return new ArmorMaterial(
                defensePoints,
                15,
                SoundEvents.ARMOR_EQUIP_NETHERITE,
                () -> Ingredient.of(ModTags.Items.METEORITE_REPAIRABLE),
                List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("at_smp", "meteorite"))), 3.0F, 0.1F
        );
    });
}