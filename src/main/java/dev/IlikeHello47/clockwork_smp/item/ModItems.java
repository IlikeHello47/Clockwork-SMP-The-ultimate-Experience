package dev.IlikeHello47.clockwork_smp.item;

import dev.IlikeHello47.clockwork_smp.Clockwork_SMP;
import dev.IlikeHello47.clockwork_smp.food.ModFoods;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.loading.FMLEnvironment;
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
