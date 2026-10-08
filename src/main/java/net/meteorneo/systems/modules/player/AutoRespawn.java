package net.meteorneo.systems.modules.player;

import net.meteorneo.core.Category;
import net.meteorneo.core.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;

/**
 * AutoRespawn: automatically respawns when the player dies.
 */
public class AutoRespawn extends Module {

    public AutoRespawn() {
        super("AutoRespawn", Category.PLAYER);
    }

    @Override
    public void onTick(Minecraft mc) {
        LocalPlayer player = mc.player;
        if (player == null) {
            return;
        }
        if (player.isDeadOrDying()) {
            player.respawn();
        }
    }
}
