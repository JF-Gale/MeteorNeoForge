package net.meteorneo.systems.modules.render;

import net.meteorneo.core.Category;
import net.meteorneo.core.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.BlockHitResult;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import org.joml.Matrix4f;

/**
 * BreakESP: highlights the block currently being looked at (targeted for breaking).
 */
public class BreakESP extends Module {

    public BreakESP() {
        super("BreakESP", Category.RENDER);
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

        MultiBufferSource.BufferSource buffers = mc.renderBuffers().bufferSource();
        VertexConsumer buffer = buffers.getBuffer(RenderType.lines());
        Matrix4f matrix = poseStack.last().pose();

        drawBox(buffer, matrix,
                (float) (pos.getX() - camX), (float) (pos.getY() - camY), (float) (pos.getZ() - camZ),
                (float) (pos.getX() + 1 - camX), (float) (pos.getY() + 1 - camY), (float) (pos.getZ() + 1 - camZ));
        buffers.endBatch();
    }

    private void drawBox(VertexConsumer buffer, Matrix4f m,
                         float x1, float y1, float z1, float x2, float y2, float z2) {
        addLine(buffer, m, x1, y1, z1, x2, y1, z1);
        addLine(buffer, m, x2, y1, z1, x2, y1, z2);
        addLine(buffer, m, x2, y1, z2, x1, y1, z2);
        addLine(buffer, m, x1, y1, z2, x1, y1, z1);
        addLine(buffer, m, x1, y2, z1, x2, y2, z1);
        addLine(buffer, m, x2, y2, z1, x2, y2, z2);
        addLine(buffer, m, x2, y2, z2, x1, y2, z2);
        addLine(buffer, m, x1, y2, z2, x1, y2, z1);
        addLine(buffer, m, x1, y1, z1, x1, y2, z1);
        addLine(buffer, m, x2, y1, z1, x2, y2, z1);
        addLine(buffer, m, x2, y1, z2, x2, y2, z2);
        addLine(buffer, m, x1, y1, z2, x1, y2, z2);
    }

    private void addLine(VertexConsumer buffer, Matrix4f m, float ax, float ay, float az, float bx, float by, float bz) {
        buffer.setColor(0, 255, 255, 255);
        buffer.setNormal(0.0f, 1.0f, 0.0f);
        buffer.addVertex(m, ax, ay, az);
        buffer.setColor(0, 255, 255, 255);
        buffer.setNormal(0.0f, 1.0f, 0.0f);
        buffer.addVertex(m, bx, by, bz);
    }
}
