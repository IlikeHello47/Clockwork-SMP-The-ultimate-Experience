package dev.IlikeHello47.clockwork_smp.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.TicketType;
import net.minecraft.world.level.ChunkPos;
import java.util.Comparator;

public class ChunkLoadingHandler {

    private static final TicketType<BlockPos> CHUNK_LOADER_TICKET = TicketType.create(
            "clockwork_smp:chunk_loader",
            Comparator.comparingLong(BlockPos::asLong)
    );

    private static final int LOADING_RADIUS = 2;

    public static void startLoading(ServerLevel level, BlockPos pos) {
        ChunkPos chunkPos = new ChunkPos(pos);
        level.getChunkSource().addRegionTicket(CHUNK_LOADER_TICKET, chunkPos, LOADING_RADIUS, pos);

        System.out.println("[ChunkLoader] START: Chunks around " + chunkPos + " are now getting loaded!");
    }

    public static void stopLoading(ServerLevel level, BlockPos pos) {
        ChunkPos chunkPos = new ChunkPos(pos);
        level.getChunkSource().removeRegionTicket(CHUNK_LOADER_TICKET, chunkPos, LOADING_RADIUS, pos);

        System.out.println("[ChunkLoader] STOP: Chunks arounds " + chunkPos + " got unloaded.");
    }
}
