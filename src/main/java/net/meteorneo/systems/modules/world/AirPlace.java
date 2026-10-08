package net.meteorneo.systems.modules.world;

import net.meteorneo.core.Category;
import net.meteorneo.core.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.phys.HitResult;

/**
 * AirPlace: holds the use key when the player is looking at a block to place items.
 */
public class AirPlace extends Module {

    public AirPlace() {
        super("AirPlace", Category.WORLD);
    }

    @Override
    public void onTick(Minecraft mc) {
        LocalPlayer player = mc.player;
        if (player == null) {
            return;
        }
        HitResult hit = player.pick(4.5, 0.0f, false);
        if (hit.getType() == HitResult.Type.BLOCK) {
            mc.options.keyUse.setDown(true);
        }
    }
}
