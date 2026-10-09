package net.meteorneo.systems.modules.misc;

import net.meteorneo.core.Category;
import net.meteorneo.core.Module;
import net.meteorneo.core.Modules;
import net.minecraft.client.Minecraft;

/**
 * Profiler: tracks how many modules are currently enabled (a light diagnostics entry).
 */
public class Profiler extends Module {

    private int lastCount = -1;

    public Profiler() {
        super("Profiler", Category.MISC);
    }

    @Override
    public void onTick(Minecraft mc) {
        int enabled = 0;
        for (Module m : Modules.getAll()) {
            if (m.isEnabled()) {
                enabled++;
            }
        }
        if (enabled != lastCount) {
            lastCount = enabled;
            // State tracked; displayed through the module system.
        }
    }
}
