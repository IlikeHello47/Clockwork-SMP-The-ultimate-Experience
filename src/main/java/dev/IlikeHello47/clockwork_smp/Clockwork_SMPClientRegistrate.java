package dev.IlikeHello47.clockwork_smp;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;

@Mod(value = Clockwork_SMP.MODID, dist = Dist.CLIENT)
public class Clockwork_SMPClientRegistrate {

    public static void onRegisterKeyMappings(RegisterKeyMappingsEvent event) {
        KeyMapping ponderKey = new KeyMapping(
                "key.ponder.open",
                InputConstants.Type.KEYSYM,
                InputConstants.KEY_W,
                "key.categories.ponder"
        );

        event.register(ponderKey);
    }

    @SubscribeEvent
    public void onClientTick(ClientTickEvent event) {

    }
}
