package net.meteorneo.systems.modules.combat;

import net.meteorneo.core.Category;
import net.meteorneo.core.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.boss.enderdragon.EndCrystal;

/**
 * CrystalAura: finds the nearest end crystal and attacks it.
 */
public class CrystalAura extends Module {

    public CrystalAura() {
        super("CrystalAura", Category.COMBAT);
    }

    @Override
    public void onTick(Minecraft mc) {
        LocalPlayer player = mc.player;
        if (player == null || mc.level == null) {
            return;
        }
        Entity crystal = findCrystal(player, mc);
        if (crystal != null) {
            rotateTo(player, crystal);
            mc.options.keyAttack.setDown(true);
        }
    }

    private Entity findCrystal(LocalPlayer player, Minecraft mc) {
        Entity best = null;
        double bestDist = Double.MAX_VALUE;
        for (Entity e : mc.level.entitiesForRendering()) {
            if (e instanceof EndCrystal) {
                double dist = player.distanceToSqr(e);
                if (dist < bestDist) {
                    bestDist = dist;
                    best = e;
                }
            }
        }
        return best;
    }

    private void rotateTo(LocalPlayer player, Entity target) {
        double dx = target.getX() - player.getX();
        double dy = target.getY() - (player.getY() + player.getEyeHeight());
        double dz = target.getZ() - player.getZ();
        double horizontal = Math.sqrt(dx * dx + dz * dz);
        player.setYRot((float) (Math.toDegrees(Math.atan2(dz, dx)) - 90.0));
        player.setXRot((float) -Math.toDegrees(Math.atan2(dy, horizontal)));
    }
}
