package net.meteorneo.systems.modules.render;

import net.meteorneo.core.Category;
import net.meteorneo.core.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import org.joml.Matrix4f;

/**
 * ChinaHat: draws a ring above every living entity's head.
 */
public class ChinaHat extends Module {

    public ChinaHat() {
        super("ChinaHat", Category.RENDER);
    }

    @Override
    public void onRenderWorld(Minecraft mc, PoseStack poseStack, float partialTick) {
        if (mc.player == null || mc.level == null) {
            return;
        }
        LocalPlayer player = mc.player;
        double camX = mc.gameRenderer.getMainCamera().getPosition().x;
        double camY = mc.gameRenderer.getMainCamera().getPosition().y;
        double camZ = mc.gameRenderer.getMainCamera().getPosition().z;

        MultiBufferSource.BufferSource buffers = mc.renderBuffers().bufferSource();
        VertexConsumer buffer = buffers.getBuffer(RenderType.lines());
        Matrix4f matrix = poseStack.last().pose();

        for (Entity entity : mc.level.entitiesForRendering()) {
            if (!(entity instanceof LivingEntity living)) {
                continue;
            }
            if (living == player) {
                continue;
            }
            float cx = (float) (entity.getX() - camX);
            float cy = (float) (entity.getY() + entity.getEyeHeight() + 0.3f - camY);
            float cz = (float) (entity.getZ() - camZ);
            float radius = 0.35f;
            int segments = 24;
            for (int i = 0; i < segments; i++) {
                float a1 = (float) (i * 2.0 * Math.PI / segments);
                float a2 = (float) ((i + 1) * 2.0 * Math.PI / segments);
                buffer.addVertex(matrix, cx + radius * (float) Math.cos(a1), cy, cz + radius * (float) Math.sin(a1)).setColor(0, 255, 255, 255).setNormal(0.0f, 1.0f, 0.0f);
                buffer.addVertex(matrix, cx + radius * (float) Math.cos(a2), cy, cz + radius * (float) Math.sin(a2)).setColor(0, 255, 255, 255).setNormal(0.0f, 1.0f, 0.0f);
            }
        }
        buffers.endBatch();
    }
}
