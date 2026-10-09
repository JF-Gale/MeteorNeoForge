package net.meteorneo.systems.modules.combat;

import net.meteorneo.core.Category;
import net.meteorneo.core.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;

/**
 * SelfTrap: looks down and places blocks to box the player in.
 */
public class SelfTrap extends Module {

    public SelfTrap() {
        super("SelfTrap", Category.COMBAT);
    }

    @Override
    public void onTick(Minecraft mc) {
        LocalPlayer player = mc.player;
        if (player == null) {
            return;
        }
        player.setXRot(90.0f);
        mc.options.keyUse.setDown(true);
    }
}
