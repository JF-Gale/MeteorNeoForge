package net.meteorneo.systems.modules.player;

import net.meteorneo.core.Category;
import net.meteorneo.core.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;

/**
 * GhostHand: fills the hole below the player through walls by looking down and using.
 */
public class GhostHand extends Module {

    public GhostHand() {
        super("GhostHand", Category.PLAYER);
    }

    @Override
    public void onTick(Minecraft mc) {
        LocalPlayer player = mc.player;
        if (player == null || mc.level == null) {
            return;
        }
        BlockPos below = player.blockPosition().below();
        if (!mc.level.getBlockState(below).isAir()) {
            return;
        }
        player.setXRot(90.0f);
        mc.options.keyUse.setDown(true);
    }
}
