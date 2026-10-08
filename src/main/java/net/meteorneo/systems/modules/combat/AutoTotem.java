package net.meteorneo.systems.modules.combat;

import net.meteorneo.core.Category;
import net.meteorneo.core.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

/**
 * AutoTotem: automatically moves a totem of undying into the offhand when possible.
 */
public class AutoTotem extends Module {

    // Offhand inventory slot index.
    private static final int OFFHAND_SLOT = 40;

    public AutoTotem() {
        super("AutoTotem", Category.COMBAT);
    }

    @Override
    public void onTick(Minecraft mc) {
        LocalPlayer player = mc.player;
        if (player == null) {
            return;
        }
        // Nothing to do if the offhand already holds a totem.
        if (player.getOffhandItem().is(Items.TOTEM_OF_UNDYING)) {
            return;
        }
        // Search the inventory for a totem and swap it into the offhand.
        for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
            ItemStack stack = player.getInventory().getItem(i);
            if (stack.is(Items.TOTEM_OF_UNDYING)) {
                player.getInventory().setItem(i, player.getOffhandItem());
                player.getInventory().setItem(OFFHAND_SLOT, stack);
                break;
            }
        }
    }
}
