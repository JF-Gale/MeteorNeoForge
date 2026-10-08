package net.meteorneo.systems.modules.combat;

import net.meteorneo.core.Category;
import net.meteorneo.core.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.block.state.BlockState;

/**
 * AutoCity: automatically mines the block below the nearest enemy to drop them.
 */
public class AutoCity extends Module {

    private final double range = 6.0;

    public AutoCity() {
        super("AutoCity", Category.COMBAT);
    }

    @Override
    public void onTick(Minecraft mc) {
        LocalPlayer player = mc.player;
        if (player == null || mc.level == null || mc.gameMode == null) {
            return;
        }
        Entity target = null;
        double best = range;
        for (Entity e : mc.level.entitiesForRendering()) {
            if (e == player || !(e instanceof LivingEntity)) {
                continue;
            }
            if (((LivingEntity) e).isDeadOrDying()) {
                continue;
            }
            double d = player.distanceToSqr(e);
            if (d < best * best) {
                best = Math.sqrt(d);
                target = e;
            }
        }
        if (target == null) {
            return;
        }
        BlockPos below = target.blockPosition().below();
        BlockState state = mc.level.getBlockState(below);
        if (!state.isAir()) {
            mc.gameMode.startDestroyBlock(below, Direction.DOWN);
        }
    }
}
