package net.meteorneo.systems.modules.world;

import net.meteorneo.core.Category;
import net.meteorneo.core.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

/**
 * Reap: automatically harvests mature crops the player is standing over.
 */
public class Reap extends Module {

    public Reap() {
        super("Reap", Category.WORLD);
    }

    @Override
    public void onTick(Minecraft mc) {
        LocalPlayer player = mc.player;
        if (player == null || mc.level == null) {
            return;
        }
        BlockState below = mc.level.getBlockState(player.blockPosition().below());
        if (below.is(Blocks.WHEAT) || below.is(Blocks.CARROTS) || below.is(Blocks.POTATOES)) {
            mc.options.keyUse.setDown(true);
        }
    }
}
