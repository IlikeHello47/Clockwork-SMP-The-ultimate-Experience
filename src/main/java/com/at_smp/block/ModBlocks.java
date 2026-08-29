package com.at_smp.block;

import com.at_smp.AT_SMP;
import com.at_smp.item.ModItems;
import net.minecraft.core.component.DataComponents;
import net.minecraft.util.Unit;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(AT_SMP.MOD_ID);
    public static final DeferredBlock<Block> METEORITE_ORE = registerBlock("meteorite_ore", () -> new Block(BlockBehaviour.Properties.of().strength(25.0f, 1200.0f).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> METEOR_STONE = registerBlock("meteor_stone", () -> new Block(BlockBehaviour.Properties.of().strength(20.0f, 1200.0f).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> METEORITE_BLOCK = registerBlock("meteorite_block", () -> new Block(BlockBehaviour.Properties.of().strength(30.0f, 1200.0f).requiresCorrectToolForDrops()), new Item.Properties().component(DataComponents.FIRE_RESISTANT, Unit.INSTANCE));
    public static final DeferredBlock<StairBlock> METEORITE_STAIRS = registerBlock("meteorite_stairs", () -> new StairBlock(ModBlocks.METEORITE_BLOCK.get().defaultBlockState(), BlockBehaviour.Properties.of().strength(30.0f, 1200.0f).requiresCorrectToolForDrops()), new Item.Properties().component(DataComponents.FIRE_RESISTANT, Unit.INSTANCE));
    public static final DeferredBlock<SlabBlock> METEORITE_SLAB = registerBlock("meteorite_slab", () -> new SlabBlock(BlockBehaviour.Properties.of().strength(30.0f, 1200.0f).requiresCorrectToolForDrops()), new Item.Properties().component(DataComponents.FIRE_RESISTANT, Unit.INSTANCE));

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }
    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }
    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block, Item.Properties itemProperties) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, itemProperties);
        return toReturn;
    }
    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block, Item.Properties itemProperties) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), itemProperties));
    }
}
