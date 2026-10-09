package net.meteorneo.systems.modules.misc;

import net.meteorneo.core.Category;
import net.meteorneo.core.Module;
import net.minecraft.client.Minecraft;

/**
 * Timer: speeds up the client tick rate to 40 TPS for faster gameplay.
 */
public class Timer extends Module {

    public Timer() {
        super("Timer", Category.MISC);
    }

    @Override
    public void onTick(Minecraft mc) {
        if (mc.tickTimer != null) {
            mc.tickTimer.msPerTick = 25.0;
        }
    }
}
