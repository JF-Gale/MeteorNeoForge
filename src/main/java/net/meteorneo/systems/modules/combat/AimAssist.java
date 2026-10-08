package net.meteorneo.systems.modules.combat;

import net.meteorneo.core.Category;
import net.meteorneo.core.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

/**
 * AimAssist: automatically aims at the nearest living entity within range.
 */
public class AimAssist extends Module {

    private final double range = 6.0;

    public AimAssist() {
        super("AimAssist", Category.COMBAT);
    }

    @Override
    public void onTick(Minecraft mc) {
        LocalPlayer player = mc.player;
        if (player == null || mc.level == null) {
            return;
        }
        Entity target = null;
        double bestDist = range;
        for (Entity e : mc.level.entitiesForRendering()) {
            if (e == player || !(e instanceof LivingEntity)) {
                continue;
            }
            if (e.isDeadOrDying()) {
                continue;
            }
            double dist = player.distanceToSqr(e);
            if (dist < bestDist * bestDist) {
                bestDist = Math.sqrt(dist);
                target = e;
            }
        }
        if (target == null) {
            return;
        }
        Vec3 t = target.position().add(0, target.getBbHeight() / 2.0, 0);
        Vec3 eye = player.getEyePosition();
        double dx = t.x - eye.x;
        double dy = t.z - eye.z;
        double dz = t.y - eye.y;
        player.setYRot((float) (Math.toDegrees(Math.atan2(dy, dx)) - 90.0));
        double horiz = Math.sqrt(dx * dx + dy * dy);
        player.setXRot((float) (-Math.toDegrees(Math.atan2(dz, horiz))));
    }
}
