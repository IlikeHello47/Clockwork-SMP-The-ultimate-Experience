package dev.IlikeHello47.clockwork_smp.effects;

import dev.IlikeHello47.clockwork_smp.Clockwork_SMP;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ComputeFovModifierEvent;
import net.neoforged.neoforge.registries.DeferredRegister;

@EventBusSubscriber(modid = Clockwork_SMP.MODID)
public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, Clockwork_SMP.MODID);

    public static final Holder<MobEffect> EXCITEMENT = MOB_EFFECTS.register("excitement",
            () -> new Excitement(MobEffectCategory.BENEFICIAL, 0xdeba18)
                    .addAttributeModifier(Attributes.MOVEMENT_SPEED, ResourceLocation.fromNamespaceAndPath(Clockwork_SMP.MODID, "excitement"), 0.75f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));

    @SubscribeEvent
    public static void onComputeFov(ComputeFovModifierEvent event) {
        Player player = event.getPlayer();

        if (player.hasEffect(EXCITEMENT)) {

            event.setNewFovModifier(1.0F);
        }
    }

    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}
