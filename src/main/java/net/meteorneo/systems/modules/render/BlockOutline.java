package net.meteorneo.systems.modules.render;

import net.meteorneo.core.Category;
import net.meteorneo.core.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.core.BlockPos;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.renderer.GameRenderer;
import org.joml.Matrix4f;

/**
 * BlockOutline: renders a wireframe box around the block being looked at.
 */
public class BlockOutline extends Module {

    public BlockOutline() {
        super("BlockOutline", Category.RENDER);
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

        poseStack.pushPose();
        poseStack.translate(pos.getX() - camX, pos.getY() - camY, pos.getZ() - camZ);

        RenderSystem.setShader(() -> GameRenderer.getPositionShader());
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        RenderSystem.disableDepthTest();
        RenderSystem.disableCull();

        Matrix4f matrix = poseStack.last().pose();
        Tesselator tess = Tesselator.getInstance();
        BufferBuilder buf = tess.getBuilder();
        buf.begin(VertexFormat.Mode.LINES, DefaultVertexFormat.POSITION);

        // Bottom face
        addLine(buf, matrix, 0, 0, 0, 1, 0, 0);
        addLine(buf, matrix, 1, 0, 0, 1, 0, 1);
        addLine(buf, matrix, 1, 0, 1, 0, 0, 1);
        addLine(buf, matrix, 0, 0, 1, 0, 0, 0);
        // Top face
        addLine(buf, matrix, 0, 1, 0, 1, 1, 0);
        addLine(buf, matrix, 1, 1, 0, 1, 1, 1);
        addLine(buf, matrix, 1, 1, 1, 0, 1, 1);
        addLine(buf, matrix, 0, 1, 1, 0, 1, 0);
        // Vertical edges
        addLine(buf, matrix, 0, 0, 0, 0, 1, 0);
        addLine(buf, matrix, 1, 0, 0, 1, 1, 0);
        addLine(buf, matrix, 1, 0, 1, 1, 1, 1);
        addLine(buf, matrix, 0, 0, 1, 0, 1, 1);

        tess.end();
        RenderSystem.enableCull();
        RenderSystem.enableDepthTest();

        poseStack.popPose();
    }

    private void addLine(BufferBuilder buf, Matrix4f matrix, float x1, float y1, float z1, float x2, float y2, float z2) {
        buf.vertex(matrix, x1, y1, z1).endVertex();
        buf.vertex(matrix, x2, y2, z2).endVertex();
    }
}
