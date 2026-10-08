package net.meteorneo.systems.modules.movement;

import net.meteorneo.core.Category;
import net.meteorneo.core.Module;
import net.minecraft.client.Minecraft;

/**
 * AutoWalk: automatically holds the forward key while enabled.
 */
public class AutoWalk extends Module {

    public AutoWalk() {
        super("AutoWalk", Category.MOVEMENT);
    }

    @Override
    protected void onEnable() {
        Minecraft.getInstance().options.keyUp.setDown(true);
    }

    @Override
    protected void onDisable() {
        Minecraft.getInstance().options.keyUp.setDown(false);
    }
}
