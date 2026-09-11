package dev.IlikeHello47.clockwork_smp.obj;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;

public class VoxelPacketHandler {

    public void handle(final VoxelSyncPayload payload, final IPayloadContext context) {
        context.enqueueWork(() -> {
            Player player = context.player();
            Level level = player.level();
            BlockPos pos = payload.pos();

            byte[] uncompressedVoxelData = decompressGzip(payload.compressedData());
            if (uncompressedVoxelData == null) return;

            BlockEntity blockEntity = level.getBlockEntity(pos);

            if (blockEntity instanceof VoxelBlockEntity voxelTile) {
                int totalVoxels = uncompressedVoxelData.length / 4;
                int resolution = (int) Math.round(Math.cbrt(totalVoxels));

                voxelTile.setVoxelData(uncompressedVoxelData, resolution);
            }
        });
    }

    private byte[] decompressGzip(byte[] compressed) {
        try (ByteArrayInputStream bais = new ByteArrayInputStream(compressed);
             GZIPInputStream gzis = new GZIPInputStream(bais);
             ByteArrayOutputStream baos = new ByteArrayOutputStream()) {

            byte[] buffer = new byte[1024];
            int len;
            while ((len = gzis.read(buffer)) > 0) {
                baos.write(buffer, 0, len);
            }
            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
