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
 * Tracer: draws lines from the player to every living entity in view.
 */
public class Tracer extends Module {

    public Tracer() {
        super("Tracer", Category.RENDER);
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

        float px = (float) (player.getX());
        float py = (float) (player.getY() + player.getEyeHeight());
        float pz = (float) (player.getZ());

        for (Entity entity : mc.level.entitiesForRendering()) {
            if (!(entity instanceof LivingEntity living)) {
                continue;
            }
            if (living == player) {
                continue;
            }
            float ex = (float) (entity.getX());
            float ey = (float) (entity.getY() + entity.getEyeHeight());
            float ez = (float) (entity.getZ());
            buffer.addVertex(matrix, px, py, pz).setColor(0, 255, 255, 255).setNormal(0.0f, 1.0f, 0.0f);
            buffer.addVertex(matrix, ex, ey, ez).setColor(0, 255, 255, 255).setNormal(0.0f, 1.0f, 0.0f);
        }
        buffers.endBatch();
    }
}
