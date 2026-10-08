package net.meteorneo.systems.modules.movement;

import net.meteorneo.core.Category;
import net.meteorneo.core.Module;
import net.meteorneo.systems.settings.BooleanSetting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;

/**
 * NoFall: cancels fall damage by resetting fall distance just before landing.
 */
public class NoFall extends Module {

    private final BooleanSetting resetFallDistance =
            setting(new BooleanSetting("Reset fall distance", "Reset fallDistance each tick to avoid damage.", true));

    public NoFall() {
        super("NoFall", Category.MOVEMENT);
    }

    @Override
    public void onTick(Minecraft mc) {
        LocalPlayer player = mc.player;
        if (player == null || player.isCreative() || player.isSpectator()) {
            return;
        }
        if (resetFallDistance.get() && player.fallDistance > 3) {
            player.fallDistance = 0;
        }
    }
}
