package dev.IlikeHello47.clockwork_smp.radio;

import dev.IlikeHello47.clockwork_smp.Clockwork_SMP;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class RadioRegistration {
    public static final String MOD_ID = Clockwork_SMP.MOD_ID;

    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(BuiltInRegistries.BLOCK, MOD_ID);
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(BuiltInRegistries.ITEM, MOD_ID);
    private static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, MOD_ID);

    public static final DeferredHolder<Block, RadioBlock> RADIO_BLOCK = BLOCKS.register("radio",
            () -> new RadioBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.JUKEBOX).noOcclusion()));

    public static final DeferredHolder<Item, BlockItem> RADIO_ITEM = ITEMS.register("radio",
            () -> new BlockItem(RADIO_BLOCK.get(), new Item.Properties()));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<RadioBlockEntity>> RADIO_BLOCK_ENTITY = BLOCK_ENTITIES.register("radio",
            () -> BlockEntityType.Builder.of(RadioBlockEntity::new, RADIO_BLOCK.get()).build(null));

    public static void register(IEventBus modEventBus) {
        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        BLOCK_ENTITIES.register(modEventBus);
    }
}