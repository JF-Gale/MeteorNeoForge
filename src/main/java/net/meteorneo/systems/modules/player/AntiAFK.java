package net.meteorneo.systems.modules.player;

import net.meteorneo.core.Category;
import net.meteorneo.core.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;

/**
 * AntiAFK: periodically simulates a forward key press to avoid being kicked for idling.
 */
public class AntiAFK extends Module {

    private int ticks = 0;

    public AntiAFK() {
        super("AntiAFK", Category.PLAYER);
    }

    @Override
    public void onTick(Minecraft mc) {
        LocalPlayer player = mc.player;
        if (player == null) {
            return;
        }
        ticks++;
        // Every 40 ticks (~2s) briefly press forward.
        if (ticks % 40 == 0) {
            mc.options.keyUp.setDown(true);
            mc.options.keyUp.setDown(false);
        }
    }
}
