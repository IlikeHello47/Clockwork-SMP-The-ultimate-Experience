package com.ilikehello47.clockwork_smp;

import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import com.mojang.logging.LogUtils;
import org.slf4j.Logger;

@Mixin(MinecraftServer.class)
public class ServerStarted {
    private static final Logger LOGGER = LogUtils.getLogger();

    @Inject(method = "serverStarted", at = @At("HEAD"))
    private void onServerStarted(CallbackInfo ci) {
        LOGGER.warn("SERVER HAS STARTED IN");
    }
}

