package net.meteorneo.systems.modules.world;

import net.meteorneo.core.Category;
import net.meteorneo.core.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.level.block.Blocks;

/**
 * Portals: automatically walks forward when standing in a nether portal.
 */
public class Portals extends Module {

    public Portals() {
        super("Portals", Category.WORLD);
    }

    @Override
    public void onTick(Minecraft mc) {
        LocalPlayer player = mc.player;
        if (player == null || mc.level == null) {
            return;
        }
        if (mc.level.getBlockState(player.blockPosition()).is(Blocks.NETHER_PORTAL)) {
            player.zza = 1.0f;
        }
    }
}
