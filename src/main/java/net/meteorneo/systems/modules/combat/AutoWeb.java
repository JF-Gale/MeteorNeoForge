package net.meteorneo.systems.modules.combat;

import net.meteorneo.core.Category;
import net.meteorneo.core.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.phys.Vec3;

/**
 * AutoWeb: places cobwebs below nearby enemies when holding cobweb.
 */
public class AutoWeb extends Module {

    private final double range = 4.0;

    public AutoWeb() {
        super("AutoWeb", Category.COMBAT);
    }

    @Override
    public void onTick(Minecraft mc) {
        LocalPlayer player = mc.player;
        if (player == null || mc.level == null) {
            return;
        }
        ItemStack main = player.getMainHandItem();
        if (!main.is(Items.COBWEB)) {
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
        Vec3 below = target.position().add(0, -1, 0);
        double dx = below.x - player.getX();
        double dy = below.z - player.getZ();
        player.setYRot((float) (Math.toDegrees(Math.atan2(dy, dx)) - 90.0));
        double horiz = Math.sqrt(dx * dx + dy * dy);
        double dz = below.y - player.getEyeY();
        player.setXRot((float) (-Math.toDegrees(Math.atan2(dz, horiz))));
        mc.options.keyUse.setDown(true);
    }
}
