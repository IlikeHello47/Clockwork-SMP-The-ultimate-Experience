package dev.IlikeHello47.clockwork_smp.radio;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;

public class RadioClientHandler {
    public static void openRadioGui(BlockPos pos) {
        Minecraft.getInstance().setScreen(new RadioScreen(pos));
    }

    public static void updateRadioStream(BlockPos pos, String url) {
        Minecraft level = Minecraft.getInstance();
        if (level.level != null) {
            BlockEntity be = level.level.getBlockEntity(pos);
            if (be instanceof RadioBlockEntity radio) {
                radio.setStreamUrl(url);
            }
        }
    }
}
