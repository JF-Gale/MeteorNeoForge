package net.meteorneo.systems.modules.combat;

import net.meteorneo.core.Category;
import net.meteorneo.core.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;

/**
 * AutoWeapon: automatically selects the strongest melee weapon in the hotbar.
 */
public class AutoWeapon extends Module {

    public AutoWeapon() {
        super("AutoWeapon", Category.COMBAT);
    }

    @Override
    public void onTick(Minecraft mc) {
        LocalPlayer player = mc.player;
        if (player == null) {
            return;
        }
        int best = -1;
        double bestDmg = 0;
        for (int i = 0; i < 9; i++) {
            ItemStack stack = player.getInventory().getItem(i);
            if (stack.getItem() instanceof SwordItem) {
                double dmg = 6.0;
                if (dmg > bestDmg) {
                    bestDmg = dmg;
                    best = i;
                }
            } else if (stack.getItem() instanceof AxeItem) {
                double dmg = 7.0;
                if (dmg > bestDmg) {
                    bestDmg = dmg;
                    best = i;
                }
            }
        }
        if (best >= 0 && player.getInventory().selected != best) {
            player.getInventory().selected = best;
        }
    }
}
