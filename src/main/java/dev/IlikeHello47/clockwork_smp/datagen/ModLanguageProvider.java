package dev.IlikeHello47.clockwork_smp.datagen;

import dev.IlikeHello47.clockwork_smp.Clockwork_SMP;
import dev.IlikeHello47.clockwork_smp.block.ModBlocks;
import dev.IlikeHello47.clockwork_smp.item.ModItems;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

import java.util.concurrent.CompletableFuture;

public class ModLanguageProvider implements DataProvider {
    private final PackOutput output;

    public ModLanguageProvider(PackOutput output) {
        this.output = output;
    }

    @Override
    public CompletableFuture<?> run(CachedOutput cache) {
        CompletableFuture<?> enFuture = new LanguageProvider(output, Clockwork_SMP.MODID, "en_us") {
            @Override
            protected void addTranslations() {
                add(ModBlocks.CHUNK_LOADER.get(), "Chunk Loader");
                add(ModItems.CAT_NIP.get(), "KITTYMINT!!!");
                add("itemGroup.clockwork_smp_tab", "Clockwork SMP Tab");
            }
        }.run(cache);
        CompletableFuture<?> deFuture = new LanguageProvider(output, Clockwork_SMP.MODID, "de_de") {
            @Override
            protected void addTranslations() {
                add(ModBlocks.CHUNK_LOADER.get(), "Chunk Lader");
                add(ModItems.CAT_NIP.get(), "KITTYMINZE!!!");
                add("itemGroup.clockwork_smp_tab", "Clockwork SMP Tab");
            }
        }.run(cache);
        CompletableFuture<?> zhFuture = new LanguageProvider(output, Clockwork_SMP.MODID, "zh_cn") {
            @Override
            protected void addTranslations() {
                add(ModBlocks.CHUNK_LOADER.get(), "区块加载器");
                add(ModItems.CAT_NIP.get(), "猫薄荷!!!");
                add("itemGroup.clockwork_smp_tab", "Clockwork SMP");
            }
        }.run(cache);
        return CompletableFuture.allOf(enFuture, deFuture, zhFuture);
    }
    @Override
    public String getName() {
        return "Languages: " + Clockwork_SMP.MODID;
    }
}
