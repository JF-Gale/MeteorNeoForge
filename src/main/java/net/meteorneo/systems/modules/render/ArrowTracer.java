package net.meteorneo.systems.modules.render;

import net.meteorneo.core.Category;
import net.meteorneo.core.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import org.joml.Matrix4f;

/**
 * ArrowTracer: draws lines from the player to arrows and thrown projectiles.
 */
public class ArrowTracer extends Module {

    public ArrowTracer() {
        super("ArrowTracer", Category.RENDER);
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

        float px = (float) (player.getX() - camX);
        float py = (float) (player.getY() + player.getEyeHeight() - camY);
        float pz = (float) (player.getZ() - camZ);

        for (Entity entity : mc.level.entitiesForRendering()) {
            if (!(entity instanceof AbstractArrow) && !(entity instanceof ThrowableProjectile)) {
                continue;
            }
            float ex = (float) (entity.getX() - camX);
            float ey = (float) (entity.getY() - camY);
            float ez = (float) (entity.getZ() - camZ);
            buffer.addVertex(matrix, px, py, pz).setColor(0, 255, 255, 255).setNormal(0.0f, 1.0f, 0.0f);
            buffer.addVertex(matrix, ex, ey, ez).setColor(0, 255, 255, 255).setNormal(0.0f, 1.0f, 0.0f);
        }
        buffers.endBatch();
    }
}
