package net.meteorneo.systems.modules.misc;

import net.meteorneo.core.Category;
import net.meteorneo.core.Module;
import net.minecraft.client.Minecraft;

/**
 * Commands: enables the dot-command system (e.g. ".tracer" toggles a module).
 * Command handling is wired through the main client chat event in MeteorNeoForge.
 */
public class Commands extends Module {

    public static final String PREFIX = ".";

    public Commands() {
        super("Commands", Category.MISC);
    }

    @Override
    public void onTick(Minecraft mc) {
        // Command parsing happens in the chat event handler in the main class.
    }
}
