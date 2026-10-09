package net.meteorneo.systems.modules.movement;

import net.meteorneo.core.Category;
import net.meteorneo.core.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;

/**
 * FreeLook: flips the camera yaw while shift is held (simplified look-around).
 */
public class FreeLook extends Module {

    public FreeLook() {
        super("FreeLook", Category.MOVEMENT);
    }

    @Override
    public void onTick(Minecraft mc) {
        LocalPlayer player = mc.player;
        if (player == null) {
            return;
        }
        if (mc.options.keyShift.isDown()) {
            player.setYRot(player.getYRot() + 180.0f);
        }
    }
}
