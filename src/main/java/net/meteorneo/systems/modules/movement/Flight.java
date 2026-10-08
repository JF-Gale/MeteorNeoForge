package net.meteorneo.systems.modules.movement;

import net.meteorneo.core.Category;
import net.meteorneo.core.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;

/**
 * Flight: grants creative-style flying. Enables flying ability while active.
 */
public class Flight extends Module {

    public Flight() {
        super("Flight", Category.MOVEMENT);
    }

    @Override
    protected void onEnable() {
        LocalPlayer player = Minecraft.getInstance().player;
        if (player != null) {
            player.getAbilities().flying = true;
        }
    }

    @Override
    protected void onDisable() {
        LocalPlayer player = Minecraft.getInstance().player;
        if (player != null) {
            player.getAbilities().flying = false;
        }
    }

    @Override
    public void onTick(Minecraft mc) {
        LocalPlayer player = mc.player;
        if (player == null) {
            return;
        }
        // Keep flight on and set a comfortable fly speed.
        player.getAbilities().flying = true;
        player.getAbilities().setFlyingSpeed(0.05f);
    }
}
