package net.meteorneo.systems.modules.combat;

import net.meteorneo.core.Category;
import net.meteorneo.core.Module;
import net.meteorneo.systems.settings.BooleanSetting;
import net.meteorneo.systems.settings.DoubleSetting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.HitResult;

/**
 * KillAura: automatically attacks the nearest attackable living entity in range.
 */
public class KillAura extends Module {

    private final DoubleSetting range = setting(new DoubleSetting("Range", "Maximum attack distance.", 4.0, 1.0, 6.0));
    private final BooleanSetting attackWhileHolding = setting(new BooleanSetting("Attack while holding", "Only attack while the attack key is held.", true));

    public KillAura() {
        super("KillAura", Category.COMBAT);
    }

    @Override
    public void onTick(Minecraft mc) {
        LocalPlayer player = mc.player;
        if (player == null || mc.level == null) {
            return;
        }
        if (mc.hitResult != null && mc.hitResult.getType() != HitResult.Type.MISS) {
            // Do not interrupt when the player is aiming at a block/entity.
            return;
        }
        if (attackWhileHolding.get() && !mc.options.keyAttack.isDown()) {
            return;
        }
        // Wait for the attack cooldown to fully recharge.
        if (player.getAttackStrengthScale(0.0f) < 1.0f) {
            return;
        }

        double bestDistance = range.get();
        LivingEntity target = null;
        for (Entity entity : mc.level.entitiesForRendering()) {
            if (entity == player || !(entity instanceof LivingEntity living)) {
                continue;
            }
            if (living.isSpectator() || !living.isAlive() || living.isAlliedTo(player)) {
                continue;
            }
            double distance = player.distanceToSqr(living);
            if (distance < bestDistance * bestDistance) {
                bestDistance = Math.sqrt(distance);
                target = living;
            }
        }

        if (target != null) {
            player.attack(target);
            player.resetAttackStrengthTicker();
        }
    }
}
