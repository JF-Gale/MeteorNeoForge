package net.meteorneo.systems.modules.misc;

import net.meteorneo.core.Category;
import net.meteorneo.core.Module;
import net.minecraft.client.Minecraft;

/**
 * Discord: shows the game state in the window title (simplified presence display).
 */
public class Discord extends Module {

    public Discord() {
        super("Discord", Category.MISC);
    }

    @Override
    public void onTick(Minecraft mc) {
        if (mc.getWindow() == null) {
            return;
        }
        String state = (mc.player != null) ? "In Game" : "Main Menu";
        mc.getWindow().setTitle("Meteor NeoForge | " + state);
    }
}
