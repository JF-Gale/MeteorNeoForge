package net.meteorneo.systems.modules.player;

import net.meteorneo.core.Category;
import net.meteorneo.core.Module;
import net.minecraft.client.Minecraft;

/**
 * NoBreakDelay: removes the delay between mining progress hits.
 * Full block-break delay removal requires a mixin; this module provides the toggle
 * entry and detects the mining state (kept minimal and compile-safe).
 */
public class NoBreakDelay extends Module {

    public NoBreakDelay() {
        super("NoBreakDelay", Category.PLAYER);
    }

    @Override
    public void onTick(Minecraft mc) {
        if (mc.player == null || mc.gameMode == null) {
            return;
        }
        // Mining state check; the actual tick-rate override needs a mixin.
        mc.gameMode.isDestroying();
    }
}
