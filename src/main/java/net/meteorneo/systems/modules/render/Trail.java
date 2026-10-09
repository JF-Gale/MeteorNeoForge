package net.meteorneo.systems.modules.render;

import net.meteorneo.core.Category;
import net.meteorneo.core.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.world.phys.Vec3;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import org.joml.Matrix4f;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Trail: renders a dense trail of lines following the player's movement.
 */
public class Trail extends Module {

    private final Deque<Vec3> points = new ArrayDeque<>();

    public Trail() {
        super("Trail", Category.RENDER);
    }

    @Override
    protected void onEnable() {
        points.clear();
    }

    @Override
    protected void onDisable() {
        points.clear();
    }

    @Override
    public void onTick(Minecraft mc) {
        if (mc.player == null) {
            return;
        }
        points.addLast(mc.player.position());
        if (points.size() > 400) {
            points.removeFirst();
        }
    }

    @Override
    public void onRenderWorld(Minecraft mc, PoseStack poseStack, float partialTick) {
        if (points.size() < 2) {
            return;
        }
        double camX = mc.gameRenderer.getMainCamera().getPosition().x;
        double camY = mc.gameRenderer.getMainCamera().getPosition().y;
        double camZ = mc.gameRenderer.getMainCamera().getPosition().z;

        MultiBufferSource.BufferSource buffers = mc.renderBuffers().bufferSource();
        VertexConsumer buffer = buffers.getBuffer(RenderType.lines());
        Matrix4f matrix = poseStack.last().pose();

        Vec3 prev = null;
        for (Vec3 p : points) {
            if (prev != null) {
                buffer.setColor(0, 255, 255, 255);
                buffer.setNormal(0.0f, 1.0f, 0.0f);
                buffer.addVertex(matrix, (float) (prev.x - camX), (float) (prev.y - camY), (float) (prev.z - camZ));
                buffer.setColor(0, 255, 255, 255);
                buffer.setNormal(0.0f, 1.0f, 0.0f);
                buffer.addVertex(matrix, (float) (p.x - camX), (float) (p.y - camY), (float) (p.z - camZ));
            }
            prev = p;
        }
        buffers.endBatch();
    }
}
