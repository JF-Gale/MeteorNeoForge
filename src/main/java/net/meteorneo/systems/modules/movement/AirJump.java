package net.meteorneo.systems.modules.movement;

import net.meteorneo.core.Category;
import net.meteorneo.core.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;

/**
 * AirJump: lets you jump while mid-air (double/triple jumps).
 */
public class AirJump extends Module {

    public AirJump() {
        super("AirJump", Category.MOVEMENT);
    }

    @Override
    public void onTick(Minecraft mc) {
        LocalPlayer player = mc.player;
        if (player == null || player.isCreative() || player.isSpectator()) {
            return;
        }
        // When off the ground and the jump key is pressed, jump again.
        if (!player.onGround() && mc.options.keyJump.consumeClick()) {
            player.jumpFromGround();
        }
    }
}
