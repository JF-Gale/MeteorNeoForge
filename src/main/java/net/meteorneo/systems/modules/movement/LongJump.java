package net.meteorneo.systems.modules.movement;

import net.meteorneo.core.Category;
import net.meteorneo.core.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;

/**
 * LongJump: charges while sprinting on the ground then jumps for a longer leap.
 */
public class LongJump extends Module {

    private int chargeTicks = 0;

    public LongJump() {
        super("LongJump", Category.MOVEMENT);
    }

    @Override
    public void onTick(Minecraft mc) {
        LocalPlayer player = mc.player;
        if (player == null) {
            return;
        }
        if (player.onGround()) {
            chargeTicks++;
            if (player.isSprinting() && chargeTicks > 10) {
                player.jumpFromGround();
                chargeTicks = 0;
            }
        } else {
            chargeTicks = 0;
        }
    }
}
