package com.ilikehello47.clockwork_smp;

import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import com.mojang.logging.LogUtils;
import org.slf4j.Logger;

@Mixin(MinecraftServer.class)
public class ServerStarted {
    private static final Logger LOGGER = LogUtils.getLogger();

    @Unique
    private long myMod$serverStartTime;

    @Inject(method = "<init>", at = @At("TAIL"))
    private void onServerInit(CallbackInfo ci) {
        this.myMod$serverStartTime = System.currentTimeMillis();
    }

    @Inject(method = "serverStarted", at = @At("HEAD"))
    private void onServerStarted(CallbackInfo ci) {
        MinecraftServer server = (MinecraftServer) (Object) this;

        long durationMs = System.currentTimeMillis() - this.myMod$serverStartTime;
        double durationSeconds = durationMs / 1000.0;
        String formattedTime = String.format("%.2f", durationSeconds);

        if (server.isDedicatedServer()) {
            LOGGER.warn("[DEDICATED SERVER] STARTED IN {} SECONDS", formattedTime);
        } else {
            LOGGER.info("[SINGLEPLAYER] WORLD LOADED IN {} SECONDS", formattedTime);
        }
    }
}

