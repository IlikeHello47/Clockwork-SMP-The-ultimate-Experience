package com.ilikehello47.clockwork_smp.item;

import com.ilikehello47.clockwork_smp.Clockwork_SMP;
import com.ilikehello47.clockwork_smp.alipay.AlipayTerminalBlock;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CONTENT_CREATIVE_MODE_TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Clockwork_SMP.MOD_ID);
    public static final Supplier<CreativeModeTab> clockwork_smp_CONTENT_TAB = CONTENT_CREATIVE_MODE_TAB.register("clockwork_smp_content_tab", () -> CreativeModeTab
            .builder()
            .icon(() -> new ItemStack(AlipayTerminalBlock.ALIPAY_TERMINAL.get()))
            .title(Component.translatable("creativetab.clockwork_smp_content_tab.tab"))
            .displayItems(((itemDisplayParameters, output) -> {
                output.accept(AlipayTerminalBlock.ALIPAY_TERMINAL.get());
            })).build());
    public static final DeferredRegister<CreativeModeTab> MUSIC_CREATIVE_MODE_TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Clockwork_SMP.MOD_ID);
    public static final Supplier<CreativeModeTab> clockwork_smp_MUSIC_TAB = MUSIC_CREATIVE_MODE_TAB.register("clockwork_smp_music_tab", () -> CreativeModeTab
            .builder()
            .icon(() -> new ItemStack(ModItems.STEREO_LOVE_MUSIC_DISC.get()))
            .title(Component.translatable("creativetab.clockwork_smp_music_tab.tab"))
            .displayItems(((itemDisplayParameters, output) -> {
                output.accept(ModItems.MUSIC_DISC_TEMPLATE.get());
                output.accept(ModItems.BE_NICE_2_ME_MUSIC_DISC.get());
                output.accept(ModItems.HOLY_WAR_MUSIC_DISC.get());
                output.accept(ModItems.PENTAGRAMMA_MUSIC_DISC.get());
                output.accept(ModItems.STEREO_LOVE_MUSIC_DISC.get());
                output.accept(ModItems.ANGEL_OF_DARKNESS_MUSIC_DISC.get());
                output.accept(ModItems.ET_MUSIC_DISC.get());
                output.accept(ModItems.KERNKRAFT_400_MUSIC_DISC.get());
                output.accept(ModItems.ALL_I_EVER_WANTED_MUSIC_DISC.get());
                output.accept(ModItems.DRUGS_MUSIC_DISC.get());
            }))
            .build());
    public static void register(IEventBus eventBus) {
        CONTENT_CREATIVE_MODE_TAB.register(eventBus);
        MUSIC_CREATIVE_MODE_TAB.register(eventBus);
    }
}
