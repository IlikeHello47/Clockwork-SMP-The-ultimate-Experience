package com.ilikehello47.clockwork_smp.creative;

import com.ilikehello47.clockwork_smp.item.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, "deinemodid");

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> CLOCKWORK_SMP_TAB = CREATIVE_TABS.register("clockwork_smp_tab",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.clockwork_smp_tab"))
                    .icon(() -> new ItemStack(ModItems.SMARTPHONE.get()))
                    .displayItems((parameters, output) -> {
                    })
                    .build()
    );

    public static void register(IEventBus eventBus) {
        CREATIVE_TABS.register(eventBus);
    }
}