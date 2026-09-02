package com.ilikehello47.clockwork_smp.block;

import com.ilikehello47.clockwork_smp.AT_SMP;
import com.ilikehello47.clockwork_smp.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.*;
import com.ilikehello47.clockwork_smp.alipay.AlipayPhoneBlock;
import com.ilikehello47.clockwork_smp.alipay.AlipayTerminalBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Function;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(AT_SMP.MOD_ID);
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(AT_SMP.MOD_ID);

        public static final DeferredBlock<Block> ALIPAY_PAYMENT_DEVICE = BLOCKS.registerBlock("alipay_payment_device", (properties) -> new AlipayPhoneBlock(properties.strength(2f).sound(SoundType.WOOD)));
        public static final DeferredBlock<Block> ALIPAY_TERMINAL = BLOCKS.registerBlock("alipay_terminal", (properties) -> new AlipayTerminalBlock(properties.strength(3f).sound(SoundType.BAMBOO)));
    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Function<BlockBehaviour.Properties, T> function) {

        DeferredBlock<T> toReturn = BLOCKS.registerBlock(name, function);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.registerItem(name, (properties) -> new BlockItem(block.get(), properties.setNoRepair()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}

