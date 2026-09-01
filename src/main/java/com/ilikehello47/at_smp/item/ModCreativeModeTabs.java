package com.ilikehello47.at_smp.item;

import com.ilikehello47.at_smp.AT_SMP;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CONTENT_CREATIVE_MODE_TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, AT_SMP.MOD_ID);
    public static final Supplier<CreativeModeTab> AT_SMP_CONTENT_TAB = CONTENT_CREATIVE_MODE_TAB.register("at_smp_content_tab", () -> CreativeModeTab
            .builder()
            .icon(() -> new ItemStack(ModItems.MUSIC_DISC_TEMPLATE.get()))
            .title(Component.translatable("creativetab.at_smp_content_tab.tab"))
            .displayItems(((itemDisplayParameters, output) -> {
            })).build());
    public static final DeferredRegister<CreativeModeTab> MUSIC_CREATIVE_MODE_TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, AT_SMP.MOD_ID);
    public static final Supplier<CreativeModeTab> AT_SMP_MUSIC_TAB = MUSIC_CREATIVE_MODE_TAB.register("at_smp_music_tab", () -> CreativeModeTab
            .builder()
            .icon(() -> new ItemStack(ModItems.STEREO_LOVE_MUSIC_DISC.get()))
            .title(Component.translatable("creativetab.at_smp_music_tab.tab"))
            .displayItems(((itemDisplayParameters, output) -> {
                output.accept(ModItems.MUSIC_DISC_TEMPLATE.get());
                output.accept(ModItems.BE_NICE_2_ME_MUSIC_DISC.get());
                output.accept(ModItems.SAVE_EUROPE_MUSIC_DISC.get());
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