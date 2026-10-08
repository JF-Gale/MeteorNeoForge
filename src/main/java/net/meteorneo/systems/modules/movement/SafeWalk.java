package net.meteorneo.systems.modules.movement;

import net.meteorneo.core.Category;
import net.meteorneo.core.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

/**
 * SafeWalk: automatically shifts when about to walk off a ledge, preventing falls.
 */
public class SafeWalk extends Module {

    public SafeWalk() {
        super("SafeWalk", Category.MOVEMENT);
    }

    @Override
    public void onTick(Minecraft mc) {
        LocalPlayer player = mc.player;
        if (player == null || mc.level == null) {
            return;
        }
        BlockPos below = player.blockPosition().below();
        BlockState state = mc.level.getBlockState(below);
        // Shift when the block under the player's feet is not solid.
        player.setShiftKeyDown(!state.isSolid());
    }
}
