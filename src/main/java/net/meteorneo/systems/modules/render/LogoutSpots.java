package net.meteorneo.systems.modules.render;

import net.meteorneo.core.Category;
import net.meteorneo.core.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import org.joml.Matrix4f;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * LogoutSpots: remembers the last known position of nearby players and renders a marker there.
 */
public class LogoutSpots extends Module {

    private final Map<UUID, Vec3> spots = new HashMap<>();

    public LogoutSpots() {
        super("LogoutSpots", Category.RENDER);
    }

    @Override
    protected void onEnable() {
        spots.clear();
    }

    @Override
    protected void onDisable() {
        spots.clear();
    }

    @Override
    public void onTick(Minecraft mc) {
        if (mc.player == null || mc.level == null) {
            return;
        }
        for (Entity entity : mc.level.entitiesForRendering()) {
            if (entity instanceof Player p && p != mc.player) {
                spots.put(entity.getUUID(), entity.position());
            }
        }
    }

    @Override
    public void onRenderWorld(Minecraft mc, PoseStack poseStack, float partialTick) {
        if (spots.isEmpty()) {
            return;
        }
        double camX = mc.gameRenderer.getMainCamera().getPosition().x;
        double camY = mc.gameRenderer.getMainCamera().getPosition().y;
        double camZ = mc.gameRenderer.getMainCamera().getPosition().z;

        MultiBufferSource.BufferSource buffers = mc.renderBuffers().bufferSource();
        VertexConsumer buffer = buffers.getBuffer(RenderType.lines());
        Matrix4f matrix = poseStack.last().pose();

        for (Vec3 v : spots.values()) {
            float x = (float) (v.x - camX);
            float y = (float) (v.y - camY);
            float z = (float) (v.z - camZ);
            drawBox(buffer, matrix, x, y, z, x + 0.5f, y + 1.8f, z + 0.5f);
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
