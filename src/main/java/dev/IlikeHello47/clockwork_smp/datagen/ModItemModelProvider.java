package dev.IlikeHello47.clockwork_smp.datagen;

import dev.IlikeHello47.clockwork_smp.Clockwork_SMP;
import dev.IlikeHello47.clockwork_smp.item.ModItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Clockwork_SMP.MODID, exFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.CAT_NIP.get());
    }
}
