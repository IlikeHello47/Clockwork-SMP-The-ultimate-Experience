package dev.IlikeHello47.clockwork_smp.datagen;

import dev.IlikeHello47.clockwork_smp.Clockwork_SMP;
import dev.IlikeHello47.clockwork_smp.block.ModBlocks;
import dev.IlikeHello47.clockwork_smp.block.custom.ChunkLoader;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Clockwork_SMP.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        getVariantBuilder(ModBlocks.CHUNK_LOADER.get()).forAllStates(state -> {
            int power = state.getValue(ChunkLoader.POWERSTATE);

            return ConfiguredModel.builder().modelFile(
                    models().cubeAll(
                            "block/chunk_loader/chunk_loader_powerstate_" + power,
                            ResourceLocation.fromNamespaceAndPath(Clockwork_SMP.MODID, "block/chunk_loader/chunk_loader_powerstate_" + power)
                    )
            ).build();
        });

        simpleBlockItem(ModBlocks.CHUNK_LOADER.get(),
                models().getExistingFile(ResourceLocation.fromNamespaceAndPath(Clockwork_SMP.MODID, "block/chunk_loader/chunk_loader_powerstate_0")));


        simpleBlock(ModBlocks.KICK_SOUND_BLOCK.get());
        simpleBlockItem(ModBlocks.KICK_SOUND_BLOCK.get(),
                models().getExistingFile(ResourceLocation.fromNamespaceAndPath(Clockwork_SMP.MODID, "block/kick_sound_block")));

        simpleBlock(ModBlocks.WOMP_SOUND_BLOCK.get());
        simpleBlockItem(ModBlocks.WOMP_SOUND_BLOCK.get(),
                models().getExistingFile(ResourceLocation.fromNamespaceAndPath(Clockwork_SMP.MODID, "block/womp_sound_block")));

    }
}
