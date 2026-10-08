package net.meteorneo.systems.modules.movement;

import net.meteorneo.core.Category;
import net.meteorneo.core.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.phys.Vec3;

/**
 * NoSlow: keeps the player moving at normal speed while using an item.
 */
public class NoSlow extends Module {

    public NoSlow() {
        super("NoSlow", Category.MOVEMENT);
    }

    @Override
    public void onTick(Minecraft mc) {
        LocalPlayer player = mc.player;
        if (player == null) {
            return;
        }
        if (player.isUsingItem() && (player.zza != 0.0f || player.xxa != 0.0f)) {
            Vec3 v = player.getDeltaMovement();
            player.setDeltaMovement(v.x * 1.5, v.y, v.z * 1.5);
        }
    }
}
