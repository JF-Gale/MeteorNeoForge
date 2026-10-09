package net.meteorneo.systems.modules.player;

import net.meteorneo.core.Category;
import net.meteorneo.core.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.SwordItem;

/**
 * InventorySort: moves tools to the first inventory slot for quick access.
 */
public class InventorySort extends Module {

    public InventorySort() {
        super("InventorySort", Category.PLAYER);
    }

    @Override
    public void onTick(Minecraft mc) {
        LocalPlayer player = mc.player;
        if (player == null) {
            return;
        }
        for (int i = 1; i < 36; i++) {
            ItemStack stack = player.getInventory().getItem(i);
            if (isTool(stack)) {
                ItemStack first = player.getInventory().getItem(0);
                player.getInventory().setItem(i, first);
                player.getInventory().setItem(0, stack);
                break;
            }
        }
    }

    private boolean isTool(ItemStack stack) {
        return stack.getItem() instanceof PickaxeItem
                || stack.getItem() instanceof AxeItem
                || stack.getItem() instanceof ShovelItem
                || stack.getItem() instanceof SwordItem;
    }
}
