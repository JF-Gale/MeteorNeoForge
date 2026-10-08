package net.meteorneo.systems.modules.world;

import net.meteorneo.core.Category;
import net.meteorneo.core.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

/**
 * InstantMine: instantly starts breaking the block in front of the player.
 */
public class InstantMine extends Module {

    public InstantMine() {
        super("InstantMine", Category.WORLD);
    }

    @Override
    public void onTick(Minecraft mc) {
        LocalPlayer player = mc.player;
        if (player == null || mc.level == null || mc.gameMode == null) {
            return;
        }
        BlockPos pos = player.blockPosition().relative(player.getDirection(), 2);
        BlockState state = mc.level.getBlockState(pos);
        if (!state.isAir() && !state.is(Blocks.BEDROCK)) {
            mc.gameMode.startDestroyBlock(pos, player.getDirection());
        }
    }
}
