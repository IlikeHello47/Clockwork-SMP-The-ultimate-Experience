package com.ilikehello47.clockwork_smp.datagen;

import com.ilikehello47.clockwork_smp.Clockwork_SMP;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;

import java.util.concurrent.CompletableFuture;

public class ModGlobalLootModifierProvider extends GlobalLootModifierProvider {
    public ModGlobalLootModifierProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, Clockwork_SMP.MOD_ID);
    }

    @Override
    protected void start() {
    }
}
