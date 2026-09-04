package com.ilikehello47.clockwork_smp.util;

import com.ilikehello47.clockwork_smp.Clockwork_SMP;
import com.ilikehello47.clockwork_smp.alipay.AlipayTerminalBlock;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, Clockwork_SMP.MOD_ID);

    // Registriert den Datenspeicher für das Alipay-Terminal
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<AlipayTerminalBlock.TerminalEntity>> ALIPAY_TERMINAL_BE =
            BLOCK_ENTITIES.register("alipay_terminal_be", () ->
                    BlockEntityType.Builder.of(AlipayTerminalBlock.TerminalEntity::new, AlipayTerminalBlock.ALIPAY_TERMINAL.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}