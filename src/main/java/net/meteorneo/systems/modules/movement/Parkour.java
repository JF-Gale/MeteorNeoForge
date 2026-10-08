package net.meteorneo.systems.modules.movement;

import net.meteorneo.core.Category;
import net.meteorneo.core.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.level.block.state.BlockState;

/**
 * Parkour: automatically jumps when about to run off a ledge while moving forward.
 */
public class Parkour extends Module {

    public Parkour() {
        super("Parkour", Category.MOVEMENT);
    }

    @Override
    public void onTick(Minecraft mc) {
        LocalPlayer player = mc.player;
        if (player == null || mc.level == null) {
            return;
        }
        BlockState below = mc.level.getBlockState(player.blockPosition().below());
        if (!below.isSolid() && player.zza > 0.0f) {
            player.jumpFromGround();
        }
    }
}
