package net.meteorneo.systems.modules.combat;

import net.meteorneo.core.Category;
import net.meteorneo.core.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;

/**
 * AntiAim: continuously rotates the player's view to make aiming harder.
 */
public class AntiAim extends Module {

    private int ticks = 0;

    public AntiAim() {
        super("AntiAim", Category.COMBAT);
    }

    @Override
    public void onTick(Minecraft mc) {
        LocalPlayer player = mc.player;
        if (player == null) {
            return;
        }
        ticks++;
        float yaw = (ticks * 20.0f) % 360.0f;
        player.setYRot(yaw);
    }
}
