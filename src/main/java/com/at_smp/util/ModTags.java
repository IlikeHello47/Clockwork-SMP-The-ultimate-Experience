package com.at_smp.util;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tier;
import net.neoforged.neoforge.common.SimpleTier;

public class ModTags{
    public class Items {
        public static final TagKey<Item> METEORITE_REPAIRABLE = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("at_smp", "meteorite_ingot"));
        public static final Tier METEORITE = new SimpleTier(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 3452, 9.0F, 0.0F, 15, () -> net.minecraft.world.item.crafting.Ingredient.of(METEORITE_REPAIRABLE)
        );
    }
}