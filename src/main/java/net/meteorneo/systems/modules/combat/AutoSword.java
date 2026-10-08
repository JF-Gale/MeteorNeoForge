package net.meteorneo.systems.modules.combat;

import net.meteorneo.core.Category;
import net.meteorneo.core.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;

/**
 * AutoSword: automatically selects the best sword in the hotbar while attacking.
 */
public class AutoSword extends Module {

    public AutoSword() {
        super("AutoSword", Category.COMBAT);
    }

    @Override
    public void onTick(Minecraft mc) {
        LocalPlayer player = mc.player;
        if (player == null) {
            return;
        }
        if (!mc.options.keyAttack.isDown()) {
            return;
        }
        // Switch to the first sword found in the hotbar while attacking.
        for (int i = 0; i < 9; i++) {
            if (player.getInventory().getItem(i).getItem() instanceof SwordItem) {
                if (player.getInventory().selected != i) {
                    player.getInventory().selected = i;
                }
                break;
            }
        }
    }
}
