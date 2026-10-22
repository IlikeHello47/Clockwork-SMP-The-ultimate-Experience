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
                add(ModBlocks.KICK_SOUND_BLOCK.get(), "Kick Sound Block");
                add(ModBlocks.WOMP_SOUND_BLOCK.get(), "WOMP Sound Block");
                add(ModBlocks.SQUEAK_SQUEAK_GOES_THE_DOMINATOR_3.get(), "SOMEBODY STOLE THE DOMINATOR!!");
                add(ModItems.CAT_NIP.get(), "KITTYMINT!!!");
                add("itemGroup.clockwork_smp_tab", "Clockwork SMP Tab");
            }
        }.run(cache);
        CompletableFuture<?> deFuture = new LanguageProvider(output, Clockwork_SMP.MODID, "de_de") {
            @Override
            protected void addTranslations() {
                add(ModBlocks.KICK_SOUND_BLOCK.get(), "Kick Ton Block");
                add(ModBlocks.WOMP_SOUND_BLOCK.get(), "WOMP Ton Block");
                add(ModBlocks.SQUEAK_SQUEAK_GOES_THE_DOMINATOR_3.get(), "JEMAND HAT DEN DOMINATOR GEKLAUT!!");
                add(ModItems.CAT_NIP.get(), "KITTYMINZE!!!");
                add("itemGroup.clockwork_smp_tab", "Clockwork SMP Tab");
            }
        }.run(cache);
        CompletableFuture<?> zhFuture = new LanguageProvider(output, Clockwork_SMP.MODID, "zh_cn") {
            @Override
            protected void addTranslations() {
                add(ModBlocks.KICK_SOUND_BLOCK.get(), "Kick 声音 堵塞");
                add(ModBlocks.WOMP_SOUND_BLOCK.get(), "WOMP 声音 堵塞");
                add(ModBlocks.SQUEAK_SQUEAK_GOES_THE_DOMINATOR_3.get(), "有人偷走了 DOMINATOR!!");
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
