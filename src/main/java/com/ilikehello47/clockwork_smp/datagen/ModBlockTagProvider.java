package com.ilikehello47.clockwork_smp.datagen;

import com.ilikehello47.clockwork_smp.AT_SMP;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.BlockTagsProvider;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, AT_SMP.MOD_ID, null);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
    }
}
