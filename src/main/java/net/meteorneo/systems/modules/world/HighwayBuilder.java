package net.meteorneo.systems.modules.world;

import net.meteorneo.core.Category;
import net.meteorneo.core.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;

/**
 * HighwayBuilder: looks down and continuously places blocks to build a road.
 */
public class HighwayBuilder extends Module {

    public HighwayBuilder() {
        super("HighwayBuilder", Category.WORLD);
    }

    @Override
    public void onTick(Minecraft mc) {
        LocalPlayer player = mc.player;
        if (player == null) {
            return;
        }
        player.setXRot(90.0f);
        mc.options.keyUse.setDown(true);
    }
}
