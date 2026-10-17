package dev.IlikeHello47.clockwork_smp.datagen;

import dev.IlikeHello47.clockwork_smp.Clockwork_SMP;
import dev.IlikeHello47.clockwork_smp.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.concurrent.CompletableFuture;

public class ModBlockTagsProvider extends BlockTagsProvider {

    public ModBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable net.neoforged.neoforge.common.data.ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, Clockwork_SMP.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.CHUNK_LOADER.get());

        this.tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.CHUNK_LOADER.get());

        this.tag(BlockTags.create(net.minecraft.resources.ResourceLocation.fromNamespaceAndPath("minecraft", "noteblock_custom_sound")))
                .add(ModBlocks.KICK_SOUND_BLOCK.get())
                .add(ModBlocks.WOMP_SOUND_BLOCK.get());
    }
}
