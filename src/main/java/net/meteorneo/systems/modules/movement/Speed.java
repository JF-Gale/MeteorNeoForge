package net.meteorneo.systems.modules.movement;

import net.meteorneo.core.Category;
import net.meteorneo.core.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.phys.Vec3;

/**
 * Speed: boosts horizontal movement velocity while on the ground.
 */
public class Speed extends Module {

    private static final double MULTIPLIER = 1.3d;

    public Speed() {
        super("Speed", Category.MOVEMENT);
    }

    @Override
    public void onTick(Minecraft mc) {
        LocalPlayer player = mc.player;
        if (player == null) {
            return;
        }
        if (!player.onGround()) {
            return;
        }
        Vec3 velocity = player.getDeltaMovement();
        double horizontal = Math.sqrt(velocity.x * velocity.x + velocity.z * velocity.z);
        if (horizontal > 0.001d) {
            player.setDeltaMovement(velocity.x * MULTIPLIER, velocity.y, velocity.z * MULTIPLIER);
        }
    }
}
