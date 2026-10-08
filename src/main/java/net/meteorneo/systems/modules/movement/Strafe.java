package net.meteorneo.systems.modules.movement;

import net.meteorneo.core.Category;
import net.meteorneo.core.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;

/**
 * Strafe: turns toward the movement direction and sprints for faster travel.
 */
public class Strafe extends Module {

    public Strafe() {
        super("Strafe", Category.MOVEMENT);
    }

    @Override
    public void onTick(Minecraft mc) {
        LocalPlayer player = mc.player;
        if (player == null) {
            return;
        }
        if (player.zza != 0.0f || player.xxa != 0.0f) {
            float yaw = player.getYRot();
            float target = yaw + (float) Math.toDegrees(Math.atan2(-player.xxa, player.zza));
            player.setYRot(target);
            player.setSprinting(true);
        }
    }
}
