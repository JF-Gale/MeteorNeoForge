package net.meteorneo.systems.modules.movement;

import net.meteorneo.core.Category;
import net.meteorneo.core.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.block.state.BlockState;

/**
 * Step: automatically jumps when facing a one-block step while moving on the ground.
 */
public class Step extends Module {

    public Step() {
        super("Step", Category.MOVEMENT);
    }

    @Override
    public void onTick(Minecraft mc) {
        LocalPlayer player = mc.player;
        if (player == null || mc.level == null) {
            return;
        }
        if (!player.onGround() || player.zza == 0.0f) {
            return;
        }
        Vec3 pos = player.position();
        Vec3 look = player.getLookAngle();
        int dx = look.x >= 0.0 ? 1 : -1;
        int dz = look.z >= 0.0 ? 1 : -1;
        BlockPos eye = new BlockPos((int) Math.floor(pos.x), (int) Math.floor(pos.y), (int) Math.floor(pos.z));
        BlockPos frontTop = eye.offset(dx, 1, dz);
        BlockState top = mc.level.getBlockState(frontTop);
        BlockState above = mc.level.getBlockState(frontTop.above());
        if (top.isSolid() && !above.isSolid()) {
            player.jumpFromGround();
        }
    }
}
