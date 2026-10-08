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
        int bestSlot = -1;
        float bestDamage = -1.0f;
        for (int i = 0; i < 9; i++) {
            ItemStack stack = player.getInventory().getItem(i);
            if (stack.getItem() instanceof SwordItem sword) {
                float damage = sword.getDamage();
                if (damage > bestDamage) {
                    bestDamage = damage;
                    bestSlot = i;
                }
            }
        }
        if (bestSlot >= 0 && bestSlot != player.getInventory().selected) {
            player.getInventory().selected = bestSlot;
        }
    }
}
