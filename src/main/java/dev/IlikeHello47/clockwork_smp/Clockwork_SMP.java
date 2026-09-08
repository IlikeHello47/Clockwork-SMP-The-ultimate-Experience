package dev.IlikeHello47.clockwork_smp;

import dev.IlikeHello47.clockwork_smp.block.ModBlocks;
import dev.IlikeHello47.clockwork_smp.create.CreateIntegration;
import dev.IlikeHello47.clockwork_smp.creative.ModCreativeTabs;
import dev.IlikeHello47.clockwork_smp.datagen.DataGen;
import dev.IlikeHello47.clockwork_smp.item.ModItems;
import dev.IlikeHello47.clockwork_smp.radio.RadioRegistration;
import dev.IlikeHello47.clockwork_smp.sound.ModSounds;
import org.slf4j.Logger;
import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

@Mod(Clockwork_SMP.MOD_ID)
public class Clockwork_SMP {
    public static final String MOD_ID = "clockwork_smp";
    public static final Logger LOGGER = LogUtils.getLogger();

    public Clockwork_SMP(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(DataGen::gatherData);
        NeoForge.EVENT_BUS.register(this);
        NeoForge.EVENT_BUS.register(new CreateIntegration());
        ModCreativeTabs.register(modEventBus);
        ModBlocks.BLOCKS.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);
        ModSounds.SOUND_EVENTS.register(modEventBus);
        RadioRegistration.register(modEventBus);
    }


    private void commonSetup(FMLCommonSetupEvent event) {
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        LOGGER.info(MOD_ID.toUpperCase() + ": " + Clockwork_SMP.class.getSimpleName() + " IS STARTING!" );
    }
}

