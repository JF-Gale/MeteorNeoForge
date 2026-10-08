package net.meteorneo.systems.modules.player;

import net.meteorneo.core.Category;
import net.meteorneo.core.Module;
import net.minecraft.client.Minecraft;

/**
 * FastUse: holds down the use key for rapid item usage (e.g. eating, throwing).
 */
public class FastUse extends Module {

    public FastUse() {
        super("FastUse", Category.PLAYER);
    }

    @Override
    public void onTick(Minecraft mc) {
        if (mc.player == null) {
            return;
        }
        mc.options.keyUse.setDown(true);
    }
}
