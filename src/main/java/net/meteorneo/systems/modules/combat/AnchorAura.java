package net.meteorneo.systems.modules.combat;

import net.meteorneo.core.Category;
import net.meteorneo.core.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

/**
 * AnchorAura: finds nearby respawn anchors and holds use to detonate them.
 */
public class AnchorAura extends Module {

    public AnchorAura() {
        super("AnchorAura", Category.COMBAT);
    }

    @Override
    public void onTick(Minecraft mc) {
        LocalPlayer player = mc.player;
        if (player == null || mc.level == null) {
            return;
        }
        BlockPos anchor = findAnchor(player, mc);
        if (anchor != null) {
            rotateTo(player, anchor);
            mc.options.keyUse.setDown(true);
        }
    }

    private BlockPos findAnchor(LocalPlayer player, Minecraft mc) {
        for (int x = -5; x <= 5; x++) {
            for (int y = -3; y <= 3; y++) {
                for (int z = -5; z <= 5; z++) {
                    BlockPos pos = player.blockPosition().offset(x, y, z);
                    BlockState state = mc.level.getBlockState(pos);
                    if (state.is(Blocks.RESPAWN_ANCHOR)) {
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
