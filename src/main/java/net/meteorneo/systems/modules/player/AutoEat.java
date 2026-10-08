package net.meteorneo.systems.modules.player;

import net.meteorneo.core.Category;
import net.meteorneo.core.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;

/**
 * AutoEat: automatically eats food from the hotbar when hungry.
 */
public class AutoEat extends Module {

    private boolean eating;

    public AutoEat() {
        super("AutoEat", Category.PLAYER);
    }

    @Override
    protected void onDisable() {
        Minecraft mc = Minecraft.getInstance();
        mc.options.keyUse.setDown(false);
        eating = false;
    }

    @Override
    public void onTick(Minecraft mc) {
        LocalPlayer player = mc.player;
        if (player == null) {
            return;
        }
        if (!player.getFoodData().needsFood()) {
            if (eating) {
                mc.options.keyUse.setDown(false);
                eating = false;
            }
            return;
        }
        for (int i = 0; i < 9; i++) {
            ItemStack stack = player.getInventory().getItem(i);
            if (stack.has(DataComponents.CONSUMABLE)) {
                if (player.getInventory().selected != i) {
                    player.getInventory().selected = i;
                }
                mc.options.keyUse.setDown(true);
                eating = true;
                return;
            }
        }
    }
}
