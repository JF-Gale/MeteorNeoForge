package net.meteorneo.systems.modules.world;

import net.meteorneo.core.Category;
import net.meteorneo.core.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

/**
 * AutoBreed: feeds nearby cows with wheat to breed them.
 */
public class AutoBreed extends Module {

    private final double range = 4.0;

    public AutoBreed() {
        super("AutoBreed", Category.WORLD);
    }

    @Override
    public void onTick(Minecraft mc) {
        LocalPlayer player = mc.player;
        if (player == null || mc.level == null) {
            return;
        }
        ItemStack main = player.getMainHandItem();
        if (main.getItem() != Items.WHEAT) {
            return;
        }
        for (Entity e : mc.level.entitiesForRendering()) {
            if (e instanceof Cow && player.distanceToSqr(e) < range * range) {
                mc.options.keyUse.setDown(true);
                return;
            }
        }
    }
}
