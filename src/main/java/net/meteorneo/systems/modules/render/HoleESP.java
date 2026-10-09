package net.meteorneo.systems.modules.render;

import net.meteorneo.core.Category;
import net.meteorneo.core.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockPos.MutableBlockPos;
import net.minecraft.world.level.block.state.BlockState;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import org.joml.Matrix4f;

/**
 * HoleESP: highlights holes in the ground that a player could fall into.
 */
public class HoleESP extends Module {

    public HoleESP() {
        super("HoleESP", Category.RENDER);
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
        MutableBlockPos pos = new MutableBlockPos();
        for (int x = -5; x <= 5; x++) {
            for (int z = -5; z <= 5; z++) {
                pos.set(center.getX() + x, center.getY(), center.getZ() + z);
                if (isHole(mc, pos)) {
                    drawBox(buffer, matrix,
                            (float) (pos.getX() - camX), (float) (pos.getY() - camY), (float) (pos.getZ() - camZ),
                            (float) (pos.getX() + 1 - camX), (float) (pos.getY() + 0.1f - camY), (float) (pos.getZ() + 1 - camZ));
                }
            }
        }
        buffers.endBatch();
    }

    private boolean isHole(Minecraft mc, BlockPos pos) {
        BlockState below = mc.level.getBlockState(pos);
        if (!below.isAir()) {
            return false;
        }
        int depth = 0;
        BlockPos cursor = pos;
        while (depth < 4) {
            cursor = cursor.below();
            BlockState state = mc.level.getBlockState(cursor);
            if (state.isAir()) {
                depth++;
            } else {
                return depth >= 2;
            }
        }
        return false;
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
