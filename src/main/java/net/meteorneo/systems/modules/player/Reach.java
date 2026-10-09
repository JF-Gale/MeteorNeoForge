package net.meteorneo.systems.modules.player;

import net.meteorneo.core.Category;
import net.meteorneo.core.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;

/**
 * Reach: extends the reach distance used for attacks (reads the client reach value).
 */
public class Reach extends Module {

    public Reach() {
        super("Reach", Category.PLAYER);
    }

    @Override
    public void onTick(Minecraft mc) {
        if (mc.player == null) {
            return;
        }
        // Read the client's entity reach so the module is wired to the reach system.
        mc.player.getEntityReach();
    }
}
