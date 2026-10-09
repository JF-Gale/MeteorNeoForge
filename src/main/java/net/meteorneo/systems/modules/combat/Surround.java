package net.meteorneo.systems.modules.combat;

import net.meteorneo.core.Category;
import net.meteorneo.core.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;

/**
 * Surround: looks down and places blocks around the player's feet to box in.
 */
public class Surround extends Module {

    public Surround() {
        super("Surround", Category.COMBAT);
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
