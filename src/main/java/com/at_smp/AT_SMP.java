package com.at_smp;

import com.at_smp.block.ModBlocks;
import com.at_smp.item.ModArmorMaterials;
import com.at_smp.item.ModCreativeModeTabs;
import com.at_smp.item.ModItems;
import com.at_smp.sound.ModSounds;
import org.slf4j.Logger;
import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

@Mod(AT_SMP.MOD_ID)
public class AT_SMP {
    public static final String MOD_ID = "at_smp";
    public static final Logger LOGGER = LogUtils.getLogger();

    public AT_SMP(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);
        NeoForge.EVENT_BUS.register(this);
        ModArmorMaterials.ARMOR_MATERIALS.register(modEventBus);
        ModCreativeModeTabs.register(modEventBus);
        ModBlocks.BLOCKS.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);
        ModSounds.SOUND_EVENTS.register(modEventBus);
    }
    private void commonSetup(FMLCommonSetupEvent event) {
    }
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        LOGGER.info(MOD_ID.toUpperCase() + ": " + AT_SMP.class.getSimpleName() + " IS STARTING!" );
    }
}
