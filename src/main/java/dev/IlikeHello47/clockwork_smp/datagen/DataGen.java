package dev.IlikeHello47.clockwork_smp.datagen;

import dev.IlikeHello47.clockwork_smp.Clockwork_SMP;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = Clockwork_SMP.MODID)
public class DataGen {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        boolean includeClient = event.includeClient();

        generator.addProvider(includeClient,
                new ModBlockStateProvider(packOutput, existingFileHelper));

        generator.addProvider(includeClient,
                new ModItemModelProvider(packOutput, existingFileHelper));

        generator.addProvider(includeClient,
                new ModLanguageProvider(packOutput));

        boolean includeServer = event.includeServer();

        generator.addProvider(includeServer,
                new ModRecipeProvider(packOutput, lookupProvider));

        generator.addProvider(includeServer,
                ModLootTableProvider.create(packOutput, lookupProvider));

        ModBlockTagsProvider blockTagsProvider = generator.addProvider(includeServer,
                new ModBlockTagsProvider(packOutput, lookupProvider, existingFileHelper));

        generator.addProvider(includeServer,
                new ModItemTagsProvider(packOutput, lookupProvider, blockTagsProvider.contentsGetter(), existingFileHelper));
    }
}
