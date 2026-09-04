package com.ilikehello47.clockwork_smp.item;

import com.ilikehello47.clockwork_smp.Clockwork_SMP;
import com.ilikehello47.clockwork_smp.sound.ModSounds;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Clockwork_SMP.MOD_ID);

    public static final DeferredItem<Item> MUSIC_DISC_TEMPLATE = ITEMS.register("music_disc_template", () -> new Item(new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> BE_NICE_2_ME_MUSIC_DISC = ITEMS.register("be_nice_2_me_music_disc", () -> new Item(new Item.Properties().jukeboxPlayable(ModSounds.BE_NICE_2_ME_KEY).stacksTo(1).rarity(Rarity.RARE)));
    public static final DeferredItem<Item> HOLY_WAR_MUSIC_DISC = ITEMS.register("holy_war_music_disc", () -> new Item(new Item.Properties().jukeboxPlayable(ModSounds.HOLY_WAR_KEY).stacksTo(1).rarity(Rarity.RARE)));
    public static final DeferredItem<Item> PENTAGRAMMA_MUSIC_DISC = ITEMS.register("pentagramma_music_disc", () -> new Item(new Item.Properties().jukeboxPlayable(ModSounds.PENTAGRAMA_KEY).stacksTo(1).rarity(Rarity.RARE)));
    public static final DeferredItem<Item> STEREO_LOVE_MUSIC_DISC = ITEMS.register("stereo_love_music_disc", () -> new Item(new Item.Properties().jukeboxPlayable(ModSounds.STEREO_LOVE_KEY).stacksTo(1).rarity(Rarity.RARE)));
    public static final DeferredItem<Item> ANGEL_OF_DARKNESS_MUSIC_DISC = ITEMS.register("angel_of_darkness_music_disc", () -> new Item(new Item.Properties().jukeboxPlayable(ModSounds.ANGEL_OF_DARKNESS_KEY).stacksTo(1).rarity(Rarity.RARE)));
    public static final DeferredItem<Item> ET_MUSIC_DISC = ITEMS.register("et_music_disc", () -> new Item(new Item.Properties().jukeboxPlayable(ModSounds.ET_KEY).stacksTo(1).rarity(Rarity.RARE)));
    public static final DeferredItem<Item> KERNKRAFT_400_MUSIC_DISC = ITEMS.register("kernkraft_400_music_disc", () -> new Item(new Item.Properties().jukeboxPlayable(ModSounds.KERNKRAFT_400_KEY).stacksTo(1).rarity(Rarity.RARE)));
    public static final DeferredItem<Item> ALL_I_EVER_WANTED_MUSIC_DISC = ITEMS.register("all_i_ever_wanted_music_disc", () -> new Item(new Item.Properties().jukeboxPlayable(ModSounds.ALL_I_EVER_WANTED_KEY).stacksTo(1).rarity(Rarity.RARE)));
    public static final DeferredItem<Item> DRUGS_MUSIC_DISC = ITEMS.register("drugs_music_disc", () -> new Item(new Item.Properties().jukeboxPlayable(ModSounds.DRUGS_KEY).stacksTo(1).rarity(Rarity.RARE)));
}
