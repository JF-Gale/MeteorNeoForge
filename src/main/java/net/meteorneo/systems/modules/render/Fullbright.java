package net.meteorneo.systems.modules.render;

import net.meteorneo.core.Category;
import net.meteorneo.core.Module;
import net.minecraft.client.Minecraft;

/**
 * Fullbright: forces full brightness (gamma) while enabled.
 */
public class Fullbright extends Module {

    public Fullbright() {
        super("Fullbright", Category.RENDER);
    }

    @Override
    protected void onEnable() {
        Minecraft.getInstance().options.gamma().set(1.0);
    }

    @Override
    protected void onDisable() {
        Minecraft.getInstance().options.gamma().set(0.5);
    }
}
