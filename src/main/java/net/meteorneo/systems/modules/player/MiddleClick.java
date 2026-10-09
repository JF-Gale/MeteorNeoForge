package net.meteorneo.systems.modules.player;

import net.meteorneo.core.Category;
import net.meteorneo.core.Module;
import net.meteorneo.core.Modules;
import net.minecraft.client.Minecraft;

/**
 * MiddleClick: toggles GhostHand when the middle mouse button (pick block) is pressed.
 */
public class MiddleClick extends Module {

    private boolean lastPick = false;

    public MiddleClick() {
        super("MiddleClick", Category.PLAYER);
    }

    @Override
    public void onTick(Minecraft mc) {
        boolean down = mc.options.keyPickItem.isDown();
        if (down && !lastPick) {
            Module ghost = Modules.get(GhostHand.class);
            if (ghost != null) {
                ghost.toggle();
            }
        }
        lastPick = down;
    }
}
