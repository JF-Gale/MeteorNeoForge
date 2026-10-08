package net.meteorneo.systems.modules.player;

import net.meteorneo.core.Category;
import net.meteorneo.core.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;

/**
 * ChestStealer: automatically moves items from an open container into the inventory.
 */
public class ChestStealer extends Module {

    public ChestStealer() {
        super("ChestStealer", Category.PLAYER);
    }

    @Override
    public void onTick(Minecraft mc) {
        LocalPlayer player = mc.player;
        if (player == null || !(mc.screen instanceof AbstractContainerScreen<?> screen)) {
            return;
        }
        AbstractContainerMenu menu = screen.getMenu();
        int playerStart = menu.slots.size() - 36;
        if (playerStart <= 0) {
            return;
        }
        for (int i = 0; i < playerStart; i++) {
            Slot slot = menu.slots.get(i);
            if (!slot.getItem().isEmpty()) {
                menu.quickMoveStack(player, i);
                return;
            }
        }
    }
}
