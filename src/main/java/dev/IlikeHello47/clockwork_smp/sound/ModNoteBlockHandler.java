package dev.IlikeHello47.clockwork_smp.sound;

import dev.IlikeHello47.clockwork_smp.Clockwork_SMP;
import dev.IlikeHello47.clockwork_smp.block.ModBlocks;
import net.minecraft.sounds.SoundSource;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.level.NoteBlockEvent;

@EventBusSubscriber(modid = Clockwork_SMP.MODID)
public class ModNoteBlockHandler {

    @SubscribeEvent
    public static void onNoteBlockPlay(NoteBlockEvent.Play event) {
        net.minecraft.world.level.block.state.BlockState blockDarunter =
                event.getLevel().getBlockState(event.getPos().below());

        if (blockDarunter.is(ModBlocks.KICK_SOUND_BLOCK.get())) {
            event.setCanceled(true);

            event.getLevel().playSound(null, event.getPos(),
                    ModSounds.KICK.get(), SoundSource.RECORDS, 3.0F, 1.0F);
        }

        if (blockDarunter.is(ModBlocks.WOMP_SOUND_BLOCK.get())) {
            event.setCanceled(true);

            event.getLevel().playSound(null, event.getPos(),
                    ModSounds.WOMP.get(), SoundSource.RECORDS, 3.0F, 1.0F);
        }
    }
}
