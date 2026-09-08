package dev.IlikeHello47.clockwork_smp.smartphone;


import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.fml.loading.FMLEnvironment;

import java.util.Properties;

public class SmartphoneItem {
    public SmartphoneItem(Properties properties) {
        super();
    }

    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if (level.isClientSide()) {
            openSmartphoneScreen();
        }
        return InteractionResultHolder.success(player.getItemInHand(hand));
    }

    private void openSmartphoneScreen() {
        if (FMLEnvironment.dist.isClient()) {
            Minecraft.getInstance().setScreen(new SmartphoneScreen());
        }
    }
}
