package net.meteorneo.systems.modules.render;

import net.meteorneo.core.Category;
import net.meteorneo.core.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import org.joml.Matrix4f;

/**
 * RedESP: highlights redstone-related blocks near the player.
 */
public class RedESP extends Module {

    public RedESP() {
        super("RedESP", Category.RENDER);
    }

    @Override
    public void onRenderWorld(Minecraft mc, PoseStack poseStack, float partialTick) {
        if (mc.player == null || mc.level == null) {
            return;
        }
        double camX = mc.gameRenderer.getMainCamera().getPosition().x;
        double camY = mc.gameRenderer.getMainCamera().getPosition().y;
        double camZ = mc.gameRenderer.getMainCamera().getPosition().z;

        MultiBufferSource.BufferSource buffers = mc.renderBuffers().bufferSource();
        VertexConsumer buffer = buffers.getBuffer(RenderType.lines());
        Matrix4f matrix = poseStack.last().pose();

        BlockPos center = mc.player.blockPosition();
        for (int dx = -10; dx <= 10; dx++) {
            for (int dy = -10; dy <= 10; dy++) {
                for (int dz = -10; dz <= 10; dz++) {
                    BlockPos pos = center.offset(dx, dy, dz);
                    BlockState state = mc.level.getBlockState(pos);
                    if (state.is(Blocks.REDSTONE_WIRE) || state.is(Blocks.REDSTONE_BLOCK)
                            || state.is(Blocks.REPEATER) || state.is(Blocks.COMPARATOR)) {
                        drawBox(buffer, matrix,
                                (float) (pos.getX() - camX), (float) (pos.getY() - camY), (float) (pos.getZ() - camZ),
                                (float) (pos.getX() + 1 - camX), (float) (pos.getY() + 1 - camY), (float) (pos.getZ() + 1 - camZ));
                    }
                }
            }
        }
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
