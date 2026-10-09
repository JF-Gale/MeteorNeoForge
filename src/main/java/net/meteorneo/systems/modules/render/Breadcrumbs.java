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

import java.util.ArrayList;
import java.util.List;

/**
 * Breadcrumbs: renders a trail of lines along the player's path.
 */
public class Breadcrumbs extends Module {

    private final List<Vec3> points = new ArrayList<>();
    private int ticks;

    public Breadcrumbs() {
        super("Breadcrumbs", Category.RENDER);
    }

    @Override
    protected void onEnable() {
        points.clear();
        ticks = 0;
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
        ticks++;
        if (ticks % 2 != 0) {
            return;
        }
        Vec3 pos = mc.player.position();
        if (points.isEmpty() || points.get(points.size() - 1).distanceToSqr(pos) > 1.0) {
            points.add(pos);
            if (points.size() > 600) {
                points.remove(0);
            }
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

        for (int i = 0; i < points.size() - 1; i++) {
            Vec3 a = points.get(i);
            Vec3 b = points.get(i + 1);
            buffer.addVertex(matrix, (float) (a.x - camX), (float) (a.y - camY), (float) (a.z - camZ)).setColor(0, 255, 255, 255).setNormal(0.0f, 1.0f, 0.0f);
            buffer.addVertex(matrix, (float) (b.x - camX), (float) (b.y - camY), (float) (b.z - camZ)).setColor(0, 255, 255, 255).setNormal(0.0f, 1.0f, 0.0f);
        }
        buffers.endBatch();
    }
}
