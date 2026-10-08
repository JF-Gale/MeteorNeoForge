package net.meteorneo.systems.modules.movement;

import net.meteorneo.core.Category;
import net.meteorneo.core.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;

/**
 * Scaffold: looks straight down and continuously places blocks below the player.
 */
public class Scaffold extends Module {

    public Scaffold() {
        super("Scaffold", Category.MOVEMENT);
    }

    @Override
    public void onTick(Minecraft mc) {
        LocalPlayer player = mc.player;
        if (player == null) {
            return;
        }
        player.setXRot(90.0f);
        mc.options.keyUse.setDown(true);
    }
}
