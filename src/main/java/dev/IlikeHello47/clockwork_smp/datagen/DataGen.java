package dev.IlikeHello47.clockwork_smp.datagen;

import dev.IlikeHello47.clockwork_smp.Clockwork_SMP;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;

public class DataGen {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        generator.addProvider(event.includeClient(), new ModLanguageProvider(output, Clockwork_SMP.MODID, "en_us"));
        generator.addProvider(event.includeClient(), new ModLanguageProvider(output, Clockwork_SMP.MODID, "de_de"));
        generator.addProvider(event.includeClient(), new ModLanguageProvider(output, Clockwork_SMP.MODID, "zh_cn"));
        generator.addProvider(event.includeClient(), new ModBlockStateProvider(output, Clockwork_SMP.MODID, existingFileHelper));
        generator.addProvider(event.includeClient(), new ModItemModelProvider(output, Clockwork_SMP.MODID, existingFileHelper));
    }

    private static class ModLanguageProvider extends LanguageProvider {
        private ModLanguageProvider(PackOutput output, String modid, String locale) {
            super(output, modid, locale);
        }


        @Override
        protected void addTranslations() {
        }
    }

    private static class ModBlockStateProvider extends BlockStateProvider {
        private ModBlockStateProvider(PackOutput output, String modid, ExistingFileHelper existingFileHelper) {
            super(output, modid, existingFileHelper);
        }

        @Override
        protected void registerStatesAndModels() {
        }
    }

    private static class ModItemModelProvider extends ItemModelProvider {
        private ModItemModelProvider(PackOutput output, String modid, ExistingFileHelper existingFileHelper) {
            super(output, modid, existingFileHelper);
        }

        @Override
        protected void registerModels() {
        }
    }
}
