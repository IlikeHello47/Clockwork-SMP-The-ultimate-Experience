package dev.IlikeHello47.clockwork_smp.item;

import dev.IlikeHello47.clockwork_smp.Clockwork_SMP;
import net.minecraft.world.item.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Clockwork_SMP.MOD_ID);

    public static final DeferredItem<Item> SMARTPHONE = ITEMS.register("smartphone",
            () -> new Item(new Item.Properties().stacksTo(1)));

    public static void register(IEventBus modEventBus) {
        ITEMS.register(modEventBus);
    }
}
