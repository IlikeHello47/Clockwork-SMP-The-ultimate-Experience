package com.at_smp.sound;

import com.at_smp.AT_SMP;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.JukeboxSong;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, AT_SMP.MOD_ID);

    private static Supplier<SoundEvent> registerSoundEvent(String name) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(AT_SMP.MOD_ID, name);
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(id));
    }
    public static final Supplier<SoundEvent> BE_NICE_2_ME = registerSoundEvent("be_nice_2_me");
    public static final ResourceKey<JukeboxSong> BE_NICE_2_ME_KEY = createSong("be_nice_2_me");
    public static final Supplier<SoundEvent> SAVE_EUROPE = registerSoundEvent("save_europe");
    public static final ResourceKey<JukeboxSong> SAVE_EUROPE_KEY = createSong("save_europe");
    public static final Supplier<SoundEvent> HOLY_WAR = registerSoundEvent("holy_war");
    public static final ResourceKey<JukeboxSong> HOLY_WAR_KEY = createSong("holy_war");
    public static final Supplier<SoundEvent> PENTAGRAMMA = registerSoundEvent("pentagramma");
    public static final ResourceKey<JukeboxSong> PENTAGRAMA_KEY = createSong("pentagramma");
    public static final Supplier<SoundEvent> STEREO_LOVE = registerSoundEvent("stereo_love");
    public static final ResourceKey<JukeboxSong>  STEREO_LOVE_KEY = createSong("stereo_love");
    public static final Supplier<SoundEvent> ANGEL_OF_DARKNESS = registerSoundEvent("angel_of_darkness");
    public static final ResourceKey<JukeboxSong> ANGEL_OF_DARKNESS_KEY = createSong("angel_of_darkness");
    public static final Supplier<SoundEvent> ET = registerSoundEvent("et");
    public static final ResourceKey<JukeboxSong> ET_KEY = createSong("et");
    public static final Supplier<SoundEvent> KERNKRAFT_400 = registerSoundEvent("kernkraft_400");
    public static final ResourceKey<JukeboxSong> KERNKRAFT_400_KEY = createSong("kernkraft_400");
    public static final Supplier<SoundEvent> ALL_I_EVER_WANTED = registerSoundEvent("all_i_ever_wanted");
    public static final ResourceKey<JukeboxSong> ALL_I_EVER_WANTED_KEY = createSong("all_i_ever_wanted");

    private static ResourceKey<JukeboxSong> createSong(String name) {
        return ResourceKey.create(Registries.JUKEBOX_SONG, ResourceLocation.fromNamespaceAndPath(AT_SMP.MOD_ID, name));
    }
    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}
