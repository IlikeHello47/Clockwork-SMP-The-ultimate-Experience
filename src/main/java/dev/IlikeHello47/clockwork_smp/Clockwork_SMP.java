package dev.IlikeHello47.clockwork_smp;

import dev.IlikeHello47.clockwork_smp.block.ModBlocks;
import dev.IlikeHello47.clockwork_smp.creative.ModCreativeTabs;
import dev.IlikeHello47.clockwork_smp.effects.ModEffects;
import dev.IlikeHello47.clockwork_smp.item.ModItems;
import dev.IlikeHello47.clockwork_smp.laser.LaserBeamRenderer;
import dev.IlikeHello47.clockwork_smp.sound.ModSounds;
import com.mojang.logging.LogUtils;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import org.slf4j.Logger;

@Mod(Clockwork_SMP.MODID)
public class Clockwork_SMP {
    public static final String MODID = "clockwork_smp";
    public static final Logger LOGGER = LogUtils.getLogger();

    public Clockwork_SMP(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::clientSetup);
        NeoForge.EVENT_BUS.register(this);
        ModCreativeTabs.register(modEventBus);
        ModBlocks.BLOCKS.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);
        ModSounds.SOUND_EVENTS.register(modEventBus);
        ModEffects.MOB_EFFECTS.register(modEventBus);
        if (FMLEnvironment.dist == Dist.CLIENT) {
            NeoForge.EVENT_BUS.register(LaserBeamRenderer.class);
            modEventBus.addListener(Clockwork_SMPClientRegistrate::onRegisterKeyMappings);
        }
    }

    private void commonSetup(FMLCommonSetupEvent event) {
    }

    private void clientSetup(FMLClientSetupEvent event) {
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        LOGGER.info(MODID.toUpperCase() + ": " + Clockwork_SMP.class.getSimpleName().toUpperCase() + " IS STARTING!");
    }
}
