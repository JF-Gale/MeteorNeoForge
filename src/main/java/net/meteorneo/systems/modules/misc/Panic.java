package net.meteorneo.systems.modules.misc;

import net.meteorneo.core.Category;
import net.meteorneo.core.Module;
import net.meteorneo.core.Modules;

/**
 * Panic: when enabled, disables every other module immediately and turns itself off.
 */
public class Panic extends Module {

    public Panic() {
        super("Panic", Category.MISC);
    }

    @Override
    protected void onEnable() {
        for (Module m : Modules.getAll()) {
            if (m.isEnabled() && m != this) {
                m.setEnabled(false);
            }
        }
        setEnabled(false);
    }
}
