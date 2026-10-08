package net.meteorneo.systems.modules.movement;

import net.meteorneo.core.Category;
import net.meteorneo.core.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;

/**
 * ElytraFly: accelerates upward while gliding with an elytra.
 */
public class ElytraFly extends Module {

    public ElytraFly() {
        super("ElytraFly", Category.MOVEMENT);
    }

    @Override
    public void onTick(Minecraft mc) {
        LocalPlayer player = mc.player;
        if (player == null) {
            return;
        }
        if (player.isFallFlying()) {
            player.setDeltaMovement(player.getDeltaMovement().add(0, 0.05, 0));
        }
    }
}
