package com.at_smp.item;

import com.at_smp.AT_SMP;
import com.at_smp.sound.ModSounds;
import com.at_smp.util.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Unit;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.Tool;
import net.minecraft.core.component.DataComponents;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(AT_SMP.MOD_ID);

    public static final DeferredItem<Item> METEORITE_SCRAP = ITEMS.register("meteorite_scrap", () -> new Item(new Item.Properties().component(DataComponents.FIRE_RESISTANT, Unit.INSTANCE)));
    public static final DeferredItem<Item> MOLTEN_METEORITE = ITEMS.register("molten_meteorite", () -> new Item(new Item.Properties().component(DataComponents.FIRE_RESISTANT, Unit.INSTANCE)));
    public static final DeferredItem<Item> METEORITE_INGOT = ITEMS.register("meteorite_ingot", () -> new Item(new Item.Properties().component(DataComponents.FIRE_RESISTANT, Unit.INSTANCE)));
    public static final DeferredItem<Item> METEORITE_UPGRADE_SMITHING_TEMPLATE = ITEMS.register("meteorite_upgrade_smithing_template", () -> new Item(new Item.Properties().component(DataComponents.FIRE_RESISTANT, Unit.INSTANCE)));

    public static final DeferredItem<Item> METEORITE_AXE = ITEMS.register("meteorite_axe", () -> new AxeItem(ModTags.Items.METEORITE, new Item.Properties().component(DataComponents.FIRE_RESISTANT, Unit.INSTANCE).durability(3452).attributes(AxeItem.createAttributes(ModTags.Items.METEORITE, 11.2F, -3.0F)).component(DataComponents.TOOL, new Tool(List.of(Tool.Rule.minesAndDrops(BlockTags.MINEABLE_WITH_AXE, 12.6F)), 1.0F, 1))));
    public static final DeferredItem<Item> METEORITE_HOE = ITEMS.register("meteorite_hoe", () -> new HoeItem(ModTags.Items.METEORITE, new Item.Properties().component(DataComponents.FIRE_RESISTANT, Unit.INSTANCE).durability(3452).attributes(HoeItem.createAttributes(ModTags.Items.METEORITE, 0.4F, 0.0F)).component(DataComponents.TOOL, new Tool(List.of(Tool.Rule.minesAndDrops(BlockTags.MINEABLE_WITH_HOE, 12.6F)), 1.0F, 1))));
    public static final DeferredItem<Item> METEORITE_PICKAXE = ITEMS.register("meteorite_pickaxe", () -> new PickaxeItem(ModTags.Items.METEORITE, new Item.Properties().component(DataComponents.FIRE_RESISTANT, Unit.INSTANCE).durability(3452).attributes(PickaxeItem.createAttributes(ModTags.Items.METEORITE, 6.0F, -2.8F)).component(DataComponents.TOOL, new Tool(List.of(Tool.Rule.minesAndDrops(BlockTags.MINEABLE_WITH_PICKAXE, 12.6F)), 1.0F, 1))));
    public static final DeferredItem<Item> METEORITE_SHOVEL = ITEMS.register("meteorite_shovel", () -> new ShovelItem(ModTags.Items.METEORITE, new Item.Properties().component(DataComponents.FIRE_RESISTANT, Unit.INSTANCE).durability(3452).attributes(ShovelItem.createAttributes(ModTags.Items.METEORITE, 6.7F, -3.0F)).component(DataComponents.TOOL, new Tool(List.of(Tool.Rule.minesAndDrops(BlockTags.MINEABLE_WITH_SHOVEL, 12.6F)), 1.0F, 1))));
    public static final DeferredItem<Item> METEORITE_SWORD = ITEMS.register("meteorite_sword", () -> new SwordItem(ModTags.Items.METEORITE, new Item.Properties().component(DataComponents.FIRE_RESISTANT, Unit.INSTANCE).durability(3452).attributes(SwordItem.createAttributes(ModTags.Items.METEORITE, 8.8F, -2.4F))));

    public static final DeferredItem<Item> METEORITE_HELMET = ITEMS.register("meteorite_helmet", properties -> new ArmorItem(ModArmorMaterials.METEORITE, ArmorItem.Type.HELMET, new Item.Properties().component(DataComponents.FIRE_RESISTANT, Unit.INSTANCE).durability(ArmorItem.Type.HELMET.getDurability(63))));
    public static final DeferredItem<Item> METEORITE_CHESTPLATE = ITEMS.register("meteorite_chestplate", properties -> new ArmorItem(ModArmorMaterials.METEORITE, ArmorItem.Type.CHESTPLATE, new Item.Properties().component(DataComponents.FIRE_RESISTANT, Unit.INSTANCE).durability(ArmorItem.Type.CHESTPLATE.getDurability(63))));
    public static final DeferredItem<Item> METEORITE_LEGGINGS = ITEMS.register("meteorite_leggings", properties -> new ArmorItem(ModArmorMaterials.METEORITE, ArmorItem.Type.LEGGINGS, new Item.Properties().component(DataComponents.FIRE_RESISTANT, Unit.INSTANCE).durability(ArmorItem.Type.LEGGINGS.getDurability(63))));
    public static final DeferredItem<Item> METEORITE_BOOTS = ITEMS.register("meteorite_boots", properties -> new ArmorItem(ModArmorMaterials.METEORITE, ArmorItem.Type.BOOTS, new Item.Properties().component(DataComponents.FIRE_RESISTANT, Unit.INSTANCE).durability(ArmorItem.Type.BOOTS.getDurability(63))));


    // public static final DeferredItem<Item> METEORITE_BEDROCK_PICKAXE = ITEMS.register("meteorite_bedrock_pickaxe", () -> new BedrockPickaxeItem(ModTags.ModTiers.METEORITE, new Item.Properties().component(DataComponents.FIRE_RESISTANT, Unit.INSTANCE).durability(16).attributes(PickaxeItem.createAttributes(ModTags.ModTiers.METEORITE, 6.0F, -2.8F)).component(DataComponents.TOOL, new Tool(List.of(Tool.Rule.minesAndDrops(List.of(Blocks.BEDROCK), 15.0F)), 0.0F, 1))));

    public static void register(IEventBus modEventBus) {ITEMS.register(modEventBus);}
    public static class BedrockPickaxeItem extends PickaxeItem {
        public BedrockPickaxeItem(Tier tier, Properties properties) {super(tier, properties);}
        @Override public float getDestroySpeed(ItemStack stack, BlockState state) {return state.is(Blocks.BEDROCK) ? 15.0F : 0.0F;}
        @Override public boolean isCorrectToolForDrops(ItemStack stack, BlockState state) {return state.is(Blocks.BEDROCK) || super.isCorrectToolForDrops(stack, state);}
    }
    @EventBusSubscriber(modid = "at_smp")
    public static class ModEvents {
        @SubscribeEvent
        public static void onBreakSpeed(PlayerEvent.BreakSpeed event) {
            if (event.getEntity().getMainHandItem().getItem() instanceof BedrockPickaxeItem) {
                if (event.getState().is(Blocks.BEDROCK)) {
                    event.setNewSpeed(15.0F);
                } else {
                    event.setNewSpeed(0.0F);
                }
            }
        }
        @SubscribeEvent
        public static void onBlockBreak(BlockEvent.BreakEvent event) {
            Player player = event.getPlayer();
            ItemStack mainHandItem = player.getMainHandItem();
            if (mainHandItem.getItem() instanceof BedrockPickaxeItem && event.getState().is(Blocks.BEDROCK)) {
                Level level = (Level) event.getLevel();
                BlockPos pos = event.getPos();
                if (!level.isClientSide) {
                    Block.dropResources(event.getState(), level, pos, null, player, mainHandItem);
                    mainHandItem.hurtAndBreak(1, player, Player.getSlotForHand(player.getUsedItemHand()));
                }
            }
        }
    }
    public static final DeferredItem<Item> MUSIC_DISC_TEMPLATE = ITEMS.register("music_disc_template", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> BE_NICE_2_ME_MUSIC_DISC = ITEMS.register("be_nice_2_me_music_disc", () -> new Item(new Item.Properties().jukeboxPlayable(ModSounds.BE_NICE_2_ME_KEY).stacksTo(1).rarity(Rarity.RARE)));
    public static final DeferredItem<Item> SAVE_EUROPE_MUSIC_DISC = ITEMS.register("save_europe_music_disc", () -> new Item(new Item.Properties().jukeboxPlayable(ModSounds.SAVE_EUROPE_KEY).stacksTo(1).rarity(Rarity.RARE)));
    public static final DeferredItem<Item> HOLY_WAR_MUSIC_DISC = ITEMS.register("holy_war_music_disc", () -> new Item(new Item.Properties().jukeboxPlayable(ModSounds.HOLY_WAR_KEY).stacksTo(1).rarity(Rarity.RARE)));
    public static final DeferredItem<Item> PENTAGRAMMA_MUSIC_DISC = ITEMS.register("pentagramma_music_disc", () -> new Item(new Item.Properties().jukeboxPlayable(ModSounds.PENTAGRAMA_KEY).stacksTo(1).rarity(Rarity.RARE)));
    public static final DeferredItem<Item> STEREO_LOVE_MUSIC_DISC = ITEMS.register("stereo_love_music_disc", () -> new Item(new Item.Properties().jukeboxPlayable(ModSounds.STEREO_LOVE_KEY).stacksTo(1).rarity(Rarity.RARE)));
    public static final DeferredItem<Item> ANGEL_OF_DARKNESS_MUSIC_DISC = ITEMS.register("angel_of_darkness_music_disc", () -> new Item(new Item.Properties().jukeboxPlayable(ModSounds.ANGEL_OF_DARKNESS_KEY).stacksTo(1).rarity(Rarity.RARE)));
    public static final DeferredItem<Item> ET_MUSIC_DISC = ITEMS.register("et_music_disc", () -> new Item(new Item.Properties().jukeboxPlayable(ModSounds.ET_KEY).stacksTo(1).rarity(Rarity.RARE)));
    public static final DeferredItem<Item> KERNKRAFT_400_MUSIC_DISC = ITEMS.register("kernkraft_400_music_disc", () -> new Item(new Item.Properties().jukeboxPlayable(ModSounds.KERNKRAFT_400_KEY).stacksTo(1).rarity(Rarity.RARE)));
    public static final DeferredItem<Item> ALL_I_EVER_WANTED_MUSIC_DISC = ITEMS.register("all_i_ever_wanted_music_disc", () -> new Item(new Item.Properties().jukeboxPlayable(ModSounds.ALL_I_EVER_WANTED_KEY).stacksTo(1).rarity(Rarity.RARE)));
}