package net.meteorneo.systems.modules.world;

import net.meteorneo.core.Category;
import net.meteorneo.core.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Blocks;

/**
 * AntiCactus: automatically shifts when near a cactus so the player does not get hurt.
 */
public class AntiCactus extends Module {

    public AntiCactus() {
        super("AntiCactus", Category.WORLD);
    }

    @Override
    public void onTick(Minecraft mc) {
        LocalPlayer player = mc.player;
        if (player == null || mc.level == null) {
            return;
        }
        BlockPos center = player.blockPosition();
        boolean nearCactus = false;
        for (int dx = -1; dx <= 1; dx++) {
            for (int dz = -1; dz <= 1; dz++) {
                BlockPos pos = center.offset(dx, 0, dz);
                if (mc.level.getBlockState(pos).is(Blocks.CACTUS)) {
                    nearCactus = true;
                    break;
                }
            }
            if (nearCactus) break;
        }
        player.setShiftKeyDown(nearCactus);
    }
}
