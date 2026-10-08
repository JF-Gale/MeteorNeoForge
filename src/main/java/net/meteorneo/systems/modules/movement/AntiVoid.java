package net.meteorneo.systems.modules.movement;

import net.meteorneo.core.Category;
import net.meteorneo.core.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;

/**
 * AntiVoid: jumps upward when falling into the void to reduce falling damage risk.
 */
public class AntiVoid extends Module {

    public AntiVoid() {
        super("AntiVoid", Category.MOVEMENT);
    }

    @Override
    public void onTick(Minecraft mc) {
        LocalPlayer player = mc.player;
        if (player == null) {
            return;
        }
        if (player.position().y < -10.0) {
            player.jumpFromGround();
        }
    }
}
