package net.meteorneo.systems.modules.combat;

import net.meteorneo.core.Category;
import net.meteorneo.core.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.item.BowItem;

/**
 * FastBow: automatically holds use while holding a bow to fire fully charged.
 */
public class FastBow extends Module {

    public FastBow() {
        super("FastBow", Category.COMBAT);
    }

    @Override
    public void onTick(Minecraft mc) {
        LocalPlayer player = mc.player;
        if (player == null) {
            return;
        }
        if (player.getMainHandItem().getItem() instanceof BowItem) {
            mc.options.keyUse.setDown(true);
        }
    }
}
