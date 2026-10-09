package net.meteorneo.systems.modules.misc;

import net.meteorneo.core.Category;
import net.meteorneo.core.Module;
import net.meteorneo.core.Modules;
import net.minecraft.client.Minecraft;

/**
 * Binds: toggles the Friend module when the jump key is pressed (edge-triggered).
 */
public class Binds extends Module {

    private boolean lastJump = false;

    public Binds() {
        super("Binds", Category.MISC);
    }

    @Override
    public void onTick(Minecraft mc) {
        boolean down = mc.options.keyJump.isDown();
        if (down && !lastJump) {
            Module friend = Modules.get(Friend.class);
            if (friend != null) {
                friend.toggle();
            }
        }
        lastJump = down;
    }
}
