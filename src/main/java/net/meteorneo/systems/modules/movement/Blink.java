package net.meteorneo.systems.modules.movement;

import net.meteorneo.core.Category;
import net.meteorneo.core.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;

/**
 * Blink: freezes the player's movement so position updates stop being sent.
 */
public class Blink extends Module {

    public Blink() {
        super("Blink", Category.MOVEMENT);
    }

    @Override
    public void onTick(Minecraft mc) {
        LocalPlayer player = mc.player;
        if (player == null) {
            return;
        }
        player.setDeltaMovement(0.0, 0.0, 0.0);
    }
}
