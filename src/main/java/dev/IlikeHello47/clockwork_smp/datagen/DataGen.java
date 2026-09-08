package dev.IlikeHello47.clockwork_smp.datagen;

import dev.IlikeHello47.clockwork_smp.Clockwork_SMP;
import dev.IlikeHello47.clockwork_smp.item.ModItems;
import dev.IlikeHello47.clockwork_smp.radio.RadioRegistration;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;

public class DataGen {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        generator.addProvider(event.includeClient(), new ModLanguageProvider(output, Clockwork_SMP.MOD_ID, "en_us", "Smartphone", "Radio", "Clockwork SMP", "Settings", "Save"));
        generator.addProvider(event.includeClient(), new ModLanguageProvider(output, Clockwork_SMP.MOD_ID, "de_de", "Smartphone", "Radio", "Clockwork SMP", "Einstellungen", "Speichern"));
        generator.addProvider(event.includeClient(), new ModLanguageProvider(output, Clockwork_SMP.MOD_ID, "zh_cn", "智能手机", "收音机", "Clockwork SMP", "设置", "保存"));
        generator.addProvider(event.includeClient(), new ModBlockStateProvider(output, Clockwork_SMP.MOD_ID, existingFileHelper));
        generator.addProvider(event.includeClient(), new ModItemModelProvider(output, Clockwork_SMP.MOD_ID, existingFileHelper));
    }

    private static class ModLanguageProvider extends LanguageProvider {
        private ModLanguageProvider(PackOutput output, String modid, String locale, String smartphoneName, String radioName, String tabName, String settingsName, String saveName) {
            super(output, modid, locale);
            this.smartphoneName = smartphoneName;
            this.radioName = radioName;
            this.tabName = tabName;
            this.settingsName = settingsName;
            this.saveName = saveName;
        }

        private final String smartphoneName;
        private final String radioName;
        private final String tabName;
        private final String settingsName;
        private final String saveName;

        @Override
        protected void addTranslations() {
            addItem(ModItems.SMARTPHONE, smartphoneName);
            add(RadioRegistration.RADIO_ITEM.get(), radioName);
            add("itemGroup.clockwork_smp_tab", tabName);
            add("radio.clockwork_smp.settings", settingsName);
            add("radio.clockwork_smp.save", saveName);
        }
    }

    private static class ModBlockStateProvider extends BlockStateProvider {
        private ModBlockStateProvider(PackOutput output, String modid, ExistingFileHelper existingFileHelper) {
            super(output, modid, existingFileHelper);
        }

        @Override
        protected void registerStatesAndModels() {
            ModelFile radioModel = models().cubeAll("radio", modLoc("block/radio_main"));
            simpleBlock(RadioRegistration.RADIO_BLOCK.get(), radioModel);
        }
    }

    private static class ModItemModelProvider extends ItemModelProvider {
        private ModItemModelProvider(PackOutput output, String modid, ExistingFileHelper existingFileHelper) {
            super(output, modid, existingFileHelper);
        }

        @Override
        protected void registerModels() {
            basicItem(ModItems.SMARTPHONE.get());
            withExistingParent(RadioRegistration.RADIO_ITEM.getId().getPath(), modLoc("block/radio"));
        }
    }
}
