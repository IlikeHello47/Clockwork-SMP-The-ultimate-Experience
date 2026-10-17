package dev.IlikeHello47.clockwork_smp.datagen;

import dev.IlikeHello47.clockwork_smp.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider {

    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.CHUNK_LOADER.get())
                .pattern("RIR")
                .pattern("IDI")
                .pattern("RIR")
                .define('R', Items.REDSTONE)
                .define('I', Items.IRON_INGOT)
                .define('D', Items.DIAMOND)
                .unlockedBy("has_iron", has(Items.IRON_INGOT))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, ModBlocks.KICK_SOUND_BLOCK.get())
                .pattern("IWI")
                .pattern("IRI")
                .pattern("ICI")
                .define('I', Items.IRON_INGOT)
                .define('C', Items.CYAN_WOOL)
                .define('W', Items.RED_WOOL)
                .define('R', Items.REDSTONE)
                .unlockedBy("has_note_block", has(Items.NOTE_BLOCK))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, ModBlocks.WOMP_SOUND_BLOCK.get())
                .pattern("ICI")
                .pattern("IRI")
                .pattern("IWI")
                .define('I', Items.IRON_INGOT)
                .define('C', Items.CYAN_WOOL)
                .define('W', Items.RED_WOOL)
                .define('R', Items.REDSTONE)
                .unlockedBy("has_note_block", has(Items.NOTE_BLOCK))
                .save(recipeOutput);
    }
}
