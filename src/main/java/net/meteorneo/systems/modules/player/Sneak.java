package net.meteorneo.systems.modules.player;

import net.meteorneo.core.Category;
import net.meteorneo.core.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;

/**
 * Sneak: forces the player to sneak continuously.
 */
public class Sneak extends Module {

    public Sneak() {
        super("Sneak", Category.PLAYER);
    }

    @Override
    public void onTick(Minecraft mc) {
        LocalPlayer player = mc.player;
        if (player == null) {
            return;
        }
        player.setShiftKeyDown(true);
    }
}
