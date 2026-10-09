package net.meteorneo.systems.modules.combat;

import net.meteorneo.core.Category;
import net.meteorneo.core.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.state.BlockState;

/**
 * BedAura: finds nearby beds and looks at them while holding use to detonate.
 */
public class BedAura extends Module {

    public BedAura() {
        super("BedAura", Category.COMBAT);
    }

    @Override
    public void onTick(Minecraft mc) {
        LocalPlayer player = mc.player;
        if (player == null || mc.level == null) {
            return;
        }
        BlockPos bed = findBed(player, mc);
        if (bed != null) {
            rotateTo(player, bed);
            mc.options.keyUse.setDown(true);
        }
    }

    private BlockPos findBed(LocalPlayer player, Minecraft mc) {
        for (int x = -5; x <= 5; x++) {
            for (int y = -3; y <= 3; y++) {
                for (int z = -5; z <= 5; z++) {
                    BlockPos pos = player.blockPosition().offset(x, y, z);
                    BlockState state = mc.level.getBlockState(pos);
                    if (state.getBlock() instanceof BedBlock) {
                        return pos;
                    }
                }
            }
        }
        return null;
    }

    private void rotateTo(LocalPlayer player, BlockPos target) {
        double dx = target.getX() + 0.5 - player.getX();
        double dy = target.getY() + 0.5 - (player.getY() + player.getEyeHeight());
        double dz = target.getZ() + 0.5 - player.getZ();
        double horizontal = Math.sqrt(dx * dx + dz * dz);
        player.setYRot((float) (Math.toDegrees(Math.atan2(dz, dx)) - 90.0));
        player.setXRot((float) -Math.toDegrees(Math.atan2(dy, horizontal)));
    }
}
