package dev.IlikeHello47.clockwork_smp.obj;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.blaze3d.vertex.Tesselator;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import org.joml.Matrix4f;

public class HighPerformanceVoxelRenderer implements BlockEntityRenderer<VoxelBlockEntity> {

    private VertexBuffer gpuBuffer;
    private boolean needsRebuild = true;

    public HighPerformanceVoxelRenderer(BlockEntityRendererProvider.Context context) {}

    public void buildMesh(byte[] voxelData, int resolution) {
        if (gpuBuffer != null) gpuBuffer.close();
        gpuBuffer = new VertexBuffer(VertexBuffer.Usage.STATIC);

        Tesselator tessellator = Tesselator.getInstance();
        BufferBuilder bufferBuilder = tessellator.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR_LIGHTMAP);

        float size = 1.0f / resolution;

        for (int x = 0; x < resolution; x++) {
            for (int y = 0; y < resolution; y++) {
                for (int z = 0; z < resolution; z++) {
                    int argb = getVoxelColor(voxelData, x, y, z, resolution);
                    if (((argb >> 24) & 0xFF) == 0) continue;

                    if (isAir(voxelData, x + 1, y, z, resolution)) {
                        drawFace(bufferBuilder, x, y, z, size, argb, Direction.EAST);
                    }
                    if (isAir(voxelData, x - 1, y, z, resolution)) {
                        drawFace(bufferBuilder, x, y, z, size, argb, Direction.WEST);
                    }
                    if (isAir(voxelData, x, y + 1, z, resolution)) {
                        drawFace(bufferBuilder, x, y, z, size, argb, Direction.UP);
                    }
                    if (isAir(voxelData, x, y - 1, z, resolution)) {
                        drawFace(bufferBuilder, x, y, z, size, argb, Direction.DOWN);
                    }
                    if (isAir(voxelData, x, y, z + 1, resolution)) {
                        drawFace(bufferBuilder, x, y, z, size, argb, Direction.SOUTH);
                    }
                    if (isAir(voxelData, x, y, z - 1, resolution)) {
                        drawFace(bufferBuilder, x, y, z, size, argb, Direction.NORTH);
                    }
                }
            }
        }

        MeshData meshData = bufferBuilder.build();
        if (meshData != null) {
            gpuBuffer.upload(meshData);
            needsRebuild = false;
        }
    }

    private void drawFace(BufferBuilder buffer, int x, int y, int z, float s, int color, Direction dir) {
        float fx = x * s; float fy = y * s; float fz = z * s;
        int r = (color >> 16) & 0xFF; int g = (color >> 8) & 0xFF; int b = color & 0xFF; int a = (color >> 24) & 0xFF;
        int light = 15728880;

        switch (dir) {
            case UP -> {
                buffer.addVertex(fx, fy + s, fz).setColor(r, g, b, a).setLight(light);
                buffer.addVertex(fx, fy + s, fz + s).setColor(r, g, b, a).setLight(light);
                buffer.addVertex(fx + s, fy + s, fz + s).setColor(r, g, b, a).setLight(light);
                buffer.addVertex(fx + s, fy + s, fz).setColor(r, g, b, a).setLight(light);
            }
        }
    }

    private boolean isAir(byte[] data, int x, int y, int z, int res) {
        if (x < 0 || x >= res || y < 0 || y >= res || z < 0 || z >= res) return true;
        return ((getVoxelColor(data, x, y, z, res) >> 24) & 0xFF) == 0;
    }

    private int getVoxelColor(byte[] data, int x, int y, int z, int res) {
        int index = (x * res * res + y * res + z) * 4;
        if (index + 3 >= data.length) return 0;
        return ((data[index] & 0xFF) << 24) | ((data[index+1] & 0xFF) << 16) | ((data[index+2] & 0xFF) << 8) | (data[index+3] & 0xFF);
    }

    @Override
    public void render(VoxelBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        if (blockEntity.hasDataChanged() || needsRebuild) {
            buildMesh(blockEntity.getVoxelData(), blockEntity.getResolution());
            blockEntity.setClean();
        }

        if (gpuBuffer == null) return;

        poseStack.pushPose();

        RenderSystem.setShader(GameRenderer::getPositionColorLightmapShader);

        Matrix4f matrix = poseStack.last().pose();

        gpuBuffer.bind();
        gpuBuffer.drawWithShader(matrix, RenderSystem.getProjectionMatrix(), RenderSystem.getShader());
        VertexBuffer.unbind();

        poseStack.popPose();
    }
}
