package net.meteorneo.systems.modules.render;

import net.meteorneo.core.Category;
import net.meteorneo.core.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.world.level.ChunkPos;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import org.joml.Matrix4f;

/**
 * ChunkESP: draws the boundaries of the current chunk.
 */
public class ChunkESP extends Module {

    public ChunkESP() {
        super("ChunkESP", Category.RENDER);
    }

    @Override
    public void onRenderWorld(Minecraft mc, PoseStack poseStack, float partialTick) {
        if (mc.player == null || mc.level == null) {
            return;
        }
        ChunkPos cp = mc.player.chunkPosition();
        double camX = mc.gameRenderer.getMainCamera().getPosition().x;
        double camY = mc.gameRenderer.getMainCamera().getPosition().y;
        double camZ = mc.gameRenderer.getMainCamera().getPosition().z;

        int x0 = cp.getMinBlockX();
        int x1 = cp.getMaxBlockX();
        int z0 = cp.getMinBlockZ();
        int z1 = cp.getMaxBlockZ();
        int y = mc.player.blockPosition().getY();

        MultiBufferSource.BufferSource buffers = mc.renderBuffers().bufferSource();
        VertexConsumer buffer = buffers.getBuffer(RenderType.lines());
        Matrix4f matrix = poseStack.last().pose();

        // Top face at player height
        addLine(buffer, matrix, x0 - camX, y - camY, z0 - camZ, x1 - camX, y - camY, z0 - camZ);
        addLine(buffer, matrix, x1 - camX, y - camY, z0 - camZ, x1 - camX, y - camY, z1 - camZ);
        addLine(buffer, matrix, x1 - camX, y - camY, z1 - camZ, x0 - camX, y - camY, z1 - camZ);
        addLine(buffer, matrix, x0 - camX, y - camY, z1 - camZ, x0 - camX, y - camY, z0 - camZ);
        // Vertical edges down to world bottom
        addLine(buffer, matrix, x0 - camX, y - camY, z0 - camZ, x0 - camX, -camY, z0 - camZ);
        addLine(buffer, matrix, x1 - camX, y - camY, z0 - camZ, x1 - camX, -camY, z0 - camZ);
        addLine(buffer, matrix, x1 - camX, y - camY, z1 - camZ, x1 - camX, -camY, z1 - camZ);
        addLine(buffer, matrix, x0 - camX, y - camY, z1 - camZ, x0 - camX, -camY, z1 - camZ);

        buffers.endBatch();
    }

    private void addLine(VertexConsumer buffer, Matrix4f m, float ax, float ay, float az, float bx, float by, float bz) {
        buffer.addVertex(m, ax, ay, az);
        buffer.addVertex(m, bx, by, bz);
    }
}
