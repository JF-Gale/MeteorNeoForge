package net.meteorneo.systems.modules.render;

import net.meteorneo.core.Category;
import net.meteorneo.core.Module;
import net.minecraft.client.Minecraft;

/**
 * FOV: forces the field of view to a wider value.
 */
public class FOV extends Module {

    private int originalFov = -1;

    public FOV() {
        super("FOV", Category.RENDER);
    }

    @Override
    public void onTick(Minecraft mc) {
        if (originalFov < 0) {
            originalFov = mc.options.fov().get();
        }
        mc.options.fov().set(110);
    }

    @Override
    protected void onDisable() {
        if (originalFov >= 0) {
            Minecraft mc = Minecraft.getInstance();
            mc.options.fov().set(originalFov);
        }
        originalFov = -1;
    }
}
