package dev.IlikeHello47.clockwork_smp.item;

import dev.IlikeHello47.clockwork_smp.Clockwork_SMP;
import dev.IlikeHello47.clockwork_smp.food.ModFoods;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Clockwork_SMP.MODID);

    public static final DeferredItem<Item> CAT_NIP = ITEMS.registerItem("cat_nip",
            properties -> new Item(properties.food(ModFoods.CAT_NIP)));

    public static void register(IEventBus modEventBus) {
        ITEMS.register(modEventBus);
    }
}
