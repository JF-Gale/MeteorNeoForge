package net.meteorneo.systems.modules.misc;

import net.meteorneo.core.Category;
import net.meteorneo.core.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.FishingHook;

/**
 * AutoFish: automatically reels in when the fishing bobber sinks.
 */
public class AutoFish extends Module {

    private boolean pulled = false;

    public AutoFish() {
        super("AutoFish", Category.MISC);
    }

    @Override
    public void onTick(Minecraft mc) {
        LocalPlayer player = mc.player;
        if (player == null || mc.level == null) {
            return;
        }
        boolean hooking = false;
        for (Entity e : mc.level.entitiesForRendering()) {
            if (e instanceof FishingHook && ((FishingHook) e).getDeltaMovement().y < -0.1) {
                hooking = true;
            }
        }
        if (hooking) {
            mc.options.keyUse.setDown(true);
            pulled = true;
        } else if (pulled) {
            mc.options.keyUse.setDown(false);
            pulled = false;
        }
    }
}
