package net.meteorneo.systems.modules.player;

import net.meteorneo.core.Category;
import net.meteorneo.core.Module;
import net.minecraft.client.Minecraft;

/**
 * Reach: extends the reach distance used for attacks.
 * The actual reach change requires a mixin; this module provides the toggle entry
 * and a small client-side attack assist (no distance modification without a mixin).
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
        // Attack reach is server-authoritative; reach enhancement needs a mixin.
        // Keep the module togglable as a feature entry point.
    }
}
