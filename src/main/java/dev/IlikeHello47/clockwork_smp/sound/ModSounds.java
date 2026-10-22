package dev.IlikeHello47.clockwork_smp.sound;

import dev.IlikeHello47.clockwork_smp.Clockwork_SMP;
import net.minecraft.client.resources.sounds.Sound;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModSounds {

    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, Clockwork_SMP.MODID);

    public static final Supplier<SoundEvent> KICK = registerSoundEvent("kick");
    public static final Supplier<SoundEvent> WOMP = registerSoundEvent("womp");
    public static final Supplier<SoundEvent> SQUEAK_SQUEAK_GOES_THE_DOMINATOR_3 = registerSoundEvent("squeak_squeak_goes_the_dominator_3");

    private static Supplier<SoundEvent> registerSoundEvent(String name) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(Clockwork_SMP.MODID, name);
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(id));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}
