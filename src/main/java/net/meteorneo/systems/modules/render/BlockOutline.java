package net.meteorneo.systems.modules.render;

import net.meteorneo.core.Category;
import net.meteorneo.core.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.core.BlockPos;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import org.joml.Matrix4f;

/**
 * BlockOutline: renders a wireframe box around the block being looked at.
 */
public class BlockOutline extends Module {

    public BlockOutline() {
        super("BlockOutline", Category.RENDER);
    }

    @Override
    public void onRenderWorld(Minecraft mc, PoseStack poseStack, float partialTick) {
        if (mc.player == null || mc.hitResult == null) {
            return;
        }
        if (!(mc.hitResult instanceof BlockHitResult bhr)) {
            return;
        }
        BlockPos pos = bhr.getBlockPos();

        double camX = mc.gameRenderer.getMainCamera().getPosition().x;
        double camY = mc.gameRenderer.getMainCamera().getPosition().y;
        double camZ = mc.gameRenderer.getMainCamera().getPosition().z;

        poseStack.pushPose();
        poseStack.translate(pos.getX() - camX, pos.getY() - camY, pos.getZ() - camZ);

        MultiBufferSource.BufferSource buffers = mc.renderBuffers().bufferSource();
        VertexConsumer buffer = buffers.getBuffer(RenderType.lines());
        Matrix4f matrix = poseStack.last().pose();

        // Bottom face
        addLine(buffer, matrix, 0, 0, 0, 1, 0, 0);
        addLine(buffer, matrix, 1, 0, 0, 1, 0, 1);
        addLine(buffer, matrix, 1, 0, 1, 0, 0, 1);
        addLine(buffer, matrix, 0, 0, 1, 0, 0, 0);
        // Top face
        addLine(buffer, matrix, 0, 1, 0, 1, 1, 0);
        addLine(buffer, matrix, 1, 1, 0, 1, 1, 1);
        addLine(buffer, matrix, 1, 1, 1, 0, 1, 1);
        addLine(buffer, matrix, 0, 1, 1, 0, 1, 0);
        // Vertical edges
        addLine(buffer, matrix, 0, 0, 0, 0, 1, 0);
        addLine(buffer, matrix, 1, 0, 0, 1, 1, 0);
        addLine(buffer, matrix, 1, 0, 1, 1, 1, 1);
        addLine(buffer, matrix, 0, 0, 1, 0, 1, 1);

        buffers.endBatch();

        poseStack.popPose();
    }

    private void addLine(VertexConsumer buffer, Matrix4f matrix, float x1, float y1, float z1, float x2, float y2, float z2) {
        buffer.addVertex(matrix, x1, y1, z1).setColor(0, 255, 255, 255).setNormal(0.0f, 1.0f, 0.0f);
        buffer.addVertex(matrix, x2, y2, z2).setColor(0, 255, 255, 255).setNormal(0.0f, 1.0f, 0.0f);
    }
}
