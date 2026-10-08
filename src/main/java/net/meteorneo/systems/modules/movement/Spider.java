package net.meteorneo.systems.modules.movement;

import net.meteorneo.core.Category;
import net.meteorneo.core.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

/**
 * Spider: automatically jumps to climb up walls when facing a solid block.
 */
public class Spider extends Module {

    public Spider() {
        super("Spider", Category.MOVEMENT);
    }

    @Override
    public void onTick(Minecraft mc) {
        LocalPlayer player = mc.player;
        if (player == null || mc.level == null) {
            return;
        }
        var pos = player.position();
        var look = player.getLookAngle();
        BlockPos front = new BlockPos(
                (int) Math.floor(pos.x + look.x),
                (int) Math.floor(pos.y + look.y),
                (int) Math.floor(pos.z + look.z)
        );
        BlockState state = mc.level.getBlockState(front);
        if (state.isSolid()) {
            player.jumpFromGround();
        }
    }
}
