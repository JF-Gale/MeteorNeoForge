package net.meteorneo.systems.modules.movement;

import net.meteorneo.core.Category;
import net.meteorneo.core.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;

/**
 * AutoJump: automatically keeps the player jumping while moving and the jump key is held.
 */
public class AutoJump extends Module {

    public AutoJump() {
        super("AutoJump", Category.MOVEMENT);
    }

    @Override
    public void onTick(Minecraft mc) {
        LocalPlayer player = mc.player;
        if (player == null || player.isCreative() || player.isSpectator()) {
            return;
        }
        if (player.onGround() && mc.options.keyJump.isDown()) {
            player.jumpFromGround();
        }
    }
}
