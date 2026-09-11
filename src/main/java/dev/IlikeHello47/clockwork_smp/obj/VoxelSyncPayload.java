package dev.IlikeHello47.clockwork_smp.obj;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

public record VoxelSyncPayload(BlockPos pos, byte[] compressedData) implements CustomPacketPayload {

    public static final Type<VoxelSyncPayload> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath("clockwork_smp", "voxel_sync"));

    public static final StreamCodec<FriendlyByteBuf, VoxelSyncPayload> CODEC = StreamCodec.of(
            (buf, payload) -> {
                buf.writeBlockPos(payload.pos());
                buf.writeByteArray(payload.compressedData());
            },
            buf -> new VoxelSyncPayload(buf.readBlockPos(), buf.readByteArray())
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }


    @SubscribeEvent
    public static void registerNetworking(RegisterPayloadHandlersEvent event) {
        PayloadRegistrar registrar = event.registrar("clockwork_smp").versioned("1.0.0");

        registrar.playBidirectional(
                VoxelSyncPayload.TYPE,
                VoxelSyncPayload.CODEC,
                (payload, context) -> new VoxelPacketHandler().handle(payload, context)
        );
    }
}
