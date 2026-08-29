package com.at_smp.item;

import com.at_smp.AT_SMP;
import com.at_smp.block.ModBlocks;
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
            .icon(() -> new ItemStack(ModItems.METEORITE_INGOT.get()))
            .title(Component.translatable("creativetab.at_smp_content_tab.tab"))
            .displayItems(((itemDisplayParameters, output) -> {
                output.accept(ModBlocks.METEOR_STONE.get());
                output.accept(ModBlocks.METEORITE_ORE.get());
                output.accept(ModBlocks.METEORITE_BLOCK.get());
                output.accept(ModBlocks.METEORITE_SLAB.get());
                output.accept(ModBlocks.METEORITE_STAIRS.get());

                output.accept(ModItems.METEORITE_SCRAP.get());
                output.accept(ModItems.MOLTEN_METEORITE.get());
                output.accept(ModItems.METEORITE_INGOT.get());
                output.accept(ModItems.METEORITE_UPGRADE_SMITHING_TEMPLATE);
                output.accept(ModItems.METEORITE_SWORD.get());
                output.accept(ModItems.METEORITE_SHOVEL.get());
                output.accept(ModItems.METEORITE_PICKAXE.get());
                output.accept(ModItems.METEORITE_AXE.get());
                output.accept(ModItems.METEORITE_HOE.get());
                // output.accept(ModItems.METEORITE_BEDROCK_PICKAXE.get());
                output.accept(ModItems.METEORITE_HELMET.get());
                output.accept(ModItems.METEORITE_CHESTPLATE.get());
                output.accept(ModItems.METEORITE_LEGGINGS.get());
                output.accept(ModItems.METEORITE_BOOTS.get());
            })).build());
    public static final DeferredRegister<CreativeModeTab> MUSIC_CREATIVE_MODE_TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, AT_SMP.MOD_ID);
    public static final Supplier<CreativeModeTab> AT_SMP_MUSIC_TAB = MUSIC_CREATIVE_MODE_TAB.register("at_smp_music_tab", () -> CreativeModeTab
            .builder()
            .icon(() -> new ItemStack(ModItems.MUSIC_DISC_TEMPLATE.get()))
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
            }))
            .build());
    public static void register(IEventBus eventBus) {
        CONTENT_CREATIVE_MODE_TAB.register(eventBus);
        MUSIC_CREATIVE_MODE_TAB.register(eventBus);
    }
}