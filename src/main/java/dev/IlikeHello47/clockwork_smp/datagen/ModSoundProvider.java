package dev.IlikeHello47.clockwork_smp.datagen;

import dev.IlikeHello47.clockwork_smp.Clockwork_SMP;
import dev.IlikeHello47.clockwork_smp.sound.ModSounds;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.SoundDefinitionsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModSoundProvider extends SoundDefinitionsProvider {
    public ModSoundProvider(PackOutput output, ExistingFileHelper helper) {
        super(output, Clockwork_SMP.MODID, helper);
    }

    @Override
    public void registerSounds() {
        add(ModSounds.KICK.get(), definition()
                .subtitle("subtitles.clockwork_smp.kick")
                .with(sound(ModSounds.KICK.get().getLocation())));

        add(ModSounds.WOMP.get(), definition()
                .subtitle("subtitles.clockwork_smp.womp")
                .with(sound(ModSounds.WOMP.get().getLocation())));

        add(ModSounds.SQUEAK_SQUEAK_GOES_THE_DOMINATOR_3.get(), definition()
                .subtitle("subtitles.clockwork_smp.squeak_squeak_goes_the_dominator_3")
                .with(sound(ModSounds.SQUEAK_SQUEAK_GOES_THE_DOMINATOR_3.get().getLocation())));
    }
}
