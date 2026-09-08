package dev.IlikeHello47.clockwork_smp.radio;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockState;

public class RadioBlockRenderer implements BlockEntityRenderer<RadioBlockEntity> {
    private final BlockRenderDispatcher blockRenderer;

    public RadioBlockRenderer(BlockEntityRendererProvider.Context context) {
        this.blockRenderer = context.getBlockRenderDispatcher();
    }

    @Override
    public void render(RadioBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int combinedLight, int combinedOverlay) {
        BlockState state = blockEntity.getBlockState();
        if (!(state.getBlock() instanceof RadioBlock)) return;

        int rotationSteps = state.getValue(RadioBlock.FACING).get2DDataValue();
        float degrees = rotationSteps * 15.0F;

        poseStack.pushPose();

        poseStack.translate(0.5D, 0.0D, 0.5D);
        poseStack.mulPose(Axis.YP.rotationDegrees(-degrees));
        poseStack.translate(-0.5D, 0.0D, -0.5D);

        ResourceLocation modelLocation = ResourceLocation.fromNamespaceAndPath("clockwork_smp", "block/radio");
        BakedModel model = this.blockRenderer.getBlockModelShaper().getModelManager().getModel(ModelResourceLocation.inventory(modelLocation));

        VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.cutout());

        this.blockRenderer.getModelRenderer().renderModel(
                poseStack.last(),
                vertexConsumer,
                state,
                model,
                1.0F, 1.0F, 1.0F,
                combinedLight,
                OverlayTexture.NO_OVERLAY,
                net.neoforged.neoforge.client.model.data.ModelData.EMPTY,
                null
        );

        poseStack.popPose();
    }
}
