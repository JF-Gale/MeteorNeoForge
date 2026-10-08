package net.meteorneo.systems.modules.combat;

import net.meteorneo.core.Category;
import net.meteorneo.core.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;

/**
 * HoleFiller: automatically places a block below the player when standing over a hole.
 */
public class HoleFiller extends Module {

    public HoleFiller() {
        super("HoleFiller", Category.COMBAT);
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
        // Look straight down and hold use to place a block below.
        player.setXRot(90.0f);
        mc.options.keyUse.setDown(true);
    }
}
