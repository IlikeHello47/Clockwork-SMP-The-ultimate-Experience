package dev.IlikeHello47.clockwork_smp.food;

import dev.IlikeHello47.clockwork_smp.effects.ModEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;

public class ModFoods {
    public static final FoodProperties CAT_NIP = new FoodProperties.Builder()
            .nutrition(20)
            .saturationModifier(10.0f)
            .alwaysEdible()
            .effect(() -> new MobEffectInstance(ModEffects.EXCITEMENT, 600, 0), 1.0f)
            .build();
}
