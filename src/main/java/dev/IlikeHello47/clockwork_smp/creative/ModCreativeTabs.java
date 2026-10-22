package dev.IlikeHello47.clockwork_smp.creative;

import dev.IlikeHello47.clockwork_smp.Clockwork_SMP;
import dev.IlikeHello47.clockwork_smp.block.ModBlocks;
import dev.IlikeHello47.clockwork_smp.item.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, "clockwork_smp");

    public static final String TAB_TRANSLATION_KEY = "itemGroup." + Clockwork_SMP.MODID + "_tab";

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> CLOCKWORK_SMP_TAB = CREATIVE_TABS.register("clockwork_smp_tab",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable(TAB_TRANSLATION_KEY))
                    .icon(() -> new ItemStack(ModItems.CAT_NIP.get()))
                    .displayItems((parameters, output) -> {
                        output.accept(ModItems.CAT_NIP.get());
                        output.accept(ModBlocks.KICK_SOUND_BLOCK.get());
                        output.accept(ModBlocks.WOMP_SOUND_BLOCK.get());
                        output.accept(ModBlocks.SQUEAK_SQUEAK_GOES_THE_DOMINATOR_3.get());
                    })
                    .build()
    );

    public static void register(IEventBus eventBus) {
        CREATIVE_TABS.register(eventBus);
    }
}