package net.meteorneo.systems.modules.combat;

import net.meteorneo.core.Category;
import net.meteorneo.core.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;

/**
 * Criticals: jumps right before attacking to guarantee critical hits.
 */
public class Criticals extends Module {

    public Criticals() {
        super("Criticals", Category.COMBAT);
    }

    @Override
    public void onTick(Minecraft mc) {
        LocalPlayer player = mc.player;
        if (player == null || player.isCreative() || player.isSpectator()) {
            return;
        }
        // Jump before a fully charged attack while on the ground.
        if (player.onGround() && mc.options.keyAttack.isDown() && player.getAttackStrengthScale(0.0f) >= 1.0f) {
            player.jumpFromGround();
        }
    }
}
