package net.meteorneo.systems.modules.movement;

import net.meteorneo.core.Category;
import net.meteorneo.core.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;

/**
 * BunnyHop: automatically jumps while moving forward on the ground for hop-based speed.
 */
public class BunnyHop extends Module {

    public BunnyHop() {
        super("BunnyHop", Category.MOVEMENT);
    }

    @Override
    public void onTick(Minecraft mc) {
        LocalPlayer player = mc.player;
        if (player == null) {
            return;
        }
        if (player.onGround() && player.zza > 0.0f) {
            player.jump();
        }
    }
}
