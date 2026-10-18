package dev.IlikeHello47.clockwork_smp.laser;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import dev.IlikeHello47.clockwork_smp.Clockwork_SMP;
import dev.IlikeHello47.clockwork_smp.block.ModBlocks;
import dev.IlikeHello47.clockwork_smp.block.custom.ChunkLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderLevelStageEvent;
import org.joml.Matrix4f;
import org.joml.Vector3f;

public class LaserBeamRenderer {

    @SubscribeEvent
    public static void onRenderLevelStage(RenderLevelStageEvent event) {
        if (event.getStage() != RenderLevelStageEvent.Stage.AFTER_TRANSLUCENT_BLOCKS) {
            return;
        }

        Minecraft mc = Minecraft.getInstance();
        Level level = mc.level;
        if (level == null) return;

        Vec3 cameraPos = mc.gameRenderer.getMainCamera().getPosition();
        PoseStack poseStack = event.getPoseStack();

        BlockPos.betweenClosedStream(
                BlockPos.containing(cameraPos).offset(-64, -64, -64),
                BlockPos.containing(cameraPos).offset(64, 64, 64)
        ).forEach(pos -> {
            BlockState state = level.getBlockState(pos);
            if (state.is(ModBlocks.CHUNK_LOADER.get())) {
                boolean fullActivate = state.getValue(ChunkLoader.FULL_ACTIVATE);

                if (fullActivate) {
                    float animationProgress = Math.min(1.0f, (level.getGameTime() % 24000) * 0.5f);

                    for (Direction direction : Direction.values()) {
                        renderSideLaser(poseStack, cameraPos, pos, direction, 1.0f);
                    }
                }
            }
        });
    }

    private static void renderSideLaser(PoseStack poseStack, Vec3 cameraPos, BlockPos blockPos, Direction direction, float progress) {
        double centerOffsetX = direction.getStepX() * 0.5;
        double centerOffsetY = direction.getStepY() * 0.5;
        double centerOffsetZ = direction.getStepZ() * 0.5;

        Vec3 start = new Vec3(
                blockPos.getX() + 0.5 + (centerOffsetX * progress),
                blockPos.getY() + 0.5 + (centerOffsetY * progress),
                blockPos.getZ() + 0.5 + (centerOffsetZ * progress)
        );

        poseStack.pushPose();
        poseStack.translate(start.x - cameraPos.x, start.y - cameraPos.y, start.z - cameraPos.z);

        Vector3f dirVec = new Vector3f(direction.getStepX(), direction.getStepY(), direction.getStepZ());
        Vector3f ortho1 = new Vector3f();
        Vector3f ortho2 = new Vector3f();

        if (Math.abs(dirVec.y) > 0.9f) {
            ortho1.set(1, 0, 0);
            ortho2.set(0, 0, 1);
        } else {
            ortho1.set(0, 1, 0);
            dirVec.cross(ortho1, ortho2).normalize();
            ortho2.cross(dirVec, ortho1).normalize();
        }

        Matrix4f matrix = poseStack.last().pose();
        Tesselator tesselator = Tesselator.getInstance();
        BufferBuilder buffer = tesselator.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR);

        float length = 6.0f;
        float outerSize = (6.0f / 16.0f) / 2.0f;
        float innerSize = (2.0f / 16.0f) / 2.0f;

        int baseAlpha = 160;
        int innerAlpha = 255;

        float lx = direction.getStepX() * length;
        float ly = direction.getStepY() * length;
        float lz = direction.getStepZ() * length;

        float o1x = ortho1.x * outerSize;
        float o1y = ortho1.y * outerSize;
        float o1z = ortho1.z * outerSize;

        float o2x = ortho2.x * outerSize;
        float o2y = ortho2.y * outerSize;
        float o2z = ortho2.z * outerSize;

        buffer.addVertex(matrix, -o1x - o2x, -o1y - o2y, -o1z - o2z).setColor(0, 255, 255, baseAlpha);
        buffer.addVertex(matrix, o1x - o2x, o1y - o2y, o1z - o2z).setColor(0, 255, 255, baseAlpha);
        buffer.addVertex(matrix, lx + o1x - o2x, ly + o1y - o2y, lz + o1z - o2z).setColor(0, 255, 255, 0);
        buffer.addVertex(matrix, lx - o1x - o2x, ly - o1y - o2y, lz - o1z - o2z).setColor(0, 255, 255, 0);

        buffer.addVertex(matrix, o1x - o2x, o1y - o2y, o1z - o2z).setColor(0, 255, 255, baseAlpha);
        buffer.addVertex(matrix, o1x + o2x, o1y + o2y, o1z + o2z).setColor(0, 255, 255, baseAlpha);
        buffer.addVertex(matrix, lx + o1x + o2x, ly + o1y + o2y, lz + o1z + o2z).setColor(0, 255, 255, 0);
        buffer.addVertex(matrix, lx + o1x - o2x, ly + o1y - o2y, lz + o1z - o2z).setColor(0, 255, 255, 0);

        buffer.addVertex(matrix, o1x + o2x, o1y + o2y, o1z + o2z).setColor(0, 255, 255, baseAlpha);
        buffer.addVertex(matrix, -o1x + o2x, -o1y + o2y, -o1z + o2z).setColor(0, 255, 255, baseAlpha);
        buffer.addVertex(matrix, lx - o1x + o2x, ly - o1y + o2y, lz - o1z + o2z).setColor(0, 255, 255, 0);
        buffer.addVertex(matrix, lx + o1x + o2x, ly + o1y + o2y, lz + o1z + o2z).setColor(0, 255, 255, 0);

        buffer.addVertex(matrix, -o1x + o2x, -o1y + o2y, -o1z + o2z).setColor(0, 255, 255, baseAlpha);
        buffer.addVertex(matrix, -o1x - o2x, -o1y - o2y, -o1z - o2z).setColor(0, 255, 255, baseAlpha);
        buffer.addVertex(matrix, lx - o1x - o2x, ly - o1y - o2y, lz - o1z - o2z).setColor(0, 255, 255, 0);
        buffer.addVertex(matrix, lx - o1x + o2x, ly - o1y + o2y, lz - o1z + o2z).setColor(0, 255, 255, 0);

        float i1x = ortho1.x * innerSize;
        float i1y = ortho1.y * innerSize;
        float i1z = ortho1.z * innerSize;

        float i2x = ortho2.x * innerSize;
        float i2y = ortho2.y * innerSize;
        float i2z = ortho2.z * innerSize;

        buffer.addVertex(matrix, -i1x - i2x, -i1y - i2y, -i1z - i2z).setColor(255, 255, 255, innerAlpha);
        buffer.addVertex(matrix, i1x - i2x, i1y - i2y, i1z - i2z).setColor(255, 255, 255, innerAlpha);
        buffer.addVertex(matrix, lx + i1x - i2x, ly + i1y - i2y, lz + i1z - i2z).setColor(255, 255, 255, 0);
        buffer.addVertex(matrix, lx - i1x - i2x, ly - i1y - i2y, lz - i1z - i2z).setColor(255, 255, 255, 0);

        buffer.addVertex(matrix, i1x - i2x, i1y - i2y, i1z - i2z).setColor(255, 255, 255, innerAlpha);
        buffer.addVertex(matrix, i1x + i2x, i1y + i2y, i1z + i2z).setColor(255, 255, 255, innerAlpha);
        buffer.addVertex(matrix, lx + i1x + i2x, ly + i1y + i2y, lz + i1z + i2z).setColor(255, 255, 255, 0);
        buffer.addVertex(matrix, lx + i1x - i2x, ly + i1y - i2y, lz + i1z - i2z).setColor(255, 255, 255, 0);

        buffer.addVertex(matrix, i1x + i2x, i1y + i2y, i1z + i2z).setColor(255, 255, 255, innerAlpha);
        buffer.addVertex(matrix, -i1x + i2x, -i1y + i2y, -i1z + i2z).setColor(255, 255, 255, innerAlpha);
        buffer.addVertex(matrix, lx - i1x + i2x, ly - i1y + i2y, lz - i1z + i2z).setColor(255, 255, 255, 0);
        buffer.addVertex(matrix, lx + i1x + i2x, ly + i1y + i2y, lz + i1z + i2z).setColor(255, 255, 255, 0);

        buffer.addVertex(matrix, -i1x + i2x, -i1y + i2y, -i1z + i2z).setColor(255, 255, 255, innerAlpha);
        buffer.addVertex(matrix, -i1x - i2x, -i1y - i2y, -i1z - i2z).setColor(255, 255, 255, innerAlpha);
        buffer.addVertex(matrix, lx - i1x - i2x, ly - i1y - i2y, lz - i1z - i2z).setColor(255, 255, 255, 0);
        buffer.addVertex(matrix, lx - i1x + i2x, ly - i1y + i2y, lz - i1z + i2z).setColor(255, 255, 255, 0);

        MeshData meshData = buffer.build();
        if (meshData != null) {
            RenderSystem.setShader(GameRenderer::getPositionColorShader);
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
            RenderSystem.disableCull();
            RenderSystem.depthMask(false);

            BufferUploader.drawWithShader(meshData);

            RenderSystem.depthMask(true);
            RenderSystem.enableCull();
            RenderSystem.disableBlend();
        }

        poseStack.popPose();
    }
}
