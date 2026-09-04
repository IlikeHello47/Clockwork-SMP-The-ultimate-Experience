package com.ilikehello47.clockwork_smp.create;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Best-effort integration with Create's Stock Ticker.
 * Uses reflection so mod doesn't require Create at compile time.
 */
public class CreateIntegration {

    @SubscribeEvent
    public void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        Level level = (Level) event.getLevel();
        if (level == null || level.isClientSide()) return;
        BlockPos pos = event.getPos();
        Block block = level.getBlockState(pos).getBlock();
        String cls = block.getClass().getName().toLowerCase();

        // quick class-name check for Stock Ticker
        if (! (cls.contains("stock") && cls.contains("ticker") ) ) return;

        BlockEntity be = level.getBlockEntity(pos);
        if (be == null) return;

        Player player = event.getEntity();
        if (player == null) return;

        List<ItemStack> desired = getDesiredFromBlockEntity(be);
        if (desired.isEmpty()) {
            // fallback: inform player
            sendMessage(player, Component.literal("Stock Ticker detected but no filters could be read."));
            return;
        }

        // build map of desired counts
        Map<Item, Integer> want = new HashMap<>();
        for (ItemStack s : desired) {
            if (s == null || s.isEmpty()) continue;
            want.merge(s.getItem(), s.getCount() <= 0 ? 1 : s.getCount(), Integer::sum);
        }

        int totalMissing = 0;
        Map<Item, Integer> missing = new HashMap<>();
        for (Map.Entry<Item, Integer> e : want.entrySet()) {
            Item it = e.getKey();
            int wantCount = e.getValue();
            int have = countItemInInventory(player, it);
            if (have < wantCount) {
                int miss = wantCount - have;
                missing.put(it, miss);
                totalMissing += miss;
            }
        }

        if (totalMissing == 0) {
            sendMessage(player, Component.literal("Your inventory already satisfies the Stock Ticker list."));
            event.setCancellationResult(InteractionResult.SUCCESS);
            event.setCanceled(true);
            return;
        }


        // give missing items (simulate purchase)
        for (Map.Entry<Item, Integer> e : missing.entrySet()) {
            Item item = e.getKey();
            int count = e.getValue();
            ItemStack give = new ItemStack(item, count);
            boolean added = player.getInventory().add(give);
            if (!added) {
                // drop
                net.minecraft.world.entity.item.ItemEntity it = new net.minecraft.world.entity.item.ItemEntity(level, pos.getX()+0.5, pos.getY()+1.0, pos.getZ()+0.5, give);
                level.addFreshEntity(it);
            }
        }

        sendMessage(player, Component.literal("Purchase complete: spent " + totalMissing + " diamonds and delivered missing items."));
        event.setCancellationResult(InteractionResult.SUCCESS);
        event.setCanceled(true);
    }

    public static List<ItemStack> getDesiredFromBlockEntity(BlockEntity be) {
        List<ItemStack> out = new ArrayList<>();
        Method[] methods = be.getClass().getMethods();
        for (Method m : methods) {
            String name = m.getName().toLowerCase();
            if (name.contains("filter") || name.contains("items") || name.contains("inventory") || name.contains("contained")) {
                try {
                    Object res = null;
                    if (m.getParameterCount() == 0) {
                        res = m.invoke(be);
                    }
                    if (res == null) continue;

                    if (res instanceof List) {
                        List<?> l = (List<?>) res;
                        for (Object o : l) {
                            if (o instanceof ItemStack) out.add((ItemStack) o);
                        }
                        if (!out.isEmpty()) return out;
                    }

                    if (res.getClass().isArray()) {
                        int len = Array.getLength(res);
                        for (int i = 0; i < len; i++) {
                            Object o = Array.get(res, i);
                            if (o instanceof ItemStack) out.add((ItemStack) o);
                        }
                        if (!out.isEmpty()) return out;
                    }

                    // try to handle NonNullList-like objects by attempting to call size/get
                    try {
                        Method size = res.getClass().getMethod("size");
                        Method get = res.getClass().getMethod("get", int.class);
                        int s = (int) size.invoke(res);
                        for (int i = 0; i < s; i++) {
                            Object o = get.invoke(res, i);
                            if (o instanceof ItemStack) out.add((ItemStack) o);
                        }
                        if (!out.isEmpty()) return out;
                    } catch (NoSuchMethodException ignored) {}

                } catch (IllegalAccessException | InvocationTargetException e) {
                    // ignore and continue
                }
            }
        }
        return out;
    }

    private int countItemInInventory(Player player, Item item) {
        int c = 0;
        for (ItemStack s : player.getInventory().items) {
            if (!s.isEmpty() && s.getItem() == item) c += s.getCount();
        }
        return c;
    }

    private void sendMessage(Player player, Component c) {
        if (player instanceof ServerPlayer) ((ServerPlayer) player).sendSystemMessage(c);
        else player.displayClientMessage(c, false);
    }
}

